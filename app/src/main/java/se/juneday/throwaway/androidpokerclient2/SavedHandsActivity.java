package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SavedHandsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedhands);
        initViews();
    }

    void initViews() {
        onMainMenuClick();
    }

    private void onMainMenuClick(){
        final Button nextButton = findViewById(R.id.main_menu_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedHandsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
