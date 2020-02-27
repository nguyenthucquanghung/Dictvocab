package lomo.hust.dictvocab.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.base.BaseActivity;
import lomo.hust.dictvocab.object.ResponseTags;
import lomo.hust.dictvocab.object.Word;
import lomo.hust.dictvocab.object.WordPost;
import lomo.hust.dictvocab.service.RetrofitService;
import lomo.hust.dictvocab.utils.Constant;
import lomo.hust.dictvocab.utils.SharedPreferencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarkWordActivity extends BaseActivity {
    EditText etTag;
    TextView tvWord;
    TextView tvSelectedTags;
    private Spinner spinner;
    private List<String> mTags;
    Toolbar toolbar;
    String wordId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mark, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            if (!etTag.getText().toString().equals("")){
                String tag = etTag.getText().toString();
                WordPost wordPost = new WordPost(wordId, "en_vi", tag);
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Constant.API)
                        .build();
                RetrofitService service = retrofit.create(RetrofitService.class);
                String token = "Bearer " + SharedPreferencesSingleton.getInstance().get(Constant.LOGIN_TOKEN, String.class);
                Call<ResponseTags> call = service.mark(token, wordPost);
                call.enqueue(new Callback<ResponseTags>() {
                    @Override
                    public void onResponse(Call<ResponseTags> call, Response<ResponseTags> response) {
                        try {
                        if (response.body().getMes() != null) {
                            if (response.body().getSuccess()) {
                                Toast.makeText(MarkWordActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MarkWordActivity.this, "Successfully!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(MarkWordActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        }} catch (NullPointerException e) {
                            Toast.makeText(MarkWordActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseTags> call, Throwable t) {

                    }
                });
            }
            else {
                Toast.makeText(MarkWordActivity.this, "Please enter hashtag!", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupUI() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etTag.setText(mTags.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTags = new ArrayList<>();
        wordId = getIntent().getStringExtra(Constant.WORD_ID);
        String word = "Word: " + getIntent().getStringExtra(Constant.WORD);
        tvWord.setText(word);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra(Constant.WORD));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void findViewById() {
        getExistedTags();
        tvWord = findViewById(R.id.tv_word);
        etTag = findViewById(R.id.et_tag);
        tvSelectedTags = findViewById(R.id.tv_select_tags);
        spinner = findViewById(R.id.spn_tags);
        toolbar = findViewById(R.id.toolbar);
    }

    private void getExistedTags() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.API)
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        String token = "Bearer " + SharedPreferencesSingleton.getInstance().get(Constant.LOGIN_TOKEN, String.class);
        Call<ResponseTags> call = service.getTags(token);
        call.enqueue(new Callback<ResponseTags>() {
            @Override
            public void onResponse(Call<ResponseTags> call, Response<ResponseTags> response) {
                try {
                    if (response.body().getMes() != null) {
                        tvSelectedTags.setVisibility(View.INVISIBLE);
                        spinner.setVisibility(View.INVISIBLE);
                    } else {
                        mTags.addAll(response.body().getTags());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                MarkWordActivity.this,
                                android.R.layout.simple_spinner_item,
                                mTags
                        );
                        spinner.setAdapter(adapter);
                    }
                } catch (NullPointerException e) {
                    mTags.addAll(response.body().getTags());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            MarkWordActivity.this,
                            android.R.layout.simple_spinner_item,
                            mTags
                    );
                    spinner.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<ResponseTags> call, Throwable t) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mark_word;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
