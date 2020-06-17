package com.exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrianglesDAO {
    private static final String jdbcDriverName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/triangles";
    private static final String user = "postgres";
    private static final String password = "dfczdfcz3344";
    private static Logger logger = Logger.getLogger(TrianglesDAO.class.getName());


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

    public static List<Triangle> closestSurfaceTo(int surface){
        return new ArrayList<>();
    }

    private static int surface(){
        return  1;
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
