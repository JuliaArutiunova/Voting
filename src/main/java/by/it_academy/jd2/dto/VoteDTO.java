package by.it_academy.jd2.dto;

import by.it_academy.jd2.instance.Comment;

import java.time.LocalDateTime;

public class VoteDTO {


    private Comment comment;
    private InfoFromClientDTO info;
    LocalDateTime createAt;

    public VoteDTO(InfoFromClientDTO info, LocalDateTime createAt) {
        this.info = info;
        this.createAt = createAt;

        this.comment = new Comment(info.getName(), info.getComment(), createAt);

    }


    public InfoFromClientDTO getInfo() {
        return info;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public String getArtist() {
        return info.getArtist();
    }

    public String[] getGenres() {
        return info.getGenres();
    }

    public Comment getComment() {
        return comment;
    }


    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "VoteDTO{" +
                "comment=" + comment +
                ", info=" + info +
                ", createAt=" + createAt +
                '}';
    }
}
