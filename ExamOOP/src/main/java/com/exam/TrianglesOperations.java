package com.exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrianglesOperations {
    private static final String jdbcDriverName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/triangles";
    private static final String user = "postgres";
    private static final String password = "dfczdfcz3344";
    private static Logger logger = Logger.getLogger(TrianglesOperations.class.getName());


    public static List<Triangle> getAllTriangles() {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select id,x1,y2,x2,y2,x3,y3 " +
                    "from triangles;";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Triangle> triangles = new ArrayList<>();
            while (rs.next()) {
                triangles.add(map(rs));
            }
            connection.close();
            return triangles;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Triangle getClosestSurfaceTo(int surface) {
        List<Triangle> triangles = getAllTriangles();
        int delta = 10000000;
        Triangle chosen = null;
        for (Triangle t : triangles) {
            if (Math.abs(surface - surface(t)) < delta) {
                delta = Math.abs(surface - surface(t));
                chosen = t;
            }
        }
        return chosen;
    }

    public static List<Triangle> getClosestSum(int surface) {
        List<Triangle> triangles = getAllTriangles();
        Triangle t1 = null;
        Triangle t2 = null;
        int delta = 10000000;
        for (int i = 0; i < triangles.size(); i++) {
            for (int j = 0; j < triangles.size(); j++) {
                if (i != j) {
                    if (Math.abs(surface - surface(triangles.get(i)) - surface(triangles.get(j))) < delta) {
                        delta = Math.abs(surface - surface(triangles.get(i)) - surface(triangles.get(j)));
                        t1 = triangles.get(i);
                        t2 = triangles.get(j);
                    }
                }

            }
        }
        ArrayList<Triangle> tt = new ArrayList<>();
        tt.add(t2);
        tt.add(t1);
        return tt;
    }

    private static int surface(Triangle t) {
        double a = Math.sqrt((t.getX1() - t.getX2()) * (t.getX1() - t.getX2()) +
                (t.getY1() - t.getY2()) * (t.getY1() - t.getY2()));
        double b = Math.sqrt((t.getX1() - t.getX3()) * (t.getX1() - t.getX3()) +
                (t.getY1() - t.getY3()) * (t.getY1() - t.getY3()));
        double c = Math.sqrt((t.getX2() - t.getX3()) * (t.getX2() - t.getX3()) +
                (t.getY2() - t.getY3()) * (t.getY2() - t.getY3()));
        if (a + b <= c || a + c <= b || a + c <= b)
            System.out.println("Does not exist");
        else {
            double p = (a + b + c) / 2.0;
            double square = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            return (int) square;
        }
        return -1;
    }

    public static Triangle map(ResultSet rs) throws SQLException {
        Triangle triangle = new Triangle();
        triangle.setId(rs.getInt(1));
        triangle.setX1(rs.getInt(2));
        triangle.setY1(rs.getInt(3));
        triangle.setX2(rs.getInt(4));
        triangle.setY2(rs.getInt(5));
        triangle.setX3(rs.getInt(6));
        triangle.setY3(rs.getInt(7));
        return triangle;
    }
}
