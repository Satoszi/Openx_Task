import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GlobDistanceTest {
    private double expectedDistance1 = 0;
    private double expectedDistance2 = 0;
    private double expectedDistance3 = 8710.3001840393770;
    private double expectedDistance4 = 6697.8574270334475;

    private static double DELTA = 10e-5;

    @Test
    public void distanceCounting() throws IOException {

        double distance1 = GlobDistance.getDistanceGlob(30,-12,30,-12);
        double distance2 = GlobDistance.getDistanceGlob(55,2,55,2);

        double distance3 = GlobDistance.getDistanceGlob(100,50,-20,1);
        double distance4 = GlobDistance.getDistanceGlob(-18,-75,16,8);

        assertTrue(Math.abs(distance1 - expectedDistance1) < DELTA);
        assertTrue(Math.abs(distance2 - expectedDistance2) < DELTA);
        assertTrue(Math.abs(distance3 - expectedDistance3) < DELTA);
        assertTrue(Math.abs(distance4 - expectedDistance4) < DELTA);
    }
}
