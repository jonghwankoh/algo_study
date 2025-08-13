import java.util.*;

public class Test {

    static class Edge {
        int to, weight, color;

        public Edge(int to, int weight, int color) {
            this.to = to;
            this.weight = weight;
            this.color = color;
        }
    }

    static int n;
    static List<Edge>[] adj;

    // Dijkstra's algorithm to find the shortest distance from a source
    public static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0}); // {node, distance}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int d = current[1];

            if (d > dist[u]) {
                continue;
            }

            for (Edge edge : adj[u]) {
                if (dist[u] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[u] + edge.weight;
                    pq.add(new int[]{edge.to, dist[edge.to]});
                }
            }
        }
        return dist;
    }

    // Main solution method
    public static int[][] solution(int n_param, int[][] roads, int[] record) {
        n = n_param;
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build the adjacency list for the graph
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int weight = road[2];
            int color = road[3];
            adj[u].add(new Edge(v, weight, color));
            adj[v].add(new Edge(u, weight, color)); // Bidirectional roads
        }

        List<int[]> results = new ArrayList<>();

        // Iterate through all possible starting points
        for (int start = 1; start <= n; start++) {
            // Try to find a path starting from this 'start' node
            // This is a recursive or iterative function to trace the path
            // For simplicity, we'll do an iterative check here for the given record length.
            // A more general solution would use a recursive backtracking approach.
            
            // Start the path from 'start'
            int currentPos = start;
            boolean pathFound = true;
            
            for (int i = 0; i < record.length; i++) {
                int colorRecord = record[i];
                int absColor = Math.abs(colorRecord);
                boolean isShortest = colorRecord > 0;
                
                boolean segmentFound = false;
                
                for (Edge edge : adj[currentPos]) {
                    if (edge.color == absColor) {
                        // We found a road with the required color
                        int nextPos = edge.to;
                        int edgeWeight = edge.weight;

                        // Check if this segment is a shortest path
                        int[] dist = dijkstra(currentPos);
                        boolean isThisShortest = (dist[nextPos] == edgeWeight);

                        if (isThisShortest == isShortest) {
                            // This segment matches the record condition.
                            // Move to the next intersection and continue the path.
                            currentPos = nextPos;
                            segmentFound = true;
                            break; // Move to the next record element
                        }
                    }
                }
                
                if (!segmentFound) {
                    pathFound = false;
                    break; // This starting node doesn't lead to a valid path
                }
            }
            
            if (pathFound) {
                // We found a valid path from 'start' to 'currentPos'
                // Add the start-end pair to the results
                results.add(new int[]{start, currentPos});
            }
        }

        // Convert the list of results to a 2D array
        int[][] finalResults = new int[results.size()][2];
        for (int i = 0; i < results.size(); i++) {
            finalResults[i] = results.get(i);
        }

        return finalResults;
    }

    public static void main(String[] args) {
        // Test case based on the provided image
        int n = 6; // Number of intersections
        int[][] roads = {
            {1, 2, 3, 1}, // Red road
            {1, 3, 4, 3}, // Blue road
            {1, 5, 4, 2}, // Green road
            {1, 6, 7, 4}, // Orange road
            {2, 4, 1, 2}, // Green road
            {2, 5, 5, 4}, // Orange road
            {3, 6, 2, 1}, // Red road
            {4, 5, 4, 1}, // Red road
            {5, 6, 5, 3}  // Blue road
        };
        int[] record = {1, -4, 2}; // Example record

        int[][] result = solution(n, roads, record);

        // Print the result to verify
        System.out.println("Possible (start, end) pairs:");
        for (int[] pair : result) {
            System.out.println("(" + pair[0] + ", " + pair[1] + ")");
        }
    }
}