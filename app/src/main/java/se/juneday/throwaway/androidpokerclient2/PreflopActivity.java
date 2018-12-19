package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PreflopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preflop);
        initViews();
    }

    void initViews() {

        Spinner dropdownLocation = findViewById(R.id.spinner1);
        String[] players = new String[]{"Players", "Erik", "Lucas", "Sven"};
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, players);
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
