package test_utils;


import a1.BFS;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.jupiter.api.Test;
import utils.GraphParser;
import utils.ResourceLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static utils.GraphParser.parseFromFile;
import static utils.GraphParser.saveGraphToFile;

public class test_GraphParser {

    final String PATHGRAPH01 = ResourceLoader.getResourcePath("graph01.gka").toString();
    final String PATHGRAPH02 = ResourceLoader.getResourcePath("graph02.gka").toString();

    final String PATHGRAPH05 = ResourceLoader.getResourcePath("graph05.gka").toString();

    final String PATHGRAPH08 = ResourceLoader.getResourcePath("graph08.gka").toString();


    final String PATHDIRECTEDGRAPH01 = ResourceLoader.getResourcePath("directedGraph01.gka").toString();
    private static final Pattern PATTERN = Pattern.compile("(\\w+\\s*)(--|->)(\\s*\\w+)\\s*(\\(\\w+\\))?(?::(\\d+))?(?:\\s*;)");

    public test_GraphParser() throws URISyntaxException {
    }


    @Test
    public void testReadFile() {
        assertDoesNotThrow(() -> parseFromFile(PATHGRAPH02));

    }

    @Test
    public void testGraphId()  {
        Graph graph = parseFromFile(PATHGRAPH02);
        assertEquals(graph.getId(), "graph02");
    }

    @Test
    public void testParseCorruptedGraph01() {
      assertThrows(IllegalArgumentException.class, () -> {
          parseFromFile(PATHGRAPH01);
        });
    }


    @Test
    public void testParseGraph02()  {
        Graph graph = parseFromFile(PATHGRAPH02);
        assertEquals(11, graph.getNodeCount());
        assertEquals(38, graph.getEdgeCount());
        assertEquals("graph02", graph.getId());

    }



    @Test
    void testParseGraph05() {
        assertThrows(IllegalArgumentException.class, () -> {
            parseFromFile(PATHGRAPH05);
        });
    }

    @Test
    void testParseGraph08(){
        Graph graph = parseFromFile(PATHGRAPH08);
        int nodeCount = graph.getNodeCount();
        int edgeCount = graph.getEdgeCount();
        assertEquals(nodeCount, 16);
        assertEquals(edgeCount, 15);
        assertTrue(graph.edges().toList().getFirst().hasAttribute("edgeWeight"));
    }

    @Test
    //Duplicate Line
    void testParseDirectedGraph01(){
        Graph graph = parseFromFile(PATHDIRECTEDGRAPH01);
        int nodeCount = graph.getNodeCount();
        int edgeCount = graph.getEdgeCount();
        Edge edge = graph.edges().toList().getFirst();
        assertEquals(nodeCount, 7);
        assertEquals(edgeCount, 10);
        assertTrue(edge.isDirected());
        assertTrue(edge.hasAttribute("edgeWeight"));
    }

    @Test
    public void saveGraph02_checkFormat(){
        Graph graph = parseFromFile(PATHGRAPH02);
        String fileName = "SavedGraph02";
        saveGraphToFile(fileName, graph, false);

        try(BufferedReader reader = new BufferedReader(new FileReader("SavedGraph02.gka"))){
            String line = reader.readLine();
            assertTrue(line.matches(PATTERN.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void saveGraphMixedDirections(){
        Graph graph = new MultiGraph("Graph01", false, true);
        Node node1 = graph.addNode("Node1");
        Node node2 = graph.addNode("Node2");
        Node node3 = graph.addNode("Node3");
        graph.addEdge("directed", node1, node2, true);
        graph.addEdge("undirected", node1, node3);
        assertThrows(IllegalArgumentException.class, ()-> saveGraphToFile("CorruptedGraph01", graph, false));
    }

    @Test
    public void saveGraphWithAllAttributes(){
        Graph graph = parseFromFile(PATHDIRECTEDGRAPH01);
        String savedDirectedGraph = "savedDirectedGraph01";
        saveGraphToFile(savedDirectedGraph, graph, true);
        try(BufferedReader reader = new BufferedReader(new FileReader(savedDirectedGraph+".gka"))){
            String line = reader.readLine();
            Matcher matcher = PATTERN.matcher(line);
            if(matcher.find()) assertTrue(matcher.groupCount() == 5);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
