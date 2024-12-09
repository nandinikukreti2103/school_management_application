package com.school_management.Service;

import com.school_management.entity.Subject;
import com.school_management.entity.User;
import com.school_management.repository.SubjectRepository;
import com.school_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject updateSubject(Long id, Subject updatedSubject) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));

        existingSubject.setName(updatedSubject.getName());
        existingSubject.setCredits(updatedSubject.getCredits());

        return subjectRepository.save(existingSubject);
    }

    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }

    public void assignSubjectsToUsers(Long subjectId, Long userId){

        Subject subject = null;
        User user = null;
        try {
            subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("subject not find with id: " + subjectId));

            user = userRepository.findById(userId)
                    .orElseThrow(() ->new RuntimeException("user not found with id: " + userId));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        user.getSubjects().add(subject);
        userRepository.save(user);
    }
}
