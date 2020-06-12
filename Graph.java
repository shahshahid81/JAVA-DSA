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
      Iterator<Integer> itr = adjacencyList.get(i).iterator();
      while (itr.hasNext()) {
        System.out.print("->" + itr.next());
      }
      System.out.println();
    }
  }

  static void DFS(int[][] adjacencyMatrix, int startingVertex) {
    System.out.println("\nDFS Iteration using adjacency matrix starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
    stack.push(startingVertex);
    visitedArray[startingVertex] = true;
    while (!stack.isEmpty()) {
      int currentVertex = stack.peek();
      boolean noAdjacentNeighbors = true;
      for (int i = 0; i < numberOfVertices; i++) {
        if (adjacencyMatrix[currentVertex][i] == 1 && !visitedArray[i]) {
          stack.push(i);
          visitedArray[i] = true;
          noAdjacentNeighbors = false;
          break;
        }
      }
      if (noAdjacentNeighbors) {
        currentVertex = stack.pop();
        System.out.print(currentVertex + "\t");
      }
    }
  }

  static void DFS(ArrayList<ArrayList<Integer>> adjacencyList, int startingVertex) {
    System.out.println("\nDFS Iteration using adjacency list starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
    stack.push(startingVertex);
    visitedArray[startingVertex] = true;
    while (!stack.isEmpty()) {
      int currentVertex = stack.peek();
      boolean noAdjacentNeighbors = true;
      Iterator<Integer> it = adjacencyList.get(currentVertex).iterator();
      while (it.hasNext()) {
        int nextValue = it.next();
        if (!visitedArray[nextValue]) {
          stack.push(nextValue);
          visitedArray[nextValue] = true;
          noAdjacentNeighbors = false;
          break;
        }
      }
      if (noAdjacentNeighbors) {
        currentVertex = stack.pop();
        System.out.print(currentVertex + "\t");
      }
    }
  }

  static void BFS(int[][] adjacencyMatrix, int startingVertex) {
    System.out.println("\nBFS Iteration using adjacency matrix starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.PriorityQueue<Integer> q = new java.util.PriorityQueue<Integer>();
    q.offer(startingVertex);
    visitedArray[startingVertex] = true;
    while (!q.isEmpty()) {
      int currentVertex = q.poll();
      for (int i = 0; i < numberOfVertices; i++) {
        if (adjacencyMatrix[currentVertex][i] == 1 && !visitedArray[i]) {
          q.offer(i);
          visitedArray[i] = true;
        }
      }
      System.out.print(currentVertex + "\t");
    }
  }

  static void BFS(ArrayList<ArrayList<Integer>> adjacencyList, int startingVertex) {
    System.out.println("\nBFS Iteration using adjacency list starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.PriorityQueue<Integer> q = new java.util.PriorityQueue<Integer>();
    q.offer(startingVertex);
    visitedArray[startingVertex] = true;
    while (!q.isEmpty()) {
      int currentVertex = q.poll();
      Iterator<Integer> it = adjacencyList.get(currentVertex).iterator();
      while (it.hasNext()) {
        int currentValue = it.next();
        if (!visitedArray[currentValue]) {
          q.offer(currentValue);
          visitedArray[currentValue] = true;
        }
      }
      System.out.print(currentVertex + "\t");
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

    BFS(adjacencyMatrix, 1);
    BFS(adjacencyMatrix, 4);

    BFS(adjacencyList, 1);
    BFS(adjacencyList, 4);

    DFS(adjacencyMatrix, 1);
    DFS(adjacencyMatrix, 4);

    DFS(adjacencyList, 1);
    DFS(adjacencyList, 4);

  }
}
