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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
public class APIController {
    @Autowired
    CodeService service;

    @GetMapping(value = "/api/code/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Code> get(@PathVariable String uuid) {
        var code = service.findCodeByUuid(uuid);
        if (code.isPresent()) {
            if (code.get().getTime() > 0) {
                code.get().setTime(Duration.between(LocalDateTime.now(), code.get().getDestroyed()).getSeconds());
                if (code.get().getTime() <= 0L) {
                    service.deleteCode(code.get());
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
                }
            }

            int views = code.get().getViews();
            if (views == 0 && code.get().isViewsB()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
            }
            if (views > 0) {
                code.get().setViews(views - 1);
                if (code.get().getViews() <= 0) {
                    service.deleteCode(code.get());
                }
            }

            service.saveCode(code.get());
            return new ResponseEntity<>(code.get(), HttpStatus.OK);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "uuid doesn't exist");
        }
    }

    @PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> post(@RequestBody Code code) {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime destroyed = created.plusSeconds(code.getTime());
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        service.saveCode(new Code(code.getCode(), code.getTime(),
                                            code.getViews(), randomUUIDString,
                                            created, destroyed));

        Map<String, String> m = Map.of("id", randomUUIDString);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Code>> getRecent() {
        List<Code> answer = service.latest();
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
