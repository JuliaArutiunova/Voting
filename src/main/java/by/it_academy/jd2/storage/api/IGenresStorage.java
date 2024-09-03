package by.it_academy.jd2.storage.api;



import java.util.Map;


public interface IGenresStorage {

    void create(String[] genresList);
    Map<Integer, String> get();


}
