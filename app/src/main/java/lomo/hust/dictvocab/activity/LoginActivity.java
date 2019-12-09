package lomo.hust.dictvocab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.base.BaseActivity;
import lomo.hust.dictvocab.object.LoginToken;
import retrofit2.Call;

public class LoginActivity extends BaseActivity {
    private TextView tvUsername;
    private TextView tvPassword;
    private TextView tvCreateAcc;
    private Button btnLogin;


    @Override
    protected void setupUI() {
        Call<LoginToken> loginTokenCall;
    }

    @Override
    protected void findViewById() {
        tvUsername = findViewById(R.id.tv_username);
        tvPassword = findViewById(R.id.tv_password);
        tvCreateAcc = findViewById(R.id.tv_create_account);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


}
