package platform;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class APIController {

    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Code> get(@PathVariable int id) {
        if (CodeSharingPlatform.codeList.size() < id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id doesn't exist");
        }
        Code c = CodeSharingPlatform.codeList.get(id - 1);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> post(@RequestBody Code code) {
        CodeSharingPlatform.codeList.add(new Code(code.getCode()));
        int id = CodeSharingPlatform.codeList.size();
        Map<String, String> m = Map.of("id", Integer.toString(id));
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Code>> getRecent() {
        List<Code> answer = new ArrayList<>();
        int count = CodeSharingPlatform.codeList.size();
        for (int i = count - 1; i > count - 11; i--) {
            answer.add(CodeSharingPlatform.codeList.get(i));
            if (i == 0) {
                break;
            }
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
