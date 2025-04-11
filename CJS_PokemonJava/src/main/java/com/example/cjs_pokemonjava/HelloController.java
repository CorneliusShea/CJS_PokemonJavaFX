package com.example.cjs_pokemonjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class HelloController
{
    //sign up text areas
    @FXML
    private TextField signConfirmPass_txt, signPassword_txt, signUsername_txt;

    @FXML
    private TextField logUsername_txt, logPassword_txt;

    @FXML
    private Label signUpGood_label, logInGood_label;

    @FXML
    private AnchorPane home_pane, pokemon_pane;

    @FXML
    private Button submitLogIn_btn;

    private ArrayList<Pokemon> pokemonList;

    private ArrayList<Move> moveList;

    private Users currentUser;

    BattleController battleController = new BattleController();


    @FXML
    private void submitSignUp() throws SQLException
    {
        TextField signUser = signUsername_txt;
        TextField signPass = signPassword_txt;
        TextField confirmPass = signConfirmPass_txt;

        //if fields are not empty
        if( !(signUser.getText().isEmpty() && signPass.getText().isEmpty() && confirmPass.getText().isEmpty()))
        {
            if(confirmPass.getText().equals(signPass.getText()))
            {
                DBUtils.addUser(signUser.getText(), signPass.getText());
                signUpGood_label.setText("Sign Up Successful");
                signUpGood_label.setVisible(true);
            }
            else //if passwords do not match they failed sign up process
            {
                signUpGood_label.setText("Sign Up Failed");
                signUpGood_label.setVisible(true);
            }
        }
        else //if fields are empty they failed sign up process
        {

            signUpGood_label.setText("Sign Up Failed");
            signUpGood_label.setVisible(true);
        }
    }

    @FXML
    private void submitLogIn() throws SQLException, IOException {
        TextField logUser = logUsername_txt;
        TextField logPass = logPassword_txt;

        // checks if fields are empty
        if(!(logUser.getText().isEmpty() && logPass.getText().isEmpty()))
        {
            if(DBUtils.findIfUser(logUser.getText(), logPass.getText()))
            {

                currentUser = new Users(logUser.getText(), logPass.getText());

                logInGood_label.setText("Log In Successful!");
                logInGood_label.setVisible(true);

                home_pane.setVisible(false);
                home_pane.setDisable(true);

                pokemon_pane.setVisible(true);
                pokemon_pane.setDisable(false);

                pokemonList = DBUtils.getPokemon();
                moveList = DBUtils.getMoves();






            }
        }
        else //if any field is empty they failed log in process
        {
            logInGood_label.setText("Log In Failed!");
            logInGood_label.setVisible(true);
        }
    }

    @FXML
    private void selectPokemonButton(MouseEvent event) throws SQLException, IOException {
        //this has the player choose a pokemon for the game they will play

        //set move variables
        Move move_1;
        Move move_2;

        Move enemyM1;
        Move enemyM2;

        Pokemon enemy = getRandomEnemyPokemon();
        enemyM1 = moveList.get(enemy.getMoves()[0]);
        enemyM2 = moveList.get(enemy.getMoves()[1]);



        //this is the getting the image view and its mouse event
        ImageView img = (ImageView) event.getSource();

        //this is getting the FXID from the image view
        String id = img.getId();


        //switch case that figures out which pokemon the player chose
        // based off of the FXID grabbed
        switch (id) {
            case "bulbasaur":
                System.out.println("Bulbasaur");

                Pokemon bulbasaur = pokemonList.get(0);
                move_1 = moveList.get(bulbasaur.getMoves()[0]);
                move_2 = moveList.get(bulbasaur.getMoves()[1]);


                DBUtils.loadBattleStage((Stage) submitLogIn_btn.getScene().getWindow(), currentUser, bulbasaur, enemy, move_1, move_2, enemyM1, enemyM2);
                break;
            case "charmander":
                System.out.println("Charmander");
                Pokemon charmander = pokemonList.get(1);

                move_1 = moveList.get(charmander.getMoves()[0]);
                move_2 = moveList.get(charmander.getMoves()[1]);




                DBUtils.loadBattleStage((Stage) submitLogIn_btn.getScene().getWindow(), currentUser, charmander, getRandomEnemyPokemon(), move_1, move_2, enemyM1, enemyM2);
                break;
            case "squirtle":
                System.out.println("Squirtle");
                Pokemon squirtle = pokemonList.get(2);

                move_1 = moveList.get(squirtle.getMoves()[0]);
                move_2 = moveList.get(squirtle.getMoves()[1]);




                DBUtils.loadBattleStage((Stage) submitLogIn_btn.getScene().getWindow(), currentUser, squirtle, getRandomEnemyPokemon(), move_1, move_2, enemyM1, enemyM2);
                break;
            case "turtwig":
                System.out.println("Turtwig");
                Pokemon turtwig = pokemonList.get(3);

                move_1 = moveList.get(turtwig.getMoves()[0]);
                move_2 = moveList.get(turtwig.getMoves()[1]);

                DBUtils.loadBattleStage((Stage) submitLogIn_btn.getScene().getWindow(), currentUser, turtwig, getRandomEnemyPokemon(), move_1, move_2, enemyM1, enemyM2);

                break;
            case "chimchar":
                System.out.println("Chimchar");
                Pokemon chimchar = pokemonList.get(4);

                move_1 = moveList.get(chimchar.getMoves()[0]);
                move_2 = moveList.get(chimchar.getMoves()[1]);


                DBUtils.loadBattleStage((Stage) submitLogIn_btn.getScene().getWindow(), currentUser, chimchar, getRandomEnemyPokemon(), move_1, move_2, enemyM1, enemyM2);
                break;
            case "piplup":
                System.out.println("Piplup");
                Pokemon piplup = pokemonList.get(5);

                move_1 = moveList.get(piplup.getMoves()[0]);
                move_2 = moveList.get(piplup.getMoves()[1]);


                DBUtils.loadBattleStage((Stage) submitLogIn_btn.getScene().getWindow(), currentUser, piplup, getRandomEnemyPokemon(), move_1, move_2, enemyM1, enemyM2);
                break;
        }
    }

    private Pokemon getRandomEnemyPokemon() throws SQLException {
        Random rand = new Random();

        int pokemonNum = rand.nextInt(pokemonList.size());

        return pokemonList.get(pokemonNum);
    }



}