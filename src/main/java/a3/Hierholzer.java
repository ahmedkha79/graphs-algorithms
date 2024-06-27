package a3;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import utils.EulerGraphGenerator;
import utils.GraphParser;
import utils.GraphVisuals;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Hierholzer {


    public static List<String> findeEulerkreis(Graph graph) {

        if (!checkEulerian(graph)) {
            System.err.println("Der Graph hat keinen Eulerkreis, da mindestens ein Knoten eine ungerade Anzahl von Kanten hat.");
            return null;
        }

        Graph copiedGraph = Graphs.clone(graph);

        List<String> eulerKreis = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node startNode = copiedGraph.getNode(0);
        stack.push(startNode);

        while (!stack.isEmpty()) {
            Node currentNode = stack.peek();
            if (currentNode.getDegree() > 0) {
                // Wähle eine benachbarte Kante
                Edge edge = currentNode.getEdge(0);
                Node neighbor = edge.getOpposite(currentNode);
                // Entferne Kante aus dem Graphen
                copiedGraph.removeEdge(edge);
                // Füge Nachbarknoten zum Stack hinzu
                stack.push(neighbor);
            } else {
                // Keine benachbarten Knoten mehr, füge zum Kreis hinzu
                eulerKreis.add(currentNode.getId());
                stack.pop();
            }
        }
        System.out.println("Eulerkreis: " + eulerKreis);
        return eulerKreis;
    }

    private static boolean checkEulerian(Graph graph) {
        for (Node node : graph) {
            if (node.getDegree() % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws URISyntaxException {
        String path3 = ResourceLoader.getResourcePath("testen.gka").toString();
        Graph graph = EulerGraphGenerator.createEulerGraph(10);
        GraphVisuals.displayGraph("javafx", graph);
        List<String> hierholzer = findeEulerkreis(graph);
        List<Edge> fleuryEdges = Fleury.searchEulerTour(graph);
        System.out.println(hierholzer.size());
        System.out.println(fleuryEdges.size());


    }
}
