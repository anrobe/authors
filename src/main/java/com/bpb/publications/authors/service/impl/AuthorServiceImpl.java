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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public boolean save(AuthorVO authorVO) {
        Author author = new Author();
        BeanUtils.copyProperties(authorVO, author);
        try {
            authorRepository.save(author);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NoRecordsException("No Records for Author for ID: " + id);
        }
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorVO> findAll() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new NoRecordsException("No Author found");
        }
        return authors.stream()
                .map(author -> {
                    AuthorVO authorVO = new AuthorVO();
                    BeanUtils.copyProperties(author, authorVO);
                    return authorVO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorVO findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoRecordsException("No Records for Author for ID: " + id));
        AuthorVO authorVO = new AuthorVO();
        BeanUtils.copyProperties(author, authorVO);
        return authorVO;
    }
}
