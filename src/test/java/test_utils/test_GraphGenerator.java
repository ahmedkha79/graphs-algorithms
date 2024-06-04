package test_utils;

import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.GraphGenerator;

public class test_GraphGenerator {

    private static final int NODE100 = 100;
    private static final int NODE1500 = 1500;

    private static final int EDGE4000 = 4000;
    private static final int EDGE500 = 500;
    private static final int MAXWEIGHT = 99;


    @Test
    public void testGraphGenerator_Success(){
        Graph testGraph = GraphGenerator.generateRandomGraph(NODE100, EDGE500, MAXWEIGHT);
        assertEquals(testGraph.getNodeCount(), NODE100);
        assertEquals(testGraph.getEdgeCount(), EDGE500);
        assertTrue(testGraph.edges().map(edge -> edge.getNumber("edgeWeight"))
                .allMatch(edge -> edge <= MAXWEIGHT));
    }

    @Test
    public void testGraphGenerator_Larger_Success(){
        Graph testGraph = GraphGenerator.generateRandomGraph(NODE1500, EDGE4000, MAXWEIGHT);
        assertEquals(testGraph.getNodeCount(), NODE1500);
        assertEquals(testGraph.getEdgeCount(), EDGE4000);
        assertTrue(testGraph.edges().map(edge -> edge.getNumber("edgeWeight"))
                .allMatch(edge -> edge <= MAXWEIGHT));
    }

    @Test
    public void testGraphGenerator_Fail(){
        int maxEdges = ((NODE100 * (NODE100-1))/2) + 1;
        assertThrows(IllegalArgumentException.class, ()-> GraphGenerator.generateRandomGraph(NODE100, maxEdges, MAXWEIGHT));
    }
}
