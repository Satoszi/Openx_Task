import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequestProviderTest {
    private JsonNode jsonUsers;
    private JsonNode jsonPosts;
    private int expectedUsersCount = 10;
    private int expectedPostsCount = 100;

    //initialize
    public RequestProviderTest() throws IOException {

        RequestProvider parseData = new RequestProvider();
        jsonUsers = parseData.getParsedData("https://jsonplaceholder.typicode.com/users");
        jsonPosts = parseData.getParsedData("https://jsonplaceholder.typicode.com/posts");
    }
    @Test
    public void usersNumber() throws IOException {

        int usersCounter = 0;
        Iterator<JsonNode> usersIterator = jsonUsers.elements();
        while(usersIterator.hasNext()){
            usersIterator.next();
            usersCounter++;
        }
        assertTrue(usersCounter == expectedUsersCount);
    }

    @Test
    public void postsNumber() throws IOException {

        int postsCounter = 0; //should be 10
        Iterator<JsonNode> usersIterator = jsonPosts.elements();
        while(usersIterator.hasNext()){
            usersIterator.next();
            postsCounter++;
        }
        assertTrue(postsCounter == expectedPostsCount);
    }
}
