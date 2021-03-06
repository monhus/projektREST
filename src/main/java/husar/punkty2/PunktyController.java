package husar.punkty2;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/punkty")
public class PunktyController {
    //private List<String> users = new CopyOnWriteArrayList<>(new String[] {"Student1", "Student2", "Student3"});
    //private StudentService service = new StudentService(repository);
    //private final StudentService service; ??
    private StudentService service;

    public PunktyController(StudentService service) {
        this.service = service;
    }

    //@RequestMapping("/users")
    @RequestMapping(value = "/students", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<Student> getUsers(){
        return this.service.getStudents().asJava();
        //return users;
        //return Arrays.asList("user1", "user2", "user3");
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Student addUser(@RequestBody NewStudent student){
        return this.service.addStudent(student);
        /*users.add(name);
        return users.size(); */
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students/{id}/number/{number}",
            produces=MediaType.APPLICATION_JSON_VALUE)
    public Student setNumber(@PathVariable("id") long id, @PathVariable("number") String number){
        return this.service.changeNumber(id, number).orElseThrow( () ->
                //new IllegalArgumentException("Student o id: " + id + " does not exist")
                new NoStudentException(id)
        );
    }

    @RequestMapping(value = "/students/{id}/scores", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public int addScore(@PathVariable("id") long id, @RequestBody Score score){
        return this.service.addScore(id, score) .orElseThrow( ()->
                //new IllegalArgumentException("Student id: " + id + " does not exist"));
                new NoStudentException(id));
    }

}
