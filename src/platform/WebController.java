package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    CodeService service;

    @GetMapping(value = "/code/{uuid}", produces = MediaType.TEXT_HTML_VALUE)
    public String get(HttpServletResponse response, @PathVariable String uuid, Model model) {
        var c = service.findCodeByUuid(uuid);
        if (c.isPresent()) {
            if (c.get().getTime() > 0L) {
                c.get().setTime(Duration.between(LocalDateTime.now(), c.get().getDestroyed()).getSeconds());
                if (c.get().getTime() <= 0L) {
                    service.deleteCode(c.get());
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
                }
            }
            int views = c.get().getViews();
            if (views == 0 && c.get().isViewsB()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
            }
            if (views > 0) {
                c.get().setViews(views - 1);
                if (c.get().getViews() <= 0) {
                    service.deleteCode(c.get());
                }
            }
            service.saveCode(c.get());
            model.addAttribute("views_bool", Boolean.toString(c.get().isViewsB()));
            model.addAttribute("code", c.get().getCode());
            model.addAttribute("date", c.get().getDate());
            model.addAttribute("views", Integer.toString(c.get().getViews()));
            model.addAttribute("time", Long.toString(c.get().getTime()));
            return "code_snippet";
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNew(HttpServletResponse response, Model model) {
        return "code_new";
    }

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatest(HttpServletResponse response, Model model) {
        List<Code> answer = service.latest();
        model.addAttribute("snippets", answer);
        return "latest";
    }
}
