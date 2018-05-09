package com.example.administrator.day20180509.model;

import android.content.Context;
import android.util.Log;


import com.example.administrator.day20180509.bean.RegBean;
import com.example.administrator.day20180509.presenter.IRegPresenter;
import com.example.administrator.day20180509.utils.DataManager;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ARP on 2018/5/4.
 */

public class RegModel {
    private Context context;
    public void getReg(String mobile, String password, final IRegPresenter iRegPresenter){
        DataManager manager = new DataManager(context);
        CompositeSubscription subscription = new CompositeSubscription();
        subscription.add(
                manager.getreg(mobile,password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RegBean>() {
                            @Override
                            public void onCompleted() {
                                Log.e("onCompleted","onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("onError","onError");
                            }

                            @Override
                            public void onNext(RegBean regBean) {
                                Log.e("onNext","onNext");
                                iRegPresenter.Show(regBean);
                            }
                        })
        );
    }
}
