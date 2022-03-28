package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import pojo.UserPOJO;
import utils.EmailValidatorStrict;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Json {

    private static final ObjectMapper objectMapper = getObjectMapper();
    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    private static final JsonReader jsonReader = new JsonReader();

    private static ObjectMapper getObjectMapper() {
        final ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new ObjectMapper();
    }

    private static JsonNode parse(final String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    private static <A> A fromJson(final JsonNode node, final Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static List<UserPOJO> getListOfUserPOJO() {
        final List<UserPOJO> result = new ArrayList<>();
        final JSONArray jsonarray;
        try {
            jsonarray = new JSONArray(jsonReader.getJson(URL));
            for (final Object json : jsonarray) {
                final JsonNode node = Json.parse(json.toString());
                final UserPOJO userPOJO = Json.fromJson(node, UserPOJO.class);
                if (EmailValidatorStrict.isValid(userPOJO.getEmail())) {
                    result.add(userPOJO);
                }
            }
        } catch (final IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
