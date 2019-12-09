package lomo.hust.dictvocab.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.activity.LoginActivity;
import lomo.hust.dictvocab.base.BaseFragment;

public class RequestFragment extends BaseFragment {
    private TextView tvLogin;
    @Override
    protected void setupUI() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    protected void findViewById(View view) {
        tvLogin = view.findViewById(R.id.tv_username);
    }

    @Override
    protected int getLayoutInflaterId() {
        return R.layout.fragment_request;
    }
}
