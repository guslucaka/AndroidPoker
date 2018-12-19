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

public class PreflopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preflop);
        initViews();
        }

        void initViews(){

        //get the spinner from the xml.
        Spinner dropdownLocation = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] players = new String[]{"Player", "Erik", "Lucas", "Sven"};
        //create an adapter to describe how the items are displayed.
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, players) {
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
        locationAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dropdownLocation.setAdapter(locationAdapter);

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
