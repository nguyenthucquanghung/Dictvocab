package lomo.hust.dictvocab.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutInflaterId(), container, false);
        findViewById(view);
        setupUI();
        return view;
    }


    protected abstract void setupUI();

    protected abstract void findViewById(View view);

    protected abstract int getLayoutInflaterId();
}
