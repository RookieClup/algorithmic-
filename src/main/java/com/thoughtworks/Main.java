package com.thoughtworks;

import com.thoughtworks.entity.Trip;
import com.thoughtworks.service.GraphMap;
import com.thoughtworks.service.Trains;
import com.thoughtworks.utils.Utils;

/**
 * 求路径距离
 * 给定起始点，求路径条数
 * 给定遍历深度，求路径条数
 * 给定路径长度，求路径条数
 * 求最短路径
 */
public class Main {

    public static void main(String[] args) {
        Trains train = getTrains();

        // 1.The distance of the route A-B-C.
        String route = "A-B-C";
        System.out.println("Output #1: " + train.calcDistance(route));

        // 2.The distance of the route A-D.
        route = "A-D";
        System.out.println("Output #2: " + train.calcDistance(route));

        // 3.The distance of the route A-D-C.
        route = "A-D-C";
        System.out.println("Output #3: " + train.calcDistance(route));

        // 4.The distance of the route A-E-B-C-D.
        route = "A-E-B-C-D";
        System.out.println("Output #4: " + train.calcDistance(route));

        // 5.The distance of the route A-E-D.
        route = "A-E-D";
        System.out.println("Output #5: " + train.calcDistance(route));

        // 6.The number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
        Trip trip = train.calcAllKindsOfTrips('C', 'C', 3, GraphMap.UNLIMITED_DISTANCE);
        System.out.println("Output #6: " + trip.getNumOfRouteInThisTrip());

        // 7.The number of trips starting at A and ending at C with exactly 4 stops. In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
        int stops = 4;
        trip = train.calcAllKindsOfTrips('A', 'C', stops, GraphMap.UNLIMITED_DISTANCE);
        System.out.println("Output #7: " + trip.getNumOfRouteExactlyStops(stops));

        // 8.The length of the shortest route (in terms of distance to travel) from A to C.
        trip = train.calcAllKindsOfTrips('A', 'C', GraphMap.OPTIMAL_ALGORITHM, GraphMap.UNLIMITED_DISTANCE);
        System.out.println("Output #8: " + trip.getShotestRouteDistance());

        // 9.The length of the shortest route (in terms of distance to travel) from B to B.
        trip = train.calcAllKindsOfTrips('B', 'B', GraphMap.OPTIMAL_ALGORITHM, GraphMap.UNLIMITED_DISTANCE);
        System.out.println("Output #9: " + trip.getShotestRouteDistance());

        // 10.The number of different routes from C to C with a distance of less than 30. In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
        int maxDistance = 30;
        trip = train.calcAllKindsOfTrips('C', 'C', 9, maxDistance);
        System.out.println("Output #10: " + trip.getNumOfRouteInThisTrip());
    }

    public static Trains getTrains() {
        String filename = Trains.class.getClassLoader().getResource("validroutes.txt").getPath();
        int[][] map = Utils.createMap(filename);

        GraphMap graphMap = new GraphMap();
        graphMap.setMap(map);

        Trains train = new Trains();
        train.setMap(graphMap);
        return train;
    }


}
