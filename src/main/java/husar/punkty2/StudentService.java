package husar.punkty2;

//import java.util.List;
import husar.punkty2.db.ScoreRepository;
import husar.punkty2.db.ScoreRow;
import husar.punkty2.db.StudentRepository;
import husar.punkty2.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService {
    //private List<Student> students = List.empty();
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;

    public StudentService(StudentRepository studentRepository, ScoreRepository scoreRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
    }
    /*private Function<StudentRow, Student> getStudentRowStudentFunction() {
        return dbObj->
                new Student(
                        dbObj.getId(),
                        dbObj.getName(),
                        dbObj.getNumber(),
                        dbObj.getGrupa());
    } */

    List<Student> getStudents(){
        return List.ofAll(this.studentRepository.findAll())
                .map(StudentRow::toStudent);
        //return this.students;
        //return List.empty();
        //throw new UnsupportedOperationException();
    }

    public Student addStudent(final NewStudent newStudent){
       return this.studentRepository.save(new StudentRow(
               newStudent.name,
               newStudent.number,
               newStudent.grupa)).toStudent();
        /*StudentRow created = this.repository.save(new StudentRow(
                newStudent.name,
                newStudent.number,
                newStudent.grupa));
        return getStudentRowStudentFunction().apply(created); */
        /*Student created = new Student(students.size()+1, newStudent.name, newStudent.number, newStudent.grupa);
        students=students.prepend(created);
        return created; */
        //return new Student(1,"aa","aa", "aa");
        //throw new UnsupportedOperationException();
    }

    @Transactional
    public Optional<Student> changeNumber (long studentId, String newNumber){
        final Optional<StudentRow> student=this.studentRepository.findById(studentId);
            return student.map(c -> {
                c.setNumber(newNumber);
                return c.toStudent();
            });
    }

    @Transactional
    public Optional<Integer> addScore(final long studentId, final Score score) {
        final Optional<StudentRow> student = this.studentRepository.findById(studentId);
        return student.map(c->{
            int existingScore=List.ofAll(c.getScores()) .foldLeft(0,(p,s)->p+s.getScore());
            final ScoreRow newScore=new ScoreRow(score.score,score.comment,c);
            this.scoreRepository.save(newScore);
            return existingScore+score.score;
        });
    }
}
