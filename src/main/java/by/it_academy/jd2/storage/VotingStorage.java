package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.KeysDTO;
import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.entity.Comment;
import by.it_academy.jd2.storage.api.IVotingStorage;
import by.it_academy.jd2.storage.utils.StorageUtils;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    private final String COMMENTS_FILEPATH = StorageUtils.getProperty(StorageUtils.propertiesSource, "comments");
    private final String VOTING_FILEPATH = StorageUtils.getProperty(StorageUtils.propertiesSource, "voting");


    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new DateTimeAdapter())
            .create();

    private final Type votesType = new TypeToken<Map<Integer, Integer>>() {
    }.getType();
    private final Type commentType = new TypeToken<ArrayList<Comment>>() {
    }.getType();

    private Map<Integer, Integer> voting = readVotes();
    private List<Comment> comments = readComments();


    private VotingStorage() {
    }

    @Override
    public void renew(KeysDTO dto) {
        Map<Integer, Integer> newVoting = new HashMap<>();
        dto.getKeys().forEach(integer -> newVoting.put(integer, 0));

        voting = newVoting;
        comments = new ArrayList<>();

        try {
            writeVotes();
            writeComments();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(VoteDTO voteDTO) {
        updateVotes(voteDTO);
        addComment(voteDTO.getComment());
    }

    public void updateVotes(VoteDTO voteDTO) {
        addVote(voteDTO.getArtist());

        for (int i = 0; i < voteDTO.getGenres().length; i++) {
            addVote(voteDTO.getGenres()[i]);
        }
        try {
            writeVotes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, Integer> readVotes() {
        try (FileReader fileReader = new FileReader(VOTING_FILEPATH)) {
            return gson.fromJson(fileReader, votesType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Comment> readComments() {
        try (FileReader fileReader = new FileReader(COMMENTS_FILEPATH)) {
            return gson.fromJson(fileReader, commentType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addComment(Comment comment) {

        comments.add(comment);

        try {
            writeComments();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addVote(int key) {
        voting.compute(key, (k, v) -> v == null ? 1 : v + 1);
    }

    public void writeComments() throws IOException {
        FileWriter fileWriter = new FileWriter(COMMENTS_FILEPATH);
        gson.toJson(comments, fileWriter);
        fileWriter.close();
    }

    public void writeVotes() throws IOException {
        FileWriter fileWriter = new FileWriter(VOTING_FILEPATH);
        gson.toJson(voting, fileWriter);
        fileWriter.close();
    }

    @Override
    public ResultsDTO get() {
        return new ResultsDTO(voting, comments);
    }


    public static VotingStorage getInstance() {
        return instance;
    }


}
