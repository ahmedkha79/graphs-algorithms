package a3;

import a1.BFS;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Fleury {

    public static List<Edge> searchEulerTour(Graph graph){
        List<Edge> eulerTour = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        Graph graphClone = Graphs.clone(graph);
        Node current = graphClone.getNode(0);

        while (!stack.isEmpty() || current.getDegree() > 0){
            if(current.getDegree() == 0){
                current = stack.pop();
            }
            else {
                Edge nextEdge;
                if(current.getDegree() == 1){
                    nextEdge = current.getEdge(0);
                } else {
                stack.push(current);
                nextEdge = getValidNextEdge(graphClone, current);
                }
                if(nextEdge != null) {
                    eulerTour.add(nextEdge);
                    Node nextNode = nextEdge.getOpposite(current);
                    graphClone.removeEdge(nextEdge.getId());
                    current = nextNode;
                }
            }
        }

        return eulerTour;
    }

    private static Edge getValidNextEdge(Graph graph, Node node){
        return node.edges().filter(edge -> isNotBridge(graph, edge)).findFirst().orElse(null);
    }

    private static boolean isNotBridge(Graph graph, Edge edge){
        Node source = edge.getNode0();
        Node target = edge.getNode1();

        graph.removeEdge(edge);
        BFS bfs = new BFS(graph);
        boolean connected = bfs.search(source.getId(), target.getId());

        graph.addEdge(edge.getId(), source, target);

        return connected;
    }


}
