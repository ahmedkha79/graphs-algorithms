package test_a3;

import a3.Fleury;
import a3.Hierholzer;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EulerGraphGenerator;
import utils.GraphParser;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class TestHielholzerAlgorithmus {
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
        Graph graph = EulerGraphGenerator.createEulerGraph(100);
        List<String> hierholzer = Hierholzer.findeEulerkreis(graph);
        assertEquals(graph.getEdgeCount(), hierholzer.size() -1);

    }
    @Test
    public void test_HierholzerAlgorithm_Fail() throws URISyntaxException {
        String path = ResourceLoader.getResourcePath("testen2.gka").toString();
        Graph graph = GraphParser.parseFromFile(path);

        List<String> erg = Hierholzer.findeEulerkreis(graph);
        assertNull(erg);
    }
}

