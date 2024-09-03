package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.ParticipantsDTO;
import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.service.FormManagerService;
import by.it_academy.jd2.service.VotingService;
import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.service.api.IVotingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.Comparator;
import java.util.LinkedHashMap;

import java.util.Map;
import java.util.stream.Collectors;

public class ResultServlet extends HttpServlet {
    IVotingService votingService = VotingService.getInstance();
    IFormManagerService formManagerService = FormManagerService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultsDTO results = votingService.getResults();
        ParticipantsDTO participants = formManagerService.getParticipants();

        req.setAttribute("artists", getSortedResults(results.getVotes(), participants.getArtists()));
        req.setAttribute("genres", getSortedResults(results.getVotes(), participants.getGenres()));
        req.setAttribute("comments", results.getSortedComments());

        req.getRequestDispatcher("/template/votingResults.jsp").forward(req, resp);

    }

    public LinkedHashMap<String, Integer> getSortedResults(Map<Integer, Integer> votes, Map<Integer, String> participants) {
        LinkedHashMap<String, Integer> results = new LinkedHashMap<>();
        votes.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(entry -> {
                    if (participants.containsKey(entry.getKey())) {
                        results.put(participants.get(entry.getKey()), entry.getValue());
                    }
                });
        return results.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    }


}
