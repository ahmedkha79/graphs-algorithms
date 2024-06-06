package utils;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GraphGenerator {


    public static Graph generateRandomGraph(int numNodes, int numEdges, int max) {
        long maxEdges = ((long) numNodes * (numNodes - 1)) / 2;
        if(max <= 0) throw new IllegalArgumentException(String.format("%d is negative or equal to zero",max));
        if (numEdges > maxEdges) {
            throw new IllegalArgumentException(String.format("To many Edges for a complete graph with %d nodes", numNodes));
        }
        Graph graph = new SingleGraph("Random Graph");

        for (int i = 0; i < numNodes; i++) {
            graph.addNode(String.valueOf(i)).setAttribute("ui.label", "v" + String.valueOf(i));
        }

        Random random = new Random();
        Set<String> edgeIDSet = new HashSet<>();
        int edgeCount = 0;

        while (edgeCount < numEdges) {
            int node0 = random.nextInt(numNodes);
            int node1 = random.nextInt(numNodes);
            if (node0 != node1) {
                String edgeID = node0 < node1 ? node0 + " -- " + node1 : node1 + " -- " + node0;
                if (edgeIDSet.add(edgeID)) {
                    int edgeWeight = random.nextInt(max+1);
                    graph.addEdge(edgeID, String.valueOf(node0), String.valueOf(node1))
                            .setAttribute("edgeWeight", edgeWeight);
                    graph.getEdge(edgeID).setAttribute("ui.label", String.format("%s", edgeWeight));
                    edgeCount++;
                }
            }
        }


        return graph;
    }
}
