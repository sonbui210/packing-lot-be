package com.sobu.parkinglot.data.repository;

import com.sobu.parkinglot.data.model.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassModel, Long> {
}
