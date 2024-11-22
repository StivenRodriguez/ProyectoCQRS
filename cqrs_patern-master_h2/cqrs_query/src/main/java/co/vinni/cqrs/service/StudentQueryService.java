package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.persistence.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentQueryService {
    StudentRepository studentRepository;

    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    @KafkaListener(topics = "student-event-topic",groupId = "student-event-group")
    public void processProductEvents(StudentEvent studentEvent) {
        Student Student = studentEvent.getStudent();
        if (studentEvent.getEventType().equals("CreateStudent")) {
            studentRepository.save(Student);
        }
        if (studentEvent.getEventType().equals("UpdateStudent")) {
            Student existingStudent = studentRepository.findById(Student.getCode()).get();
            existingStudent.setFirstName(Student.getFirstName());
            existingStudent.setLastName(Student.getLastName());
            existingStudent.setEmail(Student.getEmail());

            studentRepository.save(existingStudent);
        }
    }
}
