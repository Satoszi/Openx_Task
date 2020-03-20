import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.*;

public class DataAnalyser {

    private JsonNode usersNode;
    private JsonNode postsNode;
    private  double DELTA = 10e-5;
    public DataAnalyser() throws IOException {
        String urlUsers = "https://jsonplaceholder.typicode.com/users";
        String urlPosts = "https://jsonplaceholder.typicode.com/posts";

        RequestProvider parseData = new RequestProvider();

        usersNode = parseData.getParsedData(urlUsers);
        postsNode = parseData.getParsedData(urlPosts);
    }

    // Counts number of posts per each user
    public List <String> countPostsPerUser () {

        List <String> postsPerUserList = new ArrayList<String>();
        Iterator<JsonNode> users = usersNode.elements();

        Map <String , Integer> postsPerUserMap = new HashMap<String,Integer>();
        Map <String , String> userIdUsername = new HashMap<String,String>();

        while (users.hasNext()) {
            JsonNode user = users.next();
            postsPerUserMap.put(user.get("id").toString(), 0);
            userIdUsername.put(user.get("id").toString(),user.get("username").toString());
        }

         Iterator<JsonNode> posts = postsNode.elements();

         while (posts.hasNext()) {
             JsonNode post = posts.next();
             int increasedPostsNumber = postsPerUserMap.get(post.get("userId")+"") + 1;
             postsPerUserMap.put(post.get("userId").toString(),increasedPostsNumber);
         }

         Iterator<Map.Entry<String , Integer>> iterator = postsPerUserMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String , Integer> entry = iterator.next();
            String elementOfList = userIdUsername.get(entry.getKey()).replace("\"", "")  + " napisal " + entry.getValue() + " postow";
            postsPerUserList.add(elementOfList);
        }

        return postsPerUserList;
    }

    // Search non-unique titles of posts
    public List <String> nonUniquePosts () throws IOException {
        List <String> notUniquePosts = new ArrayList<String>();
        HashSet<String> uniquePosts = new HashSet<String>();

        Iterator<JsonNode> posts = postsNode.elements();

        while (posts.hasNext())
        {
            JsonNode post = posts.next();
            if (uniquePosts.contains(post.get("title").toString())) notUniquePosts.add(post.get("title").toString());
            uniquePosts.add(post.get("title").toString());
        }

        return notUniquePosts;
    }


    // For each user search users that lives the closest to him
    public   Map <String , String > nearestUsers () throws IOException {
            Map <String , String > nearestUsers = new HashMap<String,String>();

            Iterator<JsonNode> users1 = usersNode.elements();

            while (users1.hasNext())
            {
                JsonNode user1 = users1.next();
                double distance = -1;

                String username = user1.get("username").toString();
                String bestMatchUsername = "Nobody found";

                Iterator<JsonNode> users2 = usersNode.elements();
                while (users2.hasNext())
                {
                    JsonNode user2 = users2.next();
                    if ( !user1.get("username").equals(user2.get("username")) )
                    {
                        double tempDistance = GlobDistance.getDistanceGlob(user1,user2);
                        if ( Math.abs(distance + 1) < DELTA  ) {
                            distance = tempDistance;
                            bestMatchUsername = user2.get("username").toString();
                        }
                        else
                        if (tempDistance < distance) {
                            bestMatchUsername = user2.get("username").toString();
                            distance = tempDistance;
                        }
                    }
                }
                nearestUsers.put(username.replace("\"",""),bestMatchUsername.replace("\"",""));
            }
            return nearestUsers;
        }

}
