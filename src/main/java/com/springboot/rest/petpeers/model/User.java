package com.springboot.rest.petpeers.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Long id;

    @NotBlank(message = "Mandatory Field")
    @Size(min = 5,max = 10,message = "username can not be greater then 10 words")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Mandatory Field")
    @Size(min = 5,max = 15,message = "Password can not less then 5 words and be greater then 10 words")
    private String password;

    private String confirmPassword;

    private Set<Pet> pets;


    @AssertTrue(message = "Password and confirm password does not match")
    public boolean isPasswordMatching() {
        boolean match=password != null && password.equals(confirmPassword);
        //System.out.println("Password is matching or not :  "+match);
        return match;

    }

    public User(Long id, String name, String password, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

}
