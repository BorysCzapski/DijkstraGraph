public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(5, 1, 2);
        graph.addEdge(4, 1, 3);
        graph.addEdge(2, 2, 4);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 1, 4);
        graph.addEdge(7, 2, 3);

        graph.dijkstra(1);


        graph.printGraph();
    }

}
