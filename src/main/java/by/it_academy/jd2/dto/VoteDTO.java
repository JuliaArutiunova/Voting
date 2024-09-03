package by.it_academy.jd2.dto;


import by.it_academy.jd2.entity.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VoteDTO {


    private Comment comment;
    private InfoFromClientDTO info;
//    List<Integer> votes = new ArrayList<>();
    LocalDateTime createAt;

    public VoteDTO(InfoFromClientDTO info, LocalDateTime createAt) {
        this.info = info;
        this.createAt = createAt;

        this.comment = new Comment(info.getName(), info.getComment(), createAt);

    }

    public Comment getComment() {
        return comment;
    }

    public int[] getGenres(){
        return info.getGenres();
    }
    public int getArtist(){
       return info.getArtist();
    }

    public InfoFromClientDTO getInfo() {
        return info;
    }


    public LocalDateTime getCreateAt() {
        return createAt;
    }

}
