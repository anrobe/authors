package com.bpb.publications.authors.controller;

import com.bpb.publications.authors.service.AuthorService;
import com.bpb.publications.authors.vo.AuthorVO;
import com.bpb.publications.authors.exception.NoRecordsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<String> saveAuthor(@RequestBody AuthorVO authorVO) {
        boolean isSaved = authorService.save(authorVO);
        if (isSaved) {
            return new ResponseEntity<>("Author saved successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to save author", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteById(id);
            return new ResponseEntity<>("Author deleted successfully", HttpStatus.OK);
        } catch (NoRecordsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorVO>> getAllAuthors() {
        try {
            List<AuthorVO> authors = authorService.findAll();
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (NoRecordsException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorVO> getAuthorById(@PathVariable Long id) {
        try {
            AuthorVO authorVO = authorService.findById(id);
            return new ResponseEntity<>(authorVO, HttpStatus.OK);
        } catch (NoRecordsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
