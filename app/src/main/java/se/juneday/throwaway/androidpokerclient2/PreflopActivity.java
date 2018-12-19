package se.juneday.throwaway.androidpokerclient2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        String[] players = new String[]{"Players", "Erik", "Lucas", "Sven"};
        //create an adapter to describe how the items are displayed.
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, players);
        //set the spinners adapter to the previously created one.
        dropdownLocation.setAdapter(locationAdapter);
    }
}
