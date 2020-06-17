import java.util.*;

class Graph {

  static int numberOfVertices = 5;
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

  static void printMST(int parent[], int graph[][]) {
    System.out.println("Edge \tWeight");
    for (int i = 1; i < numberOfVertices; i++)
      System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
  }

  static int minKey(int key[], Boolean mstSet[]) {
    int min = Integer.MAX_VALUE, min_index = -1;
    for (int v = 0; v < numberOfVertices; v++) {
      if (mstSet[v] == false && key[v] < min) {
        min = key[v];
        min_index = v;
      }
    }
    return min_index;
  }

  static void minimumSpanningTreePrim(int[][] adjacencyMatrix) {
    int parent[] = new int[numberOfVertices];
    int key[] = new int[numberOfVertices];
    Boolean mstSet[] = new Boolean[numberOfVertices];
    for (int i = 0; i < numberOfVertices; i++) {
      key[i] = Integer.MAX_VALUE;
      mstSet[i] = false;
    }
    key[0] = 0;
    parent[0] = -1;
    for (int count = 0; count < numberOfVertices - 1; count++) {
      int u = minKey(key, mstSet);
      mstSet[u] = true;
      for (int v = 0; v < numberOfVertices; v++) {
        if (adjacencyMatrix[u][v] != 0 && mstSet[v] == false && adjacencyMatrix[u][v] < key[v]) {
          parent[v] = u;
          key[v] = adjacencyMatrix[u][v];
        }
      }
    }
    printMST(parent, adjacencyMatrix);
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
    int[][] adjacencyMatrix = { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 }, { 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 },
        { 0, 5, 7, 9, 0 } };
    printGraph(adjacencyMatrix);
    minimumSpanningTreePrim(adjacencyMatrix);
  }

}
