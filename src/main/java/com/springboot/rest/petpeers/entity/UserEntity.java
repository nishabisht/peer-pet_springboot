package com.springboot.rest.petpeers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "userId")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="users",uniqueConstraints={@UniqueConstraint(columnNames = "username",name ="unique_field_constraint" )})
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long userId;


    @NotBlank(message = "Mandatory Field and should be unique")
    @Column(unique = true)
//    @Size(min = 5, max = 10, message = "username can not be greater then 10 words")
    private String username;

    @NotBlank(message = "Mandatory Field")
    @Size(min = 5, max = 15, message = "Password can not less then 5 words and be greater then 10 words")
    @Column(name = "password")
    private String userPassword;


    //   @NotBlank(message = "Mandatory Field")
    @Transient
    private String confirmPassword;

//    @AssertTrue(message = "Password and confirm password does not match")
//    public boolean isPasswordMatching() {
//        boolean match=userPassword != null && userPassword.equals(confirmPassword);
//        System.out.println("Password is matching or not :  "+match);
//        return match;
//
//    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PetEntity> pets;

    public UserEntity(Long userId, String username, String userPassword, String confirmPassword) {
        this.userId = userId;
        this.username = username;
        this.userPassword = userPassword;
        this.confirmPassword = confirmPassword;
    }

    public void setPassword(String defaultPassword) {
        this.userPassword=defaultPassword;
    }

    public String getPassword() {
        return this.userPassword;
    }
}
