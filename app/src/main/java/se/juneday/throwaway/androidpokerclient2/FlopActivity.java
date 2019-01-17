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
                Intent intent = new Intent(FlopActivity.this, TurnActivity.class);
                startActivity(intent);

                Session session = Session.getInstance();

                // linjära layouten i scrollview
                LinearLayout layout = findViewById(R.id.player_list);
                List<Street> flop = new ArrayList<>();
                Log.d("onNextClick()", " layout: " + layout);

                // loopa igenom alla i lin layout tillagda viewer
                for (int i=0; i<layout.getChildCount(); i++) {
                    Log.d("onNextClick()", " list item: " + i);
                    // hämta ut i:te innerlayout
                    LinearLayout innerLayout = (LinearLayout) layout.getChildAt(i);
                    Log.d("onNextClick()", " list item: " + innerLayout);
                    // den tredje (dvs index 2) är ju en edittext, casta om och läs ut värdet
                    String name = ((Spinner) innerLayout.getChildAt(0)).getSelectedItem().toString();
                    Log.d("onNextClick()", " list item: " + name);
                    String action = ((Spinner) innerLayout.getChildAt(1)).getSelectedItem().toString();
                    Log.d("onNextClick()", " list item: " + action);
                    String amount = ((EditText) innerLayout.getChildAt(2)).getText().toString();
                    Log.d("onNextClick()", " list item: " + amount);
                    Street s = new Street(name, action, amount);
                    flop.add(s);
                    for(Street st : flop){
                        Log.d("onNextClick()", " preflop list: " + st);
                    }
                }
                session.streets.add(flop);
                for (List<Street> li : session.streets){
                    Log.d("onNextClick()", " list of  streetlists: " + li);
                }
            }
        });
    }
}