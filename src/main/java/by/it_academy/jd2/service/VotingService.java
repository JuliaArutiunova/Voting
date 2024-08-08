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
        int numberOfGenres = dto.getGenres().length;

        if (dto.getComment().getAuthor().isBlank()) {
            throw new IllegalArgumentException("Имя не введено");
        }
        if (numberOfGenres == 0) {
            throw new IllegalArgumentException("Не выбраны жанры");
        }
        if (numberOfGenres > 5 || numberOfGenres < 3) {
            throw new IllegalArgumentException("Выбрано неверное количество жанров");
        }
        if (dto.getComment().getText().isBlank()) {
            throw new IllegalArgumentException("Оставьте комментарий");
        }

        votingStorage.create(dto);
    }

    public DTOResults getResults() {
        return VotingStorage.getInstance().getInfo();
    }


    public static VotingService getInstance() {
        return instance;
    }


}
