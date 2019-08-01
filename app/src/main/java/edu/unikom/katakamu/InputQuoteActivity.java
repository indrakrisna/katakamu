package edu.unikom.katakamu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputQuoteActivity extends AppCompatActivity {

    private EditText inName, inQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_quote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inName = findViewById(R.id.inName);
        inQuote = findViewById(R.id.inQuote);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void cancel(View v) {
        finish();
    }

    public void save(View v) {
        if (inName.getText().toString().trim().length() > 0 && inQuote.getText().toString().trim().length() > 0) {
            final ProgressDialog loading = new ProgressDialog(this);
            loading.setMessage("Tunggu sebentar...");
            loading.setCancelable(false);
            loading.show();
            WebService.Factory.Instance().insertQuote(inName.getText().toString(), inQuote.getText().toString()).enqueue(new Callback<WebResponse<QuoteData>>() {
                @Override
                public void onResponse(Call<WebResponse<QuoteData>> call, Response<WebResponse<QuoteData>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(InputQuoteActivity.this, "Berhasil menyimpan quote", Toast.LENGTH_SHORT).show();
                        finish();
                    } else
                        Toast.makeText(InputQuoteActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }

                @Override
                public void onFailure(Call<WebResponse<QuoteData>> call, Throwable t) {
                    Toast.makeText(InputQuoteActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                    loading.dismiss();
                }
            });
        } else
            Toast.makeText(this, "Lengkapi form input", Toast.LENGTH_SHORT).show();
    }
}
