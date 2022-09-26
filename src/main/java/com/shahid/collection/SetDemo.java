package com.shahid.collection;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Set;

public abstract class SetDemo {
    public static<T> void printValues(@NotNull Set<T> set) {
        Iterator<T> it = set.iterator();
        if(it.hasNext()) {
            System.out.println("Values in set are:");
        }
        while (it.hasNext()) {
            System.out.println("\t" + it.next());
        }
    }
}
