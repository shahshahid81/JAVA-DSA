public class QueueLinkedList {

  static LinkedListNode front = null;
  static LinkedListNode rear = null;

  static void enqueue(int data) {
    LinkedListNode node = new LinkedListNode(data);
    if (front == null && rear == null) {
      front = node;
      rear = node;
    } else {
      rear.next = node;
      rear = node;
    }
  }

  static void dequeue() {
    if (front == null) {
      System.out.println("Queue is Empty.");
    } else {
      front = front.next;
    }
  }

  static void peek() {
    if (front == null) {
      System.out.println("Queue is Empty");
    } else {
      System.out.println("Front element is " + front.data);
    }
  }

  static void traverse() {
    if (front == null) {
      System.out.println("Queue is Empty");
    } else {
      LinkedListNode temp = front;
      do {
        System.out.print(temp.data + "\t");
        temp = temp.next;
      } while (temp != null);
      System.out.println();
    }
  }

  public static void main(String args[]) {
    enqueue(1);
    enqueue(2);
    enqueue(3);
    System.out.println("Initial Elements:");
    traverse();
    System.out.println("Enqueue 4:");
    enqueue(4);
    peek();
    System.out.println("Dequeue:");
    dequeue();
    traverse();
  }
}