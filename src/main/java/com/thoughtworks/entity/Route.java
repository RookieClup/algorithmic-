package com.thoughtworks.entity;

public class Route {
    private String name;//a-b-c
    private int distance;

    private static final int UNIT_TIME=1;

    public Route(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public Route(String name) {
        this.name = name;
    }

    public int getStops() {
        return null == name ? 0 : name.length() - 1;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public int getRouteTime() {
        int stopTime = (name.length() - 2)*2;
        int time = stopTime + distance * UNIT_TIME;
        return time;
    }


}
