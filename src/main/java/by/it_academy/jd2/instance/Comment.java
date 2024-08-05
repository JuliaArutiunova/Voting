package by.it_academy.jd2.instance;

import java.time.LocalDateTime;

public class Comment {
    private String author;
    private String text;
    LocalDateTime dateTime;

    public Comment(String author, String text, LocalDateTime dateTime) {
        this.author = author;
        this.text = text;
        this.dateTime = dateTime;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
