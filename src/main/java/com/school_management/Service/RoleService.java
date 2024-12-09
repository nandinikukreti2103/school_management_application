package com.school_management.Service;

import com.school_management.entity.Role;
import com.school_management.entity.User;
import com.school_management.repository.RoleRepository;
import com.school_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role updateRole(Long id, Role updatedRole) {
        return roleRepository.findById(id).map(existingrole -> {
            existingrole.setName(updatedRole.getName());
            existingrole.setDescription(updatedRole.getDescription());
            return roleRepository.save(existingrole);
        }).orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public void assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);
        userRepository.save(user);
    }
}

