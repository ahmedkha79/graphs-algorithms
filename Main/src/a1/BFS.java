package a1;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.*;
import java.util.stream.Collectors;

public class BFS {
    Graph graph;

    Queue<Node> queue;
    Set<Node> visited;
    Map<Node, Node> path;


    public BFS(Graph graph) {
        this.graph = graph;
        queue = new ArrayDeque<>();
        visited = new HashSet<>();
        path = new HashMap<>();
    }

    public void bfs(String start, String target){
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
        if(path.containsKey(targetNode)){
                printShortestPath(targetNode);
        } else {
            System.out.println("No Path found");
        }
    }


    private void printShortestPath(Node targetNode){
        Node currentNode = targetNode;
        StringBuilder pathErg = new StringBuilder();
        while(currentNode != null){
            pathErg.append("%s ".formatted(currentNode.getId()));
            Node prevNode = path.get(currentNode);
            currentNode = prevNode;
        }
        System.out.println(pathErg.reverse());
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