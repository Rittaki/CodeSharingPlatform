package platform;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {
//    private String title;
    private String code;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime date;


    public Code() {
    }

    public Code(String code) {
//        this.title = title;
        this.code = code;
        this.date = LocalDateTime.now();
    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return date.format(formatter);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
