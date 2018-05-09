package com.example.administrator.day20180509.presenter;


import com.example.administrator.day20180509.bean.LoginBean;
import com.example.administrator.day20180509.model.LoginModel;
import com.example.administrator.day20180509.view.ILoginView;

/**
 * Created by ARP on 2018/5/4.
 */

public class LoginPresenter implements ILoginPresenter {
    //实例化
    private LoginModel loginModel;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        this.loginModel = new LoginModel();
    }
    //重写方法
    public void getLoginData(String mobile,String password){
        loginModel.getLogin(mobile,password,this);
    }

    @Override
    public void Show(LoginBean loginBean) {
        iLoginView.onSuccess(loginBean);
    }

    @Override
    public void detachView() {
        if (iLoginView!=null){
            iLoginView=null;
        }
    }


}
