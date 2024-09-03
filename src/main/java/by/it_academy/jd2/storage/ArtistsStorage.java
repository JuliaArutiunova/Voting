package by.it_academy.jd2.storage;


import by.it_academy.jd2.storage.api.IArtistsStorage;
import by.it_academy.jd2.storage.utils.StorageUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;

import java.lang.reflect.Type;
import java.util.Map;


public class ArtistsStorage implements IArtistsStorage {
    private static final ArtistsStorage instance = new ArtistsStorage();

    private static final int artistsCommonID = 1;

    private final String ARTIST_FILEPATH = StorageUtils.getProperty(StorageUtils.propertiesSource, "artists");

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type artistsType = new TypeToken<Map<Integer, String>>() {
    }.getType();

    private Map<Integer, String> artists = read();


    private ArtistsStorage() {
    }


    public void create(String[] artistsList) {

        artists = StorageUtils.createParticipantsMap(artistsList, artistsCommonID);

        try (FileWriter fileWriter = new FileWriter(ARTIST_FILEPATH)) {
            gson.toJson(artists, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Map<Integer, String> read() {
        try (FileReader fileReader = new FileReader(ARTIST_FILEPATH)) {
            return gson.fromJson(fileReader, artistsType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, String> get() {
        return artists;
    }

    public void update(int id, String newName) {

    }

    public static ArtistsStorage getInstance() {
        return instance;
    }


    public static int getArtistsCommonID() {
        return artistsCommonID;
    }
}
