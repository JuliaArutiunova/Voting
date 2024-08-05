package by.it_academy.jd2.storage;


import by.it_academy.jd2.dto.DTOResults;
import by.it_academy.jd2.instance.Artist;
import by.it_academy.jd2.instance.Comment;
import by.it_academy.jd2.instance.Genre;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VotingStorage implements IVotingStorage {

//    class DateTimeAdapter implements JsonSerializer<LocalDateTime> {
//
//        @Override
//        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
//            return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
//        }
//    }

    private static final String ARTIST_FILEPATH = "C:/Users/arman/OneDrive/Desktop/Java courses/Projects" +
            "/Voting/src/main/resources/artists.json";
    private static final String GENRES_FILEPATH = "C:/Users/arman/OneDrive/Desktop/Java courses/Projects" +
            "/Voting/src/main/resources/genres.json";

//    private static final String COMMENTS_FILEPATH = "C:/Users/arman/OneDrive/Desktop/Java courses/Projects" +
//            "/Voting/src/main/resources/comments.json";

    private Gson gson = new GsonBuilder().setPrettyPrinting()
            //       .registerTypeAdapter(LocalDateTime.class, new DateTimeAdapter())
            .create();
    private Type listTypeArtist = new TypeToken<ArrayList<Artist>>() {
    }.getType();
    private Type listTypeGenres = new TypeToken<ArrayList<Genre>>() {
    }.getType();
//    private Type listTypeComment = new TypeToken<ArrayList<Comment>>(){
//
//    }.getType();


    private List<Artist> artistsResults;
    private List<Genre> genresResults;


    {
        try {
            artistsResults = gson.fromJson(new FileReader(ARTIST_FILEPATH), listTypeArtist);
            genresResults = gson.fromJson(new FileReader(GENRES_FILEPATH), listTypeGenres);
//            comments = gson.fromJson(new FileReader(commentsFilePath),listTypeComment);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Comment> comments = new ArrayList<>();


    @Override
    public void addComment(Comment comment) {
        comments.add(comment);

    }

    @Override
    public void updateArtist(String artist) {
        for (Artist value : artistsResults) {
            if (value.getName().equals(artist)) {
                value.setVotes(value.getVotes() + 1);
            }
        }
        try (FileWriter fileWriter = new FileWriter(ARTIST_FILEPATH)) {
            gson.toJson(artistsResults, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void updateGenres(String[] genres) {
        for (String genre : genres) {
            for (Genre genresResult : genresResults) {
                if (genresResult.getName().equals(genre)) {
                    genresResult.setVotes(genresResult.getVotes() + 1);
                }
            }
        }
        try (FileWriter fileWriter = new FileWriter(GENRES_FILEPATH)) {
            gson.toJson(genresResults, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public DTOResults getInfo() {
        return new DTOResults(artistsResults, genresResults, comments);
    }


}
