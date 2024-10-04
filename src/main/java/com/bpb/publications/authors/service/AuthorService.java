package com.bpb.publications.authors.service;

import com.bpb.publications.authors.vo.AuthorVO;

import java.util.List;

public interface AuthorService {

    boolean save(AuthorVO authorVO);

    void deleteById(Long id);

    List<AuthorVO> findAll();

    AuthorVO findById(Long id);
}
