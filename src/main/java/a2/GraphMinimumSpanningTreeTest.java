package a2;

import org.graphstream.graph.Graph;
import utils.GraphGenerator;
import utils.GraphVisuals;

public class GraphMinimumSpanningTreeTest {

    public static void main(String[] args) {
        Graph smallGraph = GraphGenerator.generateRandomGraph(10, 20, 35);


        //-----------------------Small Sized Graph Test-----------------------------
        //GraphVisuals.setStylesheetForGraph(smallGraph, GraphVisuals.getStyleSheet());

        Graph kruskalSmallGraph = Kruskal.kruskalAlgorithmus(smallGraph);
        //GraphVisuals.displayGraph("javafx", kruskalSmallGraph);
        System.out.println("SmallGraph: \n" + Kruskal.getSumWeight());


        //-----------------------Medium Sized Graph Test-----------------------------
        Graph mediumGraph = GraphGenerator.generateRandomGraph(500, 1000, 150);
        Graph kruskalMediumGraph = Kruskal.kruskalAlgorithmus(mediumGraph);
        //GraphVisuals.displayGraph("javafx", kruskalMediumGraph);
        System.out.println("MediumGraph: \n" + Kruskal.getSumWeight());


        //------------------------Big Sized Graph Test-----------------------------------
        Graph bigGraph = GraphGenerator.generateRandomGraph(10000, 12000, 200);
        Graph kruskalBigGraph = Kruskal.kruskalAlgorithmus(bigGraph);
        System.out.println("BigGraph: \n" + Kruskal.getSumWeight());
        System.out.println(kruskalBigGraph.getNodeCount() + "\n");
        System.out.println(kruskalBigGraph.getEdgeCount() + "\n");

        //------------------------very Big Sized Graph Test-----------------------------------
        Graph veryBigGraph = GraphGenerator.generateRandomGraph(100000, 500000, 15000);
        Graph kruskalVeryBigGraph = Kruskal.kruskalAlgorithmus(veryBigGraph);
        System.out.println("veryBigGraph: \n" + Kruskal.getSumWeight());


        //------------------------very very Big Sized Graph Test-------------------------------
        Graph veryVeryBigGraph = GraphGenerator.generateRandomGraph(900000, 500000, 20000);
        Graph kruskalVeryVeryBigGraph = Kruskal.kruskalAlgorithmus(veryVeryBigGraph);
        System.out.println("veryVeryBigGraph: \n" + Kruskal.getSumWeight());
    }
}
