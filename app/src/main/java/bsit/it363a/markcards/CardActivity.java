package bsit.it363a.markcards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CardActivity extends AppCompatActivity {

    TextView
            textViewQuestion = findViewById(R.id.textViewQuestion),
            textViewAnswer = findViewById(R.id.textViewAnswer);
    Button
            buttonFlip = findViewById(R.id.buttonFlip),
            buttonPrevious = findViewById(R.id.buttonPrevious),
            buttonNext = findViewById(R.id.buttonNext);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_card_set);

        buttonFlip.setOnClickListener(v -> {
            // show/hide answer
            if (textViewAnswer.getVisibility() == View.VISIBLE) {
                textViewAnswer.setVisibility(View.INVISIBLE);
            } else {
                textViewAnswer.setVisibility(View.VISIBLE);
            }
        });

        buttonPrevious.setOnClickListener(v -> {
            // previous question
        });

        buttonNext.setOnClickListener(v -> {
            // next question
        });

        // get variables passed to this activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getSupportActionBar().setTitle(extras.getString("cardset_title"));
            textViewQuestion.setText(extras.getString("question"));
            textViewAnswer.setText(extras.getString("answer"));
            textViewAnswer.setVisibility(View.INVISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void init() {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}