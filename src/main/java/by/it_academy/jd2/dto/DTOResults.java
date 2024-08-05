package by.it_academy.jd2.dto;


import by.it_academy.jd2.instance.Artist;
import by.it_academy.jd2.instance.Comment;
import by.it_academy.jd2.instance.Genre;

import java.util.Comparator;
import java.util.List;

public class DTOResults {

    private List<Artist> artistsResults;
    private List<Genre> genresResults;
    private List<Comment> comments;

    public DTOResults(List<Artist> artistsResults, List<Genre> genresResults, List<Comment> comments) {
        this.artistsResults = artistsResults;
        this.genresResults = genresResults;
        this.comments = comments;
    }

    public List<Artist> getSortedArtistsResults() {
        artistsResults.sort(Comparator.comparing(Artist::getVotes).reversed());
        return artistsResults;
    }

    public List<Genre> getSortedGenresResults() {
        genresResults.sort(Comparator.comparing(Genre::getVotes).reversed());
        return genresResults;
    }

    public List<Comment> getSortedComments() {
        comments.sort(Comparator.comparing(Comment::getDateTime).reversed());
        return comments;
    }
}
