package platform;

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
    @GetMapping(value = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String get(HttpServletResponse response, @PathVariable int id, Model model) {
        String code = CodeSharingPlatform.codeList.get(id - 1).getCode();
        model.addAttribute("code", code);
        String date = CodeSharingPlatform.codeList.get(id - 1).getDate();
        model.addAttribute("date", date);
        return "code_snippet";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNew(HttpServletResponse response, Model model) {
        return "code_new";
    }

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatest(HttpServletResponse response, Model model) {
        List<Code> answer = new ArrayList<>();
        int count = CodeSharingPlatform.codeList.size();
        for (int i = count - 1; i > count - 11; i--) {
            answer.add(CodeSharingPlatform.codeList.get(i));
            if (i == 0) {
                break;
            }
        }
        model.addAttribute("snippets", answer);
        return "latest";
    }
}
