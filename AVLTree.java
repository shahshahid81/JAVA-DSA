class AVLTreeNode {
  int data;
  int height;
  AVLTreeNode left;
  AVLTreeNode right;

  AVLTreeNode(int data) {
    this.data = data;
    this.height = 1;
    this.left = null;
    this.right = null;
  }
}

public class AVLTree {
  static AVLTreeNode root = null;

  static void inOrder(AVLTreeNode root) {
    if (root == null) {
      return;
    } else {
      inOrder(root.left);
      System.out.print(root.data + "\t");
      inOrder(root.right);
    }
  }

  static void preOrder(AVLTreeNode root) {
    if (root == null) {
      return;
    } else {
      System.out.print(root.data + "\t");
      preOrder(root.left);
      preOrder(root.right);
    }
  }

  static int heightOfTree(AVLTreeNode root) {
    if (root == null) {
      return 0;
    } else {
      return root.height;
    }
  }

  static int getBalance(AVLTreeNode root) {
    if (root == null) {
      return 0;
    } else {
      return heightOfTree(root.left) - heightOfTree(root.right);
    }
  }

  static AVLTreeNode rightRotate(AVLTreeNode y) {
    AVLTreeNode x = y.left;
    AVLTreeNode T2 = x.right;
    x.right = y;
    y.left = T2;
    x.height = Math.max(heightOfTree(x.left), heightOfTree(x.right)) + 1;
    y.height = Math.max(heightOfTree(y.left), heightOfTree(y.right)) + 1;
    return x;
  }

  static AVLTreeNode leftRotate(AVLTreeNode x) {
    AVLTreeNode y = x.right;
    AVLTreeNode T2 = y.left;
    y.left = x;
    x.right = T2;
    x.height = Math.max(heightOfTree(x.left), heightOfTree(x.right)) + 1;
    y.height = Math.max(heightOfTree(y.left), heightOfTree(y.right)) + 1;
    return y;
  }

  static AVLTreeNode insert(AVLTreeNode root, int data) {
    if (root == null) {
      return new AVLTreeNode(data);
    } else if (root.data > data) {
      root.left = insert(root.left, data);
    } else {
      root.right = insert(root.right, data);
    }
    root.height = 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    int balance = getBalance(root);
    if (balance > 1) {
      if (data < root.left.data) {
        return rightRotate(root);
      } else if (data > root.left.data) {
        root.left = leftRotate(root.left);
        return rightRotate(root);
      }
    }
    if (balance < -1) {
      if (data > root.right.data) {
        return leftRotate(root);
      } else if (data < root.right.data) {
        root.right = rightRotate(root.right);
        return leftRotate(root);
      }
    }
    return root;
  }

  static void insert(int data) {
    root = insert(root, data);
  }

  static AVLTreeNode smallestElement(AVLTreeNode root) {
    if (root.left != null) {
      return smallestElement(root.left);
    } else {
      return root;
    }
  }

  static AVLTreeNode delete(AVLTreeNode root, int data) {
    if (root == null) {
      return root;
    }
    if (data < root.data) {
      root.left = delete(root.left, data);
    } else if (data > root.data) {
      root.right = delete(root.right, data);
    } else {
      if ((root.left == null) || (root.right == null)) {
        AVLTreeNode temp = null;
        if (temp == root.left) {
          temp = root.right;
        } else {
          temp = root.left;
        }
        if (temp == null) {
          temp = root;
          root = null;
        } else {
          root = temp;
        }
      } else {
        AVLTreeNode temp = smallestElement(root.right);
        root.data = temp.data;
        root.right = delete(root.right, temp.data);
      }
    }
    if (root == null) {
      return root;
    }
    root.height = Math.max(heightOfTree(root.left), heightOfTree(root.right)) + 1;
    int balance = getBalance(root);
    if (balance > 1 && getBalance(root.left) >= 0) {
      return rightRotate(root);
    }
    if (balance > 1 && getBalance(root.left) < 0) {
      root.left = leftRotate(root.left);
      return rightRotate(root);
    }
    if (balance < -1 && getBalance(root.right) <= 0) {
      return leftRotate(root);
    }
    if (balance < -1 && getBalance(root.right) > 0) {
      root.right = rightRotate(root.right);
      return leftRotate(root);
    }
    return root;
  }

  static void delete(int data) {
    root = delete(root, data);
  }

  public static void main(String[] args) {
    insert(30);
    insert(20);
    insert(10);
    insert(40);
    insert(50);
    insert(25);
    System.out.println("\nInorder: ");
    inOrder(root);
    System.out.println("\nPreorder: ");
    preOrder(root);
    System.out.println("\nDeleting 30:");
    delete(30);
    System.out.println("\nInorder: ");
    inOrder(root);
    System.out.println("\nPreorder: ");
    preOrder(root);
    System.out.println("\nDeleting 10:");
    delete(10);
    System.out.println("\nInorder: ");
    inOrder(root);
    System.out.println("\nPreorder: ");
    preOrder(root);
  }
}