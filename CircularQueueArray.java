public class CircularQueueArray {
  static int queueSize = 5;

  static int front = -1;
  static int rear = -1;

  static int[] queue = new int[queueSize];

  static void enqueue(int data) {
    if ((front == 0 && rear == queueSize - 1) || (rear + 1 == front)) {
      System.out.println("Queue is Full");
    } else if (front != 0 && rear == queueSize - 1) {
      rear = 0;
      queue[rear] = data;
    } else {
      queue[++rear] = data;
      if (front == -1) {
        front++;
      }
    }
  }

  static void dequeue() {
    if (front == -1) {
      System.out.println("Queue is Empty");
    } else if (front == rear) {
      front = -1;
      rear = -1;
    } else if (front == queueSize - 1) {
      front = 0;
    } else {
      front++;
    }
  }

  static void peek() {
    if (front == -1) {
      System.out.println("Queue is Empty");
    } else {
      System.out.println("Front element is " + queue[front]);
    }
  }

  static void traverse() {
    if (front == -1) {
      System.out.println("Queue is Empty");
    } else {
      if (rear >= front) {
        for (int i = front; i <= rear; i++) {
          System.out.print(queue[i] + "\t");
        }
      } else {
        for (int i = front; i <= queueSize - 1; i++) {
          System.out.print(queue[i] + "\t");
        }
        for (int i = rear; i < front; i++) {
          System.out.print(queue[i] + "\t");
        }
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
    traverse();
    peek();
    System.out.println("Dequeue:");
    dequeue();
    traverse();
    System.out.println("Enqueue 5:");
    enqueue(5);
    traverse();
    System.out.println("Enqueue 6:");
    enqueue(6);
    traverse();
    peek();
  }
}