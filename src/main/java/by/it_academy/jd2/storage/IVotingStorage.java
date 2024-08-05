package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.DTOResults;
import by.it_academy.jd2.instance.Comment;


public interface IVotingStorage {

    void addComment(Comment comment);

    void updateArtist(String artist);

    void updateGenres(String[] genres);

    DTOResults getInfo();

}
