package com.example.cjs_pokemonjava;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BattleController
{
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;
    private Users currentUser;

    @FXML
    private AnchorPane attackMoves_pane, startBattle_anchor, battleStage_pane;

    @FXML
    private Label playerPokemonName_label, playerHP_label;

    @FXML
    private Label playerName_label;

    @FXML
    private Label enemyPokemonName_label, enemyHP_label;

    @FXML
    private Move move_1, move_2, enemyM1, enemyM2;

    @FXML
    private Label damageAmt_1_label, damageAmt_2_label, moveName_1_label, moveName_2_label;

    private boolean endTurn = false;

    public void setBattleVariables(Users currentUserInput, Pokemon playerPokemon, Pokemon enemyPokemon, Move move1, Move move2, Move enemyM1, Move enemyM2)
    {
        this.currentUser = currentUserInput;
        this.enemyPokemon = enemyPokemon;
        this.playerPokemon = playerPokemon;

        move_1 = move1;
        move_2 = move2;

        this.enemyM1 = enemyM1;
        this.enemyM2 = enemyM2;


    }


    @FXML
    private void setUpBattlePanels()
    {
        startBattle_anchor.setVisible(false);
        battleStage_pane.setVisible(true);

        System.out.println(currentUser.getUsername());

        //Setting player label values
        playerPokemonName_label.setText(currentUser.getUsername());
        playerPokemonName_label.setText(playerPokemon.getPokemonName());
        playerHP_label.setText(String.valueOf(playerPokemon.getPokemonHealth()));
        playerName_label.setText(String.valueOf(currentUser.getUsername()));

        //Setting enemy label values
        enemyPokemonName_label.setText(enemyPokemon.getPokemonName());
        enemyHP_label.setText(String.valueOf(enemyPokemon.getPokemonHealth()));

        //Setting player attack panel values
        moveName_1_label.setText(move_1.getMoveName());
        moveName_2_label.setText(move_2.getMoveName());

        damageAmt_1_label.setText(String.valueOf(move_1.getMoveDamage()));
        damageAmt_2_label.setText(String.valueOf(move_2.getMoveDamage()));


    }

    @FXML
    private void attackPanelButtonVisibility()
    {
        if(attackMoves_pane.visibleProperty().getValue())
        {
            attackMoves_pane.setVisible(false);
        }
        else
        {
            attackMoves_pane.setVisible(true);
        }

    }


    @FXML
    public void attackEnemy(MouseEvent event) {
        AnchorPane anchorPane = (AnchorPane) event.getSource();

        //this is getting the FXID from the image view
        String id = anchorPane.getId();

        if(!endTurn)
        {
            switch (id) {
                case "move1_pane":
                    enemyPokemon.setPokemonHealth(enemyPokemon.getPokemonHealth() - move_1.getMoveDamage());
                    enemyHP_label.setText(checkIfDead(enemyPokemon));

                    break;
                case "move2_pane":
                    enemyPokemon.setPokemonHealth(enemyPokemon.getPokemonHealth() - move_2.getMoveDamage());
                    enemyHP_label.setText(checkIfDead(enemyPokemon));

                    break;
            }
            endTurn = true;

        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                playerPokemon.setPokemonHealth(playerPokemon.getPokemonHealth() - enemyM1.getMoveDamage());
                playerHP_label.setText(checkIfDead(playerPokemon));
            }
        };

        long delay = 5000; // Delay in milliseconds (3 seconds)
        timer.schedule(task, delay);

        endTurn = false;

    }

    private String checkIfDead(Pokemon pokemon)
    {
        if(pokemon.getPokemonHealth() < 0)
        {
            System.out.println("end game");
            return "0";
        }
        else
        {
            return String.valueOf(pokemon.getPokemonHealth());
        }
    }

    /*
    WHAT YOU WERE DOING LAST CYCLE:

    -YOU WANT THERE TO BE A TIMER AFTER YOU ATTACK SO IT DOES NOT LOOK LIKE YOU AND THE ENEMY
    ATTACK AT THE SAME TIME. MR RILEY SUGGESTS TO JUST USE AN ALERT INSTEAD.

    -YOU ALSO NEED TO CHANGE THE IMAGE VIEWS FOR EACH POKEMON
    WHEN THE PLAYER CHOOSES THEM RESPECTIVELY

     */
}
