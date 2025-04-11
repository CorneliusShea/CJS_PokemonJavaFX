package com.example.cjs_pokemonjava;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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

    public static boolean findIfUser(String username, String password) throws SQLException
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

    public static Users getUser(String username, String password) throws SQLException
    {
        Connection connect = connectDB();

        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";

        try
        {
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setString(1, username);
            prepare.setString(2, password);

            return new Users(username, password);

        }
        catch (Exception e) {e.printStackTrace();}

        return null;
    }

    public static ArrayList<Pokemon> getPokemon() throws SQLException {
        Connection connect = connectDB();

        String sql = "SELECT * FROM pokemon";

        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

        try
        {
            PreparedStatement prepare = connect.prepareStatement(sql);

            ResultSet rs = prepare.executeQuery();



            while(rs.next())
            {
                Pokemon newPokemon = new Pokemon(rs.getInt("id"), rs.getString("name"), rs.getInt("health"), rs.getInt("move1_id"), rs.getInt("move2_id"));
                pokemons.add(newPokemon);
            }

            //System.out.println(pokemons.size());
            return pokemons;

        }
        catch (Exception e) {e.printStackTrace();}

        return null;
    }

    public static ArrayList<Move> getMoves() throws SQLException
    {
        Connection connect = connectDB();

        String sql = "SELECT * FROM moves";

        ArrayList<Move> moveList = new ArrayList<Move>();

        try
        {
            PreparedStatement prepare = connect.prepareStatement(sql);

            ResultSet rs = prepare.executeQuery();

            while(rs.next())
            {
                Move newMove = new Move(rs.getInt("id"), rs.getString("name"), rs.getInt("damage"));
                moveList.add(newMove);
            }

            System.out.println("Move list length: " + moveList.size());
            return moveList;

        }
        catch (Exception e) {e.printStackTrace();}

        return null;
    }

    public static void loadBattleStage(Stage currentStage, Users currentUser, Pokemon playerPokemon, Pokemon enemyPokemon, Move move1, Move move2, Move enemyM1, Move enemyM2) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("battle-stage.fxml"));
        Parent root = loader.load();

        BattleController controller = loader.getController();
        controller.setBattleVariables(currentUser, playerPokemon, enemyPokemon, move1, move2, enemyM1, enemyM2);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Battle Stage");
        stage.show();

        // Close the previous window
        currentStage.close();
    }


}
