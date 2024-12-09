package com.school_management.controller;


import com.school_management.Service.ClassroomService;
import com.school_management.Service.SchoolService;
import com.school_management.entity.Classroom;
import com.school_management.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/school")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/create")
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School savedSchool = schoolService.createSchool(school);
        return new ResponseEntity<>(savedSchool, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<School>> getAllSchool() {
        List<School> schools = schoolService.getAllSchool();
        return new ResponseEntity<>(schools, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<School>> getSchoolById(@PathVariable Long id) {
        Optional<School> school = schoolService.getSchoolById(id);
        return ResponseEntity.ok(school);
    }


    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School updatedSchool) {
        School school = schoolService.updateSchool(id, updatedSchool);
        return ResponseEntity.ok(school);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/assign-classroom-to-school/{classroomId}/{schoolId}")
    public ResponseEntity<String> assignClassroomToSchool(@PathVariable Long classroomId, @PathVariable Long schoolId){

        schoolService.assignClassroomToSchool(classroomId, schoolId);
        return ResponseEntity.ok("classroom assigned successfully");
    }
}
