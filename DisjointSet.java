class DisjointSet {
  class Node {
    int data;
    int rank;
    Node parent;
  }

  java.util.Map<Integer, Node> dataMap = new java.util.HashMap<>();

  void makeSet(int data) {
    Node node = new Node();
    node.data = data;
    node.rank = 0;
    node.parent = node;
    dataMap.put(data, node);
  }

  Node findSet(Node node) {
    if (node == node.parent) {
      return node;
    }
    node.parent = findSet(node.parent);
    return node.parent;
  }

  int findSet(int data) {
    return findSet(dataMap.get(data)).data;
  }

  void union(int data1, int data2) {
    Node node1 = dataMap.get(data1);
    Node node2 = dataMap.get(data2);

    Node parent1 = findSet(node1);
    Node parent2 = findSet(node2);

    if (parent1 == parent2) {
      return;
    }
    if (parent1.rank >= parent2.rank) {
      parent2.parent = parent1;
      if (parent1.rank == parent2.rank) {
        parent1.rank++;
      }
    } else {
      parent1.parent = parent2;
    }
  }

  public static void main(String[] args) {
    DisjointSet ds = new DisjointSet();
    int[] data = { 1, 2, 3, 4, 5, 6, 7 };
    for (int i : data) {
      ds.makeSet(i);
    }
    ds.union(1, 2);
    ds.union(2, 3);
    ds.union(4, 5);
    ds.union(6, 7);
    ds.union(5, 6);
    ds.union(3, 7);
    for (int i : data) {
      System.out.println("findSet(" + i + ") : " + ds.findSet(i));
    }
  }
}