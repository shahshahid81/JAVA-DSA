import java.util.*;

class Graph {

  static int numberOfVertices = 5;

  static void addEdge(int[][] adjacencyMatrix, int u, int v) {
    adjacencyMatrix[u][v] = 1;
    adjacencyMatrix[v][u] = 1;
  }

  static void addEdge(ArrayList<ArrayList<Integer>> adjacencyList, int u, int v) {
    adjacencyList.get(u).add(v);
    adjacencyList.get(v).add(u);
  }

  static void printGraph(int[][] adjacencyMatrix) {
    for (int i = 0; i < adjacencyMatrix.length; i++) {
      for (int j = 0; j < adjacencyMatrix.length; j++) {
        System.out.print(adjacencyMatrix[i][j]);
      }
      System.out.println();
    }
  }

  static void printGraph(ArrayList<ArrayList<Integer>> adjacencyList) {
    for (int i = 0; i < adjacencyList.size(); i++) {
      System.out.print("\nEdges of " + i);
      Iterator itr = adjacencyList.get(i).iterator();
      while (itr.hasNext()) {
        System.out.print("->" + itr.next());
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
    addEdge(adjacencyMatrix, 0, 1);
    addEdge(adjacencyMatrix, 0, 4);
    addEdge(adjacencyMatrix, 1, 2);
    addEdge(adjacencyMatrix, 1, 3);
    addEdge(adjacencyMatrix, 1, 4);
    addEdge(adjacencyMatrix, 2, 3);
    addEdge(adjacencyMatrix, 3, 4);
    printGraph(adjacencyMatrix);

    ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>(numberOfVertices);
    for (int i = 0; i < numberOfVertices; i++) {
      adjacencyList.add(new ArrayList<Integer>());
    }

    addEdge(adjacencyList, 0, 1);
    addEdge(adjacencyList, 0, 4);
    addEdge(adjacencyList, 1, 2);
    addEdge(adjacencyList, 1, 3);
    addEdge(adjacencyList, 1, 4);
    addEdge(adjacencyList, 2, 3);
    addEdge(adjacencyList, 3, 4);
    printGraph(adjacencyList);
  }
}
