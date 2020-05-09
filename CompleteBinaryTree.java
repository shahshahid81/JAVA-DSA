class BinaryTreeNode {
  int data;
  BinaryTreeNode left;
  BinaryTreeNode right;

  BinaryTreeNode(int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

class CompleteBinaryTree {
  static private BinaryTreeNode root = null;

  static private void insert(int data) {
    if (root == null) {
      root = new BinaryTreeNode(data);
    } else {
      java.util.Queue<BinaryTreeNode> queue = new java.util.LinkedList<>();
      queue.offer(root);
      while (!queue.isEmpty()) {
        BinaryTreeNode current = queue.peek();
        if (current.left != null && current.right != null) {
          queue.offer(current.left);
          queue.offer(current.right);
          queue.poll();
        } else if (current.left == null) {
          current.left = new BinaryTreeNode(data);
          return;
        } else {
          current.right = new BinaryTreeNode(data);
          return;
        }
      }
    }
  }

  static private void levelOrder() {
    if (root == null) {
      System.out.println("Empty Tree");
    } else {
      java.util.Queue<BinaryTreeNode> q = new java.util.LinkedList<>();
      System.out.println("\nElements Are:");
      q.offer(root);
      while (!q.isEmpty()) {
        BinaryTreeNode current = q.poll();
        System.out.print(current.data + "\t");
        if (current.left != null) {
          q.offer(current.left);
        }
        if (current.right != null) {
          q.offer(current.right);
        }
      }
    }
  }

  public static void main(String[] args) {
    insert(8);
    insert(9);
    insert(5);
    insert(1);
    insert(6);
    insert(3);
    levelOrder();
  }
}