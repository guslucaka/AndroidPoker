package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EditPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editplayers);
        initViews();
    }

    void initViews(){

        onSavePlayersclick();

        Spinner amountOfPlayers = findViewById(R.id.player_amount_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.player_amount, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amountOfPlayers.setAdapter(adapter);
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
