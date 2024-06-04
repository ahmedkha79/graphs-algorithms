package test_a2;

import a2.Kruskal;
import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.GraphGenerator;
import utils.GraphParser;
import utils.ResourceLoader;
import static a2.Kruskal.*;

import java.net.URISyntaxException;

public class test_MinimumSpanningTree_Algorithm {

    private static final int NODE100 = 100;

    private static final int NODE1000 = 1000;

    private static final int EDGE5000 = 5000;
    private static final int EDGE500 = 500;
    private static final int EDGEWEIGHT = 75;
    private static final String GRAPH02FILE = "graph02.gka";

    @Test
    public void testKruskalAlgorithm_Success(){
        Graph randomGraph = GraphGenerator.generateRandomGraph(NODE100, EDGE500, EDGEWEIGHT);
        Graph kruskalRandomGraph = kruskalAlgorithmus(randomGraph);

        assertNotNull(kruskalRandomGraph);
        assertEquals(kruskalRandomGraph.getNodeCount(), NODE100);
        assertEquals(kruskalRandomGraph.getNodeCount(), kruskalRandomGraph.getEdgeCount()+1);
    }

    @Test
    public void testKruskalAlgorithm_Larger_Success(){
        Graph randomGraph = GraphGenerator.generateRandomGraph(NODE1000, EDGE5000, EDGEWEIGHT);
        Graph kruskalRandomGraph = kruskalAlgorithmus(randomGraph);

        assertNotNull(kruskalRandomGraph);
        assertEquals(kruskalRandomGraph.getNodeCount(), NODE1000);
        assertEquals(kruskalRandomGraph.getNodeCount(), kruskalRandomGraph.getEdgeCount()+1);
    }

    @Test
    public void testKruskalAlgorithm_unweighted_Fail(){
        try{
            String path02 = ResourceLoader.getResourcePath(GRAPH02FILE).toString();
            Graph graph = GraphParser.parseFromFile(path02);

            assertThrows(IllegalArgumentException.class, () -> kruskalAlgorithmus(graph));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
