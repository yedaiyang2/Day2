package com.example.administrator.day20180509.model;

import android.content.Context;
import android.util.Log;


import com.example.administrator.day20180509.bean.LoginBean;
import com.example.administrator.day20180509.presenter.LoginPresenter;
import com.example.administrator.day20180509.utils.DataManager;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ARP on 2018/5/4.
 */

public class LoginModel {
    private Context context;
    public void getLogin(String mobile, String password, final LoginPresenter iLoginPresenter){
        DataManager manager = new DataManager(context);
        CompositeSubscription subscription = new CompositeSubscription();
        subscription.add(
                manager.getlogin(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError","onError");
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        Log.e("onNext","onNext");
                        iLoginPresenter.Show(loginBean);
                    }
                })
        );
    }
}
