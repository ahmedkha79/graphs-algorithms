package test_a1;

import a1.BFS;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ResourceLoader;
import static utils.GraphParser.parseFromFile;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class test_BFS {

    final String PATHGRAPH02 = ResourceLoader.getResourcePath("graph02.gka").toString();
    final String PATHGRAPH03 = ResourceLoader.getResourcePath("graph03.gka").toString();

    final String PATHGRAPH08 = ResourceLoader.getResourcePath("graph08.gka").toString();
    final String PATHGRAPH11 = ResourceLoader.getResourcePath("graph11.gka").toString();

    Graph graph02;
    Graph graph08;
    Graph graph11;

    public test_BFS() throws URISyntaxException {
    }

    @BeforeEach
    public void initialize(){
        graph02 = parseFromFile(PATHGRAPH02);
        graph08 = parseFromFile(PATHGRAPH08);
        graph11 = parseFromFile(PATHGRAPH11);
    }

    @Test
    public void test_UndirectedBFSWrongTargetNode(){
        BFS testBFS = new BFS(graph02);
        assertThrows(NullPointerException.class, () -> testBFS.search("a", "zz"));
    }

    @Test
    public void test_graph08_BFS_Success_NotWeighted(){
        BFS bfs = new BFS(graph08);
        bfs.search("v1","v16" );
        List<Node> nodeList = bfs.shortestPath(graph08.getNode("v16"));
        assertEquals(nodeList.size(), 4);



    }

    @Test
    public void test_DirectedBFS_Success_NotWeighted(){
        BFS bfs = new BFS(graph11);
        bfs.search("v1", "v11");
        assertEquals(bfs.shortestPath(graph11.getNode("v11")).size(), 2);
    }

    @Test
    public void test_DirectedBFS_Success_Weighted(){
        BFS bfs = new BFS(graph11);
        bfs.weightedSearch("v1", "v11");
        assertEquals(bfs.shortestPath(graph11.getNode("v11")).size(),3);
    }

    @Test
    public void test_NoPathFound_BFS_Weighted(){
        BFS bfs = new BFS(graph11);
        bfs.weightedSearch("v1", "v13");
        assertEquals(bfs.shortestPath(graph11.getNode("v13")).size(),0);
    }


}
