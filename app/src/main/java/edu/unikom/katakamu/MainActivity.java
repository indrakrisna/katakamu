package edu.unikom.katakamu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<QuoteData> quotes = new ArrayList<>();
    private QuoteAdapter quoteAdapter = new QuoteAdapter(quotes);
    private RecyclerView quoteList;
    private SwipeRefreshLayout quoteSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteSwipe = findViewById(R.id.swipeQuote);
        quoteList = findViewById(R.id.listQuote);
        quoteList.setAdapter(quoteAdapter);
        quoteList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        quoteSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadQuote();
            }
        });
    }

    private void loadQuote() {
        quoteSwipe.setRefreshing(true);
        WebService.Factory.Instance().getQuote().enqueue(new Callback<WebResponse<List<QuoteData>>>() {
            @Override
            public void onResponse(Call<WebResponse<List<QuoteData>>> call, Response<WebResponse<List<QuoteData>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    quotes.clear();
                    quotes.addAll(response.body().getData());
                    quoteAdapter.notifyDataSetChanged();
                }
                quoteSwipe.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<WebResponse<List<QuoteData>>> call, Throwable t) {
                t.printStackTrace();
                quoteSwipe.setRefreshing(false);
            }
        });
    }

    public void goToInput(View v) {
        Intent i = new Intent(MainActivity.this, InputQuoteActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (quoteList != null)
            loadQuote();
    }
}
