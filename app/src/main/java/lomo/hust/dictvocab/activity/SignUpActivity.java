package lomo.hust.dictvocab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.mbms.MbmsErrors;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.security.Signature;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.base.BaseActivity;
import lomo.hust.dictvocab.object.LoginToken;
import lomo.hust.dictvocab.object.SignupUser;
import lomo.hust.dictvocab.service.RetrofitService;
import lomo.hust.dictvocab.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends BaseActivity {
    Button btnCreate;
    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    EditText etPasswordConfirmation;

    @Override
    protected void setupUI() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        etEmail.getText().toString().equals("") ||
                        etUsername.getText().toString().equals("") ||
                        etPassword.getText().toString().equals("") ||
                        etPasswordConfirmation.getText().toString().equals("")
                ) {
                    Toast.makeText(SignUpActivity.this,
                            "Please fill in all information!", Toast.LENGTH_SHORT).show();
                } else if (!etPassword.getText().toString().
                        equals(etPasswordConfirmation.getText().toString())) {
                    Toast.makeText(SignUpActivity.this,
                            "Password and password confirmation must be the same!",
                            Toast.LENGTH_SHORT).show();
                } else if (!isEmailValid(etEmail.getText().toString())) {
                    Toast.makeText(SignUpActivity.this,
                            "Please enter an valid email!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    createAccount(
                            etUsername.getText().toString(),
                            etEmail.getText().toString(),
                            etPassword.getText().toString(),
                            etPassword.getText().toString()
                    );
                }
            }
        });
    }

    private void createAccount(String username, String email, String password, String passwordConfirm) {
        SignupUser user = new SignupUser(username, email, password, passwordConfirm);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.API)
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<LoginToken> tokenCall = service.signup(user);
        tokenCall.enqueue(new Callback<LoginToken>() {
            @Override
            public void onResponse(@NotNull Call<LoginToken> call, @NotNull Response<LoginToken> response) {
                Toast.makeText(SignUpActivity.this,
                        "Sign up successfully!\nYour account is now available!",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                SignUpActivity.this.finish();
            }

            @Override
            public void onFailure(@NotNull Call<LoginToken> call, @NotNull Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        SignUpActivity.this.finish();
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void findViewById() {
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPasswordConfirmation = findViewById(R.id.et_password_confirmation);
        btnCreate = findViewById(R.id.btn_create);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }
}
