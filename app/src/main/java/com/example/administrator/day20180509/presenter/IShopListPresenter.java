package com.example.administrator.day20180509.presenter;


import com.example.administrator.day20180509.bean.ShopListBean;

/**
 * Created by ARP on 2018/5/4.
 */

public interface IShopListPresenter {
    void Show(ShopListBean shopListBean);
    void detachView();
}
