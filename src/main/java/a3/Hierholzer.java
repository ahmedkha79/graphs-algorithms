package a3;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import utils.GraphParser;
import utils.GraphVisuals;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hierholzer {


    public List<String> findeEulerkreis(Graph graph) {
        // Überprüfe, ob der Graph einen Eulerkreis haben kann
        if (!checkEulerian(graph)) {
            System.err.println("Der Graph hat keine Eulerkreis, da mindestens ein Knoten eine ungerade Anzahl von Kanten hat.");
            return null;
        }

        List<String> eulerKreis = new ArrayList<>();
        Map<String, LinkedList<String>> edges = new HashMap<>();
        LinkedList<String> tour = new LinkedList<>();

        // Kopiere die Kanten des Graphen, um sie später zu entfernen
        for (Node node : graph) {
            LinkedList<String> adjacentNodes = node.leavingEdges()
                    .map(edge -> edge.getOpposite(node).getId())
                    .collect(Collectors.toCollection(LinkedList::new));
            edges.put(node.getId(), adjacentNodes);
        }

        // Wähle einen Startknoten
        String startNode = graph.getNode(0).getId();
        tour.add(startNode);

        while (!tour.isEmpty()) {
            String v = tour.getLast();
            if (!edges.get(v).isEmpty()) {
                String w = edges.get(v).removeFirst();
                edges.get(w).remove(v); // Entferne die rückwärtsgerichtete Kante
                tour.add(w);
            } else {
                eulerKreis.add(tour.removeLast());
            }
        }

        // Drucke den gefundenen Eulerkreis
        System.out.println("Eulerkreis: " + eulerKreis);
        return eulerKreis;
    }

    private boolean checkEulerian(Graph graph) {
        for (Node node : graph) {
            if (node.getDegree() % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws URISyntaxException {
        String path3 = ResourceLoader.getResourcePath("testen.gka").toString();
        Graph graph = GraphParser.parseFromFile(path3);
        Hierholzer h = new Hierholzer();
        h.findeEulerkreis(graph);
        GraphVisuals.displayGraph("javafx", graph);

    }
}