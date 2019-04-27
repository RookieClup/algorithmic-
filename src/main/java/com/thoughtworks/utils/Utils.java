package com.thoughtworks.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    private static final char START_NODE = 'A';

    public static int[][] createMap(String filename) {
        String data = null;
        try {
            data = readOriData(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] edges = data.split("[\\s]*,[\\s]*");
        List<String> nodeList = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            String nodeStart = String.valueOf(edges[i].charAt(0));
            String nodeEnd = String.valueOf(edges[i].charAt(1));
            nodeList.add(nodeStart);
            nodeList.add(nodeEnd);
        }
        List<String> nodes = nodeList.stream().distinct().sorted().collect(Collectors.toList());
        int size = nodes.size();
        int[][] map = new int[size][size];
        for (int i = 0; i < edges.length; i++) {
            int weight = Integer.parseInt(edges[i].substring(2));
            int nodeStart = nodes.indexOf(String.valueOf(edges[i].charAt(0)));
            int nodeEnd = nodes.indexOf(String.valueOf(edges[i].charAt(1)));
            map[nodeStart][nodeEnd] = weight;
        }
        return map;
    }

    private static String readOriData(String filename) throws IOException {

        BufferedReader dataFile = new BufferedReader(new FileReader(filename));
        String line, data;
        data = "";

        while ((line = dataFile.readLine()) != null)
            data += line;
        return data;
    }

}
