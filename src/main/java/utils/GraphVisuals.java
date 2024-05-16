package utils;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.awt.*;

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
            "text-size: 7px;" +
            "text-mode: normal;"+
            "text-alignment: along;"+
            "text-style: bold;"+
            "}"
            ;



    public static String getStyleSheet(){
        return STYLESHEET;
    }

    public static void setStylesheetForGraph(Graph graph, String styleSheet){
        graph.setAttribute("ui.stylesheet", STYLESHEET);
    }




}
