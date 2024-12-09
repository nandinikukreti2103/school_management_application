package com.school_management.Service;

import com.school_management.entity.Classroom;
import com.school_management.entity.School;
import com.school_management.repository.ClassroomRepository;
import com.school_management.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final ClassroomRepository classroomRepository;

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> getAllSchool() {
        return schoolRepository.findAll();
    }

    public Optional<School> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public School updateSchool(Long id, School updatedSchool) {
        return schoolRepository.findById(id).map(school -> {
            school.setName(updatedSchool.getName());
            school.setAddress(updatedSchool.getAddress());

            return schoolRepository.save(school);
        }).orElseThrow(() -> new RuntimeException("Classroom not found with id: " + id));
    }

    public void deleteSchool(Long id) {
        if (schoolRepository.existsById(id)) {
            schoolRepository.deleteById(id);
        } else {
            throw new RuntimeException("Classroom not found with id: " + id);
        }
    }

    public void assignClassroomToSchool(Long classroomId, Long schoolId){

        Classroom classroom = null;
        School school = null;
        try {
            classroom = classroomRepository.findById(classroomId)
                    .orElseThrow(()->new RuntimeException("classroom not found with id: "+ classroomId));

            school = schoolRepository.findById(schoolId)
                    .orElseThrow(()->new RuntimeException("school not found with id: "+ schoolId));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        classroom.setSchool(school);
        classroomRepository.save(classroom);

    }
}
