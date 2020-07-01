class CircularDoublyLinkedListNode {
  int data;
  CircularDoublyLinkedListNode prev;
  CircularDoublyLinkedListNode next;

  CircularDoublyLinkedListNode(int data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }
}

class CircularDoublyLinkedList {
  static CircularDoublyLinkedListNode head = null;

  static void insertAtBegin(int data) {
    CircularDoublyLinkedListNode node = new CircularDoublyLinkedListNode(data);
    if (head == null) {
      node.next = node;
      node.prev = node;
      head = node;
    } else {
      CircularDoublyLinkedListNode tail = head;
      while (tail.next != head) {
        tail = tail.next;
      }
      tail.next = node;
      node.next = head;
      node.prev = tail;
      head = node;
    }
  }

  static void insertAtEnd(int data) {
    CircularDoublyLinkedListNode node = new CircularDoublyLinkedListNode(data);
    if (head == null) {
      node.next = node;
      node.prev = node;
      head = node;
    } else {
      CircularDoublyLinkedListNode temp = head;
      while (temp.next != head) {
        temp = temp.next;
      }
      temp.next = node;
      node.next = head;
      node.prev = temp;
    }
  }

  static void traverseList() {
    CircularDoublyLinkedListNode temp = head;
    do {
      System.out.print(temp.data + "\t");
      temp = temp.next;
    } while (temp != head);
  }

  static void insertAfterNode(int nodeValue, int data) {
    CircularDoublyLinkedListNode nodeToInsertAfter = head;
    while (nodeToInsertAfter != null && nodeToInsertAfter.data != nodeValue) {
      nodeToInsertAfter = nodeToInsertAfter.next;
    }
    if (nodeToInsertAfter == null) {
      System.out.println("Node not present in list");
      return;
    }
    CircularDoublyLinkedListNode nodeToInsert = new CircularDoublyLinkedListNode(data);
    nodeToInsert.next = nodeToInsertAfter.next;
    nodeToInsert.prev = nodeToInsertAfter;
    nodeToInsertAfter.next.prev = nodeToInsert;
    nodeToInsertAfter.next = nodeToInsert;
  }

  static void deleteFirstNode() {
    if (head == null) {
      return;
    }
    CircularDoublyLinkedListNode tail = head;
    while (tail.next != head) {
      tail = tail.next;
    }
    head = head.next;
    head.prev = tail;
    tail.next = head;
  }

  static void deleteLastNode() {
    if (head == null) {
      return;
    } else {
      CircularDoublyLinkedListNode temp = head;
      while (temp.next != head) {
        temp = temp.next;
      }
      temp.prev.next = head;
      head.prev = temp.prev;
    }
  }

  static void deleteNode(int data) {
    CircularDoublyLinkedListNode temp = head;
    while (temp.next != head && temp.data != data) {
      temp = temp.next;
    }
    if (temp.next == head) {
      return;
    }
    temp.prev.next = temp.next;
    temp.next.prev = temp.prev;
  }

  public static void main(String args[]) {
    insertAtBegin(2);
    insertAtEnd(3);
    insertAtEnd(6);
    insertAtBegin(1);
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