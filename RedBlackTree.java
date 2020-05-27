enum COLOR {
  RED, BLACK
}

class RedBlackTreeNode {
  int data;
  RedBlackTreeNode left, right, parent;
  COLOR color;

  RedBlackTreeNode(int data) {
    this.data = data;
    this.left = this.right = this.parent = null;
    this.color = COLOR.RED;
  }
}

public class RedBlackTree {
  static RedBlackTreeNode root = null;

  static void inOrder() {
    inOrder(root);
  }

  static void inOrder(RedBlackTreeNode current) {
    if (current != null) {
      inOrder(current.left);
      System.out.print(current.data + "\t");
      inOrder(current.right);
    }
  }

  static void levelOrder() {
    if (root == null) {
      return;
    }
    System.out.println("Elements are:");
    java.util.Queue<RedBlackTreeNode> q = new java.util.LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      RedBlackTreeNode current = q.poll();
      System.out.print(current.data + "\t");
      if (current.left != null) {
        q.offer(current.left);
      }
      if (current.right != null) {
        q.offer(current.right);
      }
    }
    System.out.println();
  }

  static void leftRotate(RedBlackTreeNode node, boolean swapColor) {
    RedBlackTreeNode rightTree = node.right;
    RedBlackTreeNode leftTree = rightTree.left;
    node.right = leftTree;
    if (node.right != null) {
      node.right.parent = node;
    }
    rightTree.parent = node.parent;
    rightTree.left = node;
    if (node.parent == null) {
      root = rightTree;
    } else if (node == node.parent.left) {
      node.parent.left = rightTree;
    } else {
      node.parent.right = rightTree;
    }
    node.parent = rightTree;
    if (swapColor) {
      node.color = COLOR.RED;
      rightTree.color = COLOR.BLACK;
    }
  }

  static void rightRotate(RedBlackTreeNode node, boolean swapColor) {
    RedBlackTreeNode leftTree = node.left;
    RedBlackTreeNode rightTree = leftTree.right;
    node.left = rightTree;
    if (node.left != null) {
      node.left.parent = node;
    }
    leftTree.parent = node.parent;
    if (node.parent == null) {
      root = leftTree;
    } else if (node == node.parent.left) {
      node.parent.left = leftTree;
    } else {
      node.parent.right = leftTree;
    }
    leftTree.right = node;
    node.parent = leftTree;
    if (swapColor) {
      node.color = COLOR.RED;
      leftTree.color = COLOR.BLACK;
    }
  }

  static RedBlackTreeNode getSibling(RedBlackTreeNode current) {
    if (current == null || current.parent == null || current.parent.left == null || current.parent.right == null) {
      return null;
    }
    if (current.parent.left.data != current.data) {
      return current.parent.left;
    } else {
      return current.parent.right;
    }
  }

  static void balanceTree(RedBlackTreeNode newNode) {
    RedBlackTreeNode parent = null, grandParent = null;
    while (newNode != root && newNode.color == COLOR.RED && newNode.parent.color == COLOR.RED) {
      parent = newNode.parent;
      grandParent = parent.parent;
      RedBlackTreeNode uncle = getSibling(parent);
      if (uncle != null && uncle.color == COLOR.RED) {
        uncle.color = COLOR.BLACK;
        parent.color = COLOR.BLACK;
        grandParent.color = COLOR.RED;
        newNode = grandParent;
      } else {
        if (parent == grandParent.left) {
          if (newNode == parent.right) {
            leftRotate(parent, false);
            newNode = parent;
            parent = newNode.parent;
          }
          rightRotate(grandParent, true);
          newNode = parent;
        } else {
          if (newNode == parent.left) {
            rightRotate(newNode, false);
            newNode = parent;
            parent = newNode.parent;
          }
          leftRotate(grandParent, true);
          newNode = parent;
        }
      }
    }
    root.color = COLOR.BLACK;
  }

  static RedBlackTreeNode smallestElement(RedBlackTreeNode root) {
    if (root == null || root.left == null) {
      return root;
    } else {
      return smallestElement(root.left);
    }
  }

  static RedBlackTreeNode search(int data) {
    RedBlackTreeNode current = root;
    while (current != null && current.data != data) {
      if (current.data > data) {
        current = current.left;
      } else {
        current = current.right;
      }
    }
    return current;
  }

  static void delete(int data) {
    if (root == null) {
      return;
    }
    RedBlackTreeNode v = search(data);
    if (v.data != data) {
      return;
    }
    deleteNode(v);
  }

  static RedBlackTreeNode BSTreplace(RedBlackTreeNode x) {
    if (x.left != null && x.right != null)
      return smallestElement(x.right);
    if (x.left == null && x.right == null)
      return null;
    if (x.left != null)
      return x.left;
    else
      return x.right;
  }

  static void fixDoubleBlack(RedBlackTreeNode x) {
    if (x == root)
      return;
    RedBlackTreeNode sibling = getSibling(x), parent = x.parent;
    if (sibling == null) {
      fixDoubleBlack(parent);
    } else {
      if (sibling.color == COLOR.RED) {
        parent.color = COLOR.RED;
        sibling.color = COLOR.BLACK;
        if (sibling == parent.left) {
          rightRotate(parent, false);
        } else {
          leftRotate(parent, false);
        }
        fixDoubleBlack(x);
      } else {
        if ((sibling.left != null && sibling.left.color == COLOR.RED)
            || (sibling.right != null && sibling.right.color == COLOR.RED)) {
          if (sibling.left != null && sibling.left.color == COLOR.RED) {
            if (sibling == parent.left) {
              sibling.left.color = sibling.color;
              sibling.color = parent.color;
              rightRotate(parent, false);
            } else {
              sibling.left.color = parent.color;
              rightRotate(sibling, false);
              leftRotate(parent, false);
            }
          } else {
            if (sibling == parent.left) {
              sibling.right.color = parent.color;
              leftRotate(sibling, false);
              rightRotate(parent, false);
            } else {
              sibling.right.color = sibling.color;
              sibling.color = parent.color;
              leftRotate(parent, false);
            }
          }
          parent.color = COLOR.BLACK;
        } else {
          sibling.color = COLOR.RED;
          if (parent.color == COLOR.BLACK)
            fixDoubleBlack(parent);
          else
            parent.color = COLOR.BLACK;
        }
      }
    }
  }

  static void deleteNode(RedBlackTreeNode v) {
    RedBlackTreeNode u = BSTreplace(v);
    boolean uvBlack = ((u == null || u.color == COLOR.BLACK) && (v.color == COLOR.BLACK));
    RedBlackTreeNode parent = v.parent;
    if (u == null) {
      if (v == root) {
        root = null;
      } else {
        if (uvBlack) {
          fixDoubleBlack(v);
        } else {
          if (getSibling(v) != null)
            getSibling(v).color = COLOR.RED;
        }
        if (v == parent.left) {
          parent.left = null;
        } else {
          parent.right = null;
        }
      }
      return;
    }
    if (v.left == null || v.right == null) {
      if (v == root) {
        v.data = u.data;
        v.left = v.right = null;
      } else {
        if (v == parent.left) {
          parent.left = u;
        } else {
          parent.right = u;
        }
        u.parent = parent;
        if (uvBlack) {
          fixDoubleBlack(u);
        } else {
          u.color = COLOR.BLACK;
        }
      }
      return;
    }
    swapValues(u, v);
    deleteNode(u);
  }

  static void swapValues(RedBlackTreeNode u, RedBlackTreeNode v) {
    int temp;
    temp = u.data;
    u.data = v.data;
    v.data = temp;
  }

  static RedBlackTreeNode insert(RedBlackTreeNode root, RedBlackTreeNode newNode) {
    if (root == null) {
      return newNode;
    } else {
      if (root.data > newNode.data) {
        root.left = insert(root.left, newNode);
        root.left.parent = root;
      } else {
        root.right = insert(root.right, newNode);
        root.right.parent = root;
      }
      return root;
    }
  }

  static void insert(int data) {
    RedBlackTreeNode newNode = new RedBlackTreeNode(data);
    root = insert(root, newNode);
    balanceTree(newNode);
  }

  public static void main(String[] args) {
    int[] elements = { 10, 15, -10, 20, 30, 40, 50, -15, 25, 17, 21, 24, 28, 34, 32, 26, 35, 19 };
    for (int i : elements) {
      insert(i);
    }
    levelOrder();
    System.out.println();
    inOrder();
    int[] elementsToDelete = { 50, 40, -10, 15, 17, 24, 21, 32, 26 };
    for (int i : elementsToDelete) {
      delete(i);
    }
    System.out.println("Elements after deletion:");
    levelOrder();
    System.out.println();
    inOrder();
  }
}