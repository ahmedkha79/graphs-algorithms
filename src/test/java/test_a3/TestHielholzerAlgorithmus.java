package test_a3;

import a3.Hierholzer;
import org.graphstream.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EulerGraphGenerator;
import utils.GraphParser;
import utils.ResourceLoader;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestHielholzerAlgorithmus {
    private Hierholzer algorithmus;
    @BeforeEach
    void setUp() {
        algorithmus = new Hierholzer();
    }

    @Test
    public void test_HierholzerAlgorithm_Success() throws URISyntaxException {
        String path = ResourceLoader.getResourcePath("testen.gka").toString();
        Graph graph = GraphParser.parseFromFile(path);

        List<String> erg = algorithmus.findEulerCircle(graph);
        assertNotNull(erg);
        assertFalse(erg.isEmpty());

    }

    @Test
    public void test_HierholzerAlgorithm_EulerianGraph_Success(){
        Graph graph = EulerGraphGenerator.createEulerGraph(100);

    }
    @Test
    public void test_HierholzerAlgorithm_Fail() throws URISyntaxException {
        String path = ResourceLoader.getResourcePath("testen2.gka").toString();
        Graph graph = GraphParser.parseFromFile(path);

        List<String> erg = algorithmus.findEulerCircle(graph);
        assertNull(erg);
    }
}

