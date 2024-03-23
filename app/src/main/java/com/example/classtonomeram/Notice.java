package com.example.classtonomeram;
import java.util.Date;
public class Notice {
    private String noticeId;
    private String title;
    private String message;
    private Date date;

    public Notice() {
        // Default constructor required for Firebase
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Notice(String noticeId, String title, String message,Date date) {
        this.noticeId = noticeId;
        this.title = title;
        this.message = message;
        this.date=date;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
