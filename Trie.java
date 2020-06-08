import java.util.*;

class TrieNode {
  HashMap<Character, TrieNode> characters;
  boolean isEndOfWord;

  TrieNode() {
    this.characters = new HashMap<Character, TrieNode>();
    this.isEndOfWord = false;
  }

  void insert(char ch, TrieNode next) {
    this.characters.put(ch, next);
  }

  boolean contains(char ch) {
    return this.characters.containsKey(ch);
  }

  TrieNode getNode(char ch) {
    return this.characters.get(ch);
  }

  boolean isEmpty() {
    return this.characters.isEmpty();
  }

  TrieNode remove(char ch) {
    return this.characters.remove(ch);
  }

}

class Trie {
  static TrieNode root = null;

  static TrieNode insert(TrieNode current, String data, int index) {
    if (current == null) {
      current = new TrieNode();
    }
    char characterToInsert = data.charAt(index);
    if (current.contains(characterToInsert)) {
      TrieNode nextNode = current.getNode(characterToInsert);
      if (index == data.length() - 1) {
        if (nextNode != null && !nextNode.isEndOfWord) {
          nextNode.isEndOfWord = true;
        }
      } else {
        TrieNode newNode = insert(nextNode, data, index + 1);
        current.insert(characterToInsert, newNode);
      }
      return current;
    } else {
      TrieNode next;
      if (index == data.length() - 1) {
        next = new TrieNode();
        next.isEndOfWord = true;
      } else {
        next = insert(null, data, index + 1);
      }
      current.insert(characterToInsert, next);
      return current;
    }
  }

  static void insert(String data) {
    int initialIndex = 0;
    root = insert(root, data, initialIndex);
  }

  static TrieNode delete(TrieNode current, String data, int index) {
    char characterToDelete = data.charAt(index);
    if (!current.contains(characterToDelete)) {
      return current;
    } else {
      TrieNode nextNode = current.getNode(characterToDelete);
      if (index != data.length() - 1) {
        TrieNode newNode = delete(nextNode, data, index + 1);
        if (newNode.isEmpty()) {
          current.remove(characterToDelete);
        }
        return current;
      } else {
        if (nextNode.isEndOfWord && nextNode.isEmpty()) {
          current.remove(characterToDelete);
        } else {
          nextNode.isEndOfWord = false;
        }
        return current;
      }
    }
  }

  static void delete(String data) {
    int initialIndex = 0;
    root = delete(root, data, initialIndex);
  }

  static boolean search(String data) {
    if (root == null) {
      return false;
    }
    TrieNode current = root;
    for (int i = 0; i < data.length(); i++) {
      char ch = data.charAt(i);
      if (!current.contains(ch)) {
        return false;
      }
      TrieNode nextNode = current.characters.get(ch);
      if (nextNode == null) {
        return false;
      }
      current = nextNode;
    }
    return current.isEndOfWord;
  }

  public static void main(String[] args) {
    String[] elements = { "Hello", "World", "Lenovo", "Shahid", "Work", "Leonard", "Shah" };
    for (String str : elements) {
      System.out.println("Inserting " + str);
      insert(str);
    }
    for (String str : elements) {
      System.out.println("Searching " + str);
      System.out.println(search(str));
    }
    String[] deleteElements = { "Worm", "Hello", "World", "Shah" };
    for (String str : deleteElements) {
      System.out.println("Deleting " + str);
      delete(str);
    }
    for (String str : elements) {
      System.out.println("Searching " + str);
      System.out.println(search(str));
    }
  }

}