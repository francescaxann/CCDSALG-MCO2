import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph;

        System.out.print("Input file path: ");
        String filePath = scanner.nextLine();
        if (filePath.isEmpty()) filePath = scanner.nextLine(); // handle nextInt() leftover newline
        try {
            graph = GraphLoader.loadFromFile(filePath);
            System.out.println("Graph loaded!");
        } catch (Exception e) {
            System.out.println("Error loading graph: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\nMAIN MENU");
            System.out.println("[1] Get friend list");
            System.out.println("[2] Get connection");
            System.out.println("[3] Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter ID of person: ");
                int id = scanner.nextInt();
                if (!graph.isValidId(id)) {
                    System.out.println("Invalid ID.");
                } else {
                    List<Integer> friends = graph.getFriends(id);
                    System.out.println("Person " + id + " has " + friends.size() + (friends.size() == 1 ? " friend!" : " friends!"));
                    System.out.print("List of friends:");
                    if (!friends.isEmpty()) {
                        for (int f : friends) {
                            System.out.print(" " + f);
                        }
                    }
                    System.out.println();
                }
            } else if (choice == 2) {
                System.out.print("Enter ID of first person: ");
                int a = scanner.nextInt();
                System.out.print("Enter ID of second person: ");
                int b = scanner.nextInt();

                if (!graph.isValidId(a) || !graph.isValidId(b)) {
                    System.out.println("Invalid ID(s).");
                } else {
                    List<Integer> connection = graph.findConnection(a, b);
                    if (connection == null || connection.size() < 2) {
                        System.out.println("Cannot find a connection between " + a + " and " + b);
                    } else {
                        System.out.println("There is a connection from " + a + " to " + b + "!");
                        for (int i = 0; i < connection.size() - 1; i++) {
                            int from = connection.get(i);
                            int to = connection.get(i + 1);
                            System.out.println(from + " is friends with " + to);
                        }
                    }
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
