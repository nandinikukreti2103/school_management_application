package com.school_management.repository;

import com.school_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.school.id = :schoolId AND u.role.name = :roleName")
    List<User> findBySchoolIdAndRoleName(@Param("schoolId") Long schoolId, @Param("roleName") String roleName);

//    @Query("Select u From user u WHERE u.school.id = :schoolId AND u.user.name = :firstName")
//    List<User> getUsersByNameInSchool(@Param("schoolId") Long schoolId, @Param("userName") String userName);

    @Query("SELECT u FROM User u WHERE u.firstName = :name AND u.school.id = :schoolId")
    List<User> getUsersByNameInSchool(@Param("schoolId") Long schoolId, @Param("name") String name);


}
