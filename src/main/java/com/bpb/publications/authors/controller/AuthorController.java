package com.bpb.publications.authors.controller;

import com.bpb.publications.authors.service.AuthorService;
import com.bpb.publications.authors.vo.AuthorVO;
import com.bpb.publications.authors.exception.NoRecordsException;
import com.bpb.publications.authors.exception.ErrorMessage;
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

    @PostMapping("/save")
    public ResponseEntity<ErrorMessage> saveAuthor(@RequestBody AuthorVO authorVO) {
        boolean isSaved = authorService.save(authorVO);
        if (isSaved) {
            return new ResponseEntity<>(new ErrorMessage("Author saved successfully"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ErrorMessage("Failed to save author"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ErrorMessage> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteById(id);
            return new ResponseEntity<>(new ErrorMessage("Author deleted successfully"), HttpStatus.OK);
        } catch (NoRecordsException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAuthors() {
        try {
            List<AuthorVO> authors = authorService.findAll();
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (NoRecordsException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        try {
            AuthorVO authorVO = authorService.findById(id);
            return new ResponseEntity<>(authorVO, HttpStatus.OK);
        } catch (NoRecordsException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}