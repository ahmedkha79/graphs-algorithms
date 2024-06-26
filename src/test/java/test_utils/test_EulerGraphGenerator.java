package test_utils;

import org.graphstream.graph.Graph;
import org.junit.Test;
import utils.EulerGraphGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class test_EulerGraphGenerator {

    @Test
    public void test_EulerGraph(){
        Graph eulerGraph = EulerGraphGenerator.createEulerGraph(10);
        assertEquals(10, eulerGraph.getNodeCount());
        assertTrue(eulerGraph.nodes().allMatch(node -> node.getDegree() % 2 == 0));

    }

    @Test
    public void test_EulerGraph_Larger(){
        Graph eulerGraph = EulerGraphGenerator.createEulerGraph(1000);
        assertEquals(1000, eulerGraph.getNodeCount());
        assertTrue(eulerGraph.nodes().allMatch(node -> node.getDegree() % 2 == 0));

    }
}
