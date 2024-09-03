package by.it_academy.jd2.dto;

import by.it_academy.jd2.entity.Comment;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultsDTO {
    Map<Integer,Integer> votes;
    List<Comment> comments;



    public ResultsDTO(Map<Integer, Integer> votes, List<Comment> comments) {
        this.votes = votes;
        this.comments = comments;
    }

    public List<Comment> getSortedComments() {
        comments.sort(Comparator.comparing(Comment::getDateTime).reversed());
        return comments;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public Map<Integer, Integer> getVotes() {
        return votes;
    }



    public void setVotes(Map<Integer, Integer> votes) {
        this.votes = votes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
