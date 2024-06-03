package a2;

import org.graphstream.algorithm.util.DisjointSets;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import utils.GraphParser;
import utils.GraphVisuals;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Kruskal {
    private static double sumWeight;

    public static Graph kruskalAlgorithmus(Graph graph) {
        Graph mst = new MultiGraph("mst");
        sumWeight = 0;

        //Kanten werden nach Gewichten sortiert
        List<Edge> edges = graph.edges().sorted(Comparator.comparing(edge -> edge.getNumber("edgeWeight"))).collect(Collectors.toCollection(ArrayList::new));

        //Füge Knoten dem minimalen Spannbaum hinzu
        graph.nodes().forEach(node -> mst.addNode(node.getId()).setAttribute("ui.label", node.getId()));

        //Menge über disjunkten Mengen, Identifikation von Kreisen
        DisjointSets<Node> disjointSets = new DisjointSets<>();

        graph.nodes().forEach(disjointSets::add);

        while(!edges.isEmpty() && (mst.getEdgeCount() != (mst.getNodeCount())-1)){
            Edge edge = edges.removeFirst();
            if(!disjointSets.inSameSet(edge.getNode0(), edge.getNode1())){
                Edge mstEdge = mst.addEdge(edge.getId(), edge.getNode0().getId(), edge.getNode1().getId());
                mstEdge.setAttribute("edgeWeight", edge.getAttribute("edgeWeight"));
                mstEdge.setAttribute("ui.label", edge.getAttribute("edgeWeight"));
                sumWeight += mstEdge.getNumber("edgeWeight");
                disjointSets.union(edge.getNode0(), edge.getNode1());
            }
        }

        return mst;
    }



    public static void main(String[] args) throws URISyntaxException {
        String path3 = ResourceLoader.getResourcePath("graph12.gka").toString();
        Graph graph = GraphParser.parseFromFile(path3);
        Graph kruskal = kruskalAlgorithmus(graph);
        GraphVisuals.displayGraph("javafx", kruskal);



    }

}
