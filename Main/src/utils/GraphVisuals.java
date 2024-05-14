package utils;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.awt.*;

public class GraphVisuals {

    public static void modifyGraphVisuals(Graph graph){
        for(Node node : graph){
            node.setAttribute("ui.style","text-alignment: at-left;" + "text-size: 25;" + "fill-color:red;");
        }
    }




}
