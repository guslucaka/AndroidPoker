package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FlopActivity extends AppCompatActivity {

    private static final String LOG_TAG = FlopActivity.class.getCanonicalName();

    private class PlayerAction {
        private Spinner playersSpinner = new Spinner(FlopActivity.this);
        private Spinner actions = new Spinner(FlopActivity.this);
        private String[] actionStrings = new String[]{"Bet", "Post", "Call", "Fold", "Allin"};
        private EditText amount = new EditText(FlopActivity.this);
        LinearLayout innerLayout = new LinearLayout(FlopActivity.this);

        public PlayerAction(List<Player> players) {
            ArrayAdapter<String> playersAdapter = new ArrayAdapter<>(FlopActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, playersToNames(players));
            playersSpinner.setAdapter(playersAdapter);
            ArrayAdapter<String> actionsAdapter = new ArrayAdapter<>(FlopActivity.this,
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
        setContentView(R.layout.activity_flop);
        initViews();
    }

    void initViews() {
        onNextClick();
        onPlusClick();



    }

    private void onPlusClick() {
        final Button plusButton = findViewById(R.id.new_action_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Player> players = Session.getInstance().players;
                LinearLayout layout = findViewById(R.id.player_list);
                FlopActivity.PlayerAction pa = new FlopActivity.PlayerAction(players);
                layout.addView(pa.innerLayout);
            }
        });
    }

    private void onNextClick() {
        final Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String flopCard1 = ((EditText)findViewById(R.id.card1_editText)).getText().toString();
                String flopCard2 = ((EditText)findViewById(R.id.card2_editText)).getText().toString();
                String flopCard3 = ((EditText)findViewById(R.id.card3_editText)).getText().toString();

                Session session = Session.getInstance();

                session.communityCards.add(flopCard1);
                session.communityCards.add(flopCard2);
                session.communityCards.add(flopCard3);

                Log.d(LOG_TAG, "onNextClick() " + session);
                Log.d(LOG_TAG, "onNextClick() " + session.communityCards.size());
                Log.d(LOG_TAG, "onNextClick() " + session.communityCards.get(0));

                Intent intent = new Intent(FlopActivity.this, TurnActivity.class);
                startActivity(intent);
            }
        });
    }
}