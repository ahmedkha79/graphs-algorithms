package a1;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.*;
import java.util.stream.Collectors;

public class BFS {
    private Graph graph;
    private Queue<Node> queue;
    private Set<Node> visited;
    private Map<Node, Node> path;
    private Map<Node, Double> edgeWeightMap;



    public BFS(Graph graph) {
        this.graph = graph;
        queue = new ArrayDeque<>();
        visited = new HashSet<>();
        path = new HashMap<>();
        edgeWeightMap = new HashMap<>();
    }

    public void search(String start, String target){
        Node startNode = graph.getNode(start);
        Node targetNode = graph.getNode(target);

        queue.add(startNode);
        visited.add(startNode);
        path.put(startNode, null);

            while (!(queue.isEmpty() && path.containsKey(targetNode))){
            Node currentNode = queue.poll();
            List<Node> neighbors = currentNode
                    .neighborNodes()
                    .filter(node -> !visited.contains(node))
                    .collect(Collectors.toCollection(LinkedList::new));
            for(Node neighbor: neighbors){
                queue.add(neighbor);
                visited.add(neighbor);
                path.put(neighbor, currentNode);
            }
        }
        printShortestPath(targetNode, false);
        if(path.containsKey(targetNode)){
                visualizeShortestPath(shortestPath(targetNode));
        } else {
            System.out.println("No Path found");
        }
    }

    public void weightedSearch(String start, String target) {
        Node startNode = graph.getNode(start);
        Node targetNode = graph.getNode(target);


        queue.add(startNode);
        visited.add(startNode);
        path.put(startNode, null);
        edgeWeightMap.put(startNode, 0.0);
        Node currentNode = null;

        do{
            currentNode = queue.poll();

           final Node finalCurrentNode = currentNode;


            currentNode.neighborNodes().forEach(neighbor -> {
                Edge edge = finalCurrentNode.getEdgeBetween(neighbor);

                if(!edge.hasAttribute("edgeWeight")) throw new IllegalArgumentException("No edgeWeight on given Edge");

                double edgeWeight = Double.parseDouble(edge.getAttribute("edgeWeight").toString());
                double pathWeight = edgeWeightMap.get(finalCurrentNode) + edgeWeight;

                if(!visited.contains(neighbor) || pathWeight < edgeWeightMap.getOrDefault(neighbor, Double.MAX_VALUE)){
                    visited.add(neighbor);
                    queue.add(neighbor);
                    path.put(neighbor, finalCurrentNode);
                    edgeWeightMap.put(neighbor, pathWeight);
                }
            });


        }while(!(queue.isEmpty()));
        printShortestPath(targetNode, true);

        if (path.containsKey(targetNode)){
            visualizeShortestPath(shortestPath(targetNode));
        } else {
            System.out.println("No Shortest Path found");
        }

    }

    public List<Node> shortestPath(Node targetNode){
        List<Node> nodePath = new ArrayList<>();
        Node currentNode = targetNode;
        if(path.containsKey(targetNode)) {
            while (currentNode != null) {
                nodePath.addFirst(currentNode);
                currentNode = path.get(currentNode);
            }
        }
        return nodePath;
    }

    private void visualizeShortestPath(List<Node> nodeList){
        for(int i = 0; i < nodeList.size()-1; i++){
            Node node = nodeList.get(i);
            node.setAttribute("ui.style", "fill-color: green;");
            node.getEdgeBetween(nodeList.get(i+1)).setAttribute("ui.style", "fill-color: blue;" );
        }
        nodeList.get(nodeList.size()-1).setAttribute("ui.style", "fill-color: green;");
    }


    private void printShortestPath(Node targetNode, boolean weighted) {
        List<Node> nodeList = shortestPath(targetNode);
        if (!nodeList.isEmpty()) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < nodeList.size(); i++) {
                str.append(String.format("%s", nodeList.get(i)));
                if (i < nodeList.size() - 1) {
                    str.append(" -> ");
                }
            }
            if (weighted) {
                str.append("\n");
                for (int i = 0; i < nodeList.size() - 1; i++) {
                    Node node = nodeList.get(i);
                    String weight = node.getEdgeBetween(nodeList.get(i + 1)).getAttribute("edgeWeight").toString();
                    Double edgeWeight = Double.parseDouble(weight);
                    str.append(String.format("%s -> %s: %.2f \n", nodeList.get(i), nodeList.get(i + 1), edgeWeight));
                }
                str.append(String.format("Totale Weglänge von %s -> %s beträgt: %.2f", nodeList.get(0), nodeList.get(nodeList.size() - 1),
                        edgeWeightMap.get(targetNode)));
            }
            System.out.println("Kürzester Pfad: " + str);
            System.out.printf("Anzahl benötiger Kanten: %d \n", nodeList.size() - 1);
        }
    }

//public static void bfs(Graph graph, String start, String target){
//    Node startNode = graph.getNode(start);
//    Node targetNode = graph.getNode(target);
//
//    Queue<Node> queue = new ArrayDeque<>();
//    Set<Node> visited = new HashSet<>();
//    Map<Node, Node> path = new HashMap<>();
//
//    path.put(startNode, null);
//    StringBuilder traversedPath = new StringBuilder(startNode.getId());
//
//    queue.add(startNode);
//    visited.add(startNode);
//
//    while (!(queue.isEmpty() && path.containsKey(targetNode))){
//        Node currentNode = queue.poll();
//        LinkedList<Node> neighbors = currentNode.neighborNodes()
//                .filter(node -> !visited.contains(node))
//                .collect(Collectors.toCollection(LinkedList::new));
//        if (neighbors.contains(targetNode)){
//            traversedPath.append(targetNode.getId());
//            System.out.println("Endknoten erreicht! Pfad = ");
//            return;
//        }
//        for (Node neighbor : neighbors){
//            path.put(neighbor, currentNode);
//            if (!visited.contains(neighbor)) {
//                queue.add(neighbor);
//                visited.add(neighbor);
//            }
//        }
//    }
//}
}