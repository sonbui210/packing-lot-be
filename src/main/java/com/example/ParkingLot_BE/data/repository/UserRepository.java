package com.example.ParkingLot_BE.data.repository;


import com.example.ParkingLot_BE.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findById(long id);

    User findByEmail(String email);

    User findByPhone(String phone);

    @Query("select u from User u where lower(u.username) like concat('%', :searchKey, '%') or lower(u.email) like concat('%', :searchKey, '%') or lower(u.phone) like concat('%', :searchKey, '%') ")
    Iterable<User> searchUser(@Param("searchKey") String searchKey);
}
