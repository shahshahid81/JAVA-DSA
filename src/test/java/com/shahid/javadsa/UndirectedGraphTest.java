package com.shahid.javadsa;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Undirected Graph Test")
public class UndirectedGraphTest {
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
  @DisplayName("Should add vertex in the graph")
  public void testInsertVertex() {
    UndirectedGraph<Character> graph = new UndirectedGraph<>();
    graph.insertVertex('A');
    graph.print();
    Assertions.assertEquals(outContent.toString().trim(), "A");
  }

  @Test
  @DisplayName("Should remove vertex in the graph")
  public void testRemoveVertex() {
    UndirectedGraph<Character> graph = new UndirectedGraph<>();
    graph.insertEdge('A', 'B');
    graph.print();
    Assertions.assertEquals(outContent.toString().trim(), "A -> B\nB -> A");

    outContent.reset();
    graph.removeVertex('A');
    graph.removeVertex('B');
    graph.print();
    Assertions.assertEquals(outContent.toString().trim(), "");
  }

  @Test
  @DisplayName("Should add edge in the graph")
  public void testInsertEdge() {
    UndirectedGraph<Character> graph = new UndirectedGraph<>();
    graph.insertEdge('A', 'B');
    graph.print();
    Assertions.assertEquals(outContent.toString().trim(), "A -> B\nB -> A");
  }

  @Test
  @DisplayName("Should remove edge in the graph")
  public void testRemoveEdge() {
    UndirectedGraph<Character> graph = new UndirectedGraph<>();
    graph.insertEdge('A', 'B');
    graph.print();
    Assertions.assertEquals(outContent.toString().trim(), "A -> B\nB -> A");

    outContent.reset();
    graph.removeEdge('A', 'B');
    graph.print();
    Assertions.assertEquals(outContent.toString().trim(), "A\nB");
  }

  @Test
  @DisplayName("Should traverse graph breadth first")
  public void testBfs() {
    UndirectedGraph<Integer> graph = new UndirectedGraph<>();
    graph.insertEdge(2, 0);
    graph.insertEdge(2, 3);
    graph.insertEdge(0, 1);
    graph.insertEdge(0, 2);
    graph.insertEdge(1, 2);
    graph.insertEdge(3, 3);
    graph.bfs();

    Assertions.assertEquals(outContent.toString(), "2\t0\t3\t1\t");
  }
}
