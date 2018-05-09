package com.example.administrator.day20180509.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.day20180509.R;
import com.example.administrator.day20180509.bean.LoginBean;
import com.example.administrator.day20180509.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements ILoginView{

    @BindView(R.id.login_ed_mobile)
    EditText mLoginEdMobile;
    @BindView(R.id.login_ed_password)
    EditText mLoginEdPassword;
    @BindView(R.id.login_tv_forget)
    TextView mLoginTvForget;
    @BindView(R.id.login_tv_reg)
    TextView mLoginTvReg;
    @BindView(R.id.login_bt_login)
    Button mLoginBtLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
    }

    @butterknife.OnClick({R.id.login_tv_forget, R.id.login_tv_reg, R.id.login_bt_login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_tv_forget:
                Toast.makeText(this,"该功能正在维护",Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_tv_reg:
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_bt_login:
                String mobile = mLoginEdMobile.getText().toString();
                String password = mLoginEdPassword.getText().toString();
                loginPresenter.getLoginData(mobile,password);
                break;
        }
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        if (loginBean.getCode().equals("0")){
            Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ShoppListActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailed(LoginBean loginBean) {
        Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter!=null){
            loginPresenter.detachView();
            loginPresenter=null;
            CompositeSubscription subscription = new CompositeSubscription();
            subscription.unsubscribe();
        }
    }
}
