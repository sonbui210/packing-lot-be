package com.sobu.parkinglot.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_user")
@Data
//@JsonIgnoreProperties({ "student_id" })
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private String phone;


    private String email;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student_id;

    private String busCode;
/*
    @JsonIgnore
    private Student student_bus;
*/

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
