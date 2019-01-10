package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditPlayersActivity extends AppCompatActivity {

    private static final String LOG_TAG = EditPlayersActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editplayers);
        initViews();
        Session session = Session.getInstance();
        Log.d(LOG_TAG, session.handInfo.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_editplayers);
        initViews();
    }

    public static class PlayerViewHolder {
        EditText name;
        EditText stack;
        EditText position;
        EditText card1;
        EditText card2;
    }

    void initViews() {


        Spinner amountOfPlayers = findViewById(R.id.player_amount_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.player_amount, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amountOfPlayers.setAdapter(adapter);
        if (Session.getInstance().noOfPlayers != null) {
            Log.d(LOG_TAG, Session.getInstance().noOfPlayers);
            amountOfPlayers.setSelection(adapter.getPosition(Session.getInstance().noOfPlayers));
        }
        amountOfPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.d(LOG_TAG, "Jag heter Rikard och är så jävla grym på allt, " + position);

                // hitta layouten
                LinearLayout layout = findViewById(R.id.player_list);
                // ta bort all vyer (som ligger i layouten)
                layout.removeAllViews();

                final List<PlayerViewHolder> playerInputViews = new ArrayList<>();

                // loopa igenom (position är platsen i spinnern)
                Log.d(LOG_TAG, " Value: " + ((TextView) view).getText());

                int noPlayers = position + 2;
                List<Player> players = Session.getInstance().players;
                for (int i = 0; i < noPlayers; i++) {

                    LinearLayout innerLayout = new LinearLayout(EditPlayersActivity.this);
                    innerLayout.setOrientation(LinearLayout.HORIZONTAL);

                    EditText name = new EditText(EditPlayersActivity.this);
                    name.setHint("Name");
                    if(players.size()!=0) {
                        name.setText(players.get(i).name());
                    }
                    EditText stack = new EditText(EditPlayersActivity.this);
                    stack.setHint("Stack");
                    if(players.size()!=0) {
                        stack.setText(players.get(i).stakk());
                    }
                    EditText pos = new EditText(EditPlayersActivity.this);
                    pos.setHint("Position");
                    if(players.size()!=0) {
                        pos.setText(players.get(i).pos());
                    }
                    EditText card1 = new EditText(EditPlayersActivity.this);
                    card1.setHint("Card 1");
                    if(players.size()!=0) {
                        card1.setText(players.get(i).kort1());
                    }
                    EditText card2 = new EditText(EditPlayersActivity.this);
                    card2.setHint("Card 2");
                    if(players.size()!=0) {
                        card2.setText(players.get(i).kort2());
                    }


                    PlayerViewHolder holder = new PlayerViewHolder();
                    holder.name = name;
                    holder.stack = stack;
                    holder.position = pos;
                    holder.card1 = card1;
                    holder.card2 = card2;

                    innerLayout.addView(name);
                    innerLayout.addView(stack);
                    innerLayout.addView(pos);
                    innerLayout.addView(card1);
                    innerLayout.addView(card2);

                    layout.addView(innerLayout);
                    playerInputViews.add(holder);
                }

                final Button button = findViewById(R.id.save_players_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Session.getInstance().players.clear();

                        Session session = Session.getInstance();
                        session.noOfPlayers = ((Spinner)findViewById(R.id.player_amount_spinner)).getSelectedItem().toString();
                        Log.d(LOG_TAG, " playerAmount: " + session.noOfPlayers);

                        for (PlayerViewHolder pvh : playerInputViews) {
                            Log.d(LOG_TAG, " " + pvh.name.getText().toString() + " " + pvh.stack.getText().toString());
                            Player p = new Player(pvh.name.getText().toString(), pvh.stack.getText()
                                    .toString(), pvh.position.getText().toString(), pvh.card1.getText()
                                    .toString(), pvh.card2.getText().toString());
                            session.players.add(p);
                        }
                        for (Player p : session.players) {
                            Log.d(LOG_TAG, " * " + p);
                        }

                        Intent intent = new Intent(EditPlayersActivity.this, NewHandActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}