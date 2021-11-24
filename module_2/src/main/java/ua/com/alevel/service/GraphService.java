package ua.com.alevel.service;

import ua.com.alevel.graph.Graph;
import ua.com.alevel.graph.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphService {

    public String getShortestPathForCities(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("input/" + fileName)))) {
            String[] cities = reader.lines().collect(Collectors.joining("\n")).split("\\s+");
            Graph graph = new Graph();
            int cityIndex = 0;
            int citiesNumber = Integer.parseInt(cities[cityIndex++]);
            ArrayList<Node> nodes = new ArrayList<>();
            for (int cityNumber = 1; cityNumber <= citiesNumber; cityNumber++) {
                nodes.add(new Node(cities[cityIndex]));
                if (cityNumber != citiesNumber) {
                    int numberOfNeighbors = Integer.parseInt(cities[++cityIndex]);
                    cityIndex = numberOfNeighbors * 2 + cityIndex + 1;
                }
            }
            int pathCitiesIndex = 0;
            for (int neighborIndex = 2, cityCount = 0; neighborIndex < cities.length && cityCount < nodes.size(); ) {
                int offset = Integer.parseInt(cities[neighborIndex++]);
                for (int innerIndex = 0; innerIndex < offset; innerIndex++) {
                    Node currentNode = nodes.get(cityCount);
                    Node neighbor = nodes.get(Integer.parseInt(cities[neighborIndex]) - 1);
                    int weight = Integer.parseInt(cities[++neighborIndex]);
                    currentNode.addDestination(neighbor, weight);
                    neighborIndex++;
                }
                cityCount++;
                pathCitiesIndex = neighborIndex++;
            }
            for (Node node : nodes) {
                graph.addNode(node);
            }
            int citiesNumberToResult = Integer.parseInt(cities[pathCitiesIndex++]);
            StringBuilder resultPaths = new StringBuilder();
            for (int i = 0; i < citiesNumberToResult; i++) {
                String sourceCity = cities[pathCitiesIndex++];
                String targetCity = cities[pathCitiesIndex++];
                Node sourceNode = graph.getNodes().stream()
                        .filter(node -> node.getName().equals(sourceCity))
                        .findFirst()
                        .get();
                Node targetNode = graph.getNodes().stream()
                        .filter(node -> node.getName().equals(targetCity))
                        .findFirst()
                        .get();
                resultPaths.append(getShortestPathFromSourceToTarget(graph, sourceNode, targetNode)).append("\n");
            }
            return resultPaths.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Not found";
        }
    }


    private Integer getShortestPathFromSourceToTarget(Graph graph, Node source, Node target) {

        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            currentNode.getAdjacentNodes().forEach((adjacentNode, edgeWeigh) -> {
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            });
            settledNodes.add(currentNode);
        }

        return graph.getNodes().stream()
                .filter(node -> node.getName().equals(target.getName()))
                .mapToInt(Node::getDistance)
                .findFirst()
                .getAsInt();
    }

    private void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
