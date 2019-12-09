package com.example.bazaarapp;

import android.text.TextUtils;

public class LPresenterImpl implements LPresenter
{
    private LView loginView;
    public LPresenterImpl(LView loginView)
    {
        this.loginView = loginView;
    }
    @Override
    public void handleLogin(String username, String password)
    {
        if ((TextUtils.isEmpty(username) || TextUtils.isEmpty(password)))
        {
            loginView.showValidationErrorMsg();
        }
        else
        {
            if (username.equals("Standerd") && password.equals("Standerd"))
            {
                loginView.loginSuccessFully();
            }
            else
            {
                loginView.loginFail();
            }
        }
    }
}
