class CircularLinkedListNode {
  int data;
  CircularLinkedListNode next;

  CircularLinkedListNode(int data) {
    this.data = data;
    this.next = null;
  }
}

class CircularLinkedList {
  static CircularLinkedListNode head = null;

  static void insertAtBegin(int data) {
    CircularLinkedListNode node = new CircularLinkedListNode(data);
    if (head == null) {
      node.next = node;
      head = node;
    } else {
      CircularLinkedListNode temp = head;
      while (temp.next != head) {
        temp = temp.next;
      }
      temp.next = node;
      node.next = head;
      head = node;
    }
  }

  static void insertAtEnd(int data) {
    CircularLinkedListNode node = new CircularLinkedListNode(data);
    if (head == null) {
      node.next = node;
      head = node;
    } else {
      CircularLinkedListNode temp = head;
      while (temp.next != head) {
        temp = temp.next;
      }
      temp.next = node;
      node.next = head;
    }
  }

  static void traverseList() {
    if (head == null) {
      return;
    }
    CircularLinkedListNode temp = head;
    do {
      System.out.print(temp.data + "\t");
      temp = temp.next;
    } while (temp != head);
  }

  static void insertAfterNode(int nodeValue, int data) {
    CircularLinkedListNode nodeToInsertAfter = head;
    while (nodeToInsertAfter != null && nodeToInsertAfter.data != nodeValue) {
      nodeToInsertAfter = nodeToInsertAfter.next;
    }
    if (nodeToInsertAfter == null) {
      System.out.println("Node not present in list");
      return;
    } else {
      CircularLinkedListNode nodeToInsert = new CircularLinkedListNode(data);
      nodeToInsert.next = nodeToInsertAfter.next;
      nodeToInsertAfter.next = nodeToInsert;
    }
  }

  static void deleteFirstNode() {
    if (head == null) {
      return;
    } else if (head.next == head) {
      head = null;
    } else {
      CircularLinkedListNode temp = head;
      while (temp.next != head) {
        temp = temp.next;
      }
      head = head.next;
      temp.next = head;
    }
  }

  static void deleteLastNode() {
    if (head == null) {
      return;
    } else if (head.next == head) {
      head = null;
    } else {
      CircularLinkedListNode temp = head, prev = head;
      while (temp.next != head) {
        prev = temp;
        temp = temp.next;
      }
      prev.next = head;
    }
  }

  static void deleteNode(int data) {
    CircularLinkedListNode temp = head, prev = head;
    while (temp != head && temp.data != data) {
      prev = temp;
      temp = temp.next;
    }
    if (temp == head) {
      return;
    }
    prev.next = temp.next;
  }

  public static void main(String args[]) {
    insertAtBegin(2);
    insertAtBegin(1);
    insertAtEnd(3);
    insertAtEnd(6);
    System.out.println("\nInitial List:");
    traverseList();
    insertAfterNode(3, 4);
    System.out.println("\nInserting 4 after 3:");
    traverseList();
    System.out.println("\nDeleting first node:");
    deleteFirstNode();
    traverseList();
    System.out.println("\nDeleting last node:");
    deleteLastNode();
    traverseList();
    System.out.println("\nDeleting 3:");
    deleteNode(3);
    traverseList();
    System.out.println("\nDeleting 1:");
    deleteNode(1);
    traverseList();
  }
}