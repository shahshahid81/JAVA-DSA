import java.util.*;

class WeightedGraphEdgeNode {
  int src;
  int dest;
  int weight;

  WeightedGraphEdgeNode(int src, int dest, int weight) {
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }
}

class WeightedGraphEdgeNodeComparator implements Comparator<WeightedGraphEdgeNode> {
  public int compare(WeightedGraphEdgeNode n1, WeightedGraphEdgeNode n2) {
    return n1.weight - n2.weight;
  }
}

class Graph {

  static int numberOfVertices = 9;
  static boolean isDirectedGraph = false;
  static int MAX = Integer.MAX_VALUE;

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

  static void addEdge(ArrayList<WeightedGraphEdgeNode> adjacencyList, int u, int v, int weight) {
    WeightedGraphEdgeNode node = new WeightedGraphEdgeNode(u, v, weight);
    adjacencyList.add(node);
  }

  static void printGraph(int[][] adjacencyMatrix) {
    for (int i = 0; i < adjacencyMatrix.length; i++) {
      for (int j = 0; j < adjacencyMatrix.length; j++) {
        System.out.print(adjacencyMatrix[i][j] + " ");
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

  static void printGraphWeighted(ArrayList<WeightedGraphEdgeNode> adjacencyList) {
    for (int i = 0; i < adjacencyList.size(); i++) {
      WeightedGraphEdgeNode node = adjacencyList.get(i);
      System.out.print(node.src + " ---- " + node.dest + "(" + node.weight + ")");
      System.out.println();
    }
  }

  static void printMST(int parent[], int graph[][]) {
    System.out.println("Edge \tWeight");
    for (int i = 1; i < numberOfVertices; i++)
      System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
  }

  static int minKey(int key[], Boolean mstSet[]) {
    int min = MAX, min_index = -1;
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
      key[i] = MAX;
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

  static void minimumSpanningTreeKruskal(ArrayList<WeightedGraphEdgeNode> adjacencyList) {
    Collections.sort(adjacencyList, new WeightedGraphEdgeNodeComparator());
    DisjointSet ds = new DisjointSet();
    for (int i = 0; i < numberOfVertices; i++) {
      ds.makeSet(i);
    }
    ArrayList<WeightedGraphEdgeNode> result = new ArrayList<>();
    int edgeCount = adjacencyList.size() - 1;
    for (int i = 0; i < adjacencyList.size(); i++) {
      WeightedGraphEdgeNode node = adjacencyList.get(i);
      if (ds.findSet(ds.dataMap.get(node.src)) != ds.findSet(ds.dataMap.get(node.dest))) {
        result.add(new WeightedGraphEdgeNode(node.src, node.dest, node.weight));
        ds.union(node.src, node.dest);
        edgeCount--;
      }
      if (edgeCount == 0)
        break;
    }
    printGraphWeighted(result);
  }

  static int minDistance(int[] distance, boolean[] shortestPathSet) {
    int minIndex = -1, min = MAX;
    for (int i = 0; i < distance.length; i++) {
      if (!shortestPathSet[i] && distance[i] <= min) {
        min = distance[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  static void dijkstra(int[][] adjacencyMatrix) {
    int numberOfVertices = adjacencyMatrix.length;
    boolean[] shortestPathSet = new boolean[numberOfVertices];
    int[] distance = new int[numberOfVertices];
    for (int i = 0; i < numberOfVertices; i++) {
      distance[i] = MAX;
      shortestPathSet[i] = false;
    }
    distance[0] = 0;
    for (int i = 0; i < numberOfVertices - 1; i++) {
      int minimumIndex = minDistance(distance, shortestPathSet);
      shortestPathSet[minimumIndex] = true;
      for (int v = 0; v < numberOfVertices; v++) {
        if (!shortestPathSet[v] && adjacencyMatrix[minimumIndex][v] != 0 && distance[minimumIndex] != MAX
            && distance[minimumIndex] + adjacencyMatrix[minimumIndex][v] < distance[v]) {
          distance[v] = distance[minimumIndex] + adjacencyMatrix[minimumIndex][v];
        }
      }
    }
    System.out.println("Vertex \t\t Distance from Source");
    for (int i = 0; i < numberOfVertices; i++) {
      System.out.println(i + " \t\t " + distance[i]);
    }
  }

  static void floydWarshall(int[][] adjacencyMatrix) {
    /**
     * First copy adjacency matrix to distance matrix Declare path
     * matrix[numberOfVertices][numberOfVertices] for i =0 to numberOfVertices for j
     * =0 to numberOfVertices if(dist[i][j] != MAX && i!=j) path[i][j] = [i] else
     * path[i][j] = -1 For i =0 to numberOfVertices For j=0 to numberOfVertices For
     * k=0 to numberOfVertices if dist[i][j] > dist[i][k] + dist[k][j] dist[i][j] =
     * dist[i][k] + dist[k][j]
     */
    int numberOfVertices = adjacencyMatrix.length;
    int[][] distanceMatrix, pathMatrix;
    distanceMatrix = new int[numberOfVertices][numberOfVertices];
    pathMatrix = new int[numberOfVertices][numberOfVertices];
    for (int i = 0; i < numberOfVertices; i++) {
      for (int j = 0; j < numberOfVertices; j++) {
        distanceMatrix[i][j] = adjacencyMatrix[i][j];
        pathMatrix[i][j] = distanceMatrix[i][j] != MAX && i != j ? i : -1;
      }
    }
    for (int i = 0; i < numberOfVertices; i++) {
      for (int j = 0; j < numberOfVertices; j++) {
        for (int k = 0; k < numberOfVertices; k++) {
          if (distanceMatrix[k][j] == MAX || distanceMatrix[i][k] == MAX)
            continue;
          if (distanceMatrix[i][j] > distanceMatrix[i][k] + distanceMatrix[k][j]) {
            distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
            pathMatrix[i][j] = pathMatrix[k][j];
          }
        }
      }
    }
    for (int i = 0; i < distanceMatrix.length; i++) {
      for (int j = 0; j < distanceMatrix.length; j++) {
        if (distanceMatrix[i][j] == MAX)
          System.out.print("MAX ");
        else
          System.out.print(distanceMatrix[i][j] + " ");
      }
      System.out.println("");
    }
    printPath(pathMatrix, 0, 3);
  }

  static void printPath(int[][] path, int start, int end) {
    if (start < 0 || end < 0 || start >= path.length || end >= path.length) {
      return;
    }
    System.out.println("Actual path - between " + start + " " + end);
    Deque<Integer> stack = new java.util.LinkedList<>();
    stack.addFirst(end);
    while (true) {
      end = path[start][end];
      if (end == -1) {
        return;
      }
      stack.addFirst(end);
      if (end == start) {
        break;
      }
    }
    while (!stack.isEmpty()) {
      System.out.print(stack.pollFirst() + " ");
    }
    System.out.println();
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
    // int[][] adjacencyMatrix = { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 }, { 0, 3, 0,
    // 0, 7 }, { 6, 8, 0, 0, 9 },
    // { 0, 5, 7, 9, 0 } };
    // printGraph(adjacencyMatrix);
    // minimumSpanningTreePrim(adjacencyMatrix);

    // ArrayList<WeightedGraphEdgeNode> adjacencyList = new
    // ArrayList<>(numberOfVertices);
    // addEdge(adjacencyList, 0, 1, 2);
    // addEdge(adjacencyList, 0, 3, 6);
    // addEdge(adjacencyList, 2, 1, 3);
    // addEdge(adjacencyList, 1, 3, 8);
    // addEdge(adjacencyList, 1, 4, 5);
    // addEdge(adjacencyList, 2, 4, 6);
    // addEdge(adjacencyList, 3, 4, 9);
    // minimumSpanningTreeKruskal(adjacencyList);

    // int[][] adjacencyMatrix = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4,
    // 0, 8, 0, 0, 0, 0, 11, 0 },
    // { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9,
    // 0, 10, 0, 0, 0 },
    // { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0,
    // 0, 0, 0, 1, 0, 7 },
    // { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
    // dijkstra(adjacencyMatrix);

    int[][] adjacencyMatrix = { { 0, 5, MAX, 10 }, { MAX, 0, 3, MAX }, { MAX, MAX, 0, 1 }, { MAX, MAX, MAX, 0 } };
    floydWarshall(adjacencyMatrix);
  }
}
