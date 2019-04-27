package com.thoughtworks.service;

import com.thoughtworks.entity.Route;
import com.thoughtworks.entity.Trip;

import java.util.ArrayList;
import java.util.List;

public class GraphMap {
    private static final char START_NODE = 'A';
    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
    public static final int OPTIMAL_ALGORITHM = -1;
    public static final int UNLIMITED_DISTANCE = -1;
    private static List<Route> routes;

    /**
     * Map of ABCDE places.
     * Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7 .
     **/
    private int[][] map = new int[][]{};

    public GraphMap() {
    }


    /**
     * 根据输入的字符，确定当前线路的距离
     *
     * @param start
     * @param end
     * @return
     */
    public int getDistance(char start, char end) {
        return getLengthByIndex(start - START_NODE, end - START_NODE);
    }

    public Trip makeTripFromStart2End(char start, char end, int stops, int maxDistance) {
        if (!checkPosition(start - START_NODE, end - START_NODE)) {
            return null;
        }
        routes = new ArrayList<Route>();
        search(start - START_NODE, end - START_NODE, "" + start, 0, stops, maxDistance);
        Trip trip = new Trip(routes, start, end);
        return trip;
    }

    private void search(int x, int y, String path, int distance, int stops, int maxDistance) {
        for (int i = 0; i < this.map[x].length; ++i) {
            if (this.map[x][i] > 0) {
                if (stops > 0) {
                    if (path.length() >= stops + 1) {
                        continue;
                    }
                } else if (path.substring(1).contains(String.valueOf((char) (i + START_NODE)))) {
                    continue;
                }

                boolean isExistDistance = maxDistance <= 0 || distance < maxDistance - this.map[x][i];
                if (isExistDistance) {
                    if (i == y) {
                        routes.add(new Route(path + (char) (y + START_NODE), distance + this.map[x][i]));
                        System.out.println("path=" + path + (char) (y + START_NODE) + ", distance=" + (distance + this.map[x][i]));
                    }

                    this.search(i, y, path + (char) (i + START_NODE), distance + this.map[x][i], stops, maxDistance);
                }
            }
        }

    }

    private int getLengthByIndex(int x, int y) {
        if (!checkPosition(x, y)) {
            return 0;
        }
        return map[x][y];
    }

    private boolean checkPosition(int x, int y) {
        if (x < 0 || y < 0 || x > map.length || y >= map.length) {
            return false;
        }
        return true;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}
