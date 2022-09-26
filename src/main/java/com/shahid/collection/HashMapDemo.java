package com.shahid.collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 1);
        map.putIfAbsent("John", 2);


        Map<String, Integer> map2 = new HashMap<>();
        map2.put("Michael", 1);
        map2.put("John", 3);
        map.putAll(map2);
        map.remove("John", 5);
        map.remove("Michael");

        System.out.println("Entries of map: ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        map.compute("John", (key, value) -> value != null ? value * 2 : 0);
        map.putIfAbsent("Michael", 10);
        map.computeIfPresent("Wick", (key, value) -> 20);

        map.put("Cena", 11);
        map.put("Cooper", 22);
        map.put("Uchiha", 44);

        System.out.println("Entries of map: ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Map contains John: " + map.containsKey("John"));
        System.out.println("John's value is " + map.get("John"));
    }
}
