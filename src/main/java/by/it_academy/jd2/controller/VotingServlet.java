package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.dto.ParticipantsDTO;
import by.it_academy.jd2.service.FormManagerService;
import by.it_academy.jd2.service.VotingService;
import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.service.api.IVotingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class VotingServlet extends HttpServlet {

    public static final String USERNAME_PARAMETER = "firstname";
    public static final String ARTIST_PARAMETER = "artist";
    public static final String GENRE_PARAMETER = "genre";
    public static final String COMMENT_PARAMETER = "txtpole";

    IFormManagerService formManagerService = FormManagerService.getInstance();
    IVotingService votingService = VotingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ParticipantsDTO participants = formManagerService.getParticipants();
//Если форма не создана, написать, что голосованиене проводится
        req.setAttribute("artists", participants.getArtists());
        req.setAttribute("genres", participants.getGenres());
        req.getRequestDispatcher("/template/votingForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String userName = req.getParameter(USERNAME_PARAMETER);
        String artist = req.getParameter(ARTIST_PARAMETER);
        String[] genre = req.getParameterValues(GENRE_PARAMETER);
        String comment = req.getParameter(COMMENT_PARAMETER);

        req.setAttribute("name", userName);

        Cookie cookie = new Cookie("status","voted");
        

        try {
            votingService.create(new InfoFromClientDTO(userName, artist, genre, comment));
            req.getRequestDispatcher("/template/accepted.jsp").forward(req, resp);


        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/template/error.jsp").forward(req, resp);
        }
    }
}
