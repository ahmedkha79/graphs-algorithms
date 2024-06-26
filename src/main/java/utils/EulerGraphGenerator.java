package utils;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class EulerGraphGenerator {

    public static Graph createEulerGraph(int nodeCount){
        if(nodeCount < 3) throw new IllegalArgumentException("Parameter 'nodeCount' must be bigger than 3");
        Graph graph = new MultiGraph("Eulergraph");
        List<Node> connectedNodes = new LinkedList<>();
        List<Node> unConnectedNodes;

        Random random = new Random();

        //Knoten zum Graphen hinzufügen
        for(int i = 0; i < nodeCount; i++){
            graph.addNode(String.valueOf(i)).setAttribute("ui.label", "v"+ i);
        }
        unConnectedNodes = new LinkedList<>(graph.nodes().toList());
        //Initialer Startknoten
        Node node = unConnectedNodes.getFirst();

        connectedNodes.add(node);
        unConnectedNodes.remove(node);

        while (!unConnectedNodes.isEmpty()){
            Node node0 = unConnectedNodes.get(random.nextInt(unConnectedNodes.size()));
            Node node1 = connectedNodes.get(random.nextInt(connectedNodes.size()));
            unConnectedNodes.remove(node0);
            connectedNodes.add(node0);
            createEdge(graph, node0, node1);
        }

        List<Node> oddNodes = new LinkedList<>();
        List<Node> evenNodes = new LinkedList<>();
        graph.nodes().forEach(graphNode -> {
            if(graphNode.getDegree() % 2 == 0) {
                evenNodes.add(graphNode);
            } else {
                oddNodes.add(graphNode);
            }
        });


        while(!oddNodes.isEmpty()){
            Node odd = oddNodes.get(random.nextInt(oddNodes.size()));
            oddNodes.remove(odd);
            Optional<Node> target = findNonNeighbourNode(odd, oddNodes);

            if(target.isPresent()){
                createEdge(graph, odd, target.get());
                evenNodes.add(target.get());
                oddNodes.remove(target.get());
            } else {
                Optional<Node> evenTarget = findNonNeighbourNode(odd, evenNodes);
                evenTarget.ifPresent(evenNode -> createEdge(graph, odd, evenNode));
                oddNodes.add(evenTarget.get());
                evenNodes.remove(evenTarget.get());
            }
            evenNodes.add(odd);
        }

        return graph;
    }

    private static Optional<Node> findNonNeighbourNode(Node node, List<Node> nodes){
        return nodes.stream().filter(oddNode -> !oddNode.hasEdgeBetween(node)).findFirst();
    }

    private static Edge createEdge(Graph graph, Node node0, Node node1){
        return graph.addEdge(node0 + " -- " + node1, node0, node1);
    }
}
