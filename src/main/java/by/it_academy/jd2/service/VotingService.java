package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.service.api.IVotingService;
import by.it_academy.jd2.storage.VotingStorage;

import java.time.LocalDateTime;

public class VotingService implements IVotingService {
    private static final VotingService instance = new VotingService();
    private final VotingStorage votingStorage = VotingStorage.getInstance();

    private VotingService() {
    }

    @Override
    public void create(InfoFromClientDTO infoFromClientDTO) {
        int numberOfGenres = infoFromClientDTO.getGenres().length;

        if (infoFromClientDTO.getName().isBlank()) {
            throw new IllegalArgumentException("Имя не введено");
        }
        if (numberOfGenres == 0) {
            throw new IllegalArgumentException("Не выбраны жанры");
        }
        if (numberOfGenres > 5 || numberOfGenres < 3) {
            throw new IllegalArgumentException("Выбрано неверное количество жанров");
        }
        if (infoFromClientDTO.getComment().isBlank()) {
            throw new IllegalArgumentException("Оставьте комментарий");
        }
        votingStorage.create(new VoteDTO(infoFromClientDTO, LocalDateTime.now()));

    }
    public ResultsDTO getResults(){
        return votingStorage.get();

    }

    public static VotingService getInstance() {
        return instance;
    }
}
