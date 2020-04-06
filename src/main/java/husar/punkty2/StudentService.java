package husar.punkty2;

//import java.util.List;
import husar.punkty2.db.StudentRepository;
import husar.punkty2.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentService {
    //private List<Student> students = List.empty();
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    private Function<StudentRow, Student> getStudentRowStudentFunction() {
        return dbObj->
                new Student(
                        dbObj.getId(),
                        dbObj.getName(),
                        dbObj.getNumber(),
                        dbObj.getGrupa());
    }

    List<Student> getStudents(){
        return List.ofAll(this.repository.findAll())
                .map(getStudentRowStudentFunction()
                );
        //return this.students;
        //return List.empty();
        //throw new UnsupportedOperationException();
    }

    public Student addStudent(final NewStudent newStudent){
        StudentRow created = this.repository.save(new StudentRow(
                newStudent.name,
                newStudent.number,
                newStudent.grupa));
        return getStudentRowStudentFunction().apply(created);
        /*Student created = new Student(students.size()+1, newStudent.name, newStudent.number, newStudent.grupa);
        students=students.prepend(created);
        return created; */
        //return new Student(1,"aa","aa", "aa");
        //throw new UnsupportedOperationException();
    }
}
