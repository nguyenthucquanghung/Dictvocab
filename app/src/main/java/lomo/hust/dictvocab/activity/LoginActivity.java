package lomo.hust.dictvocab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.base.BaseActivity;
import lomo.hust.dictvocab.object.LoginToken;
import lomo.hust.dictvocab.object.LoginUser;
import lomo.hust.dictvocab.service.RetrofitService;
import lomo.hust.dictvocab.utils.Constant;
import lomo.hust.dictvocab.utils.SharedPreferencesSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends BaseActivity {
    private TextView tvUsername;
    private TextView tvPassword;
    private TextView tvCreateAcc;
    private Button btnLogin;


    @Override
    protected void setupUI() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvUsername.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter your email!", Toast.LENGTH_SHORT).show();
                } else if (tvPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter your password!", Toast.LENGTH_SHORT).show();
                } else {
                    login(tvUsername.getText().toString(), tvPassword.getText().toString());
                }
            }
        });

        tvCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }

    private void login(final String email, String password) {
        Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_LONG).show();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.API)
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        LoginUser user = new LoginUser(email, password, true);
        Call<LoginToken> loginTokenCall = service.login(user);
        loginTokenCall.enqueue(new Callback<LoginToken>() {
            @Override
            public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("")) {
                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_LONG).show();
                        SharedPreferencesSingleton.getInstance().put(Constant.LOGIN_TOKEN, response.body().getAccessToken());
                        SharedPreferencesSingleton.getInstance().put(Constant.LOG_IN_STATE, true);
                        SharedPreferencesSingleton.getInstance().put(Constant.USER_EMAIL, email);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong password or the account is not exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong password or the account is not exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginToken> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Server error!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void findViewById() {
        tvUsername = findViewById(R.id.tv_username);
        tvPassword = findViewById(R.id.tv_password);
        tvCreateAcc = findViewById(R.id.tv_create_account);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


}
