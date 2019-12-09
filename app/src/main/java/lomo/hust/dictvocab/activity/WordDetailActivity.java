package lomo.hust.dictvocab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.base.BaseActivity;
import lomo.hust.dictvocab.base.BaseFragment;
import lomo.hust.dictvocab.utils.Constant;

public class WordDetailActivity extends BaseActivity {
    TextView tvKeyAndTypes;
    TextView tvTraits;
    TextView tvMeanings;

    @Override
    protected void setupUI() {
        Intent intent = getIntent();
        tvKeyAndTypes.setText(intent.getStringExtra(Constant.KEY));
        tvTraits.setText(intent.getStringExtra(Constant.TRAITS));
        tvMeanings.setText(intent.getStringExtra(Constant.MEANING));
        tvMeanings.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    protected void findViewById() {
        tvKeyAndTypes = findViewById(R.id.tv_key_type);
        tvTraits = findViewById(R.id.tv_traits);
        tvMeanings = findViewById(R.id.tv_meaning);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_word_detail;
    }
}
