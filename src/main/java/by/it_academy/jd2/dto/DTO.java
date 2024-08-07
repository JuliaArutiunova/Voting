package by.it_academy.jd2.dto;

import by.it_academy.jd2.instance.Comment;

import java.util.Arrays;

public class DTO {

    private String artist;
    private String[] genres;
    private Comment comment;

    public DTO() {
    }

    public DTO( String artist, String[] genres, Comment comment) {

        this.artist = artist;
        this.genres = genres;
        this.comment = comment;
    }



    public String getArtist() {
        return artist;
    }

    public String[] getGenres() {
        return genres;
    }

    public Comment getComment() {
        return comment;
    }



    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "artist='" + artist + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", comment=" + comment +
                '}';
    }
}
