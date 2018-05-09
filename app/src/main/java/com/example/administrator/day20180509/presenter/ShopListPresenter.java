package com.example.administrator.day20180509.presenter;


import com.example.administrator.day20180509.bean.ShopListBean;
import com.example.administrator.day20180509.model.ShopListModel;
import com.example.administrator.day20180509.view.IShopListView;

/**
 * Created by ARP on 2018/5/4.
 */

public class ShopListPresenter implements IShopListPresenter {
    private ShopListModel shopListModel;
    private IShopListView iShopListView;

    public ShopListPresenter(IShopListView iShopListView) {
        this.iShopListView = iShopListView;
        this.shopListModel = new ShopListModel();
    }

    //重写方法
    public void getShopListData(int pscid){
        shopListModel.getShopList(pscid,this);
    }

    @Override
    public void Show(ShopListBean shopListBean) {
        iShopListView.onSuccess(shopListBean);
    }

    @Override
    public void detachView() {
        if (iShopListView!=null){
            iShopListView=null;
        }
    }
}
