package com.example.administrator.day20180509.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import android.widget.Toast;

import com.example.administrator.day20180509.R;
import com.example.administrator.day20180509.adapter.MyShopAdapter;
import com.example.administrator.day20180509.bean.ShopListBean;
import com.example.administrator.day20180509.presenter.ShopListPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public class ShoppListActivity extends AppCompatActivity implements IShopListView {

    @BindView(R.id.shop_list_xrlv)
    XRecyclerView mShopListxRlv;

    private ShopListPresenter shopListPresenter;
    int PAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopp_list);
        ButterKnife.bind(this);
        shopListPresenter = new ShopListPresenter(this);
        shopListPresenter.getShopListData(PAGE);
    }

    @Override
    public void onSuccess(ShopListBean shopListBean) {
        final List<ShopListBean.DataBean> data = shopListBean.getData();
        MyShopAdapter adapter = new MyShopAdapter(this, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mShopListxRlv.setLayoutManager(linearLayoutManager);
        mShopListxRlv.setAdapter(adapter);
        adapter.setSetonItem(new MyShopAdapter.SetonItem() {
            @Override
            public void onItemLinister(int postion, String pid, View view) {
                Toast.makeText(ShoppListActivity.this, pid, Toast.LENGTH_SHORT).show();
            }
        });

        mShopListxRlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                PAGE=1;
                data.clear();
                shopListPresenter.getShopListData(PAGE);
                mShopListxRlv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                shopListPresenter.getShopListData(PAGE);
                mShopListxRlv.refreshComplete();
            }
        });
    }

    @Override
    public void onFailed(ShopListBean shopListBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CompositeSubscription subscription = new CompositeSubscription();
        subscription.unsubscribe();
    }
}
