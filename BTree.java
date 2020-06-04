class BTreeNode {
  int[] data;
  BTreeNode[] child;
  int capacity;
  boolean isLeaf;
  int size;

  BTreeNode(int size, boolean isLeaf) {
    this.size = size;
    this.isLeaf = isLeaf;
    this.data = new int[2 * size - 1];
    this.child = new BTreeNode[2 * size];
    this.capacity = 0;
  }

  void splitChild(int index, BTreeNode node) {
    BTreeNode newNode = new BTreeNode(node.size, node.isLeaf);
    newNode.capacity = this.size - 1;
    for (int j = 0; j < this.size - 1; j++) {
      newNode.data[j] = node.data[j + this.size];
    }
    if (!node.isLeaf) {
      for (int j = 0; j < this.size; j++) {
        newNode.child[j] = node.child[j + this.size];
      }
    }
    node.capacity = this.size - 1;
    for (int j = this.capacity; j >= index + 1; j--) {
      this.child[j + 1] = this.child[j];
    }
    this.child[index + 1] = newNode;
    for (int j = this.capacity - 1; j >= index; j--) {
      this.data[j + 1] = this.data[j];
    }
    this.data[index] = node.data[this.size - 1];
    this.capacity++;
  }

  void insert(int data) {
    int i = this.capacity - 1;
    if (this.isLeaf) {
      while (i >= 0 && this.data[i] > data) {
        this.data[i + 1] = this.data[i];
        i--;
      }
      this.data[i + 1] = data;
      this.capacity++;
    } else {
      while (i >= 0 && this.data[i] > data) {
        i--;
      }
      if (this.child[i + 1].capacity == 2 * this.size - 1) {
        splitChild(i + 1, this.child[i + 1]);
        if (this.data[i + 1] < data) {
          i++;
        }
      }
      this.child[i + 1].insert(data);
    }
  }
}

class BTree {
  static int minimumDegree = 3;
  static BTreeNode root = null;

  static void traverse() {
    if (root == null) {
      return;
    }
    System.out.print("\nElements are: \n");
    traverse(root);
  }

  static void traverse(BTreeNode root) {
    int i;
    for (i = 0; i < root.capacity; i++) {
      if (!root.isLeaf) {
        traverse(root.child[i]);
      }
      System.out.print(root.data[i] + " ");
    }
    if (!root.isLeaf) {
      traverse(root.child[i]);
    }
  }

  static void insert(int data) {
    if (root == null) {
      root = new BTreeNode(minimumDegree, true);
      root.data[0] = data;
      root.capacity = 1;
    } else {
      if (root.capacity != 2 * minimumDegree - 1) {
        root.insert(data);
      } else {
        BTreeNode node = new BTreeNode(minimumDegree, false);
        node.child[0] = root;
        node.splitChild(0, root);
        int i = 0;
        if (node.data[0] < data) {
          i++;
        }
        node.child[i].insert(data);
        root = node;
      }
    }
  }

  public static void main(String args[]) {
    int[] elements = { 10, 20, 5, 6, 12, 39, 7, 17 };
    for (int i : elements) {
      insert(i);
    }
    traverse();
  }
}