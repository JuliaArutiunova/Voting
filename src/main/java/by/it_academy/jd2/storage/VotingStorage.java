package by.it_academy.jd2.storage;


import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.dto.NamesDTO;
import by.it_academy.jd2.instance.Artist;
import by.it_academy.jd2.instance.Comment;
import by.it_academy.jd2.instance.Genre;
import by.it_academy.jd2.storage.api.IVotingStorage;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VotingStorage implements IVotingStorage {

    private static final VotingStorage instance = new VotingStorage();

    private class DateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        }

        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ISO_DATE_TIME);
        }
    }

    private final String ARTIST_FILEPATH = getProperty("artists");
    private final String GENRES_FILEPATH = getProperty("genres");
    private final String COMMENTS_FILEPATH = getProperty("comments");


    private Gson gson = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new DateTimeAdapter())
            .create();

    private Type listTypeArtist = new TypeToken<ArrayList<Artist>>() {
    }.getType();
    private Type listTypeGenres = new TypeToken<ArrayList<Genre>>() {
    }.getType();
    private Type listTypeComment = new TypeToken<ArrayList<Comment>>() {
    }.getType();


    private List<Artist> artistsResults;
    private List<Genre> genresResults;
    private List<Comment> comments;

    {
        try {
            artistsResults = gson.fromJson(new FileReader(ARTIST_FILEPATH), listTypeArtist);
            genresResults = gson.fromJson(new FileReader(GENRES_FILEPATH), listTypeGenres);
            comments = gson.fromJson(new FileReader(COMMENTS_FILEPATH), listTypeComment);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final List<String> artistNames = artistsResults.stream().map(Artist::getName).toList();
    private final List<String> genresNames = genresResults.stream().map(Genre::getName).toList();


    private VotingStorage() {
    }


    @Override
    public void create(VoteDTO dto) {
        updateArtist(dto.getArtist());
        updateGenres(dto.getGenres());
        addComment(dto.getComment());
    }

    public void addComment(Comment comment) {

        comments.add(comment);

        try (FileWriter fileWriter = new FileWriter(COMMENTS_FILEPATH)) {
            gson.toJson(comments, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public ResultsDTO getInfo() {
        return new ResultsDTO(artistsResults, genresResults, comments);
    }

    public NamesDTO getNames() {
        return new NamesDTO(artistNames, genresNames);
    }


    public static VotingStorage getInstance() {
        return instance;
    }

    public String getProperty(String propertyName) {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("address.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName);
    }
}
