package by.it_academy.jd2.service;

import by.it_academy.jd2.storage.IVotingStorage;
import by.it_academy.jd2.storage.VotingStorage;
import by.it_academy.jd2.dto.DTO;
import by.it_academy.jd2.dto.DTOResults;
import by.it_academy.jd2.service.api.IVotingService;

public class VotingService implements IVotingService {
    private static final VotingService instance = new VotingService();
    IVotingStorage votingStorage = new VotingStorage();

    private VotingService() {
    }

    @Override
    public void create(DTO dto) {
        votingStorage.updateArtist(dto.getArtist());
        votingStorage.updateGenres(dto.getGenres());
        votingStorage.addComment(dto.getComment());
    }

    public DTOResults getResults() {
        return votingStorage.getInfo();
    }


    public static VotingService getInstance() {
        return instance;
    }


}
