package by.it_academy.jd2.dto;

import java.lang.reflect.Array;
import java.util.Arrays;

public class InfoFromClientDTO {
    private String name;
    private int artist;
    private int[] genres;
    private String comment;

    public InfoFromClientDTO(String name, String artist, String[] genres, String comment) {
        this.name = name;
        this.artist = Integer.parseInt(artist);
        this.genres = Arrays.stream(genres).mapToInt(Integer::parseInt).toArray();
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public int getArtist() {
        return artist;
    }

    public int[] getGenres() {
        return genres;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(int artist) {
        this.artist = artist;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
