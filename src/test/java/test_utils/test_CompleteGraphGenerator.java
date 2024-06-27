package test_utils;

import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;
import utils.CompleteGraphGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class test_CompleteGraphGenerator {

    private static final int NODE100 = 100;
    private static final int EDGE1000 = 1000;

    @Test
    public void testGenerator_10_Success(){
        Graph graph = CompleteGraphGenerator.generateCompleteRandomGraph(5, 10);
        assertEquals(graph.getNodeCount(), 5);
        assertEquals(graph.getEdgeCount(), 10);
    }

    @Test
    public void testGenerator_100_Success(){
        Graph graph = CompleteGraphGenerator.generateCompleteRandomGraph(NODE100, EDGE1000);
        assertTrue(graph.isStrict());
        assertEquals(graph.getEdgeCount(), EDGE1000);
        assertEquals(graph.getNodeCount(), NODE100);
    }
}
