import java.sql.*;

public class Demo {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "postgres";       // your PostgreSQL username
        String password = "@Harshul135"; // your PostgreSQL password

        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connection successful!");

            Statement stmt = con.createStatement();

            // ---------------- CREATE ----------------
            // Create table if not exists
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS student (" +
                    "sid SERIAL PRIMARY KEY, " +
                    "sname VARCHAR(100), " +
                    "marks INT)");
            System.out.println("✅ Table ensured!");

            // Insert new student
            stmt.executeUpdate("INSERT INTO student (sname, marks) VALUES ('Amit', 85)");
            stmt.executeUpdate("INSERT INTO student (sname, marks) VALUES ('Riya', 92)");
            stmt.executeUpdate("INSERT INTO student (sname, marks) VALUES ('Karan', 67)");
            System.out.println("✅ Sample data inserted!");

            // ---------------- READ ----------------
            System.out.println("\n🎓 Student Records (READ):");
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                int id = rs.getInt("sid");
                String name = rs.getString("sname");
                int marks = rs.getInt("marks");
                System.out.println("ID: " + id + " | Name: " + name + " | Marks: " + marks);
            }
            rs.close();

            // ---------------- UPDATE ----------------
            // Update marks of a student
            int updated = stmt.executeUpdate("UPDATE student SET marks = 95 WHERE sname = 'Amit'");
            if (updated > 0) {
                System.out.println("\n✏️ Updated Amit's marks to 95!");
            }

            // ---------------- DELETE ----------------
            // Delete a student record
            int deleted = stmt.executeUpdate("DELETE FROM student WHERE sname = 'Karan'");
            if (deleted > 0) {
                System.out.println("\n🗑 Deleted student Karan!");
            }

            // ---------------- FINAL READ ----------------
            System.out.println("\n📌 Final Student Records:");
            rs = stmt.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                int id = rs.getInt("sid");
                String name = rs.getString("sname");
                int marks = rs.getInt("marks");
                System.out.println("ID: " + id + " | Name: " + name + " | Marks: " + marks);
            }
            rs.close();

            // Close resources
            stmt.close();
            con.close();
            System.out.println("\n✅ CRUD operations completed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
