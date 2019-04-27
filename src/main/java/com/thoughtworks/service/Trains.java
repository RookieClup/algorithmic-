package com.thoughtworks.service;

import com.thoughtworks.entity.Route;
import com.thoughtworks.entity.Trip;

public class Trains {
    private GraphMap map = new GraphMap();

    public String calcDistance(String route) {
        route = getRefactFormat(route);
        int distance = 0;
        int index = 0;
        int temp = 0;
        while (index + 1 < route.length()) {
            temp = map.getDistance(route.charAt(index), route.charAt(index + 1));
            if (0 == temp) {
                return GraphMap.NO_SUCH_ROUTE;
            }
            distance += temp;
            index++;
        }
        return distance + "";
    }


    public Trip calcAllKindsOfTrips(char start, char end, int maxStops, int maxDistance) {
        return map.makeTripFromStart2End(start, end, maxStops, maxDistance);
    }


    public GraphMap getMap() {
        return map;
    }

    public void setMap(GraphMap map) {
        this.map = map;
    }

    public String calcTime(String route) {
        String routeName = getRefactFormat(route);

        String distance = calcDistance(route);
        if(distance.equals(GraphMap.NO_SUCH_ROUTE)){
            return GraphMap.NO_SUCH_ROUTE;
        }
        Route routeTime = new Route(routeName, Integer.parseInt(distance));
        return String.valueOf(routeTime.getRouteTime());
    }

    private String getRefactFormat(String route) {
        if (null == route || route.length() < 2) {
            return GraphMap.NO_SUCH_ROUTE;
        }
        return route.replace("-", "").toUpperCase();
    }

}
