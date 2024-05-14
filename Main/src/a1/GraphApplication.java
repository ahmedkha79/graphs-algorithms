package a1;


import org.graphstream.graph.*;
import static utils.GraphParser.parseFromFile;
import static utils.GraphVisuals.setStylesheetForGraph;
import static utils.GraphVisuals.getStyleSheet;
import org.graphstream.ui.javafx.util.Display;

public class GraphApplication {

    static final String PATHGRAPH02 =  "Main/resources/graph02.gka";

    public static void main(String[] args) {
        setProperty(args[0]);
        Graph graph = parseFromFile(PATHGRAPH02);
        setStylesheetForGraph(graph, getStyleSheet());
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
