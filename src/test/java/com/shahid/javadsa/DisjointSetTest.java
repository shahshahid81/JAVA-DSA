package com.shahid.javadsa;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Disjoint Set Test")
public class DisjointSetTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  @DisplayName("Should throw error if null is passed as list.")
  public void testNullListInitialization() {
    IllegalArgumentException nullListInitializationException = Assertions.assertThrows(IllegalArgumentException.class, () -> new DisjointSet<>(null));
    Assertions.assertEquals(nullListInitializationException.getMessage(), "Node list is required");
  }

  @Test
  @DisplayName("Should perform union and find operation")
  public void testUnionAndFind() {
    ArrayList<Character> characterList = new ArrayList<>(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'));
    DisjointSet<Character> disjointSet = new DisjointSet<>(characterList);
    disjointSet.union('A', 'B');
    Assertions.assertEquals(disjointSet.find('A'), 'A');
    Assertions.assertEquals(disjointSet.find('B'), 'A');
    disjointSet.union('C', 'D');
    disjointSet.union('E', 'A');
    disjointSet.union('E', 'C');
    Assertions.assertEquals(disjointSet.find('A'), 'E');
    Assertions.assertEquals(disjointSet.find('C'), 'E');
    Assertions.assertEquals(disjointSet.find('B'), 'E');
    disjointSet.union('H', 'E');
    Assertions.assertEquals(disjointSet.find('B'), 'H');
    Assertions.assertEquals(disjointSet.find('G'), 'G');
  }
}
