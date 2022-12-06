package platform;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Codes")
public class Code {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String code;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime date;
//    @NotNull
//    private int time;
//    @NotNull
//    private int views;

    public Code() {
    }

    public Code(String code) {
//    public Code(String code, int time, int views) {
//        this.title = title;
        this.code = code;
        this.date = LocalDateTime.now();
//        this.time = time;
//        this.views = views;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

//    public int getTime() {
//        return time;
//    }
//
//    public void setTime(int time) {
//        this.time = time;
//    }
//
//    public int getViews() {
//        return views;
//    }
//
//    public void setViews(int views) {
//        this.views = views;
//    }
}
