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

                // hitta layoiuten
                LinearLayout layout = findViewById(R.id.player_list);
                // ta bort all vyer (som ligger i layouten)
                layout.removeAllViews();

                List<PlayerViewHolder> playerInputViews = new ArrayList<>();

                // loopa igenom (position är platsen i spinnern)
                Log.d(LOG_TAG, " Value: " + ((TextView)view).getText());
                int noPlayers = position + 2;
                for ( int i=0; i<noPlayers; i++) {
                    // skapa linearlayout (horizontal)
                    EditText e = new EditText(EditPlayersActivity.this);
                    PlayerViewHolder holder = new PlayerViewHolder();
                    holder.name = e;
                    //b.setText("Liverpool");
                    // lägg till e i den nya layouten
                    layout.addView(e);  // lägg till den nya layout istället
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
