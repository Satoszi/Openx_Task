import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class RequestProvider {

    // Downloads, parses and returns Data from api (from the url)
    public JsonNode getParsedData (String url) throws IOException {
        String jsonDataAsString = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] jsonData = jsonDataAsString.getBytes();
        return objectMapper.readTree(jsonData);
    }
}
