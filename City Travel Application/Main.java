import java.util.*;

public class Main{
    static class Edge {
        String src;
        String des;
        int w;

        public Edge(String src, String des, int w) {
            this.src = src;
            this.des = des;
            this.w = w;
        }
    }

    public static class Pair implements Comparable<Pair> {
        String node;
        int dist;

        public Pair(String n, int d) {
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist;
        }
    }

    public static void dijkstra(ArrayList<Edge> graph[], String src, String destination) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int V = graph.length;
        int dist[] = new int[V];
        String places[] = new String[V];

        places[0] = "New York City, NY";
        places[1] = "Los Angeles, CA";
        places[2] = "Chicago, IL";
        places[3] = "Toronto, ON";
        places[4] = "Miami, FL";
        places[5] = "Houston, TX";

        for (int i = 0; i < V; i++) {
            if (!places[i].equals(src)) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        int srcIndex = getIndex(places, src);
        int destIndex = getIndex(places, destination);

        if (srcIndex == -1 || destIndex == -1) {
            System.out.println("Invalid source or destination");
            return;
        }

        pq.add(new Pair(src, 0));
        dist[srcIndex] = 0;
        String parent[] = new String[V];

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            if (curr.node.equals(destination)) {
                break;
            }

            if (dist[getIndex(places, curr.node)] < curr.dist) {
                continue;
            }

            for (Edge e : graph[getIndex(places, curr.node)]) {
                String u = e.src;
                String v = e.des;
                int newDist = dist[getIndex(places, u)] + e.w;

                if (newDist < dist[getIndex(places, v)]) {
                    dist[getIndex(places, v)] = newDist;
                    parent[getIndex(places, v)] = u;
                    pq.add(new Pair(v, newDist));
                }
            }
        }

        // Build the path using the parent array
        String current = destination;
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.insert(0, " -> " + current);
    
        while (parent[getIndex(places, current)] != null) {
            current = parent[getIndex(places, current)];
            if(parent[getIndex(places, current)] == null){
                pathBuilder.insert(0, current);
            }else{
                pathBuilder.insert(0, " -> " + current);
            }
        }
    
    
        String path = pathBuilder.toString();
    
        if (dist[destIndex] == Integer.MAX_VALUE) {
            System.out.println("No route found from " + src + " to " + destination);
        } else {
            System.out.println("Minimum Cost Path: " + path);
            System.out.println("Minimum Cost: " + dist[destIndex]);
        }
    }

    static int getIndex(String places[], String node) {
        for (int i = 0; i < places.length; i++) {
            if (places[i].equals(node)) {
                return i;
            }
        }
        return -1;
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge("New York City, NY", "Los Angeles, CA", 2500));
        graph[0].add(new Edge("New York City, NY", "Chicago, IL", 800));
        graph[1].add(new Edge("Los Angeles, CA", "Chicago, IL", 1750));
        graph[1].add(new Edge("Los Angeles, CA", "Miami, FL", 2800));
        graph[2].add(new Edge("Chicago, IL", "Miami, FL", 1200));
        graph[2].add(new Edge("Chicago, IL", "Toronto, ON", 500));
        graph[3].add(new Edge("Toronto, ON", "New York City, NY", 800));
        graph[3].add(new Edge("Toronto, ON", "Miami, FL", 1400));
        graph[3].add(new Edge("Toronto, ON", "Houston, TX", 1500));
        graph[4].add(new Edge("Miami, FL", "Houston, TX", 1200));
    }

    public static void main(String[] args) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
        createGraph(graph);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println();
            System.out.println("Welcome to our App!");
            System.out.println("1. List all locations");
            System.out.println("2. Get shortest path from a source station to destination station");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Nodes (Places):");
                    Set<String> visitedLocations = new HashSet<>();
                    for (int i = 0; i < V; i++) {
                        for (Edge edge : graph[i]) {
                            String destination = edge.des;
                            if (!visitedLocations.contains(destination)) {
                                System.out.println((visitedLocations.size() + 1) + ". " + destination);
                                visitedLocations.add(destination);
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter source (e.g., New York City, NY): ");
                    String source = scanner.nextLine();
                    System.out.print("Enter destination (e.g., Los Angeles, CA): ");
                    String destination = scanner.nextLine();
                    dijkstra(graph, source, destination);
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 3);
    }
}
