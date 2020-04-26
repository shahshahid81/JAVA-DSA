class BinarySearchTreeNode {
  int data;
  BinarySearchTreeNode left;
  BinarySearchTreeNode right;

  BinarySearchTreeNode(int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

public class BinarySearchTree {
  static BinarySearchTreeNode root = null;

  static void insert(int data) {
    BinarySearchTreeNode node = new BinarySearchTreeNode(data);
    if (root == null) {
      root = node;
    } else {
      BinarySearchTreeNode current = root;
      while (current != null) {
        if (current.data == data) {
          System.out.println("No Duplicate Values Allowed");
          break;
        } else if (current.data > data) {
          if (current.left != null) {
            current = current.left;
          } else {
            current.left = node;
            break;
          }
        } else if (current.data < data) {
          if (current.right != null) {
            current = current.right;
          } else {
            current.right = node;
            break;
          }
        }
      }
    }
  }

  static void delete(BinarySearchTreeNode root, int data) {

  }

  static void inOrder(BinarySearchTreeNode root) {
    if (root == null) {
      return;
    } else {
      inOrder(root.left);
      System.out.print(root.data + "\t");
      inOrder(root.right);
    }
  }

  static void preOrder(BinarySearchTreeNode root) {
    if (root == null) {
      return;
    } else {
      System.out.print(root.data + "\t");
      preOrder(root.left);
      preOrder(root.right);
    }
  }

  static void postOrder(BinarySearchTreeNode root) {
    if (root == null) {
      return;
    } else {
      postOrder(root.left);
      postOrder(root.right);
      System.out.print(root.data + "\t");
    }
  }

  static int smallestElement(BinarySearchTreeNode root) {
    if (root.left != null) {
      return smallestElement(root.left);
    } else {
      return root.data;
    }
  }

  static int largestElement(BinarySearchTreeNode root) {
    if (root.right != null) {
      return largestElement(root.right);
    } else {
      return root.data;
    }
  }

  static int heightOfTree(BinarySearchTreeNode root) {
    if (root == null) {
      return 0;
    } else {
      return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.left));
    }
  }

  public static void main(String[] args) {
    insert(25);
    System.out.println("\nElements are: ");
    inOrder(root);
    // delete(root, 25);
    // System.out.println("\nElements are: ");
    // inOrder(root);
    insert(25);
    System.out.println("\nElements are: ");
    inOrder(root);
    System.out.println();
    insert(15);
    insert(10);
    insert(22);
    insert(4);
    insert(12);
    insert(18);
    insert(24);
    insert(50);
    insert(35);
    insert(70);
    insert(31);
    insert(44);
    insert(66);
    insert(90);
    insert(3);
    System.out.println("\nElements are: ");
    inOrder(root);
    // delete(root, 4);
    // System.out.println("\nElements after delete 4 are: ");
    // inOrder(root);
    System.out.println();
    System.out.println("\nPreOrder: ");
    preOrder(root);
    System.out.println();
    System.out.println("\nPostOrder: ");
    postOrder(root);
    System.out.println("\nSmallest Element is " + smallestElement(root));
    System.out.println("\nLargest Element is " + largestElement(root));
    System.out.println("\nTree height is " + heightOfTree(root));
  }
}