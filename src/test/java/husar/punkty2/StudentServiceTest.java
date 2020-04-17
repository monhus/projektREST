package husar.punkty2;

import husar.punkty2.db.ScoreRepository;
import husar.punkty2.db.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import io.vavr.collection.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
//import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private ScoreRepository scoreRepository;

    @AfterEach
    public void cleanAfterTest(){
        this.repository.deleteAll();
    }

    @Test
    public void getEmptyList(){
        final StudentService service = new StudentService(repository, scoreRepository);
        List<Student> students = service.getStudents();
        assertTrue(students.isEmpty());
    }
    @Test
    public void addStudent() {
        final StudentService service = new StudentService(repository, scoreRepository);
        final Student created = service.addStudent(new NewStudent("Student1", "1-2-3","IP"));
        assertNotNull(created);
    }

    @Test
    public void addStudentIsReturned() {
        final StudentService service = new StudentService(repository, scoreRepository);
        final Student created = service.addStudent(new NewStudent("Student1", "1-2-3","IP"));
        final List<Student> all = service.getStudents();
        assertEquals("Student1",all.get(0).name);
        //assertEquals(created.name, all.head().name);
    }
    @Test
    public void addStudentHasNewId() {
        final StudentService service = new StudentService(repository, scoreRepository);
        final Student created1 = service.addStudent(new NewStudent("Student1", "1-2-3","IP"));
        final Student created2 = service.addStudent(new NewStudent("Student2", "4-5-6","IP"));
        List<Student> students = service.getStudents();
        assertEquals(2, students.size());
        assertNotEquals(created1.id, created2.id);
    }
}