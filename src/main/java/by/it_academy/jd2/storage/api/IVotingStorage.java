package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.KeysDTO;
import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.dto.VoteDTO;

public interface IVotingStorage {
    void renew(KeysDTO dto);
    void create(VoteDTO voteDTO);

    public ResultsDTO get();

}
