package com.school_management.Service;

import com.school_management.Exception.UserNotFoundException;
import com.school_management.entity.School;
import com.school_management.entity.User;
import com.school_management.repository.SchoolRepository;
import com.school_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setEnrollmentDate(updatedUser.getEnrollmentDate());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void assignSchoolToUser(Long schoolId, Long userId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found with id: " + schoolId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        user.setSchool(school);

        userRepository.save(user);
    }

    public List<User> getUsersBySchoolAndRole(Long schoolId, String roleName ){
        List<User> users = userRepository.findBySchoolIdAndRoleName(schoolId, roleName);
        if (users.isEmpty()){
            try {
                throw new RoleNotFoundException("Role does not exist that you entered in this school");
            } catch (RoleNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

    public List<User> searchUsersByTheirNameInSchool(Long schoolId, String userName) {
        List<User> users = userRepository.getUsersByNameInSchool(schoolId, userName);
        if (users.isEmpty()) {
            try {
                throw new UserNotFoundException("User does not exist by the name you entered in this school.");
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }


}
