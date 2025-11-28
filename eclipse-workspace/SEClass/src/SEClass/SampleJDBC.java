package SEClass;
import java.sql.*;

public class SampleJDBC {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/school";
        String user = "root";
        String password = "521Annadale!";
        String driverClass = "com.mysql.cj.jdbc.Driver"; // For Connector/J 8.0 and newer
        String query = "SELECT * FROM procedures";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. Register the Driver (Class.forName is still supported, but not required for JDBC 4.0+)
            // For Connector/J 9.4.0, this is still fine, but you might see it omitted in newer code
            Class.forName(driverClass);

            // 2. Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");

            // 3. Create a statement
            statement = connection.createStatement();

            // 4. Execute the query
            resultSet = statement.executeQuery(query);

            // 5. Process the results
            while (resultSet.next()) {
             String name = resultSet.getString("Name"); 
             String procedureID = resultSet.getString("Procedure ID");
             String baseCost = resultSet.getString("Base Cost");
             String category = resultSet.getString("Category");
            	 System.out.print(name + ", ");
            	 System.out.print(procedureID + ", ");
            	 System.out.print(baseCost + ", ");
            	 System.out.println(category);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error loading the JDBC driver: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 6. Close the connection, statement, and result set
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}