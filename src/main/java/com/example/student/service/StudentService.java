package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.exception.DuplicateEmailException;
import com.example.student.exception.StudentNotFoundException;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student addStudent(Student student) {
        if (repository.existsByEmail(student.getEmail())) {
            throw new DuplicateEmailException("A student with email '" + student.getEmail() + "' already exists");
        }
        student.setId(null); // make sure this is always an insert
        return repository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }

    public Student updateStudent(Long id, Student details) {
        Student existing = getStudentById(id);
        if (repository.existsByEmailAndIdNot(details.getEmail(), id)) {
            throw new DuplicateEmailException("A student with email '" + details.getEmail() + "' already exists");
        }
        existing.setName(details.getName());
        existing.setEmail(details.getEmail());
        existing.setCourse(details.getCourse());
        existing.setAge(details.getAge());
        return repository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        repository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByCourse(String course) {
        return repository.findByCourseIgnoreCase(course);
    }
}
