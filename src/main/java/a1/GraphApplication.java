package a1;


import org.graphstream.graph.Graph;
import utils.ResourceLoader;


import java.io.IOException;
import java.net.URISyntaxException;

import static utils.GraphParser.parseFromFile;
import static utils.GraphVisuals.getStyleSheet;
import static utils.GraphVisuals.setStylesheetForGraph;

public class GraphApplication {

    static final String PATHGRAPH02 =  "Main/resources/graph02.gka";
    static final String PATHGRAPHNOTFOUND =  "Main/resources/testen.gka";




    public static void main(String[] args) throws IOException, URISyntaxException {
        setProperty(args[0]);
        String path2 = ResourceLoader.getResourcePath("graph02.gka").toString();
        String path3 = ResourceLoader.getResourcePath("graph03.gka").toString();
       // String file = "Main/resources/"+(args[1])+".gka";
        Graph graph = parseFromFile(path3);
        setStylesheetForGraph(graph, getStyleSheet());
        BFS bfs = new BFS(graph);
        bfs.weightedBFS("Luebeck", "Cuxhaven");
        graph.display();


    }


    private static void setProperty(String visual){

        switch (visual.toLowerCase()){
            case "swing" -> {
                System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");
            }
            case "javafx" -> {
                System.setProperty("org.graphstream.ui", "javafx");
                System.setProperty("org.graphstream.debug", "true");
            }default -> {
                throw new IllegalArgumentException("Invalid or not supported visualization");}
        }
    }

}
