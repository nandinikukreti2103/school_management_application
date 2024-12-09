package com.school_management.controller;

import com.school_management.Service.UserService;
import com.school_management.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.saveUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign-school-to-user/{schoolId}/{userId}")
    public ResponseEntity<String> assignSchoolToUser(@PathVariable Long schoolId, @PathVariable Long userId){
        userService.assignSchoolToUser(schoolId,userId);
        return ResponseEntity.ok("school successfully assigned to user");
    }

    @GetMapping("/get-all-users-by-schoolId-and-roleName/{schoolId}/{roleName}")
    public ResponseEntity<List<User>> getAllUsersBySchoolAndRole(@PathVariable Long schoolId, @PathVariable String roleName){
        List<User> users = userService.getUsersBySchoolAndRole(schoolId,roleName);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search-user-by-their-name-in-school/{schoolId}/{userName}")
    public ResponseEntity<?> getUsersByTheirNameInSchool(@PathVariable Long schoolId, @PathVariable String userName) {

        List<User> users = userService.searchUsersByTheirNameInSchool(schoolId, userName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
