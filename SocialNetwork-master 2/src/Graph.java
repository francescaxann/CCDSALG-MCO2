import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjacencyList;
    private int nodeCount;
    private int edgeCount;

    public Graph(int nodeCount) {
        this.nodeCount = nodeCount;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int a, int b) {
        // Bidirectional friendship
        adjacencyList.get(a).add(b);
        adjacencyList.get(b).add(a);
        edgeCount++;
    }

    public List<Integer> getFriends(int id) {
        return adjacencyList.getOrDefault(id, new ArrayList<>());
    }

    public boolean isValidId(int id) {
        return adjacencyList.containsKey(id);
    }

    public List<Integer> findConnection(int start, int end) {
        // Breadth-First Search for shortest path
        if (!isValidId(start) || !isValidId(end)) return null;

        Queue<List<Integer>> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(Arrays.asList(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int last = path.getLast();

            if (last == end) {
                return path; // Found the connection
            }

            for (int neighbor : adjacencyList.get(last)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return null; // No connection found
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }
}

