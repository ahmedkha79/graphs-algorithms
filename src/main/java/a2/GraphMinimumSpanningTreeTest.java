package a2;

import org.graphstream.graph.Graph;
import utils.GraphGenerator;
import utils.GraphVisuals;

public class GraphMinimumSpanningTreeTest {

    public static void main(String[] args) {



        //-----------------------Small Sized Graph Test-----------------------------
        //GraphVisuals.setStylesheetForGraph(smallGraph, GraphVisuals.getStyleSheet());
        Graph smallGraph = GraphGenerator.generateRandomGraph(10, 20, 35);
        Graph kruskalSmallGraph = Kruskal.kruskalAlgorithmus(smallGraph);
        Graph primSmallGraph = Prim.primAlgorithmus(smallGraph);
        //GraphVisuals.displayGraph("javafx", kruskalSmallGraph);
        System.out.printf("%-25s %9d \n", "Kruskal - SmallGraph: ", Kruskal.getSumWeight());
        System.out.printf("%-25s %9d \n", "Prim - SmallGraph: ", Prim.getSumWeight());


        //-----------------------Medium Sized Graph Test-----------------------------
        Graph mediumGraph = GraphGenerator.generateRandomGraph(500, 1000, 150);
        Graph kruskalMediumGraph = Kruskal.kruskalAlgorithmus(mediumGraph);
        Graph primMediumGraph = Prim.primAlgorithmus(mediumGraph);
        //GraphVisuals.displayGraph("javafx", kruskalMediumGraph);
        System.out.printf("%-25s %9d \n", "Kruskal - MediumGraph:  ", Kruskal.getSumWeight());
        System.out.printf("%-25s %9d \n", "Prim - MediumGraph:  ", Prim.getSumWeight());


        //------------------------Big Sized Graph Test-----------------------------------
        Graph bigGraph = GraphGenerator.generateRandomGraph(10000, 20000, 15000);
        Graph kruskalBigGraph = Kruskal.kruskalAlgorithmus(bigGraph);
        Graph primBigGraph = Prim.primAlgorithmus(bigGraph);
        System.out.printf("%-25s %9d \n","Kruskal - BigGraph: ", Kruskal.getSumWeight());
        System.out.printf("%-25s %9d \n","Prim - BigGraph: ", Prim.getSumWeight());

        //------------------------very Big Sized Graph Test-----------------------------------
        Graph veryBigGraph = GraphGenerator.generateRandomGraph(100000, 500000, 15000);
        Graph kruskalVeryBigGraph = Kruskal.kruskalAlgorithmus(veryBigGraph);
        Graph primVeryBigGraph = Prim.primAlgorithmus(veryBigGraph);
        System.out.printf("%-25s %9d \n","Kruskal - VeryBigGraph: ", Kruskal.getSumWeight());
        System.out.printf("%-25s %9d \n","Prim - VeryBigGraph: ", Prim.getSumWeight());


        //------------------------very very Big Sized Graph Test-------------------------------
        Graph veryVeryBigGraph = GraphGenerator.generateRandomGraph(900000, 500000, 20000);
        Graph kruskalVeryVeryBigGraph = Kruskal.kruskalAlgorithmus(veryVeryBigGraph);
        Graph primVeryVeryBigGraph = Prim.primAlgorithmus(veryVeryBigGraph);
        System.out.printf("%-25s %9d \n","Kruskal - VeryVeryBigGraph: ", Kruskal.getSumWeight());
        System.out.printf("%-25s %9d \n","Prim - VeryVeryBigGraph: ", Prim.getSumWeight());
    }
}
