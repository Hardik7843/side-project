import java.security.PrivateKey;
import java.sql.*;

public class Main {
        private static final String url = "jdbc:mysql://localhost:3306/jdbc_demo";
        private static final String user = "root";
        private static final String password = "root";
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * from users";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                System.out.println("id: " + id);
                System.out.println("name: " + name);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}