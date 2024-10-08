package com.bpb.publications.authors.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "author", schema = "pub")
public class Author {

    @Id
    @NotNull(message = "not null id")
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotEmpty(message = "empty first name")
    @Column(name = "first_name", length = 30)
    private String firstName;

    @Size(max = 30)
    @NotEmpty(message = "empty last name")
    @Column(name = "last_name", length = 30)
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