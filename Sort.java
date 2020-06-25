class Sort {

  static void printArray(int[] arr) {
    for (int i : arr) {
      System.out.print(i + "\t");
    }
    System.out.println();
  }

  void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      boolean isSorted = true;
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          isSorted = false;
        }
      }
      if (isSorted)
        break;
    }
  }

  void selectionSort(int[] arr) {

    for (int i = 0; i < arr.length; i++) {
      int min_index = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[min_index]) {
          min_index = j;
        }
      }
      if (min_index != i) {
        int temp = arr[min_index];
        arr[min_index] = arr[i];
        arr[i] = temp;
      }
    }
  }

  void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int key = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = key;
    }
  }

  int[] heapSort(int[] arr) {
    int[] resultArray = new int[arr.length];
    MaxHeap mh = new MaxHeap(arr.length);
    for (int i : arr) {
      mh.insert(i);
    }
    int i = 0;
    while (mh.size >= 0) {
      int max = mh.removeMax();
      resultArray[i++] = max;
    }
    return resultArray;
  }

  void swap(int[] arr,int a,int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  int partition(int[] arr, int low, int high) {
    int pivot = arr[high], i = low -1;
    for(int j=low;j<=high-1;j++) {
      if(arr[j] < pivot) {
        swap(arr, ++i, j);
      }
    }
    swap(arr, ++i, high);
    return i;
  }

  void quickSort(int[] arr, int low, int high) {
    if (low < high) {
      int partitionIndex = partition(arr, low, high);
      quickSort(arr, low, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, high);
    }
  }

  void quickSort(int[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  public static void main(String[] args) {
    Sort sort = new Sort();
    int[] arr = { 3, 123, 43, 21, 492, 132, 93, 1, 600, 12 };
    int[] arr2 = { 51, 323, 3, 11, 49 };
    int[] arr3 = { 3, 123, 43, 21, 2, 121, 1, 600, 12 };
    int[] arr4 = { 3, 331, 43, 21, 93, 1, 600, 12 };
    sort.bubbleSort(arr);
    printArray(arr);
    sort.selectionSort(arr2);
    printArray(arr2);
    sort.insertionSort(arr3);
    printArray(arr3);
    int[] resultArray = sort.heapSort(arr4);
    printArray(resultArray);
    sort.quickSort(arr4);
    printArray(arr4);
  }
}