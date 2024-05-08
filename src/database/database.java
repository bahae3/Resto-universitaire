package database;

import java.sql.*;

public class database {
    public static boolean userLogin(String userEmail, String userMdp) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try {
            // Establishing the connection
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
            // Fetching data from a table
            String users = "SELECT email, mdp FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Process each row of the result set
                String usernameSQL = resultSet.getString("email");
                String passwordSQL = resultSet.getString("mdp");

                // Retrieve other columns similarly
                if (usernameSQL.equals(userEmail) && passwordSQL.equals(userMdp)) {
                    return true;
                }
            }

            // Close the connections
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static int isAdmin(String userEmail, String userMdp) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try {
            // Establishing the connection
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
            // Fetching data from a table
            String users = "SELECT email, mdp FROM user WHERE estPersonnel=1";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Process each row of the result set
                String usernameSQL = resultSet.getString("email");
                String passwordSQL = resultSet.getString("mdp");

                // Retrieve other columns similarly
                if (usernameSQL.equals(userEmail) && passwordSQL.equals(userMdp)) {
                    return 1;
                }
            }

            // Close the connections
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            return 0;
        }
        return 0;
    }

}
