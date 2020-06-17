package com.exam;


import lombok.Data;

@Data
public class Triangle {
    private int id;
    private int x1;
    private int y1;

    private int x2;
    private int y2;

    private int x3;
    private int y3;

    public String toString() {
        return "<" + id + "> " +
                "(" + x1 + ", " + y1 + "), " +
                "(" + x2 + ", " + y2 + "), " +
                "(" + x3 + ", " + y3 + ") ";
    }
}
