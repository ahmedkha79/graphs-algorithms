package a1;


import org.graphstream.graph.Graph;
import utils.ResourceLoader;


import java.io.IOException;
import java.net.URISyntaxException;

import static utils.GraphParser.parseFromFile;
import static utils.GraphVisuals.getStyleSheet;
import static utils.GraphVisuals.setStylesheetForGraph;

public class GraphApplication {





    public static void main(String[] args) throws IOException, URISyntaxException {
        setProperty(args[0]);
        String path3 = ResourceLoader.getResourcePath(args[1]).toString();
        Graph graph = parseFromFile(path3);
        setStylesheetForGraph(graph, getStyleSheet());
        BFS bfs = new BFS(graph);
        bfs.weightedSearch("Luebeck", "Luebeck");
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
