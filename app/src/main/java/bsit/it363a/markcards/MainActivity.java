package bsit.it363a.markcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import bsit.it363a.markcards.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements CardSetRecyclerAdapter.OnCardSetListener {

    List<CardSet> cardSets = new ArrayList<>();
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // add to db
        helper.insertCardSet("Sample card set 1");
        helper.insertCardSet("Sample card set 2");
        helper.insertCardSet("Sample card set 3");
        // get from db
        Cursor data  = helper.getCardSets();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String title = data.getString(DatabaseHelper.CARDSET_TITLE_INDEX);
            cardSets.add(new CardSet(id, title));
        }

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