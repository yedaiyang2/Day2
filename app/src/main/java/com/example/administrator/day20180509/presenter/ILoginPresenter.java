package com.example.administrator.day20180509.presenter;


import com.example.administrator.day20180509.bean.LoginBean;

/**
 * Created by ARP on 2018/5/4.
 */

public interface ILoginPresenter {
    void Show(LoginBean loginBean);
    void detachView();
}
