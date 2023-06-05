package bsit.it363a.markcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CardSetRecyclerAdapter.OnCardSetListener {

    List<CardSet> cardSets = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardSets.add(new CardSet("title1"));
        cardSets.add(new CardSet("title2"));
        cardSets.add(new CardSet("title3"));
        cardSets.add(new CardSet("title4"));
        cardSets.add(new CardSet("title5"));
        cardSets.add(new CardSet("title6"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardSetRecyclerAdapter(getApplicationContext(), cardSets, this::onCardSetClick));
    }

    @Override
    public void onCardSetClick(int position) {
        Intent intent = new Intent(this, CardActivity.class);
        String title = cardSets.get(position).getTitle();
        intent.putExtra("cardset_title", title);
        intent.putExtra("question", "My Question " + title);
        intent.putExtra("answer", "My Answer " + title);

        startActivity(intent);
    }
}