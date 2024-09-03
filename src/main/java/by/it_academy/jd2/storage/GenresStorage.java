package by.it_academy.jd2.storage;


import by.it_academy.jd2.storage.api.IGenresStorage;
import by.it_academy.jd2.storage.utils.StorageUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class GenresStorage implements IGenresStorage {
    private static final GenresStorage instance = new GenresStorage();
    private static final int genresCommonID = 2;
    private final String GENRES_FILEPATH = StorageUtils.getProperty(StorageUtils.propertiesSource,"genres");



    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type genresType = new TypeToken<Map<Integer, String>>() {
    }.getType();

   private Map<Integer, String> genres = read();


    private GenresStorage() {
    }

    public void create(String[] genresList) {

        genres = StorageUtils.createParticipantsMap(genresList,genresCommonID);

        try (FileWriter fileWriter = new FileWriter(GENRES_FILEPATH)) {
            gson.toJson(genres, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, String> get() {
        return genres;
    }


    private Map<Integer, String> read() {
        try (FileReader fileReader = new FileReader(GENRES_FILEPATH)){
            return gson.fromJson(fileReader,genresType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static GenresStorage getInstance() {
        return instance;
    }




    public static int getGenresCommonID() {
        return genresCommonID;
    }
}
