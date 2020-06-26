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

  void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  int partition(int[] arr, int low, int high) {
    int pivot = arr[high], i = low - 1;
    for (int j = low; j <= high - 1; j++) {
      if (arr[j] < pivot) {
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

  void merge(int[] arr, int low, int mid, int high) {
    int n1 = mid - low + 1;
    int n2 = high - mid;
    int L[] = new int[n1];
    int R[] = new int[n2];
    for (int i = 0; i < n1; ++i) {
      L[i] = arr[low + i];
    }
    for (int j = 0; j < n2; ++j) {
      R[j] = arr[mid + 1 + j];
    }
    int i = 0, j = 0;
    int k = low;
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        arr[k] = L[i];
        i++;
      } else {
        arr[k] = R[j];
        j++;
      }
      k++;
    }
    while (i < n1) {
      arr[k] = L[i];
      i++;
      k++;
    }
    while (j < n2) {
      arr[k] = R[j];
      j++;
      k++;
    }
  }

  void mergeSort(int[] arr, int low, int high) {
    if (low < high) {
      int mid = (low + high) / 2;
      mergeSort(arr, low, mid);
      mergeSort(arr, mid + 1, high);
      merge(arr, low, mid, high);
    }
  }

  void mergeSort(int[] arr) {
    mergeSort(arr, 0, arr.length - 1);
  }

  void shellSort(int[] arr) {
    for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
      for (int i = 0, j = gap; j < arr.length; i++, j++) {
        if (arr[i] > arr[j]) {
          swap(arr, i, j);
          if ((i - gap) >= 0) {
            for (int k = i, l = i - gap; k >= 0; k -= gap, l -= gap) {
              if (arr[k] > arr[l]) {
                break;
              } else {
                swap(arr, k, l);
              }
            }
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Sort sort = new Sort();
    // int[] arr = { 3, 123, 43, 21, 492, 132, 93, 1, 600, 12 };
    // sort.bubbleSort(arr);
    // printArray(arr);
    // int[] arr2 = { 51, 323, 3, 11, 49 };
    // sort.selectionSort(arr2);
    // printArray(arr2);
    // int[] arr3 = { 3, 123, 43, 21, 2, 121, 1, 600, 12 };
    // sort.insertionSort(arr3);
    // printArray(arr3);
    // int[] arr4 = { 3, 331, 43, 21, 93, 1, 600, 12 };
    // int[] resultArray = sort.heapSort(arr4);
    // printArray(resultArray);
    // sort.quickSort(arr4);
    // printArray(arr4);
    // int[] arr5 = { 39, 13, 23, 45, 62, 33, 29, 79, 58 };
    // sort.mergeSort(arr5);
    // printArray(arr5);
    int[] arr6 = { 9, 18, 3, 2, 36, 54, 22, 45 };
    sort.shellSort(arr6);
    printArray(arr6);
  }
}