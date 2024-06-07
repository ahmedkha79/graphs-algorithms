package a2;

import org.graphstream.algorithm.util.DisjointSets;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import utils.GraphParser;
import utils.GraphVisuals;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class Kruskal {
    private static int sumWeight;

    public static int getSumWeight() {
        return sumWeight;
    }

    public static Graph kruskalAlgorithmus(Graph graph) {
       if(!checkIfWeighted(graph.edges().toList())) throw new IllegalArgumentException("Edges not weighted");
        Graph mst = new SingleGraph("mst");
        sumWeight = 0;

        //Kanten werden nach Gewichten sortiert
        List<Edge> edges = graph.edges().sorted(Comparator.comparingDouble(edge -> edge.getNumber("edgeWeight"))).collect(Collectors.toCollection(LinkedList::new));

        //Füge Knoten dem minimalen Spannbaum hinzu
        graph.nodes().forEach(node -> mst.addNode(node.getId()).setAttribute("ui.label", node.getId()));

        //Menge über disjunkten Mengen, Identifikation von Kreisen
        DisjointSets<Node> disjointSets = new DisjointSets<>();

        graph.nodes().forEach(disjointSets::add);

        while(!edges.isEmpty() && (mst.getEdgeCount() != mst.getNodeCount()-1)){
            Edge edge = edges.removeFirst();

            if(!disjointSets.inSameSet(edge.getNode0(), edge.getNode1())){
                Edge mstEdge = mst.addEdge(edge.getId(), edge.getNode0().getId(), edge.getNode1().getId());

                mstEdge.setAttribute("edgeWeight", edge.getAttribute("edgeWeight"));
                mstEdge.setAttribute("ui.label", edge.getAttribute("edgeWeight"));

                sumWeight += (int) mstEdge.getNumber("edgeWeight");
                disjointSets.union(edge.getNode0(), edge.getNode1());
            }
        }

        return mst;
    }

    private static boolean checkIfWeighted(List<Edge> edges){
        return edges.stream().allMatch(edge -> edge.hasAttribute("edgeWeight"));
    }





    public static void main(String[] args) throws URISyntaxException {
        String path3 = ResourceLoader.getResourcePath("graph12.gka").toString();
        Graph graph = GraphParser.parseFromFile(path3);
        Graph kruskal = kruskalAlgorithmus(graph);
        GraphVisuals.displayGraph("javafx", kruskal);



    }

}
