package test_utils;

import a1.BFS;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;
import utils.EulerGraphGenerator;
import utils.GraphParser;
import utils.GraphVisuals;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class test_EulerGraphGenerator {


    private boolean checkIsEulerianGraph(Graph graph){
        return graph.nodes().allMatch(node -> node.getDegree() % 2 == 0);
    }

    private boolean checkIfCohertGraph(Graph graph){
        Node target = graph.getNode(0);
        return graph.nodes().allMatch(node -> {
            BFS bfs = new BFS(graph);
            return bfs.search(node.getId(), target.getId());
        });
    }

    @Test
    public void test_checkForSmallGraph() throws URISyntaxException {
        Graph eulerGraph = GraphParser.parseFromFile(ResourceLoader.getResourcePath("eulerianGraph.gka").toString());
        assertTrue(checkIsEulerianGraph(eulerGraph));
    }

    @Test
    public void test_EulerGraph(){
        Graph eulerGraph = EulerGraphGenerator.createEulerGraph(100);
        assertEquals(10, eulerGraph.getNodeCount());
        assertTrue(checkIsEulerianGraph(eulerGraph));

    }

    @Test
    public void test_EulerGraph_Larger(){
        Graph eulerGraph = EulerGraphGenerator.createEulerGraph(1000);

        assertEquals(1000, eulerGraph.getNodeCount());
        assertTrue(checkIsEulerianGraph(eulerGraph));
        assertTrue(checkIfCohertGraph(eulerGraph));

    }

    @Test
    public void test_EulerianGraph_VeryLarger_Success(){
        Graph eulerGraph = EulerGraphGenerator.createEulerGraph(10000);

        assertEquals(10000, eulerGraph.getNodeCount());
        assertTrue(checkIsEulerianGraph(eulerGraph));
        assertTrue(checkIfCohertGraph(eulerGraph));
    }

    @Test
    public void testLargeRandomEulerGraphs(){
        Random random = new Random();
        for (int i = 0; i < 20; i++){
            int numNodes = random.nextInt(10000) + 10;
            Graph randomEulerGraph = EulerGraphGenerator.createEulerGraph(numNodes);
            assertTrue(checkIsEulerianGraph(randomEulerGraph));
        }
    }
}
