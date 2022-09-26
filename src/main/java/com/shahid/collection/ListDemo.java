package com.shahid.collection;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class ListDemo extends BaseDemo {
    public static<T> void printValues(@NotNull List<T> list) {
        Iterator<T> it = list.iterator();
        if(it.hasNext()) {
            System.out.println("Values in list are:");
        }
        while (it.hasNext()) {
            System.out.println("\t" + it.next());
        }
    }
}
