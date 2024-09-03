package by.it_academy.jd2.dto;

import java.util.Arrays;

public class FormCreateDTO {
    String[] artists;
    String[] genres;

    public FormCreateDTO(String[] artists, String[] genres) {
        this.artists = artists;
        this.genres = genres;
    }

    public String[] getArtists() {
        return artists;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setArtists(String[] artists) {
        this.artists = artists;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "FormCreateDTO{" +
                "artists=" + Arrays.toString(artists) +
                ", genres=" + Arrays.toString(genres) +
                '}';
    }
}
