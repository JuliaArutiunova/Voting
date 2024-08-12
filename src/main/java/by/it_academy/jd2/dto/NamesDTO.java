package by.it_academy.jd2.dto;

import java.util.List;

public class NamesDTO {
    private List<String> artistNames;
    private List<String> genresNames;

    public NamesDTO(List<String> artistNames, List<String> genresNames) {
        this.artistNames = artistNames;
        this.genresNames = genresNames;
    }

    public List<String> getArtistNames() {
        return artistNames;
    }

    public List<String> getGenresNames() {
        return genresNames;
    }
}
