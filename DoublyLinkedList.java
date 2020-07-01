class DoublyLinkedListNode {
  int data;
  DoublyLinkedListNode prev;
  DoublyLinkedListNode next;

  DoublyLinkedListNode(int data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }
}

class DoublyLinkedList {
  static DoublyLinkedListNode head = null;

  static void insertAtBegin(int data) {
    DoublyLinkedListNode node = new DoublyLinkedListNode(data);
    if (head == null) {
      head = node;
    } else {
      head.prev = node;
      node.next = head;
      head = node;
    }
  }

  static void insertAtEnd(int data) {
    DoublyLinkedListNode node = new DoublyLinkedListNode(data);
    if (head == null) {
      head = node;
    } else {
      DoublyLinkedListNode temp = head;
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.next = node;
      node.prev = temp;
    }
  }

  static void traverseList() {
    DoublyLinkedListNode temp = head;
    while (temp != null) {
      System.out.print(temp.data + "\t");
      temp = temp.next;
    }
  }

  static void insertAfterNode(int nodeValue, int data) {
    DoublyLinkedListNode nodeToInsertAfter = head;
    while (nodeToInsertAfter != null && nodeToInsertAfter.data != nodeValue) {
      nodeToInsertAfter = nodeToInsertAfter.next;
    }
    if (nodeToInsertAfter == null) {
      System.out.println("Node not present in list");
      return;
    }
    DoublyLinkedListNode nodeToInsert = new DoublyLinkedListNode(data);
    nodeToInsert.next = nodeToInsertAfter.next;
    nodeToInsert.prev = nodeToInsertAfter;
    nodeToInsertAfter.next.prev = nodeToInsert;
    nodeToInsertAfter.next = nodeToInsert;
  }

  static void deleteFirstNode() {
    if (head == null) {
      return;
    }
    head = head.next;
    head.prev = null;
  }

  static void deleteLastNode() {
    if (head == null) {
      return;
    } else {
      DoublyLinkedListNode temp = head;
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.prev.next = null;
      temp.prev = null;
    }
  }

  static void deleteNode(int data) {
    DoublyLinkedListNode temp = head;
    while (temp != null && temp.data != data) {
      temp = temp.next;
    }
    if (temp == null) {
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