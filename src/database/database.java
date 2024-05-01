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
            String users = "SELECT email, mdp FROM Users";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Process each row of the result set
                String usernameSQL = resultSet.getString("username");
                String passwordSQL = resultSet.getString("password");

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
            return false;
        }
        return false;
    }

}
