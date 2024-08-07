package by.it_academy.jd2.service;

import by.it_academy.jd2.storage.api.IVotingStorage;
import by.it_academy.jd2.storage.VotingStorage;
import by.it_academy.jd2.dto.DTO;
import by.it_academy.jd2.dto.DTOResults;
import by.it_academy.jd2.service.api.IVotingService;

public class VotingService implements IVotingService {
    private static final VotingService instance = new VotingService();
    IVotingStorage votingStorage = VotingStorage.getInstance();

    private VotingService() {
    }

    @Override
    public void create(DTO dto) {
        votingStorage.create(dto);
    }

    public DTOResults getResults() {
        return VotingStorage.getInstance().getInfo();
    }


    public static VotingService getInstance() {
        return instance;
    }


}
