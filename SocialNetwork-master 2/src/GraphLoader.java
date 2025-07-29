import java.io.*;

public class GraphLoader {

    public static Graph loadFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String[] firstLine = reader.readLine().trim().split("\\s+");
        int nodeCount = Integer.parseInt(firstLine[0]);
        int edgeCount = Integer.parseInt(firstLine[1]);

        Graph graph = new Graph(nodeCount);

        for (int i = 0; i < edgeCount; i++) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) continue;
            String[] parts = line.trim().split("\\s+");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            graph.addEdge(a, b);
        }

        reader.close();
        return graph;
    }
}

