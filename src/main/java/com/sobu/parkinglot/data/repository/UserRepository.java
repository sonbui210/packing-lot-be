package com.sobu.parkinglot.data.repository;

import com.sobu.parkinglot.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Boolean existsByPhoneNumber(String phoneNumber);
/*
  User findById(long id);

  User findByPhoneNumber(String phoneNumber);

  @Query("select u from User u where lower(u.username) like concat('%', :searchKey, '%') or lower(u.email) like concat('%', :searchKey, '%') or lower(u.phone) like concat('%', :searchKey, '%') ")
  User searchUser(@Param("searchKey") String searchKey);*/
}
