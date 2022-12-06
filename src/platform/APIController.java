package platform;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
public class APIController {
    @Autowired
    CodeService service;

    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Code> get(@PathVariable Long id) {
        var code = service.findCodeById(id);
        if (code.isPresent()) {
            return new ResponseEntity<>(code.get(), HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id doesn't exist");
        }
    }

    @PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> post(@RequestBody Code code) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
//        Code recivedCode = service.saveCode(new Code(code.getCode(), code.getTime(), code.getViews()));
        Code recivedCode = service.saveCode(new Code(code.getCode()));
//        Long idx = recivedCode.getId();
//        Map<String, String> m = Map.of("id", Long.toString(idx));
        Map<String, String> m = Map.of("id", randomUUIDString);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Code>> getRecent() {
        List<Code> answer = service.latest();
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
