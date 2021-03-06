package a207.fall18.gamecentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * The game choice activity
 */
public class GameChoiceActivity extends AppCompatActivity {

    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);
        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }
        addLogoutButtonListener();
        addSlidingtilesButtonListener();
        addOtherGame1ButtonListener();
        addOtherGame2ButtonListener();
        addscoreBoardButtonListener();
        addruleButtonListener();
    }

    /**
     * Activate the Slidingtiles Button.
     */
    private void addSlidingtilesButtonListener() {
        Button slidingtilesButton = findViewById(R.id.slidingtilesButton);
        slidingtilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToComplexity();
            }
        });
    }

    /**
     * Activate the other game 1 Button.
     */
    private void addOtherGame1ButtonListener() {
        Button otherGame1Button = findViewById(R.id.therGame1Button);
        otherGame1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToast("Sorry, this game is not available~");
            }
        });
    }

    /**
     * Activate the other game 2 Button.
     */
    private void addOtherGame2ButtonListener() {
        Button otherGame2Button = findViewById(R.id.therGame2Button);
        otherGame2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToast("Sorry, this game is not available~");
            }
        });
    }

    /**
     * Activate the Logout Button.
     */
    private void addLogoutButtonListener() {
        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            logout();
            }
        });


    }
    /**
     * Activate the rules Button.
     */
    private void addruleButtonListener() {
        Button ruleButton = findViewById(R.id.ruleButton);
        ruleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userScoreboardScreen =
                        new Intent(GameChoiceActivity.this, GameRulesActivity.class);
                startActivity(userScoreboardScreen);
            }
        });


    }

    /**
     * Activate the scoreBoard Button.
     */
    private void addscoreBoardButtonListener() {
        Button btnscoreBoard = findViewById(R.id.btnscoreboard);
        btnscoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userScoreboardScreen =
                        new Intent(GameChoiceActivity.this, UserScoreboardActivity.class);
                startActivity(userScoreboardScreen);
            }
        });


    }


    /**
     * Switch the screen to choose game complexity.
     */
    private void switchToComplexity() {
        Intent complexityScreen = new Intent(this, GameComplexityActivity.class);
        startActivity(complexityScreen);
    }


    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(GameChoiceActivity.this,LoginActivity.class));
        LoginActivity.currentUser = "";
    }
    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
