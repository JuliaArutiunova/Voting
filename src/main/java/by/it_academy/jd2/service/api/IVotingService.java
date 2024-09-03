package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.dto.ResultsDTO;

public interface IVotingService {
    void create(InfoFromClientDTO infoFromClientDTO);
    ResultsDTO getResults();
}
