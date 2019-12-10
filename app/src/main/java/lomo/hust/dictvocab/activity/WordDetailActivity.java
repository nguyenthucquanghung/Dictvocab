package lomo.hust.dictvocab.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.adapter.WordAdapter;
import lomo.hust.dictvocab.base.BaseActivity;
import lomo.hust.dictvocab.base.BaseFragment;
import lomo.hust.dictvocab.object.Word;
import lomo.hust.dictvocab.utils.Constant;

public class WordDetailActivity extends BaseActivity {
    TextView tvKeyAndTypes;
    TextView tvTraits;
    TextView tvMeanings;
    private Toolbar toolbar;

    String wordId;
    String word;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            Intent intent = new Intent(WordDetailActivity.this, MarkWordActivity.class);
            intent.putExtra(Constant.WORD_ID, wordId);
            intent.putExtra(Constant.WORD, word);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupUI() {
        wordId = getIntent().getStringExtra(Constant.WORD_ID);
        word = getIntent().getStringExtra(Constant.WORD);
        Intent intent = getIntent();
        tvKeyAndTypes.setText(intent.getStringExtra(Constant.KEY));
        tvTraits.setText(intent.getStringExtra(Constant.TRAITS));
        tvMeanings.setText(intent.getStringExtra(Constant.MEANING));
        tvMeanings.setMovementMethod(new ScrollingMovementMethod());
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra(Constant.WORD));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void findViewById() {
        tvKeyAndTypes = findViewById(R.id.tv_key_type);
        tvTraits = findViewById(R.id.tv_traits);
        tvMeanings = findViewById(R.id.tv_meaning);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WordDetailActivity.this, MainActivity.class);
        startActivity(intent);
        WordDetailActivity.this.finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_word_detail;
    }
}
