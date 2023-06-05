package bsit.it363a.markcards.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    // private final static String TAG = "DatabaseHelper";
    private static final String DB_NAME = "markcards";

    public static final String CARDSETS = "cardsets";
    public static final String CARDSET_ID = "cardset_id";
    public static final int CARDSET_ID_INDEX = 0;
    public static final String CARDSET_TITLE = "cardset_title";
    public static final int CARDSET_TITLE_INDEX = 1;
    public static final String CARDSET_LAST_ITEM = "last_item";
    public static final int CARDSET_LAST_ITEM_INDEX = 2;
    public static final Map<String, Integer> CARDSET_COLS = new HashMap<String, Integer>() {
        {
            put(CARDSET_ID, 0);
            put(CARDSET_TITLE, 1);
            put(CARDSET_LAST_ITEM, 2);
        }
    };

    public static final String CARDS = "cards";
    public static final String CARD_ID = "card_id";
    public static final String CARD_QUESTION = "card_question";
    public static final String CARD_ANSWER = "card_answer";
    public static Map<String, Integer> CARD_COLS = new HashMap<String, Integer>() {
        {
            put(CARD_ID, 0);
            put(CARDSET_ID, 1);
            put(CARD_QUESTION, 2);
            put(CARD_ANSWER, 3);
        }
    };

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String fs;
        String sql;
        // create TABLE_CARDSETS
        fs = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)";
        sql = String.format(Locale.ENGLISH, fs, CARDSETS, CARDSET_ID, CARDSET_TITLE, CARDSET_LAST_ITEM);
        db.execSQL(sql);

        // create TABLE_CARDS
        fs = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s TEXT, %s TEXT)";
        sql = String.format(Locale.ENGLISH, fs, CARDS, CARD_ID, CARDSET_ID, CARD_QUESTION, CARD_ANSWER);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CARDSETS);
        db.execSQL("DROP TABLE IF EXISTS " + CARDS);
        onCreate(db);
    }


    public Cursor getCardSets() {
        String sql = "SELECT * FROM " + CARDSETS;
        Cursor data = this.getReadableDatabase().rawQuery(sql, null);
        return data;
    }

    public boolean updateCardSet(int id, String name, int last_item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (name != null) contentValues.put(CARDSET_TITLE, name);
        if (last_item != 0) contentValues.put(CARDSET_LAST_ITEM, last_item);
        long result = -1;
        if (id != 0) result = db.update(CARDSETS, contentValues, CARDSET_ID + " = " + id, null);
        // if true then data was inserted correctly
        return result != -1;
    }

    public void insertCardSet(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARDSET_TITLE, title);
        // last_item can be null
        db.insert(CARDSETS, CARDSET_LAST_ITEM, contentValues);
    }

    public void deleteCardSet(int cardSetId) {
        // delete all cards containing cardSetId
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CARDS, CARDSET_ID + " = " + cardSetId, null);
        // delete the card set itself
        db.delete(CARDSETS, CARDSET_ID + " = " + cardSetId, null);
    }

    public Cursor getCards(int cardSetId) {
        String sql = "SELECT * FROM " + CARDS + " WHERE cardset_id = " + cardSetId;
        Cursor data = this.getReadableDatabase().rawQuery(sql, null);
        return data;
    }

    public void insertCard(int cardSetId, String question, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARDSET_ID, cardSetId);
        contentValues.put(CARD_QUESTION, question);
        contentValues.put(CARD_ANSWER, answer);
        db.insert(CARDSETS, null, contentValues);
    }
}
