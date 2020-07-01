class LinkedListNode {
    int data;
    LinkedListNode next;

    LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    LinkedListNode head = null;

    void insertAtBegin(int data) {
        LinkedListNode node = new LinkedListNode(data);
        if (this.head == null) {
            this.head = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
    }

    void insertAtEnd(int data) {
        LinkedListNode node = new LinkedListNode(data);
        if (this.head == null) {
            this.head = node;
        } else {
            LinkedListNode temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    void traverseList() {
        LinkedListNode temp = this.head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
    }

    boolean search(int data) {
        LinkedListNode temp = this.head;
        while (temp != null && temp.data != data) {
            temp = temp.next;
        }
        return temp == null ? false : true;
    }

    void insertAfterNode(int nodeValue, int data) {
        LinkedListNode nodeToInsertAfter = this.head;
        while (nodeToInsertAfter != null && nodeToInsertAfter.data != nodeValue) {
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

    void deleteFirstNode() {
        if (this.head == null) {
            return;
        }
        this.head = this.head.next;
    }

    void deleteLastNode() {
        if (this.head == null) {
            return;
        } else if (this.head.next == null) {
            this.head = null;
        } else {
            LinkedListNode temp = this.head, prev = this.head;
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = null;
        }
    }

    void deleteNode(int data) {
        if (data == this.head.data) {
            deleteFirstNode();
        }
        LinkedListNode temp = this.head, prev = this.head;
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
        LinkedList list = new LinkedList();
        list.insertAtBegin(2);
        list.insertAtEnd(3);
        list.insertAtEnd(6);
        list.insertAtBegin(1);
        System.out.println("\nInitial List:");
        list.traverseList();
        list.insertAfterNode(3, 4);
        System.out.println("\nInserting 4 after 3:");
        list.traverseList();
        System.out.println("\nDeleting first node:");
        list.deleteFirstNode();
        list.traverseList();
        System.out.println("\nDeleting last node:");
        list.deleteLastNode();
        list.traverseList();
        System.out.println("\nDeleting 3:");
        list.deleteNode(3);
        list.traverseList();
        System.out.println("\nDeleting 1:");
        list.deleteNode(1);
        list.traverseList();
    }
}