package com.msmyrnov.gameofthree.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    @NotBlank(message = "Name may not be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
