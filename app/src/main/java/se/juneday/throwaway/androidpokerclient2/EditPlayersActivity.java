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

    private static class PlayerViewHolder {
        EditText name;
        EditText stack;
        EditText position;
        EditText card1;
        EditText card2;
    }

    void initViews(){

      //  onSavePlayersclick();

        Spinner amountOfPlayers = findViewById(R.id.player_amount_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.player_amount, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amountOfPlayers.setAdapter(adapter);
        amountOfPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, "Jag heter Rikard och är så jävla grym på allt, " + position);

                // hitta layouten
                LinearLayout layout = findViewById(R.id.player_list);
                // ta bort all vyer (som ligger i layouten)
                layout.removeAllViews();

                final List<PlayerViewHolder> playerInputViews = new ArrayList<>();

                // loopa igenom (position är platsen i spinnern)
                Log.d(LOG_TAG, " Value: " + ((TextView)view).getText());

                int noPlayers = position + 2;
                for ( int i=0; i<noPlayers; i++) {

                    LinearLayout innerLayout = new LinearLayout(EditPlayersActivity.this);
                    innerLayout.setOrientation(LinearLayout.HORIZONTAL);

                    EditText name = new EditText(EditPlayersActivity.this);
                    name.setHint("Name");
                    EditText stack = new EditText(EditPlayersActivity.this);
                    stack.setHint("Stack");
                    EditText pos = new EditText(EditPlayersActivity.this);
                    pos.setHint("Posision");
                    EditText card1 = new EditText(EditPlayersActivity.this);
                    card1.setHint("Card 1");
                    EditText card2 = new EditText(EditPlayersActivity.this);
                    card2.setHint("Card 2");


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

                    layout.addView(innerLayout);  // lägg till den nya layout istället
                    playerInputViews.add(holder);
                }
                // leta upp (findViewById) Save-knappen
                // skapa en lyssnare
                // på knappens ok-tryck:
                // loopa igenom alla edittext:er (som finns i playerInputView) och spara undan värdena

                final Button button = findViewById(R.id.save_players_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // loopa igenom listan
                        // skapa Player-instanser
                        // lägg till dessa i ert Seesion-objekt
                        Session session = Session.getInstance();
                        for (PlayerViewHolder pvh : playerInputViews) {
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

    private void onSavePlayersclick(){
        final Button button = findViewById(R.id.save_players_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditPlayersActivity.this, NewHandActivity.class);
                startActivity(intent);
            }
        });
    }

}
