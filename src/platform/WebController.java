package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    CodeService service;

    @GetMapping(value = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String get(HttpServletResponse response, @PathVariable Long id, Model model) {
        String code = service.findCodeById(id).get().getCode();
        model.addAttribute("code", code);
        String date = service.findCodeById(id).get().getDate();
        model.addAttribute("date", date);
        return "code_snippet";
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
