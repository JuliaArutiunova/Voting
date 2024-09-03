package by.it_academy.jd2.dto;

import java.util.List;

public class KeysDTO {
    List<Integer> keys;

    public KeysDTO(List<Integer> keys) {
        this.keys = keys;
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public void setKeys(List<Integer> keys) {
        this.keys = keys;
    }
}
