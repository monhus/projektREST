package husar.punkty2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    String hello(){
        return "hello, time is: " + LocalDateTime.now();
    }
}
