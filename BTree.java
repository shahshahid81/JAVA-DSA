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

  int findKey(int data) {
    int index = 0;
    while (index < this.capacity && this.data[index] < data) {
      ++index;
    }
    return index;
  }

  void removeFromLeaf(int index) {
    for (int i = index + 1; i < this.capacity; ++i) {
      this.data[i - 1] = this.data[i];
    }
    this.capacity--;
  }

  void removeFromNonLeaf(int index) {
    int dataToRemove = this.data[index];
    if (this.child[index].capacity >= this.size) {
      int predecessor = getPredecessor(index);
      this.data[index] = predecessor;
      this.child[index].remove(predecessor);
    } else if (this.child[index + 1].capacity >= this.size) {
      int successor = getSuccessor(index);
      this.data[index] = successor;
      this.child[index + 1].remove(successor);
    } else {
      merge(index);
      this.child[index].remove(dataToRemove);
    }
  }

  int getPredecessor(int index) {
    BTreeNode current = this.child[index];
    while (!current.isLeaf) {
      current = current.child[current.capacity];
    }
    return current.data[current.capacity - 1];
  }

  int getSuccessor(int index) {
    BTreeNode current = this.child[index + 1];
    while (!current.isLeaf) {
      current = current.child[0];
    }
    return current.data[0];
  }

  void fill(int index) {
    if (index != 0 && this.child[index - 1].capacity >= this.size) {
      borrowFromPrev(index);
    } else if (index != this.capacity && this.child[index + 1].capacity >= this.size) {
      borrowFromNext(index);
    } else {
      if (index != this.capacity) {
        merge(index);
      } else {
        merge(index - 1);
      }
    }
  }

  void borrowFromPrev(int index) {
    BTreeNode child = this.child[index];
    BTreeNode sibling = this.child[index - 1];
    for (int i = child.capacity - 1; i >= 0; --i) {
      child.data[i + 1] = child.data[i];
    }
    if (!child.isLeaf) {
      for (int i = child.capacity; i >= 0; --i) {
        child.child[i + 1] = child.child[i];
      }
    }
    child.data[0] = data[index - 1];
    if (!child.isLeaf) {
      child.child[0] = sibling.child[sibling.capacity];
    }
    data[index - 1] = sibling.data[sibling.capacity - 1];
    child.capacity += 1;
    sibling.capacity -= 1;
  }

  void borrowFromNext(int index) {
    BTreeNode child = this.child[index];
    BTreeNode sibling = this.child[index + 1];
    child.data[(child.capacity)] = data[index];
    if (!(child.isLeaf)) {
      child.child[(child.capacity) + 1] = sibling.child[0];
    }
    data[index] = sibling.data[0];
    for (int i = 1; i < sibling.capacity; ++i) {
      sibling.data[i - 1] = sibling.data[i];
    }
    if (!sibling.isLeaf) {
      for (int i = 1; i <= sibling.capacity; ++i) {
        sibling.child[i - 1] = sibling.child[i];
      }
    }
    child.capacity += 1;
    sibling.capacity -= 1;
  }

  void merge(int index) {
    BTreeNode child = this.child[index];
    BTreeNode sibling = this.child[index + 1];
    child.data[this.size - 1] = data[index];
    for (int i = 0; i < sibling.capacity; ++i) {
      child.data[i + this.size] = sibling.data[i];
    }
    if (!child.isLeaf) {
      for (int i = 0; i <= sibling.capacity; ++i) {
        child.child[i + this.size] = sibling.child[i];
      }
    }
    for (int i = index + 1; i < this.capacity; ++i) {
      data[i - 1] = data[i];
    }
    for (int i = index + 2; i <= this.capacity; ++i) {
      this.child[i - 1] = this.child[i];
    }
    child.capacity += sibling.capacity + 1;
    this.capacity--;
    sibling = null;
  }

  void remove(int data) {
    int index = findKey(data);
    if (index < this.capacity && this.data[index] == data) {
      if (this.isLeaf) {
        removeFromLeaf(index);
      } else {
        removeFromNonLeaf(index);
      }
    } else {
      if (this.isLeaf) {
        System.out.println("The key " + data + " is does not exist in the tree\n");
      } else {
        boolean flag = ((index == this.capacity) ? true : false);
        if (this.child[index].capacity < this.size) {
          fill(index);
        }
        if (flag && index > this.capacity) {
          this.child[index - 1].remove(data);
        } else {
          this.child[index].remove(data);
        }
      }
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
    int[] insertElement = { 1, 3, 7, 10, 11, 13, 14, 15, 18, 16, 19, 24, 25, 26, 21, 4, 5, 20, 22, 2, 17, 12, 6 };
    for (int i : insertElement) {
      insert(i);
    }
    traverse();
    int[] deleteElements = { 6, 13, 7, 4, 2, 16 };
    for (int i : deleteElements) {
      System.out.print("\nRemoving " + i + ":");
      root.remove(i);
      traverse();
    }
  }
}