package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.*;
import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.storage.ArtistsStorage;
import by.it_academy.jd2.storage.GenresStorage;
import by.it_academy.jd2.storage.VotingStorage;
import by.it_academy.jd2.storage.api.IArtistsStorage;
import by.it_academy.jd2.storage.api.IGenresStorage;
import by.it_academy.jd2.storage.api.IVotingStorage;

import java.util.*;

public class FormManagerService implements IFormManagerService {

    private static final FormManagerService instance = new FormManagerService();
    IArtistsStorage artistsStorage = ArtistsStorage.getInstance();
    IGenresStorage genresStorage = GenresStorage.getInstance();
    IVotingStorage votingStorage = VotingStorage.getInstance();

    private FormManagerService() {
    }

    @Override
    public void create(FormCreateDTO dto) {

        if (dto.getArtists().length < 2 || dto.getGenres().length < 2) {
            throw new IllegalArgumentException("Выбрано менее двух участников");
        }

        artistsStorage.create(dto.getArtists());
        genresStorage.create(dto.getGenres());

        votingStorage.renew(getParticipantIDs(artistsStorage.get().keySet(),
                genresStorage.get().keySet()));
    }

    @SafeVarargs
    public final KeysDTO getParticipantIDs(Set<Integer>... sets) {
        List<Integer> keys = new ArrayList<>();
        for (Set<Integer> set : sets) {
            keys.addAll(set.stream().toList());
        }
        return new KeysDTO(keys);
    }

    public ParticipantsDTO getParticipants() {
        return new ParticipantsDTO(artistsStorage.get(), genresStorage.get());
    }

//    public void update() {
//
//    }

    public static FormManagerService getInstance() {
        return instance;
    }

}
