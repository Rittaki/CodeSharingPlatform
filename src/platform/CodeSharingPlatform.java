package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class CodeSharingPlatform {
    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

}
