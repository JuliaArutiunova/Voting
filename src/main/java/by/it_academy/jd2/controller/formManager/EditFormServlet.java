package by.it_academy.jd2.controller.formManager;

import by.it_academy.jd2.dto.ParticipantsDTO;
import by.it_academy.jd2.service.FormManagerService;
import by.it_academy.jd2.service.api.IFormManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EditFormServlet extends HttpServlet {

    IFormManagerService formManagerService = FormManagerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParticipantsDTO participants = formManagerService.getParticipants();

        req.setAttribute("artists", participants.getArtists());
        req.setAttribute("genres", participants.getGenres());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }
}
