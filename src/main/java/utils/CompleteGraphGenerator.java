package utils;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CompleteGraphGenerator {
    public static Graph generateCompleteRandomGraph(int numNodes, int numEdges){
        long maxEdges = ((long) numNodes * (numNodes - 1)) / 2;
        if (numEdges > maxEdges) {
            throw new IllegalArgumentException(String.format("To many Edges for a complete graph with %d nodes", numNodes));
        }
        if(numEdges < numNodes-1){
            throw new IllegalArgumentException(String.format("numEdges %d has to at least be equal to numNodes %s -1 ", numEdges, numNodes));
        }



        Graph completeGraph = new MultiGraph("Complete Graph");


        for (int i = 0; i < numNodes; i++) {
            completeGraph.addNode(String.valueOf(i)).setAttribute("ui.label", "v" + i);
        }

        Set<String> edgeIDSet = new HashSet<>();
        int edgeCount = 0;
        Random rand = new Random();

        for(int k = 0; k < numNodes-1; k++){
            Edge edge = addEdge(completeGraph, k, k+1);

            edgeIDSet.add(edge.getId());
            edgeCount++;

        }

        while(edgeCount < numEdges){
            int node0 = rand.nextInt(numNodes);
            int node1 = rand.nextInt(numNodes);
            if(edgeIDSet.add(createEdgeId(node0, node1))){
                addEdge(completeGraph, node0, node1);
                edgeCount++;
            }
        }

        return completeGraph;

    }

    private static Edge addEdge(Graph graph, int node0, int node1){
        String edgeID = createEdgeId(node0, node1);
        graph.addEdge(edgeID, String.valueOf(node0), String.valueOf(node1));
        return graph.getEdge(edgeID);
    }

    private static String createEdgeId(int node0, int node1){
        return node0 < node1 ? node0 + " -- " : node1 + " -- " + node0;
    }

    private static void setEdgeWeight(Edge edge, int edgeWeight){
        edge.setAttribute("edgeWeight", edgeWeight);
        edge.setAttribute("ui.label", edgeWeight);
    }


}
