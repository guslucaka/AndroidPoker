package se.juneday.throwaway.androidpokerclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TurnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);
        initViews();
    }

    void initViews(){
        onNextClick();
    }

    private void onNextClick() {
        final Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TurnActivity.this, RiverActivity.class);
                startActivity(intent);
            }
        });
    }

}
