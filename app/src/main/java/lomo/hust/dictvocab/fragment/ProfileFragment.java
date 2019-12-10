package lomo.hust.dictvocab.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.activity.LoginActivity;
import lomo.hust.dictvocab.base.BaseFragment;
import lomo.hust.dictvocab.object.LoginToken;
import lomo.hust.dictvocab.service.RetrofitService;
import lomo.hust.dictvocab.utils.Constant;
import lomo.hust.dictvocab.utils.SharedPreferencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends BaseFragment {
    private TextView tvEmail;
    private Button btnLogout;
    private Button btnChangePassword;

    @Override
    protected void setupUI() {
        String contentEmailBox = "Email: " + SharedPreferencesSingleton.getInstance().get(Constant.USER_EMAIL, String.class);
        tvEmail.setText(contentEmailBox);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(SharedPreferencesSingleton.getInstance().get(Constant.LOGIN_TOKEN, String.class));
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void logout(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.API)
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<LoginToken> loginTokenCall = service.logout("Bearer " + token);
        loginTokenCall.enqueue(new Callback<LoginToken>() {
            @Override
            public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                SharedPreferencesSingleton.getInstance().put(Constant.LOG_IN_STATE, false);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<LoginToken> call, Throwable t) {

            }
        });
    }

    @Override
    protected void findViewById(View view) {
        tvEmail = view.findViewById(R.id.tv_email);
        btnLogout = view.findViewById(R.id.btn_log_out);
        btnChangePassword = view.findViewById(R.id.btn_change_password);
    }

    @Override
    protected int getLayoutInflaterId() {
        return R.layout.fragment_profile;
    }
}
