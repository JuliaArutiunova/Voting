package by.it_academy.jd2.dto;

import java.util.Map;

public class ParticipantsDTO {
    Map<Integer, String> artists;
    Map<Integer, String> genres;

    public ParticipantsDTO(Map<Integer, String> artists, Map<Integer, String> genres) {
        this.artists = artists;
        this.genres = genres;
    }

    public Map<Integer, String> getArtists() {
        return artists;
    }

    public Map<Integer, String> getGenres() {
        return genres;
    }

    public String getName(int id) {
        if (artists.containsKey(id)) {
            return artists.get(id);
        } else if (genres.containsKey(id)) {
            return genres.get(id);
        }
        return null;
    }
}
