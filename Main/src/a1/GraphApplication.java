package a1;


import org.graphstream.graph.Graph;


import static utils.GraphParser.parseFromFile;
import static utils.GraphVisuals.getStyleSheet;
import static utils.GraphVisuals.setStylesheetForGraph;

public class GraphApplication {

    static final String PATHGRAPH02 =  "Main/resources/graph02.gka";
    static final String PATHGRAPHNOTFOUND =  "Main/resources/testen.gka";


    public static void main(String[] args) {
        setProperty(args[0]);
       // String file = "Main/resources/"+(args[1])+".gka";
        Graph graph = parseFromFile(PATHGRAPH02);
        setStylesheetForGraph(graph, getStyleSheet());
        BFS bfs = new BFS(graph);
        graph.display();
        bfs.bfs("a", "d");


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
