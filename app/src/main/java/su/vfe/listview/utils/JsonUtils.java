package su.vfe.listview.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import su.vfe.listview.model.Item;

public class JsonUtils {
    public static List<Item> getItemsListFromJson(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Item> resultList = null;

        try {
            resultList = objectMapper.readValue(jsonString, new TypeReference<List<Item>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}