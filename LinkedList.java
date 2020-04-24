class LinkedListNode {
    int data;
    LinkedListNode next;

    LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    static LinkedListNode head = null;

    static void insertAtBegin(int data) {
        LinkedListNode node = new LinkedListNode(data);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    static void insertAtEnd(int data) {
        LinkedListNode node = new LinkedListNode(data);
        if (head == null) {
            head = node;
        } else {
            LinkedListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    static void traverseList() {
        LinkedListNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + "\t");
            temp = temp.next;
        }
    }

    static void insertAfterNode(int nodeValue, int data) {
        LinkedListNode nodeToInsertAfter = head;
        while (nodeToInsertAfter.data != nodeValue) {
            nodeToInsertAfter = nodeToInsertAfter.next;
        }
        if (nodeToInsertAfter == null) {
            System.out.println("Node not present in list");
            return;
        }
        LinkedListNode nodeToInsert = new LinkedListNode(data);
        nodeToInsert.next = nodeToInsertAfter.next;
        nodeToInsertAfter.next = nodeToInsert;
    }

    static void deleteFirstNode() {
        if (head == null) {
            return;
        }
        head = head.next;
    }

    static void deleteLastNode() {
        if (head == null) {
            return;
        } else if (head.next == null) {
            head = null;
        } else {
            LinkedListNode temp = head, prev = head;
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = null;
        }
    }

    static void deleteNode(int data) {
        LinkedListNode temp = head, prev = head;
        while (temp != null && temp.data != data) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            return;
        }
        prev.next = temp.next;
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