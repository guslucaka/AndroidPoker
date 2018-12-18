package se.juneday.throwaway.androidpokerclient2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewHandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    //get the spinner from the xml.
        Spinner dropdownLocation = findViewById(R.id.spinner1);
    //create a list of items for the spinner.
        String[] locations = new String[]{"Location", "Göteborg", "Stockholm", "Malmö", "Sundsvall"};
    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
    //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
    //set the spinners adapter to the previously created one.
        dropdownLocation.setAdapter(locationAdapter);

        //get the spinner from the xml.
        Spinner dropdownGametype = findViewById(R.id.spinner2);
        //create a list of items for the spinner.
        String[] gametypes = new String[]{"Gametype", "Cashgame", "Tournament"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> gametypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gametypes);
        //set the spinners adapter to the previously created one.
        dropdownGametype.setAdapter(gametypeAdapter);

        //Spinner dropdownSmallblind = findViewById(R.id.spinner3);


    }
}
