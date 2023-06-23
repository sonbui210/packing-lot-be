package com.sobu.parkinglot.data.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student")
@Data
public class Student extends AuditModel{
    @Id
    private Long id;

    private String fullName;

    private String address;

    private String className;

    private String schoolYear;

    private Status status;

    private String updatedAt;

    private String phoneNumber;

    private String emailStudent;

    private String phoneNumberGrand;

    private String emailGrand;

    private String busCodeGet;

    private String avatar;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
