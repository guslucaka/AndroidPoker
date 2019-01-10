package se.juneday.throwaway.androidpokerclient2;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PreflopActivity extends AppCompatActivity {

    List<String> playersToNames(List<Player> players) {
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

        List<Player> players = Session.getInstance().players;



        //get the spinner from the xml.
        Spinner dropdownPlayers = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        //String[] players = new String[]{"Player", "Erik", "Lucas", "Sven"};
        //create an adapter to describe how the items are displayed.
        ArrayAdapter<String> playersAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, playersToNames(players)) {
            /*
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
            */
        };
        //set the spinners adapter to the previously created one.
        playersAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dropdownPlayers.setAdapter(playersAdapter);

        Spinner dropdownAction = findViewById(R.id.spinner2);
        //create a list of items for the spinner.
        String[] action = new String[]{"Action", "Bet", "Call", "Fold", "Allin"};
        //create an adapter to describe how the items are displayed.
        ArrayAdapter<String> actionAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, action) {
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        //set the spinners adapter to the previously created one.
        actionAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dropdownAction.setAdapter(actionAdapter);

        onNextClick();
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
