package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.DTO;
import by.it_academy.jd2.dto.DTOResults;
import by.it_academy.jd2.instance.Comment;
import by.it_academy.jd2.service.VotingService;
import by.it_academy.jd2.service.api.IVotingService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class VotingServlet extends HttpServlet {
    public static final String USERNAME_PARAMETER = "firstname";
    public static final String ARTIST_PARAMETER = "artist";
    public static final String GENRE_PARAMETER = "genre";
    public static final String COMMENT_PARAMETER = "txtpole";

    private final static IVotingService votingService = VotingService.getInstance();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm");


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException
            , IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String userName = req.getParameter(USERNAME_PARAMETER);
        String artist = req.getParameter(ARTIST_PARAMETER);
        String[] genre = req.getParameterValues(GENRE_PARAMETER);
        String comment = req.getParameter(COMMENT_PARAMETER);

        votingService.create(new DTO(artist, genre, new Comment(userName, comment, LocalDateTime.now())));

        PrintWriter writer = resp.getWriter();
        writer.write("<p style=\"font-size:45px;\">Спасибо за участие!</p>");

        printResult(resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         printResult(resp);
    }

    public void printResult(HttpServletResponse resp) throws IOException {

        DTOResults results = VotingService.getInstance().getResults();

        PrintWriter writer = resp.getWriter();
        writer.write("""
                <head>
                    <meta charset="UTF-8">
                </head>""");
        writer.write("<h1>Результаты:</h1>");

        writer.write("<h3>Любимая группа</h3>");
        writer.write("""
                <table style="width:30%">
                  <tr>
                    <th>Исполнитель</th>
                    <th>Набрано голосов</th>
                  </tr>""");
        results.getSortedArtistsResults().forEach(artist -> writer.write("<tr><td style=\"text-align:center;\">" + artist.getName()
                + "</td><td style=\"text-align:center;\">" + artist.getVotes() + "</td></tr>"));
        writer.write(" </table>");


        writer.write("<h3>Любимый жанр</h3>");
        writer.write("""
                <table style="width:30%">
                  <tr>
                    <th>Жанр</th>
                    <th>Набрано голосов</th>
                 </tr>""");
        results.getSortedGenresResults().forEach(genre -> writer.write("<tr><td style=\"text-align:center;\">" + genre.getName()
                + "</td><td style=\"text-align:center;\">" + genre.getVotes() + "</td></tr>"));
        writer.write(" </table>");

        writer.write("<h3>Комментарии:</h3>");
        results.getSortedComments().forEach(comment -> writer.write("<p>" + formatter.format(comment.getDateTime())
                + " " + comment.getAuthor() + " " + comment.getText() + "</p>"));


    }
}
