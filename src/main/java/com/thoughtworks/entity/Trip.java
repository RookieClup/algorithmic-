package com.thoughtworks.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trip {
	private List<Route> routes;
	private char start;
	private char end;
	
	public Trip(List<Route> routes, char start, char end) {
		this.routes = routes;
		this.start = start;
		this.end = end;
	}

	public int getShotestRouteDistance(){
		return routes.stream().mapToInt(Route::getDistance).min().getAsInt();
	}
	public int getShotestRouteTime(){
		return routes.stream().mapToInt(Route::getRouteTime).min().getAsInt();
	}

	public int getNumOfRouteInThisTrip(){
		return null == routes ? 0 : routes.size();
	}

	public long getNumOfRouteExactlyStops(int stops){
		return routes.stream().filter(e -> e.getStops() == stops).count();
	}
	public long getAllKindsTripBaseTime(int time){
		return routes.stream().filter(e->e.getRouteTime()<time).count();
	}
	public long getAllKindsTripExactlyBaseTime(int time){
		return routes.stream().filter(e->e.getRouteTime()==time).count();
	}
	
	public char getStart() {
		return start;
	}

	public void setStart(char start) {
		this.start = start;
	}

	public char getEnd() {
		return end;
	}

	public void setEnd(char end) {
		this.end = end;
	}

}
