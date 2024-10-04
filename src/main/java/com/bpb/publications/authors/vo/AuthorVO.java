package com.bpb.publications.authors.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthorVO {
    @NotNull
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}