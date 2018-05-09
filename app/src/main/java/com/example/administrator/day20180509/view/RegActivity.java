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
import com.example.administrator.day20180509.bean.RegBean;
import com.example.administrator.day20180509.presenter.RegPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

public class RegActivity extends AppCompatActivity implements IRegView{

    @BindView(R.id.reg_ed_mobile)
    EditText mRegEdMobile;
    @BindView(R.id.reg_ed_password)
    EditText mRegEdPassword;
    @BindView(R.id.reg_tv_login)
    TextView mRegTvLogin;
    @BindView(R.id.reg_bt_reg)
    Button mRegBtReg;
    private RegPresenter regPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        regPresenter = new RegPresenter(this);
    }

    @OnClick({R.id.reg_tv_login, R.id.reg_bt_reg})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.reg_tv_login:
                Intent intent = new Intent(RegActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.reg_bt_reg:
                String mobile = mRegEdMobile.getText().toString();
                String password = mRegEdPassword.getText().toString();
                regPresenter.getRegData(mobile,password);
                break;
        }
    }

    @Override
    public void onSuccess(RegBean regBean) {
        if (regBean.getCode().equals("0")){
            Toast.makeText(this,regBean.getMsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,regBean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailed(RegBean regBean) {
        Toast.makeText(this,regBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (regPresenter!=null){
            regPresenter.detachView();
            regPresenter=null;
            CompositeSubscription subscription = new CompositeSubscription();
            subscription.unsubscribe();
        }
    }
}
