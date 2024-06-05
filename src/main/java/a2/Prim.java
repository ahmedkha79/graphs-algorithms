package a2;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import utils.GraphParser;
import utils.GraphVisuals;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Prim {
    public static Graph primAlgorithmus(Graph graph){
        Graph geruest = new SingleGraph("geruest");

        Set<Node> besucht = new HashSet<>();
        Set<Edge> usedEdges = new HashSet<>();
        List<Node> allNodes = new ArrayList<>();
        graph.nodes().forEach(allNodes::add);

        //beliebigen Knoten als Startgraph
        //int randomIndex = (int) (Math.random() * allNodes.size());
        //Node currentNode = allNodes.get(randomIndex);
        Node currentNode = allNodes.get(2);
        PriorityQueue<Edge> edges = currentNode.leavingEdges()
                .sorted(Comparator.comparing(edge -> edge.getNumber("edgeWeight")))
                .collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.getNumber("edgeWeight")))));

        while(!(edges.isEmpty())){
            Edge edge=  edges.poll();
            Node x = edge.getNode0();
            Node y = edge.getNode1() == currentNode? edge.getNode0() : edge.getNode1();

            if (!(besucht.contains(currentNode))){
                geruest.addNode(currentNode.getId()).setAttribute("ui.label", currentNode.getId());
                besucht.add(currentNode);
            }

            if (!besucht.contains(y)){
                geruest.addNode(y.getId()).setAttribute("ui.label", y.getId());
                geruest.addEdge(edge.getId(), currentNode.getId(), y.getId()).setAttribute("ui.label", edge.getAttribute("edgeWeight"));
                besucht.add(y);
                edges.clear();
                edges.addAll(y.leavingEdges().toList());
                edges.stream()
                        .sorted(Comparator.comparing(edge1 -> edge1.getNumber("edgeWeight")))
                        .collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.comparingDouble(edge1 -> edge1.getNumber("edgeWeight")))));
            }

            currentNode = y;

            if (besucht.contains(allNodes)){
                break;
            }
        }
        return geruest;
    }
    public static void main(String[] args) throws URISyntaxException {
        String path3 = ResourceLoader.getResourcePath("testen.gka").toString();
        Graph graph = GraphParser.parseFromFile(path3);
        Graph primmed = primAlgorithmus(graph);
        GraphVisuals.displayGraph("javafx", primmed);
    }
}



//public class Prim {
//    public static Graph primAlgorithmus(Graph graph){
//        Graph geruest = new SingleGraph("geruest");
//        Set<Node> besucht = new HashSet<>();
//        List<Node> allNodes = new ArrayList<>();
//
//        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.getNumber("edgeWeight")));
//        graph.nodes().forEach(allNodes::add);
//
//        //beliebigen Knoten als Startgraph
//        //int randomIndex = (int) (Math.random() * allNodes.size());
//        //Node currentNode = allNodes.get(randomIndex);
//        Node currentNode = allNodes.get(2);
//        edges.addAll(currentNode.leavingEdges().toList());
//
//        while(!edges.isEmpty()){
//            Edge edge=  edges.poll();
//            Node y = edge.getNode1() == currentNode? edge.getNode0() : edge.getNode1();
//
//            if (!(besucht.contains(currentNode))){
//                geruest.addNode(currentNode.getId()).setAttribute("ui.label", currentNode.getId());
//                besucht.add(currentNode);
//            }
//            if (!besucht.contains(y)){
//                geruest.addNode(y.getId()).setAttribute("ui.label", y.getId());
//                geruest.addEdge(edge.getId(), currentNode.getId(), y.getId()).setAttribute("ui.label", edge.getAttribute("edgeWeight"));
//                besucht.add(y);
//                edges.addAll(y.leavingEdges().toList());
//            }
//            currentNode=y;
//            if (besucht.contains(allNodes)){
//                break;
//            }
//        }
//
//        return geruest;
//
//    }

//public static Graph primAlgorithmus(Graph graph){
//    Graph geruest = new SingleGraph("geruest");
//
//    Set<Node> besucht = new HashSet<>();
//
//    List<Node> allNodes = new ArrayList<>();
//    graph.nodes().forEach(allNodes::add);
//
//    //beliebigen Knoten als Startgraph
//    //int randomIndex = (int) (Math.random() * allNodes.size());
//    //Node currentNode = allNodes.get(randomIndex);
//    Node currentNode = allNodes.get(2);
//    PriorityQueue<Edge> edges = currentNode.leavingEdges()
//            .sorted(Comparator.comparing(edge -> edge.getNumber("edgeWeight")))
//            .collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.getNumber("edgeWeight")))));
//
//    while(!edges.isEmpty()){
//        Edge edge=  edges.poll();
//        Node x = edge.getNode0();
//        Node y = edge.getNode1() == currentNode? edge.getNode0() : edge.getNode1();
//
//        if (!(besucht.contains(currentNode))){
//            geruest.addNode(currentNode.getId()).setAttribute("ui.label", currentNode.getId());
//            besucht.add(currentNode);
//        }
//
//        if (!besucht.contains(y)){
//            geruest.addNode(y.getId()).setAttribute("ui.label", y.getId());
//            geruest.addEdge(edge.getId(), currentNode.getId(), y.getId()).setAttribute("ui.label", edge.getAttribute("edgeWeight"));
//            besucht.add(y);
//            edges.addAll(y.leavingEdges().toList());
//            edges.stream()
//                    .sorted(Comparator.comparing(edge1 -> edge1.getNumber("edgeWeight")))
//                    .collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.comparingDouble(edge1 -> edge1.getNumber("edgeWeight")))));
//        }
//
//        currentNode = y;
//
//        if (besucht.contains(allNodes)){
//            break;
//        }
//    }
//    return geruest;
//}
