package se.juneday.throwaway.androidpokerclient2;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PreflopActivity extends AppCompatActivity {

    private class PlayerAction {
        private Spinner playersSpinner = new Spinner(PreflopActivity.this);
        private Spinner actions = new Spinner(PreflopActivity.this);
        private String[] actionStrings = new String[]{"Bet", "Post", "Call", "Fold", "Allin"};
        private EditText amount = new EditText(PreflopActivity.this);
        LinearLayout innerLayout = new LinearLayout(PreflopActivity.this);

        public PlayerAction(List<Player> players) {
            ArrayAdapter<String> playersAdapter = new ArrayAdapter<>(PreflopActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, playersToNames(players));
            playersSpinner.setAdapter(playersAdapter);
            ArrayAdapter<String> actionsAdapter = new ArrayAdapter<>(PreflopActivity.this,
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
        setContentView(R.layout.activity_preflop);
        initViews();
        }

        void initViews(){
            onNextClick();
            onPlusClick();

        List<Player> players = Session.getInstance().players;
        LinearLayout layout = findViewById(R.id.player_list);
        layout.removeAllViews();
        PlayerAction pa = new PlayerAction(players);
        layout.addView(pa.innerLayout);
        }


    private void onPlusClick() {
        final Button plusButton = findViewById(R.id.new_action_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Player> players = Session.getInstance().players;
                LinearLayout layout = findViewById(R.id.player_list);
                PlayerAction pa = new PlayerAction(players);
                layout.addView(pa.innerLayout);
            }
        });
    }

    private void onNextClick() {
        final Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreflopActivity.this, FlopActivity.class);
                startActivity(intent);
            }
        });
    }
}
