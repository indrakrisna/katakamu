package edu.unikom.katakamu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private List<QuoteData> listQuote = new ArrayList<>();

    public QuoteAdapter(List<QuoteData> listQuote) {
        this.listQuote = listQuote;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        holder.bind(listQuote.get(position));
    }

    @Override
    public int getItemCount() {
        return listQuote.size();
    }

    class QuoteViewHolder extends RecyclerView.ViewHolder {

        private TextView tQuote, tDate, tName;

        QuoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tQuote = itemView.findViewById(R.id.tQuote);
            tDate = itemView.findViewById(R.id.tDate);
            tName = itemView.findViewById(R.id.tName);
        }

        void bind(QuoteData item) {
            tQuote.setText(item.getQuote());
            tDate.setText(item.getDate());
            tName.setText(item.getName());
        }
    }
}
