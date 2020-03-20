import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksTest {

    @Test
    public void numberPostsPerUser() throws IOException {
        DataAnalyser dataAnalyser = new DataAnalyser();
        List<String> postPerUser = dataAnalyser.countPostsPerUser();
        String element;
        element = postPerUser.get(0);
        assertTrue(element.equals("Bret napisal 10 postow"));
        element = postPerUser.get(2);
        assertTrue(element.equals("Samantha napisal 10 postow"));
        element = postPerUser.get(4);
        assertTrue(element.equals("Kamren napisal 10 postow"));
        element = postPerUser.get(6);
        assertTrue(element.equals("Elwyn.Skiles napisal 10 postow"));
        element = postPerUser.get(8);
        assertTrue(element.equals("Delphine napisal 10 postow"));
        element = postPerUser.get(9);
        assertTrue(element.equals("Moriah.Stanton napisal 10 postow"));
    }

    @Test
    public void uniquePosts() throws IOException {
        DataAnalyser dataAnalyser = new DataAnalyser();
        List<String> nonUniquePosts = dataAnalyser.nonUniquePosts();
        assertTrue(nonUniquePosts.isEmpty());
    }

    @Test
    public void nearestUsers() throws IOException {
        DataAnalyser dataAnalyser = new DataAnalyser();
        Map<String , String > nearestUsers = dataAnalyser.nearestUsers();

        assertTrue(nearestUsers.get("Bret").equals("Kamren"));
        assertTrue(nearestUsers.get("Kamren").equals("Moriah.Stanton"));
        assertTrue(nearestUsers.get("Karianne").equals("Delphine"));
        assertTrue(nearestUsers.get("Maxime_Nienow").equals("Karianne"));
        assertTrue(nearestUsers.get("Antonette").equals("Samantha"));
    }
}





