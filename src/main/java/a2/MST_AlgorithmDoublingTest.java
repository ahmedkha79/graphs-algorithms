package a2;

import org.graphstream.graph.Graph;
import utils.GraphGenerator;

public class MST_AlgorithmDoublingTest {

    public static void main(String[] args) {
        int repeat = Integer.parseInt(args[0]);
        int numNodes = Integer.parseInt(args[1]);
        int numEdges = Integer.parseInt(args[2]);
        int maxWeight = Integer.parseInt(args[3]);
        double kruskalTimes[] = new double[repeat];
        double primTimes[] = new double[repeat];

        Graph graph = GraphGenerator.generateRandomGraph(numNodes, numEdges, maxWeight);
        runTest(graph, 0, kruskalTimes, 1);
        runTest(graph, 0, primTimes, 2);
        String kruskal = "Kruskal";
        String prim = "Prim";

        for(int i = 1; i < repeat; i++){
                numNodes += numNodes;
                numEdges += numEdges;
                graph = GraphGenerator.generateRandomGraph(numNodes, numEdges, maxWeight);
                runTest(graph, i, kruskalTimes, 1);
                runTest(graph, i, primTimes, 2);
                System.out.printf("Nodes: %7d  Edges: %7d | KruskalTime: %6.1f, KruskalTime-ratio: %5.3f; sumWeight: %7d | PrimTime : %6.1f, PrimTime-ratio: %5.3f; " +
                                " sumWeight: %7d |\n",
                        numNodes, numEdges, kruskalTimes[i], kruskalTimes[i] / kruskalTimes[i-1], Kruskal.getSumWeight(),
                               primTimes[i], primTimes[i] / primTimes[i-1], Prim.getSumWeight());
        }


    }

    private static void runTest(Graph graph, int time, double[] times, int type){
        Graph algorithmGraph;

        double start = System.currentTimeMillis();
        if(type == 1){
            algorithmGraph = Kruskal.kruskalAlgorithmus(graph);
        } else if(type == 2){
            algorithmGraph = Prim.primAlgorithmus(graph);
        } else {
            throw new IllegalArgumentException("Unknown algorithm");
        }
        double end = System.currentTimeMillis();

        times[time] = end-start;
    }



}
