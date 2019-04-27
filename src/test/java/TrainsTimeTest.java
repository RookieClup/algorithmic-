import com.thoughtworks.Main;
import com.thoughtworks.entity.Trip;
import com.thoughtworks.service.GraphMap;
import com.thoughtworks.service.Trains;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrainsTimeTest {
    private Trains train = Main.getTrains();

    // 1.The distance of the route A-B-C.
    @Test
    public void test1() {
        String route = "A-B-C";
        assertEquals("11", train.calcTime(route));
    }

    // 2.The distance of the route A-D.
    @Test
    public void test2() {
        String route = "A-D";
        assertEquals("5", train.calcTime(route));
    }

    // 3.The distance of the route A-D-C.
    @Test
    public void test3() {
        String route = "A-D-C";
        assertEquals("15", train.calcTime(route));
    }

    // 4.The distance of the route A-E-B-C-D.
    @Test
    public void test4() {
        String route = "A-E-B-C-D";
        assertEquals("28", train.calcTime(route));
    }

    // 5.The distance of the route A-E-D.
    @Test
    public void test5() {
        String route = "A-E-D";
        assertEquals(GraphMap.NO_SUCH_ROUTE, train.calcTime(route));
    }

    // 6.The number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
    @Test
    public void shouldReturnTrueWhenRouteEqual2() {
        Trip trip =train.calcAllKindsOfTrips('C','C', 6, GraphMap.UNLIMITED_DISTANCE);
        assertEquals(4,trip.getAllKindsTripBaseTime(30));
    }

    // 7.The number of trips starting at A and ending at C with exactly 4 stops. In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    @Test
    public void test7() {
        int stops = 4;
        Trip trip = train.calcAllKindsOfTrips('A', 'C', 5, GraphMap.UNLIMITED_DISTANCE);
        assertEquals(1, trip.getAllKindsTripExactlyBaseTime(30));
    }

    // 8.The length of the shortest route (in terms of distance to travel) from A to C.
    @Test
    public void test8() {
        Trip trip = train.calcAllKindsOfTrips('A', 'C', GraphMap.OPTIMAL_ALGORITHM, GraphMap.UNLIMITED_DISTANCE);
        assertEquals(11, trip.getShotestRouteTime());
    }

    // 9.The length of the shortest route (in terms of distance to travel) from B to B.
    @Test
    public void test9() {
        Trip trip = train.calcAllKindsOfTrips('B', 'B', GraphMap.OPTIMAL_ALGORITHM, GraphMap.UNLIMITED_DISTANCE);
        assertEquals(13, trip.getShotestRouteTime());
    }

    // 10.The number of different routes from C to C with a distance of less than 30. In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
    @Test
    public void test10() {

        int maxDistance = 30;
        Trip trip = train.calcAllKindsOfTrips('C', 'C', 9, maxDistance);
        assertEquals(7, trip.getNumOfRouteInThisTrip());
    }
}
