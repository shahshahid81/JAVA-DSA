class SumSegmentTree {
  int[] segmentTree;

  SumSegmentTree(int size) {
    int x = (int) (Math.ceil(Math.log(size) / Math.log(2)));
    int max_size = 2 * (int) Math.pow(2, x) - 1;
    segmentTree = new int[max_size];
  }

  int parent(int index) {
    return (index - 1) / 2;
  }

  int leftChild(int index) {
    return 2 * index + 1;
  }

  int rightChild(int index) {
    return 2 * index + 2;
  }

  void constructTree(int[] inputArray, int low, int high, int index) {
    if (low == high) {
      segmentTree[index] = inputArray[low];
    } else {
      int mid = (low + high) / 2;
      int left = 0, leftIndex = leftChild(index);
      if (leftIndex <= segmentTree.length - 1) {
        constructTree(inputArray, low, mid, leftIndex);
        left = segmentTree[leftIndex];
      }
      int right = 0, rightIndex = rightChild(index);
      if (rightIndex <= segmentTree.length - 1) {
        constructTree(inputArray, mid + 1, high, rightIndex);
        right = segmentTree[rightIndex];
      }
      segmentTree[index] = left + right;
    }
  }

  void constructTree(int[] inputArray) {
    constructTree(inputArray, 0, inputArray.length - 1, 0);
  }

  int getSum(int currentLow, int currentHigh, int low, int high, int index) {
    if (currentLow == low && currentHigh == high) {
      return segmentTree[index];
    } else if (currentLow > high || currentHigh < low) {
      return 0;
    } else if (currentLow == currentHigh) {
      return segmentTree[index];
    } else if (currentLow >= low && currentHigh <= high) {
      return segmentTree[index];
    } else {
      int mid = (currentLow + currentHigh) / 2;
      int sum1 = 0, sum2 = 0, leftChild = leftChild(index), rightChild = rightChild(index);
      if (leftChild <= segmentTree.length - 1) {
        sum1 = getSum(currentLow, mid, low, high, leftChild(index));
      }
      if (rightChild <= segmentTree.length - 1) {
        sum2 = getSum(mid + 1, currentHigh, low, high, rightChild(index));
      }
      return sum1 + sum2;
    }
  }

  void printSum(int low, int high, int inputLength) {
    if (low >= 0 && high <= inputLength) {
      int sum = getSum(0, inputLength, low, high, 0);
      System.out.println("\nSum of " + low + " and " + high + " is: " + sum);
    }
  }

  public static void main(String[] args) {
    int[] inputArray = { 4, 8, 12, 10, 9, 6, 2 };
    System.out.println();
    for (int i : inputArray)
      System.out.print(i + "\t");
    System.out.println();
    SumSegmentTree st = new SumSegmentTree(inputArray.length);
    st.constructTree(inputArray);
    st.printSum(0, 6, inputArray.length - 1); // 51
    st.printSum(0, 3, inputArray.length - 1); // 34
    st.printSum(1, 4, inputArray.length - 1); // 39
    st.printSum(3, 6, inputArray.length - 1); // 27

    int[] inputArray2 = { 1, 3, 5, 7, 9, 11 };
    System.out.println();
    for (int i : inputArray2)
      System.out.print(i + "\t");
    System.out.println();
    SumSegmentTree st2 = new SumSegmentTree(inputArray2.length);
    st2.constructTree(inputArray2);
    st2.printSum(3, 5, inputArray2.length - 1);// 27
  }

}