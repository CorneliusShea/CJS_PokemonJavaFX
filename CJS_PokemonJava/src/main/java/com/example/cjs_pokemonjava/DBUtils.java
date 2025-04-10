package com.example.cjs_pokemonjava;

import java.sql.*;

public class DBUtils
{
    public static Connection connectDB() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon_game", "root", "password");
    }

    public static void addUser(String username, String password) throws SQLException
    {
        Connection connect = connectDB();

        String sql = "INSERT INTO users (name, password) VALUES (?, ?)";

        try
        {
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, username);
            prepare.setString(2, password);
            prepare.executeUpdate();

        }
        catch (Exception e) {e.printStackTrace();}
    }

    public static boolean findUser(String username, String password) throws SQLException
    {
        Connection connect = connectDB();

        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";

        try
        {
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, username);
            prepare.setString(2, password);

            ResultSet rs = prepare.executeQuery();;

            return rs.next();

        }
        catch (Exception e) {e.printStackTrace();}

        return false;
    }

    public static Pokemon getPokemon(String name) throws SQLException {
        Connection connect = connectDB();

        String sql = "SELECT * FROM pokemon WHERE name = ?";

        try
        {
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, name);

            ResultSet rs = prepare.executeQuery();

            //Pokemon newPokemon = Pokemon()

            return

        }
        catch (Exception e) {e.printStackTrace();}

        return null;
    }

    /*
    public static void loadBattleStage(ArrayList<Player> playerList) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
        Parent root = loader.load();

        DashboardController controller = loader.getController();
        controller.setUpNewGame(playerList);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("User Dashboard");
        stage.show();
    }
     */


}
