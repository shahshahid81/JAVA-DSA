public class QueueArray {
  static int queueSize = 10;

  static int front = -1;
  static int rear = -1;

  static int[] queue = new int[queueSize];

  static void enqueue(int data) {
    if (rear == queueSize - 1) {
      System.out.println("Queue is Full");
    } else {
      queue[++rear] = data;
      if (front == -1) {
        front++;
      }
    }
  }

  static void dequeue() {
    if (front == -1 || front > rear) {
      System.out.println("Queue is Empty");
    } else {
      front++;
      if (front > rear) {
        front = -1;
        rear = -1;
      }
    }
  }

  static void peek() {
    if (front == -1 || front > rear) {
      System.out.println("Queue is Empty");
    } else {
      System.out.println("Front element is " + queue[front]);
    }
  }

  static void traverse() {
    if (front == -1 || front > rear) {
      System.out.println("Queue is Empty");
    } else {
      for (int i = front; i <= rear; i++) {
        System.out.print(queue[i] + "\t");
      }
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