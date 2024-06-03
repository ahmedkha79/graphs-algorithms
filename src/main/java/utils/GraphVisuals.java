package utils;

import org.graphstream.graph.Graph;

public class GraphVisuals {

    //Visual Sheet for Graph
    private static final String STYLESHEET = "node { " +
            "size: 12px, 10px; shape: circle;" +
            "text-size: 13px;" +
            "text-background-mode: rounded-box;" +
            "text-alignment: at-right;" +
            "text-offset: -15px, 12px;" +
            "text-style: bold;" +
            "text-padding: 2px, 2px;"+
            "fill-color: black;" +
            "}" +
            "node:clicked {" +
            "fill-color: red;"+
            "}" +
            "edge{" +
            "text-size: 10px;" +
            "text-mode: normal;"+
            "text-alignment: along;"+
            "text-style: bold;"+
            "}"
            ;

public static void displayGraph(String property, Graph graph){
    setProperty(property);
    setStylesheetForGraph(graph, STYLESHEET);
    graph.display();
}


    public static String getStyleSheet(){
        return STYLESHEET;
    }

    public static void setStylesheetForGraph(Graph graph, String styleSheet){
        graph.setAttribute("ui.stylesheet", STYLESHEET);
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
