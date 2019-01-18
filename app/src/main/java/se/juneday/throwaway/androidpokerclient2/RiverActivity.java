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

public class RiverActivity extends AppCompatActivity {

    private static final String LOG_TAG = RiverActivity.class.getCanonicalName();
    private TextView flopCard1; // = (TextView)findViewById(R.id.textView);
    private TextView flopCard2; // = (TextView)findViewById(R.id.textView2);
    private TextView flopCard3;
    private TextView flopCard4;

    private class PlayerAction {
        private Spinner playersSpinner = new Spinner(RiverActivity.this);
        private Spinner actions = new Spinner(RiverActivity.this);
        private String[] actionStrings = new String[]{"Bet", "Raise", "Check", "Post", "Call", "Fold", "Allin"};
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

        Session session = Session.getInstance();

        flopCard1 = findViewById(R.id.textView);
        flopCard2 = findViewById(R.id.textView2);
        flopCard3 = findViewById(R.id.textView3);
        flopCard4 = findViewById(R.id.textView4);

        Log.d(LOG_TAG, "onCreate() " + session);
        Log.d(LOG_TAG, "onCreate() " + session.communityCards.size());
        Log.d(LOG_TAG, "onCreate() " + session.communityCards.get(0));

        flopCard1.setText(session.communityCards.get(0));
        flopCard2.setText(session.communityCards.get(1));
        flopCard3.setText(session.communityCards.get(2));
        flopCard4.setText(session.communityCards.get(3));
    }

    void initViews(){
        onSaveHandClick();
        onPlusClick();
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

                String flopCard5 = ((EditText)findViewById(R.id.card5_editText)).getText().toString();

                Session session = Session.getInstance();

                session.communityCards.add(flopCard5);

                // linjära layouten i scrollview
                LinearLayout layout = findViewById(R.id.player_list);
                List<Street> river = new ArrayList<>();
                Log.d("onSaveHandClick()", " layout: " + layout);

                // loopa igenom alla i lin layout tillagda viewer
                for (int i=0; i<layout.getChildCount(); i++) {
                    Log.d("onSaveHandClick()", " list item: " + i);
                    // hämta ut i:te innerlayout
                    LinearLayout innerLayout = (LinearLayout) layout.getChildAt(i);
                    Log.d("onSaveHandClick()", " list item: " + innerLayout);
                    // den tredje (dvs index 2) är ju en edittext, casta om och läs ut värdet
                    String name = ((Spinner) innerLayout.getChildAt(0)).getSelectedItem().toString();
                    Log.d("onSaveHandClick()", " list item: " + name);
                    String action = ((Spinner) innerLayout.getChildAt(1)).getSelectedItem().toString();
                    Log.d("onSaveHandClick()", " list item: " + action);
                    String amount = ((EditText) innerLayout.getChildAt(2)).getText().toString();
                    Log.d("onSaveHandClick()", " list item: " + amount);
                    Street s = new Street(name, action, amount);
                    river.add(s);
                    for(Street st : river){
                        Log.d("onSaveHandClick()", " river list: " + st);
                    }
                }
                session.streets.add(river);
                Log.d("onSaveHandClick()", "HandInfo: " + session.handInfo);
                for (Player p : session.players) {
                    Log.d("onSaveHandClick()", "PlayerList: " + p);
                }
                for (String s : session.communityCards) {
                    Log.d("onSaveHandClick()", "CardList: " + s);
                }
                for (List<Street> li : session.streets){
                    Log.d("onSaveHandClick()", " list of  streetlists: " + li);
                }
            }
        });
    }
}
