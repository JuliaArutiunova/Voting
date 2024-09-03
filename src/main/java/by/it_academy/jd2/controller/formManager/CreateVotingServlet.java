package by.it_academy.jd2.controller.formManager;

import by.it_academy.jd2.dto.FormCreateDTO;
import by.it_academy.jd2.service.FormManagerService;
import by.it_academy.jd2.service.api.IFormManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class CreateVotingServlet extends HttpServlet {

    private static final String CREATE_FORM_PATH = "/template/formManager/createForm.html";


    IFormManagerService formManagerService = FormManagerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(CREATE_FORM_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        String[] newArtists = getFilledInFields(req.getParameterValues("artist"));
        String[] newGenres = getFilledInFields(req.getParameterValues("genre"));

        Writer writer = resp.getWriter();
        if (newArtists != null || newGenres != null) {
            try {
                formManagerService.create(new FormCreateDTO(newArtists, newGenres));
                writer.write("Голосование успешно создано");
            } catch (IllegalArgumentException e) {
                writer.write("<p>Голосование не создано!</p>");
                writer.write("<p>" + e.getMessage() + "<p>");
            }
        }
    }

    public String[] getFilledInFields(String[] parameters) {
        List<String> filledInFields = new ArrayList<>();
        for (String parameter : parameters) {
            if (!parameter.isBlank()) {
                filledInFields.add(parameter);
            }
        }
        return filledInFields.toArray(new String[0]);
    }


}
