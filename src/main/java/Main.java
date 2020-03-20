import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        DataAnalyser dataAnalyser = new DataAnalyser();

        List<String> postsPerUser = dataAnalyser.countPostsPerUser();
        List<String> nonUniquePosts = dataAnalyser.nonUniquePosts();
        Map <String , String > nearestUsers = dataAnalyser.nearestUsers();

        Iterator<String> task1 = postsPerUser.iterator();
        Iterator<String> task2 = nonUniquePosts.iterator();
        Iterator<Map.Entry<String , String>> task3 = nearestUsers.entrySet().iterator();

        while (task1.hasNext())
            System.out.println(task1.next());

        System.out.println("\n");

        while (task2.hasNext())
            System.out.println(task2.next());

        System.out.println("\n");

        while (task3.hasNext()) {
            Map.Entry<String , String> entry = task3.next();
            System.out.println(entry.getKey() + " ma najblizej do: " + entry.getValue());
        }
    }
}

