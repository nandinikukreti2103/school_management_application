package com.school_management.controller;


import com.school_management.Service.ClassroomService;
import com.school_management.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping("/create")
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        Classroom savedClassroom = classroomService.createClassroom(classroom);
        return new ResponseEntity<>(savedClassroom, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Classroom>> getClassroomById(@PathVariable Long id) {
        Optional<Classroom> classroom = classroomService.getClassroomById(id);
        return ResponseEntity.ok(classroom);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @RequestBody Classroom updatedClassroom) {
        Classroom classroom = classroomService.updateClassroom(id, updatedClassroom);
        return ResponseEntity.ok(classroom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
