package platform;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
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
    @JsonIgnore
    private boolean timeB = false;
    @JsonIgnore
    private boolean viewsB = false;
    private long time;

//    @NotNull
    private int views;
    @JsonIgnore
    private String uuid;

    @JsonIgnore
    private LocalDateTime created;

    @JsonIgnore
    private LocalDateTime destroyed;

    public Code() {
    }

//    public Code(String code) {
    public Code(String code, long time, int views, String uuid, LocalDateTime created, LocalDateTime destroyed) {
//        this.title = title;
        this.code = code;
        this.date = LocalDateTime.now();
        if (time > 0L) {
            this.time = time;
            this.timeB = true;
        }
        if (views > 0) {
            this.views = views;
            this.viewsB = true;
        }
        this.uuid = uuid;
        this.created = created;
        this.destroyed = destroyed;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isTimeB() {
        return timeB;
    }

    public void setTimeB(boolean timeB) {
        this.timeB = timeB;
    }

    public boolean isViewsB() {
        return viewsB;
    }

    public void setViewsB(boolean viewsB) {
        this.viewsB = viewsB;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(LocalDateTime destroyed) {
        this.destroyed = destroyed;
    }
}
