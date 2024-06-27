package test_a3;

import a3.Fleury;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;
import utils.EulerGraphGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

public class test_FleuryAlgorithmus {


    //Prüft ob Kantenfolge eine Eulerkreis beschreibt
    private boolean isValidEulerTour(List<Edge> edges){
        Edge startEdge = edges.get(0);
        Edge endEdge = edges.get(edges.size()-1);
        return startEdge.getSourceNode().equals(endEdge.getSourceNode()) || startEdge.getTargetNode().equals(endEdge.getSourceNode())
                || startEdge.getTargetNode().equals(endEdge.getSourceNode()) || startEdge.getTargetNode().equals(endEdge.getTargetNode());
    }

    @Test
    public void test_SimpleFleury(){
        Graph graph = EulerGraphGenerator.createEulerGraph(100);
        List<Edge> fleuryEdges = Fleury.searchEulerTour(graph);
        assertEquals(graph.getEdgeCount(), fleuryEdges.size());
        assertTrue(isValidEulerTour(fleuryEdges));
    }

    @Test
    public void test_LargerFleury(){
        Graph graph = EulerGraphGenerator.createEulerGraph(15000);
        List<Edge> fleuryEdges = Fleury.searchEulerTour(graph);
        assertEquals(graph.getEdgeCount(), fleuryEdges.size());
        assertTrue(isValidEulerTour(fleuryEdges));
    }

    @Test
    public void test_RandomEulerGraphs_Success(){
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int numNodes = rand.nextInt(10000) + 10;
            Graph randomEulerGraph = EulerGraphGenerator.createEulerGraph(numNodes);
            List<Edge> flueryEdges = Fleury.searchEulerTour(randomEulerGraph);
            assertTrue(isValidEulerTour(flueryEdges));
            assertEquals(randomEulerGraph.getEdgeCount(), flueryEdges.size());

        }
    }
}
