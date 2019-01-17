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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TurnActivity extends AppCompatActivity {

    private static final String LOG_TAG = TurnActivity.class.getCanonicalName();
    private TextView flopCard1; // = (TextView)findViewById(R.id.textView);
    private TextView flopCard2; // = (TextView)findViewById(R.id.textView2);
    private TextView flopCard3; // = (TextView)findViewById(R.id.textView3);

    private class PlayerAction {
        private Spinner playersSpinner = new Spinner(TurnActivity.this);
        private Spinner actions = new Spinner(TurnActivity.this);
        private String[] actionStrings = new String[]{"Bet", "Post", "Call", "Fold", "Allin"};
        private EditText amount = new EditText(TurnActivity.this);
        LinearLayout innerLayout = new LinearLayout(TurnActivity.this);


        public PlayerAction(List<Player> players) {
            ArrayAdapter<String> playersAdapter = new ArrayAdapter<>(TurnActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, playersToNames(players));
            playersSpinner.setAdapter(playersAdapter);
            ArrayAdapter<String> actionsAdapter = new ArrayAdapter<>(TurnActivity.this,
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
        setContentView(R.layout.activity_turn);
        initViews();

        Session session = Session.getInstance();

        flopCard1 = findViewById(R.id.textView);
        flopCard2 = findViewById(R.id.textView2);
        flopCard3 = findViewById(R.id.textView3);

        Log.d(LOG_TAG, "onCreate() " + session);
        Log.d(LOG_TAG, "onCreate() " + session.communityCards.size());
        Log.d(LOG_TAG, "onCreate() " + session.communityCards.get(0));

        flopCard1.setText(session.communityCards.get(0));
        flopCard2.setText(session.communityCards.get(1));
        flopCard3.setText(session.communityCards.get(2));

    }

    void initViews(){
        onPlusClick();
        onNextClick();
    }

    private void onPlusClick() {
        final Button plusButton = findViewById(R.id.new_action_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Player> players = Session.getInstance().players;
                LinearLayout layout = findViewById(R.id.player_list);
                TurnActivity.PlayerAction pa = new TurnActivity.PlayerAction(players);
                layout.addView(pa.innerLayout);
            }
        });
    }

    private void onNextClick() {
        final Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String flopCard4 = ((EditText)findViewById(R.id.card4_editText)).getText().toString();

                Session session = Session.getInstance();

                session.communityCards.add(flopCard4);

                Intent intent = new Intent(TurnActivity.this, RiverActivity.class);
                startActivity(intent);

                // linjära layouten i scrollview
                LinearLayout layout = findViewById(R.id.player_list);
                List<Street> turn = new ArrayList<>();
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
                    turn.add(s);
                    for(Street st : turn){
                        Log.d("onNextClick()", " preflop list: " + st);
                    }
                }
                session.streets.add(turn);
                for (List<Street> li : session.streets){
                    Log.d("onNextClick()", " list of  streetlists: " + li);
                }
            }
        });
    }

}
