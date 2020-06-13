import java.util.*;

class Graph {

  static int numberOfVertices = 6;
  static boolean isDirectedGraph = true;

  static void addEdge(int[][] adjacencyMatrix, int u, int v) {
    adjacencyMatrix[u][v] = 1;
    if (!isDirectedGraph) {
      adjacencyMatrix[v][u] = 1;
    }
  }

  static void addEdge(ArrayList<ArrayList<Integer>> adjacencyList, int u, int v) {
    if (!adjacencyList.get(u).contains(v)) {
      adjacencyList.get(u).add(v);
    }
    if (!isDirectedGraph) {
      if (!adjacencyList.get(u).contains(v)) {
        adjacencyList.get(v).add(u);
      }
    }
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

  static void topologicalSort(int[][] adjacencyMatrix, int startingVertex) {
    System.out.println("\nTopological Sorting using adjacency matrix starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
    java.util.Stack<Integer> printStack = new java.util.Stack<Integer>();
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
        printStack.push(currentVertex);
      }
      if (stack.isEmpty()) {
        for (int i = 0; i < numberOfVertices; i++) {
          if (!visitedArray[i]) {
            stack.push(i);
            visitedArray[i] = true;
            break;
          }
        }
      }
    }
    while (!printStack.isEmpty()) {
      System.out.print(printStack.pop() + "\t");
    }
  }

  static void DFS(int[][] adjacencyMatrix, int startingVertex) {
    System.out.println("\nDFS Iteration using adjacency matrix starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
    stack.push(startingVertex);
    visitedArray[startingVertex] = true;
    System.out.print(startingVertex + "\t");
    while (!stack.isEmpty()) {
      int currentVertex = stack.peek();
      boolean noAdjacentNeighbors = true;
      for (int i = 0; i < numberOfVertices; i++) {
        if (adjacencyMatrix[currentVertex][i] == 1 && !visitedArray[i]) {
          System.out.print(i + "\t");
          stack.push(i);
          visitedArray[i] = true;
          noAdjacentNeighbors = false;
          break;
        }
      }
      if (noAdjacentNeighbors) {
        currentVertex = stack.pop();
      }
      if (stack.isEmpty()) {
        for (int i = 0; i < numberOfVertices; i++) {
          if (!visitedArray[i]) {
            System.out.print(i + "\t");
            stack.push(i);
            visitedArray[i] = true;
            break;
          }
        }
      }
    }
  }

  static void DFS(ArrayList<ArrayList<Integer>> adjacencyList, int startingVertex) {
    System.out.println("\nDFS Iteration using adjacency list starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
    stack.push(startingVertex);
    visitedArray[startingVertex] = true;
    System.out.print(startingVertex + "\t");
    while (!stack.isEmpty()) {
      int currentVertex = stack.peek();
      boolean noAdjacentNeighbors = true;
      Iterator<Integer> it = adjacencyList.get(currentVertex).iterator();
      while (it.hasNext()) {
        int nextValue = it.next();
        if (!visitedArray[nextValue]) {
          System.out.print(nextValue + "\t");
          stack.push(nextValue);
          visitedArray[nextValue] = true;
          noAdjacentNeighbors = false;
          break;
        }
      }
      if (noAdjacentNeighbors) {
        currentVertex = stack.pop();
      }
      if (stack.isEmpty()) {
        for (int i = 0; i < numberOfVertices; i++) {
          if (!visitedArray[i]) {
            stack.push(i);
            visitedArray[i] = true;
            System.out.print(i + "\t");
            break;
          }
        }
      }
    }
  }

  static void BFS(int[][] adjacencyMatrix, int startingVertex) {
    System.out.println("\nBFS Iteration using adjacency matrix starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.PriorityQueue<Integer> q = new java.util.PriorityQueue<Integer>();
    q.offer(startingVertex);
    visitedArray[startingVertex] = true;
    System.out.print(startingVertex + "\t");
    while (!q.isEmpty()) {
      int currentVertex = q.poll();
      for (int i = 0; i < numberOfVertices; i++) {
        if (adjacencyMatrix[currentVertex][i] == 1 && !visitedArray[i]) {
          q.offer(i);
          visitedArray[i] = true;
          System.out.print(i + "\t");
        }
      }
      if (q.isEmpty()) {
        for (int i = 0; i < numberOfVertices; i++) {
          if (!visitedArray[i]) {
            q.offer(i);
            visitedArray[i] = true;
            System.out.print(i + "\t");
            break;
          }
        }
      }
    }
  }

  static void BFS(ArrayList<ArrayList<Integer>> adjacencyList, int startingVertex) {
    System.out.println("\nBFS Iteration using adjacency list starting from: " + startingVertex);
    boolean[] visitedArray = new boolean[numberOfVertices];
    java.util.PriorityQueue<Integer> q = new java.util.PriorityQueue<Integer>();
    q.offer(startingVertex);
    visitedArray[startingVertex] = true;
    System.out.print(startingVertex + "\t");
    while (!q.isEmpty()) {
      int currentVertex = q.poll();
      Iterator<Integer> it = adjacencyList.get(currentVertex).iterator();
      while (it.hasNext()) {
        int currentValue = it.next();
        if (!visitedArray[currentValue]) {
          System.out.print(currentValue + "\t");
          q.offer(currentValue);
          visitedArray[currentValue] = true;
        }
      }
      if (q.isEmpty()) {
        for (int i = 0; i < numberOfVertices; i++) {
          if (!visitedArray[i]) {
            q.offer(i);
            visitedArray[i] = true;
            System.out.print(i + "\t");
            break;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    int[][] adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
    ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>(numberOfVertices);
    for (int i = 0; i < numberOfVertices; i++) {
      adjacencyList.add(new ArrayList<Integer>());
    }
    addEdge(adjacencyMatrix, 4, 0);
    addEdge(adjacencyMatrix, 4, 1);
    addEdge(adjacencyMatrix, 5, 2);
    addEdge(adjacencyMatrix, 5, 0);
    addEdge(adjacencyMatrix, 2, 3);
    addEdge(adjacencyMatrix, 3, 1);
    topologicalSort(adjacencyMatrix, 3);
    // BFS(adjacencyMatrix, 5);
    // DFS(adjacencyMatrix, 5);
    // addEdge(adjacencyList, 4, 0);
    // addEdge(adjacencyList, 4, 1);
    // addEdge(adjacencyList, 5, 0);
    // addEdge(adjacencyList, 5, 2);
    // addEdge(adjacencyList, 2, 3);
    // addEdge(adjacencyList, 3, 1);
    // BFS(adjacencyList, 5);
    // DFS(adjacencyList, 5);
  }
}
