package test_a3;

import a3.Hierholzer;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.EulerGraphGenerator;
import utils.GraphParser;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class test_HierholzerAlgorithmus {
    private Hierholzer algorithmus;

    @Test
    public void test_HierholzerAlgorithm_Success() throws URISyntaxException {
        String path = ResourceLoader.getResourcePath("testen.gka").toString();
        Graph graph = GraphParser.parseFromFile(path);

        List<String> erg = Hierholzer.findeEulerkreis(graph);
        assertEquals(graph.getEdgeCount(), erg.size() -1);


    }

    @Test
    public void test_HierholzerAlgorithm_EulerianGraph_Success(){
        Graph graph = EulerGraphGenerator.createEulerGraph(15);
        List<String> hierholzer = Hierholzer.findeEulerkreis(graph);
        assertEquals(graph.getEdgeCount(), hierholzer.size()-1);
        assertTrue(isValidEulerTour(hierholzer));

    }

    @Test
    public void test_Hierholzer_MultipleGraphs_Success(){
        Random rand = new Random();
        for(int i = 0; i < 20; i++){
            int numNodes = rand.nextInt(10000) + 1;
            Graph randomEulerGraph = EulerGraphGenerator.createEulerGraph(numNodes);
            List<String> hierholzerEdges = Hierholzer.findeEulerkreis(randomEulerGraph);
            assert hierholzerEdges != null;
            assertTrue(isValidEulerTour(hierholzerEdges));
            assertEquals(randomEulerGraph.getEdgeCount(), hierholzerEdges.size()-1);
        }
    }
    @Test
    public void test_HierholzerAlgorithm_Fail() throws URISyntaxException {
        String path = ResourceLoader.getResourcePath("testen2.gka").toString();
        Graph graph = GraphParser.parseFromFile(path);

        List<String> erg = Hierholzer.findeEulerkreis(graph);
        assertNull(erg);
    }

    private boolean isValidEulerTour(List<String> nodes){
        return nodes.get(0).equals(nodes.get(nodes.size()-1));
    }
}

