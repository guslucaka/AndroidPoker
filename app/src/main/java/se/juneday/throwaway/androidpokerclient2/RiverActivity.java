package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class RiverActivity extends AppCompatActivity {

    private class PlayerAction {
        private Spinner playersSpinner = new Spinner(RiverActivity.this);
        private Spinner actions = new Spinner(RiverActivity.this);
        private String[] actionStrings = new String[]{"Bet", "Post", "Call", "Fold", "Allin"};
        private EditText amount = new EditText(RiverActivity.this);
        LinearLayout innerLayout = new LinearLayout(RiverActivity.this);

        public PlayerAction(List<Player> players) {
            ArrayAdapter<String> playersAdapter = new ArrayAdapter<>(RiverActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, playersToNames(players));
            playersSpinner.setAdapter(playersAdapter);
            ArrayAdapter<String> actionsAdapter = new ArrayAdapter<>(RiverActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, actionStrings);
            actions.setAdapter(actionsAdapter);
            innerLayout.setOrientation(LinearLayout.HORIZONTAL);
            innerLayout.addView(playersSpinner);
            innerLayout.addView(actions);
            innerLayout.addView(amount);
        }
    }


    static List<String> playersToNames(List<Player> players) {
        List<String> result = new ArrayList<>();
        for (Player p : players) {
            result.add(p.name());
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river);
        initViews();
    }

    void initViews(){

        onSaveHandClick();
        onPlusClick();

        List<Player> players = Session.getInstance().players;
        LinearLayout layout = findViewById(R.id.player_list);
        layout.removeAllViews();
        RiverActivity.PlayerAction pa = new RiverActivity.PlayerAction(players);
        layout.addView(pa.innerLayout);
    }

    private void onPlusClick() {
        final Button plusButton = findViewById(R.id.new_action_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Player> players = Session.getInstance().players;
                LinearLayout layout = findViewById(R.id.player_list);
                RiverActivity.PlayerAction pa = new RiverActivity.PlayerAction(players);
                layout.addView(pa.innerLayout);
            }
        });
    }

    private void onSaveHandClick() {
        final Button nextButton = findViewById(R.id.save_hand_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiverActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
