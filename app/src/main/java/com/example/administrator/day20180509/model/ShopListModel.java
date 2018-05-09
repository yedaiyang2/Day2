package com.example.administrator.day20180509.model;

import android.content.Context;


import com.example.administrator.day20180509.bean.ShopListBean;
import com.example.administrator.day20180509.presenter.IShopListPresenter;
import com.example.administrator.day20180509.utils.DataManager;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ARP on 2018/5/4.
 */

public class ShopListModel {
    private Context context;
    public void getShopList(int pscid, final IShopListPresenter iShopListPresenter){
        DataManager manager = new DataManager(context);
        CompositeSubscription subscription = new CompositeSubscription();
        subscription.add(
                manager.getshoplist(pscid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShopListBean shopListBean) {
                        iShopListPresenter.Show(shopListBean);
                    }
                })
        );
    }
}
