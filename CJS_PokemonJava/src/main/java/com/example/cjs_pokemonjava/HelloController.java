package com.example.cjs_pokemonjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    private void submitLogIn() throws SQLException
    {
        TextField logUser = logUsername_txt;
        TextField logPass = logPassword_txt;

        // checks if fields are empty
        if(!(logUser.getText().isEmpty() && logPass.getText().isEmpty()))
        {
            DBUtils.findUser(logUser.getText(), logPass.getText());

            logInGood_label.setText("Log In Successful!");
            logInGood_label.setVisible(true);

            home_pane.setVisible(false);
            home_pane.setDisable(true);

            pokemon_pane.setVisible(true);
            pokemon_pane.setDisable(false);

        }
        else //if any field is empty they failed log in process
        {
            logInGood_label.setText("Log In Failed!");
            logInGood_label.setVisible(true);
        }
    }

    @FXML
    private void selectPokemonButton(MouseEvent event) throws SQLException
    {
        //choose a pokemon for the game they will play

        //this is the getting the image view and its mouse event
        ImageView img = (ImageView) event.getSource();

        //this is getting the FXID from the image view
        String id = img.getId();


        //switch case that figures out which pokemon the player chose
        // based off of the FXID grabbed
        switch (id) {
            case "bulbasaur":
                System.out.println("Bulbasaur");
                break;
            case "charmander":
                System.out.println("Charmander");
                break;
            case "squirtle":
                System.out.println("Squirtle");
                break;
            case "turtwig":
                System.out.println("Turtwig");
                break;
            case "chimchar":
                System.out.println("Chimchar");
                break;
            case "piplup":
                System.out.println("Piplup");
                break;
        }
    }



}