package bsit.it363a.markcards;

import java.util.List;

public class CardSet {
    String title;
    int count;

    public CardSet(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
