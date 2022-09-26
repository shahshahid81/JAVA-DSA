package com.shahid.collection;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Queue;

public abstract class QueueDemo extends BaseDemo {
    public static<T> void printValues(@NotNull Queue<T> queue) {
        Iterator<T> it = queue.iterator();
        if(it.hasNext()) {
            System.out.println("Values in queue are:");
        }
        while (it.hasNext()) {
            System.out.println("\t" + it.next());
        }
    }
}
