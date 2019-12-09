package lomo.hust.dictvocab.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        findViewById();
        setupUI();
    }

    protected abstract void setupUI();

    protected abstract void findViewById();

    protected abstract int getLayoutId();


}
