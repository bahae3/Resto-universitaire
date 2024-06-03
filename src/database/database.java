package database;

import objects.CommandeObject;
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

    public static int getUserId(String userEmail, String userMdp) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try {
            // Establishing the connection
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
            // Fetching data from a table
            String users = "SELECT idUser FROM user WHERE email=? AND mdp=?";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            // these setString methods replace the "?" in var users query
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, userMdp);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idUser = 0;
            if (resultSet.next()) {
                idUser = resultSet.getInt("idUser");
            }
            // Close the connections
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return idUser;
        } catch (SQLException e) {
            e.printStackTrace();
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
            String query;
            PreparedStatement preparedStatement;
            // Fetching data from a table
            if (idCategorie != 0) {
                query = "SELECT * FROM menu WHERE (jourMenu IS NULL OR jourMenu=CURDATE()) and idCategorie=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idCategorie);
            } else {
                query = "SELECT * FROM menu";
                preparedStatement = connection.prepareStatement(query);
            }
            resultSet = preparedStatement.executeQuery();

            // Process the ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("idMenu");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                String jourMenu = resultSet.getString("jourMenu");
                String etatLivraison = "En cours de preparation";
                double prix = resultSet.getDouble("prix");
                menuItems.add(new MenuObject(id, 0, nom, nom, description, jourMenu, etatLivraison, prix));
            }

            // Close the connections
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public static boolean insertMenuToDb(String nom, int idCategorie, String description, String jour, double prix) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            // Stocking data to a table
            String users = "INSERT INTO menu (idCategorie, nom, description, jourMenu, prix) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            preparedStatement.setInt(1, idCategorie);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, jour);
            preparedStatement.setFloat(5, (float) prix);
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

    public static boolean updateItem(int idPlat, String nomPlat, int idCategorie, String description, String jourPlat, double prixPlat) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            // Stocking data to a table
            String users = """
                    UPDATE menu
                    SET idCategorie=?, nom=?, description=?, jourMenu=?, prix=?
                    WHERE idMenu=?""";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            preparedStatement.setInt(1, idCategorie);
            preparedStatement.setString(2, nomPlat);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, jourPlat);
            preparedStatement.setFloat(5, (float) prixPlat);
            preparedStatement.setInt(6, idPlat);
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

    public static boolean deleteItem(int itemId) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            // Stocking data to a table
            String itemToDelete = "DELETE FROM menu WHERE idMenu=?";
            PreparedStatement preparedStatement = connection.prepareStatement(itemToDelete);
            preparedStatement.setString(1, String.valueOf(itemId));
            int rowsInserted = preparedStatement.executeUpdate();

            // Check if insertion was successful
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;


    }

    public static boolean insertCommande(int idMenu, int idUser, int quantite, String etat, int numCommande) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            // Stocking data to a table
            String users = "INSERT INTO commande (idMenu, idUser, quantite, etat, numCommande) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            preparedStatement.setInt(1, idMenu);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setInt(3, quantite);
            preparedStatement.setString(4, etat);
            preparedStatement.setInt(5, numCommande);
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

    public static ArrayList<CommandeObject> selectCommande(int idUser) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";
        ArrayList<CommandeObject> commandeItems = new ArrayList<>();
        ResultSet resultSet = null; // Initialize to null
        try {
            // Establishing the connection
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
            String query;
            PreparedStatement preparedStatement;
            if (idUser == 0) {
                // Fetching data from a table
                query = """
                        SELECT *
                        FROM Commande c
                        JOIN User u ON c.idUser = u.idUser
                        JOIN Menu m ON c.idMenu = m.idMenu\s
                        """;
                preparedStatement = connection.prepareStatement(query);
            } else {
                // Fetching data from a table
                query = """
                        SELECT *
                        FROM Commande c
                        JOIN User u ON c.idUser = u.idUser
                        JOIN Menu m ON c.idMenu = m.idMenu\s
                        WHERE u.idUser=?""";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idUser);
            }

            resultSet = preparedStatement.executeQuery();
            // Process the ResultSet
            while (resultSet.next()) {
                int idCommande = resultSet.getInt("idCommande");
                int idMenu = resultSet.getInt("idMenu");
                int quantite = resultSet.getInt("quantite");
                String nomMenu = resultSet.getString("m.nom");
                String etatLivraison = resultSet.getString("etat");
                double prix = resultSet.getDouble("prix");
                int numCommande = resultSet.getInt("numCommande");
                commandeItems.add(new CommandeObject(idCommande, idMenu, nomMenu, prix, idUser, quantite, etatLivraison, numCommande));
            }

            // Close the connections
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return commandeItems;
    }


    public static boolean modifierEtatCommande(int idCommande) {
        // Connecting to database
        String url = "jdbc:mysql://localhost:3306/resto_univ";
        String usernameDB = "root";
        String passwordDB = "";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            // Stocking data to a table
            String users = "UPDATE commande SET etat=? WHERE idCommande=?";
            PreparedStatement preparedStatement = connection.prepareStatement(users);
            preparedStatement.setString(1, "Livré");
            preparedStatement.setInt(2, idCommande);
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
}
