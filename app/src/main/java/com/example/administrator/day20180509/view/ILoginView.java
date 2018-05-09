package com.example.administrator.day20180509.view;


import com.bw.arp.lianxi_0508.bean.LoginBean;
import com.example.administrator.day20180509.bean.LoginBean;

/**
 * Created by ARP on 2018/5/4.
 */

public interface ILoginView {
    void onSuccess(LoginBean loginBean);
    void onFailed(LoginBean loginBean);

    void onFailed(LoginBean loginBean);
}
