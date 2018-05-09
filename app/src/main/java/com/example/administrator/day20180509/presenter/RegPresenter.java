package com.example.administrator.day20180509.presenter;


import com.example.administrator.day20180509.bean.RegBean;
import com.example.administrator.day20180509.model.RegModel;
import com.example.administrator.day20180509.view.IRegView;

/**
 * Created by ARP on 2018/5/4.
 */

public class RegPresenter implements IRegPresenter {
    private RegModel regModel;
    private IRegView iRegView;

    public RegPresenter(IRegView iRegView) {
        this.iRegView = iRegView;
        this.regModel = new RegModel();
    }

    public void getRegData(String mobile,String password){
        regModel.getReg(mobile,password,this);
    }

    @Override
    public void Show(RegBean regBean) {
        iRegView.onSuccess(regBean);
    }
    public void detachView(){
        if (iRegView!=null){
            iRegView = null;
        }
    }
}
