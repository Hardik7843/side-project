import java.sql.*;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/jdbc_demo";
    private static final String user = "root";
    private static final String password = "root";

    /**
     * Reads users from database connection.
     */
    public static void readUsers() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * from users";
            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                System.out.println("users table is empty");
                return;
            }

            do {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("____________________");
                System.out.println("| id: " + id + " | name: " + name + " |");
            } while (resultSet.next());
            System.out.println("____________________");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds users to users table.
     */
    public static void addUsers() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "INSERT INTO users (id, name) VALUES (2, 'Alice'),(3, 'Bob')";
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Reading Inserted Users");
            readUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Truncate users from user table.
     */
    public static void truncateUsers() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "TRUNCATE TABLE users";
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Reading Truncated Users");
            readUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds users to users table with prepared statement.
     */
    public static void addUsersPreparedStmt() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO users (name, id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, "Alice");
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Bob");
            preparedStatement.setInt(2, 2);
            preparedStatement.executeUpdate();
            connection.close();

            System.out.println("Reading Inserted Users");
            readUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates user where id = 1 using PreparedStatement.
     */
    public static void updateUserPreparedStmt() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "UPDATE users SET name = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, "Alice Khan Pandey");
            preparedStatement.setInt(2, 1);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsAffected);

            connection.close();
            readUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes user where id = 1 using PreparedStatement.
     */
    public static void deleteUserPreparedStmt() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, 1);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsAffected);

            connection.close();
            readUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Batch insert using normal Statement.
     */
    public static void addUsersBatch() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            statement.addBatch("INSERT INTO users (id, name) VALUES (1, 'Alice')");
            statement.addBatch("INSERT INTO users (id, name) VALUES (2, 'Bob')");
            statement.addBatch("INSERT INTO users (id, name) VALUES (3, 'Charlie')");
            statement.addBatch("INSERT INTO users (id, name) VALUES (4, 'David')");
            statement.addBatch("INSERT INTO users (id, name) VALUES (5, 'Eve')");

            int[] results = statement.executeBatch();

            System.out.println("Batch inserted (Statement): " + results.length + " records");

            connection.close();
            readUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Batch insert using PreparedStatement with loop.
     */
    public static void addUserPreparedBatch() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO users (id, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};

            for (int i = 0; i < names.length; i++) {
                preparedStatement.setInt(1, i + 1);      // id
                preparedStatement.setString(2, names[i]); // name
                preparedStatement.addBatch();
            }

            int[] results = preparedStatement.executeBatch();

            System.out.println("Batch inserted: " + results.length + " records");

            connection.close();
            readUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        readUsers();
//        addUsers();
//        truncateUsers();

//        addUsersPreparedStmt();
//        updateUserPreparedStmt();
//        deleteUserPreparedStmt();

//        addUsersBatch();
//        addUserPreparedBatch();
    }
}