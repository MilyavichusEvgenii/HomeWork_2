package org.example;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
//        Employee employee1 = new Employee("Mark", 28);
//        Employee employee2 = new Employee("Mikle", 29);
//        System.out.println(employee1.hashCode());
//        System.out.println(employee2.hashCode());
        hashMap<String, String> hashMap = new hashMap<>();
        String addResult = hashMap.put("880020000", "DDDDDDD");
//        System.out.println(addResult);
        String addResult1 = hashMap.put("880020000", "SSSSSS");
//        System.out.println(addResult1);
        String addResult2 = hashMap.put("880020000", "DDDHHHH");
//        System.out.println(addResult2);
        String addResult3 = hashMap.put("8800200001", "DJJJJGGG");
        String addResult4 = hashMap.put("8800200002", "DJJJJGGG");
        String addResult5 = hashMap.put("8800200003", "DJJJJGGG");
        String addResult6 = hashMap.put("8800200004", "DJJJJGGG");
        String addResult7 = hashMap.put("8800200005", "DJJJJGGG");
        String addResult8 = hashMap.put("8800200006", "DJJJJGGG");
        String addResult9 = hashMap.put("8800200007", "DJJJJGGG");
        String addResult10 = hashMap.put("8800200008", "DJJJJGGG");
//        System.out.println(addResult3);
//        System.out.println(hashMap.get("8800200001"));
//        System.out.println(hashMap);

        for (Iterator<org.example.hashMap.Entity> it = hashMap.iterator(); it.hasNext(); ) {
            org.example.hashMap.Entity e = it.next();
            System.out.println(e.key);
        }
    }
}