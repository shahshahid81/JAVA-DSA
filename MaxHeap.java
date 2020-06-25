class MaxHeap {
  int[] heap;
  int size;
  int capacity;
  final int FRONT = 0;

  MaxHeap(int capacity) {
    this.capacity = capacity;
    this.size = -1;
    this.heap = new int[capacity];
  }

  int getParentIndex(int index) {
    return (int) Math.floor((index - 1) / 2);
  }

  int getLeftChildIndex(int index) {
    return 2 * index + 1;
  }

  int getRightChildIndex(int index) {
    return 2 * index + 2;
  }

  void swap(int a, int b) {
    int temp = this.heap[a];
    this.heap[a] = this.heap[b];
    this.heap[b] = temp;
  }

  void bubbleUp() {
    int current = this.size;
    while (current >= FRONT) {
      int parent = getParentIndex(current);
      if (heap[parent] < heap[current]) {
        swap(parent, current);
        current = parent;
      } else {
        break;
      }
    }
  }

  void bubbleDown() {
    int current = FRONT;
    while (current <= (this.size / 2)) {
      int leftChildIndex = getLeftChildIndex(current);
      int rightChildIndex = getRightChildIndex(current);
      if (leftChildIndex < size && heap[current] < heap[leftChildIndex]
          && heap[leftChildIndex] > heap[rightChildIndex]) {
        swap(current, leftChildIndex);
      } else if (rightChildIndex < size && heap[current] < heap[rightChildIndex]
          && heap[leftChildIndex] < heap[rightChildIndex]) {
        swap(current, rightChildIndex);
      }
      current++;
    }
  }

  void insert(int data) {
    if (this.size == this.capacity - 1) {
      System.out.println("Heap is Full");
    } else {
      this.heap[++size] = data;
      bubbleUp();
    }
  }

  int getMax() {
    if (this.size == -1) {
      System.out.println("Heap is Empty");
      return -1;
    } else {
      System.out.println(this.heap[FRONT]);
      return this.heap[FRONT];
    }
  }

  int removeMax() {
    if (this.size == -1) {
      System.out.println("Heap is Empty");
      return -1;
    } else {
      int deletedElement = this.heap[FRONT];
      this.heap[FRONT] = this.heap[this.size--];
      bubbleDown();
      return deletedElement;
    }
  }

  public static void main(String[] args) {
    int[] arr = { 3, 123, 43, 21, 492, 132, 93, 1, 600, 12 };
    MaxHeap mh = new MaxHeap(arr.length);
    for (int i : arr) {
      mh.insert(i);
    }
    for (int i = mh.size; i >= 0; i--) {
      mh.getMax();
      mh.removeMax();
    }
  }
}