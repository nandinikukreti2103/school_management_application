package com.school_management.Service;

import com.school_management.entity.Classroom;
import com.school_management.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> getClassroomById(Long id) {
        return classroomRepository.findById(id);
    }

    public Classroom updateClassroom(Long id, Classroom updatedClassroom) {
        return classroomRepository.findById(id).map(classroom -> {
            classroom.setClassName(updatedClassroom.getClassName());
            classroom.setRoomNumber(updatedClassroom.getRoomNumber());

            return classroomRepository.save(classroom);
        }).orElseThrow(() -> new RuntimeException("Classroom not found with id: " + id));
    }

    public void deleteClassroom(Long id) {
        if (classroomRepository.existsById(id)) {
            classroomRepository.deleteById(id);
        } else {
            throw new RuntimeException("Classroom not found with id: " + id);
        }
    }
}
