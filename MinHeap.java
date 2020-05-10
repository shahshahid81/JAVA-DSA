class MinHeap {
  int[] heap;
  int size;
  int maxSize;
  final int FRONT = 1;

  MinHeap(int maxSize) {
    this.maxSize = maxSize;
    this.size = 0;
    this.heap = new int[maxSize];
  }

  int getParent(int child) {
    return (int) Math.floor(child / 2);
  }

  int getLeftChildIndex(int index) {
    return index * 2;
  }

  int getRightChildIndex(int index) {
    return index * 2 + 1;
  }

  void swap(int parent, int child) {
    int temp = heap[child];
    heap[child] = heap[parent];
    heap[parent] = temp;
  }

  void bubbleUp() {
    int current = size;
    while (current >= FRONT) {
      int parent = getParent(current);
      if (heap[parent] > heap[current]) {
        swap(parent, current);
        current = parent;
      } else {
        break;
      }
    }
  }

  void sinkDown() {
    int current = FRONT;
    while (current <= (size / 2)) {
      int left = getLeftChildIndex(current);
      int right = getRightChildIndex(current);
      if (heap[current] > heap[left] && heap[left] < heap[right]) {
        swap(current, left);
      } else if (heap[current] > heap[right] && heap[right] < heap[left]) {
        swap(current, right);
      }
      current++;
    }
  }

  void insert(int data) {
    if (this.size >= (this.maxSize - 1)) {
      System.out.println("Heap is full");
    } else {
      this.heap[++this.size] = data;
      bubbleUp();
    }
  }

  void print() {
    System.out.println("\nElements Are: ");
    for (int i = 1; i <= size; i++) {
      System.out.print("\t" + heap[i]);
    }
    System.out.println();
    for (int i = 1; i <= size / 2; i++) {
      int rightChildIndex = getRightChildIndex(i);
      if (rightChildIndex > size) {
        System.out.print(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[getLeftChildIndex(i)]);
      } else {
        System.out.print(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[getLeftChildIndex(i)] + " RIGHT CHILD :"
            + heap[rightChildIndex]);
      }
      System.out.println();
    }
  }

  void getMin() {
    if (size > 0) {
      System.out.println("\nMinimum element is " + heap[FRONT]);
    }
  }

  void removeMin() {
    if (size <= 0) {
      System.out.println("Empty Heap.");
    } else {
      this.heap[FRONT] = this.heap[size--];
      sinkDown();
    }
  }

  public static void main(String[] args) {
    MinHeap mh = new MinHeap(10);
    mh.insert(5);
    mh.insert(3);
    mh.insert(17);
    mh.insert(10);
    mh.insert(84);
    mh.insert(19);
    mh.insert(6);
    mh.insert(22);
    mh.insert(9);
    mh.print();
    mh.getMin();
    mh.removeMin();
    mh.print();
    mh.removeMin();
    mh.print();
  }
}
