package utils;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GraphParser {
    private static final Pattern PATTERN = Pattern.compile(
            "(\\w+\\s*)(--|->)(\\s*\\w+)\\s*(\\(\\w+\\))?(?::(\\d+))?(?:\\s*;)");
    //TODO ASK

    private static final String ABSOULTE_PATH_PROJECT = System.getProperty("user.dir");
    private static final String RELATIVE_OUTPUT_PATH = File.separator + "Main" + File.separator +
                                                    "savedGraphs" + File.separator;

    private static final String MIXED_PATH = ABSOULTE_PATH_PROJECT+ RELATIVE_OUTPUT_PATH;
    static final String path1 = File.separator + "gka_praktikum" + RELATIVE_OUTPUT_PATH;
    private static final String EXTENSION = ".gka";
   public static Graph parseFromFile(String path)  {

       Graph graph = new MultiGraph(extractNameFromFile(path),false, true);
       try(BufferedReader reader =  new BufferedReader(new FileReader(path))){

           //Duplikate erkennen / Mehrfachkanten werden ignoriert
           Set<String> lineList = reader.lines().filter(line -> !line.isEmpty())
                   .map(line -> line.replaceAll(" ", ""))
                   .collect(Collectors.toSet());

           boolean unknownDirection = true; //direction for first line unknown
           boolean directed = false; //default is undirected graph
           if(lineList.stream().allMatch(line -> line.matches(PATTERN.pattern()))){
               for(String line : lineList){
                    if(unknownDirection){
                        directed = line.contains("->");
                        unknownDirection = false;
                    } else if (directed != line.contains("->")) throw new IllegalArgumentException("Two different edge types used");
                    addNodes_EdgesToGraph(line, graph, directed);
               }

           } else {
               throw new IllegalArgumentException("Not supported file format");
           }
       }
       catch (IOException e){
           e.printStackTrace();
       }


      return graph;
   }


   private static void addNodes_EdgesToGraph(String line, Graph graph, boolean directed){
        Matcher matcher = PATTERN.matcher(line);
        if(matcher.find()) {
            String node1 = matcher.group(1);
            String node2 = matcher.group(3);
            String edgeName = (matcher.group(4) != null) ? matcher.group(4) : node1 + "_" + node2;
            int edgeWeight = (matcher.group(5) != null) ? Integer.parseInt(matcher.group(5)) : -1; //-1 = not set
            //Add Node
            addNodeToGraph(node1, graph);
            addNodeToGraph(node2, graph);

            Edge edge;

            if(directed){
                edge = graph.addEdge(edgeName, node1, node2, true);
            } else {
                edge = graph.addEdge(edgeName, node1, node2, false);
            }

            //Gewicht
            if(edgeWeight != -1){
                edge.setAttribute("edgeWeight", edgeWeight);
            }

            edge.setAttribute("ui.label", edgeName);

        } else {
            throw new IllegalArgumentException("Invalid Line passed");
        }

   }
    private static void addNodeToGraph(String node, Graph graph){
       if(graph.getNode(node) == null){
           graph.addNode(node).setAttribute("ui.label", node);
       }
    }

    public static void main(String[] args) throws IOException {
          Graph graph = parseFromFile("Main/resources/graph02.gka");
//        System.out.println(graph.getEdge(0).getAttribute("edgeWeight"));
//        String path = System.getProperty("user.dir")+RELATIVEOUTPUTPATH;
        System.out.println(System.getProperty("user.dir"));
//        System.out.println(path);
//        System.out.println(MIXEDPATH);
        saveGraphToFile("gaaa02",graph, false);

        System.out.println(path1);


    }

    public static void saveGraphToFile(String fileName, Graph graph, boolean edgeNames){
       String file = fileName+EXTENSION;
       try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
          List<Edge> edges = graph.edges().toList(); //edges
           //consistent directions
           boolean consistentDirection = checkDirection(edges);
           if(!consistentDirection){
               throw new IllegalArgumentException("Mixed Edges in the graph");
           }
           boolean directed = edges.getFirst().isDirected();
           boolean weighted = edges.getFirst().hasAttribute("edgeWeight");
           String edgeDirection = (directed) ? " -> " : " -- ";

           for(Edge edge: edges){
               //Attributes
               String sourceNode = edge.getSourceNode().toString();
               String targetNode = edge.getTargetNode().toString();
               String weight = "";
               if(weighted) {
                   weight = ": " + edge.getAttribute("edgeWeight").toString();
               }

               if(weighted) {
                   if(edgeNames) {
                       String edgeName = "(" + edge.getId() + ")";
                       writer.write(sourceNode + edgeDirection + targetNode + edgeName + weight + ";" + "\n");
                   } else {
                       writer.write(sourceNode + edgeDirection + targetNode + weight + ";" + "\n");
                   }
               } else {
                   if(edgeNames){
                       String edgeName = "(" + edge.getId() + ")";
                       writer.write(sourceNode + edgeDirection + targetNode + edgeName + ";" + "\n");
                   } else {
                       writer.write(sourceNode + edgeDirection + targetNode + ";" + "\n");
                   }
               }
           }



       } catch (IOException e) {
           e.printStackTrace();
       }

    }


    private static boolean checkDirection(List<Edge> edges){
       boolean isDirected = edges.stream().allMatch(edge -> edge.isDirected());
       boolean isUndirected = edges.stream().allMatch(edge ->  !edge.isDirected());
       return isDirected ^ isUndirected;
    }



    private static String extractNameFromFile(String file){
       Pattern pattern = Pattern.compile("(?:.*/)?(.+)");
       String fileName = "";
       Matcher matcher = pattern.matcher(file);
       if(matcher.find()){
           String matcherFind = matcher.group(1);
           fileName = matcherFind.substring(0, matcherFind.indexOf("."));
       }
       return fileName;
   }
}