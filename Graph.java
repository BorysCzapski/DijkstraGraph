import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {

    private LinkedList<Vertex> vertexes;

    public Graph() {
        vertexes = new LinkedList<>();
    }

    public void addVertex(int id) {
        if (findVertex(id) != null) {
            System.out.println("Wierzchołek o identyfikatorze " + id + " już istnieje");
        }else{
            vertexes.add(new Vertex(id));
        }

    }

    public void addEdge(int weight, int source, int aim) {

        if (weight <= 0) {
            System.out.println("Waga połączenia musi być dodatnia");
        } else {
            Vertex vertexSource = findVertex(source);
            Vertex vertexAim = findVertex(aim);

            if (vertexSource == null || vertexAim == null) {
                System.out.println("Wierzchołek o podanym identyfikatorze nie istnieje");
            } else {

                vertexSource.addEdge(new Edge(weight, aim, source));
                vertexAim.addEdge(new Edge(weight, source, aim));

            }
        }
    }

    public void printGraph() {
        for (Vertex vertex : vertexes) {
            System.out.println("Node " + vertex.id + ":");
            for (Edge connection : vertex.edges) {
                System.out.println(connection.source +"  <-> " + connection.aim + " (waga: " + connection.weight + ")");
            }
        }
    }

    private Vertex findVertex(int id) {
        for (Vertex vertex : vertexes) {
            if (vertex.id == id) {
                return vertex;
            }
        }
        return null;
    }

    private class Vertex {

        int id;
        LinkedList<Edge> edges;
        double distance;
        boolean visited;
        Vertex parent; // This stores the parent vertex in the shortest path

        public Vertex(int id) {
            this.id = id;
            edges = new LinkedList<>();
            distance = Double.POSITIVE_INFINITY;
            visited = false;
            parent = null;
        }

        public void addEdge(Edge connection) {
            edges.add(connection);
        }
    }

    private class Edge {

        int weight;
        int source;
        int aim;

        public Edge(int weight, int source, int aim) {
            this.weight = weight;
            this.source = source;
            this.aim = aim;
        }
    }


    public void dijkstra(int sourceId) {

        Vertex sourceVertex = findVertex(sourceId);// looks for the source vertex
        if (sourceVertex == null) { // checks whether it is null/doesn't exist
            System.out.println("Wierzchołek źródłowy o identyfikatorze " + sourceId + " nie istnieje");
            return;
        }

        // set distances for all vertices
        for (Vertex vertex : vertexes) {
            vertex.distance = Double.POSITIVE_INFINITY;
        }
        sourceVertex.distance = 0; // set the distance from source to source = 0

        // Priority queue for vertices to visit wit ha comparator, to compare by distances
        PriorityQueue<Vertex> queue = new PriorityQueue<>((v1, v2) -> Double.compare(v1.distance, v2.distance)); //compare the distances and create the priority queue
        queue.add(sourceVertex);// add the source to the queue

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            current.visited = true;

            for (Edge neighbor : current.edges) {
                Vertex neighborVertex = findVertex(neighbor.aim);
                if (neighborVertex != null && !neighborVertex.visited) {
                    double newDistance = current.distance + neighbor.weight;
                    if (newDistance < neighborVertex.distance) {
                        neighborVertex.distance = newDistance;
                        neighborVertex.parent = current; // Set the parent for the neighbor
                        queue.add(neighborVertex);
                    }
                }
            }
        }
    }
}
