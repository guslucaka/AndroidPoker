package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews(){
        onSavedHandsClick();
        onNewHandClick();
        onSettingsClick();
    }

    private void onSavedHandsClick(){
        final Button savedHandsButton = findViewById(R.id.saved_hands_button);
        savedHandsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SavedHandsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onNewHandClick(){
        final Button newHandButton = findViewById(R.id.new_hand_button);
        newHandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Session session = Session.getInstance();

                session.handInfo = null;
                session.players.clear();
                session.streets.clear();
                session.communityCards.clear();
                session.noOfPlayers = null;

                Log.d("onNewHandClick()", "HandInfo: " + session.handInfo);
                Log.d("onNewHandClick()", "playerList size: " + session.players.size());
                Log.d("onNewHandClick()", "cardList size: " + session.communityCards.size());
                Log.d("onNewHandClick()", "streetList size: " + session.streets.size());

                Intent intent = new Intent(MainActivity.this, NewHandActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onSettingsClick(){
        final Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }


}
