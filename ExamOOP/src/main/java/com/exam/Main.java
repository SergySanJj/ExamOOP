package com.exam;

public class Main {
    public static void main(String[] args) {
        System.out.println(TrianglesOperations.getAllTriangles());
        System.out.println("Closest: " + TrianglesOperations.getClosestSurfaceTo(3));
        System.out.println("Closest sum: " + TrianglesOperations.getClosestSum(10));

    }
}
