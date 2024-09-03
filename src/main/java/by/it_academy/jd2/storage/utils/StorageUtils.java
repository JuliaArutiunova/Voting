package by.it_academy.jd2.storage.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class StorageUtils {
    public static final String propertiesSource = "address.properties";



    public static String getProperty(String source, String propertyName) {
        Properties properties = new Properties();
        InputStream inputStream = StorageUtils.class.getClassLoader().getResourceAsStream(source);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName);
    }

    public static int generateParticipantID(int startsWith) {
        Random random = new Random();
        int lowerBound = startsWith * 1000;
        int upperBound = (lowerBound + 1000) - 1;

        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public static Map<Integer, String> createParticipantsMap(String[] participants, int commonId) {

        Map<Integer, String> map = new HashMap<>();
        for (String participant : participants) {
            String addError = map.put(generateParticipantID(commonId), participant);
            if (addError != null) {
                while (addError != null) {
                    addError = map.put(generateParticipantID(commonId), addError);
                }
            }
        }
        return map;
    }


}
