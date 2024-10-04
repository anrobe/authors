package com.bpb.publications.authors.service.impl;

import com.bpb.publications.authors.entity.Author;
import com.bpb.publications.authors.repository.AuthorRepository;
import com.bpb.publications.authors.service.AuthorService;
import com.bpb.publications.authors.vo.AuthorVO;
import com.bpb.publications.authors.exception.NoRecordsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public boolean save(AuthorVO authorVO) {
        boolean result = true;
        Author author = new Author();
        BeanUtils.copyProperties(authorVO, author);
        try {
            authorRepository.save(author);
        }catch(DataAccessException e) {
            result = false;
        }
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if(!author.isPresent()) {
            throw new NoRecordsException("No Records for Author for ID: "+id);
        }
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorVO> findAll() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        if(authors.isEmpty()) {
            throw new NoRecordsException("No Author found");
        }
        List<AuthorVO> authorsVO = new ArrayList<>();
        for(Author author:authors) {
            AuthorVO authorVO = new AuthorVO();
            BeanUtils.copyProperties(author, authorVO);
            authorsVO.add(authorVO);
        }
        return authorsVO;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorVO findById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if(!author.isPresent()) {
            throw new NoRecordsException("No Records for Author for ID: "+id);
        }
        AuthorVO authorVO = new AuthorVO();
        BeanUtils.copyProperties(author.get(), authorVO);
        return authorVO;
    }

}
