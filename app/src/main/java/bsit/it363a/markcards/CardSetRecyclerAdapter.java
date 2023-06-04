package bsit.it363a.markcards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardSetRecyclerAdapter extends RecyclerView.Adapter<CardSetRecyclerAdapter.ViewHolder> {

    //private static final String TAG = "CardSetRecyclerAdapter";
    Context context;
    List<CardSet> cardSets;
    OnCardSetListener onCardSetListener;

    public CardSetRecyclerAdapter(Context context, List<CardSet> cardSets, OnCardSetListener onCardSetListener) {
        this.context = context;
        this.cardSets = cardSets;
        this.onCardSetListener = onCardSetListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardset_view, parent, false);
        return new ViewHolder(view, onCardSetListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get text from card set, assign to textview
        holder.cardSetTitle.setText(cardSets.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return cardSets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView cardSetTitle;
        OnCardSetListener onCardSetListener;

        public ViewHolder(@NonNull View itemView, OnCardSetListener onCardSetListener) {
            super(itemView);
            cardSetTitle = itemView.findViewById(R.id.textViewCardSetTitle);
            this.onCardSetListener = onCardSetListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCardSetListener.onCardSetClick(getAdapterPosition());
        }
    }

    public interface OnCardSetListener {
        void onCardSetClick(int position);
    }

}
