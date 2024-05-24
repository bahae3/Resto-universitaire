package database;

import objects.MenuObject;

import java.sql.*;
import java.util.ArrayList;

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

    public static boolean userSignup(String prenomUser, String nomUser, String emailUser, String mdpUser, Integer codeUser) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            // Stocking data to a table
            String users = "INSERT INTO user (nom, prenom, email, mdp, codeUser, estPersonnel) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            // making the first letter capitalized
            String nomUserCapitalized = nomUser.substring(0, 1).toUpperCase() + nomUser.substring(1);
            preparedStatement.setString(1, nomUserCapitalized);

            String prenomUserCapitalized = prenomUser.substring(0, 1).toUpperCase() + prenomUser.substring(1);
            preparedStatement.setString(2, prenomUserCapitalized);
            preparedStatement.setString(3, emailUser);
            preparedStatement.setString(4, mdpUser);
            preparedStatement.setInt(5, codeUser);
            preparedStatement.setBoolean(6, false);
            int rowsInserted = preparedStatement.executeUpdate();

            // Check if insertion was successful
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public static ArrayList<MenuObject> selectMenu(int idCategorie) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";
        ArrayList<MenuObject> menuItems = new ArrayList<>();
        ResultSet resultSet = null; // Initialize to null
        try {
            // Establishing the connection
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
            // Fetching data from a table
            String query = "SELECT * FROM menu WHERE (jourMenu IS NULL OR jourMenu=CURDATE()) and idCategorie=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCategorie);
            resultSet = preparedStatement.executeQuery();

            // Process the ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("idMenu");
                String nom = resultSet.getString("nom");
                String jourMenu = resultSet.getString("jourMenu");
                String etatLivraison = "En cours de preparation";
                double prix = resultSet.getDouble("prix");
                menuItems.add(new MenuObject(id, 0, nom, nom, jourMenu, etatLivraison, prix));
            }

            // Close the connections
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception properly
            // You may want to log the error or display a message to the user
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return menuItems;
    }


}
