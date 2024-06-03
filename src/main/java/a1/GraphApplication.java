package a1;


import org.graphstream.graph.Graph;
import utils.ResourceLoader;


import java.io.IOException;
import java.net.URISyntaxException;

import static utils.GraphParser.parseFromFile;
import static utils.GraphVisuals.*;

public class GraphApplication {



    public static void main(String[] args) throws IOException, URISyntaxException {

        String path3 = ResourceLoader.getResourcePath(args[1]).toString();
        Graph graph = parseFromFile(path3);
        BFS bfs = new BFS(graph);
        bfs.weightedSearch("Luebeck", "Rotenburg");
        displayGraph(args[0], graph);


    }


}
