package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river);
        initViews();
    }

    void initViews(){
        onSaveHandClick();
    }

    private void onSaveHandClick() {
        final Button nextButton = findViewById(R.id.save_hand_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiverActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
