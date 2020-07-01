class SeperateChaining {

  final int tableSize = 10;
  LinkedList[] hashTable;

  SeperateChaining() {
    hashTable = new LinkedList[tableSize];
    for (int i = 0; i < tableSize; i++) {
      hashTable[i] = new LinkedList();
    }
  }

  int hash(int key) {
    return key % tableSize;
  }

  void printHashTable() {
    for (int i = 0; i < tableSize; i++) {
      System.out.print(i + ": ");
      hashTable[i].traverseList();
      System.out.println();
    }
  }

  void insert(int data) {
    int hashIndex = hash(data);
    hashTable[hashIndex].insertAtEnd(data);
  }

  void delete(int data) {
    int hashIndex = hash(data);
    hashTable[hashIndex].deleteNode(data);
  }

  boolean search(int data) {
    int hashIndex = hash(data);
    return hashTable[hashIndex].search(data);
  }

}

class HashTable {

  public static void main(String[] args) {
    SeperateChaining sc = new SeperateChaining();
    int[] elements = { 10, 20, 15, 13, 59, 69, 22, 405, 36, 45, 11, 201 };
    for (int i : elements) {
      sc.insert(i);
    }
    sc.printHashTable();
    if (sc.search(15)) {
      System.out.println("15 Is Present");
    } else {
      System.out.println("15 Is Absent");
    }
    if (sc.search(19)) {
      System.out.println("19 Is Present");
    } else {
      System.out.println("19 Is Absent");
    }
    sc.delete(15);
    sc.delete(405);
    sc.delete(22);
    sc.delete(69);
    System.out.println("After Deletion: ");
    sc.printHashTable();
  }

}