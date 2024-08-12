package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.service.VotingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



public class ResultsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultsDTO results = VotingService.getInstance().getResults();

        req.setAttribute("artists",results.getSortedArtistsResults());
        req.setAttribute("genres",results.getSortedGenresResults());
        req.setAttribute("comments",results.getSortedComments());

        req.getRequestDispatcher("/template/votingResults.jsp").forward(req,resp);
    }


}
