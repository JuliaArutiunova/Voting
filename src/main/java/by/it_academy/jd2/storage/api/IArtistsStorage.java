package by.it_academy.jd2.storage.api;


import java.util.Map;


public interface IArtistsStorage {

    void create(String[] artistsList);
    Map<Integer, String> get();



}
