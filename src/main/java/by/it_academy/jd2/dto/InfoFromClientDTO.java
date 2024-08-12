package by.it_academy.jd2.dto;

import java.util.Arrays;

public class InfoFromClientDTO {
    String name;
    String artist;
    String[] genres;
    String comment;

    public InfoFromClientDTO(String name, String artist, String[] genres, String comment) {
        this.name = name;
        this.artist = artist;
        this.genres = genres;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "InfoFromClientDTO{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", comment='" + comment + '\'' +
                '}';
    }
}
