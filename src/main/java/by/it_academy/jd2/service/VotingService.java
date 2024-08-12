package by.it_academy.jd2.service;


import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.dto.NamesDTO;
import by.it_academy.jd2.storage.api.IVotingStorage;
import by.it_academy.jd2.storage.VotingStorage;
import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.service.api.IVotingService;

import java.time.LocalDateTime;

public class VotingService implements IVotingService {
    private static final VotingService instance = new VotingService();
    IVotingStorage votingStorage = VotingStorage.getInstance();

    private VotingService() {
    }

    @Override
    public void create(InfoFromClientDTO dto) {
        int numberOfGenres = dto.getGenres().length;

        if (dto.getName().isBlank()) {
            throw new IllegalArgumentException("Имя не введено");
        }
        if (numberOfGenres == 0) {
            throw new IllegalArgumentException("Не выбраны жанры");
        }
        if (numberOfGenres > 5 || numberOfGenres < 3) {
            throw new IllegalArgumentException("Выбрано неверное количество жанров");
        }
        if (dto.getComment().isBlank()) {
            throw new IllegalArgumentException("Оставьте комментарий");
        }
        votingStorage.create(new VoteDTO(dto,LocalDateTime.now()));

    }

    public ResultsDTO getResults() {
        return VotingStorage.getInstance().getInfo();
    }

    public NamesDTO getNames() {
        return VotingStorage.getInstance().getNames();
    }


    public static VotingService getInstance() {
        return instance;
    }


}
