package com.school_management.controller;

import com.school_management.Service.SubjectService;
import com.school_management.entity.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

        @PostMapping("/create")
        public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
            Subject createdSubject = subjectService.createSubject(subject);
            return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
            Subject subject = subjectService.getSubjectById(id);
            return ResponseEntity.ok(subject);
        }

        @GetMapping("/getAll")
        public ResponseEntity<List<Subject>> getAllSubjects() {
            List<Subject> subjects = subjectService.getAllSubjects();
            return ResponseEntity.ok(subjects);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject updatedSubject) {
            Subject subject = subjectService.updateSubject(id, updatedSubject);
            return ResponseEntity.ok(subject);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
            subjectService.deleteSubject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PostMapping("/assign-subjects-to-users/{subjectId}/{userId}")
        public ResponseEntity<String> assignSubjectsToUsers(@PathVariable Long subjectId, @PathVariable Long userId){
            subjectService.assignSubjectsToUsers(subjectId,userId);
            return ResponseEntity.ok("subject successfully assigned to user.");
        }
    }

