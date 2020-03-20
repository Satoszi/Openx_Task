import com.fasterxml.jackson.databind.JsonNode;

public class GlobDistance {

    //Method counts the distance between 2 points on the sphere (Earth)
    static double getDistanceGlob(double lng_1, double lat_1, double lng_2, double lat_2) {

        double subVal1 = Math.sin((lat_2 - lat_1) / 2);
        double subVal2 = Math.sin((lng_2-lng_1)/2);
        double subPattern = Math.pow(subVal1 , 2) + Math.cos(lat_1) * Math.cos (lat_2) * Math.pow(subVal2,2) ;
        double mainPattern = 2 * Math.atan2(Math.sqrt(subPattern),Math.sqrt((1-subPattern)));

        return 6378.14 * mainPattern;
    }

    //Method counts the distance between 2 users on the (Earth)
    public static double getDistanceGlob(JsonNode user1, JsonNode user2) {
        String latUser1 = (user1.get("address").get("geo").get("lat") + "").replace("\"","");
        String lngUser1 = (user1.get("address").get("geo").get("lng") + "").replace("\"","");
        String latUser2 = (user2.get("address").get("geo").get("lat") + "").replace("\"","");
        String lngUser2 = (user2.get("address").get("geo").get("lng") + "").replace("\"","");

        return getDistanceGlob(
                Math.toRadians(Double.parseDouble(lngUser1)),
                Math.toRadians(Double.parseDouble(latUser1)),
                Math.toRadians(Double.parseDouble(lngUser2)),
                Math.toRadians(Double.parseDouble(latUser2))
        );
    }
}
