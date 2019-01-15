package com.resourceit.app.views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.resourceit.app.R;
import com.resourceit.app.models.LoginModel;
import com.resourceit.app.models.StatmentModel;
import com.resourceit.app.tools.Validator;
import com.resourceit.app.viewmodels.LoginViewModel;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {


    @BindView(R.id.user_layout) TextInputLayout user_layout;
    @BindView(R.id.password_layout) TextInputLayout password_layout;
    @BindView(R.id.user) TextView user;
    @BindView(R.id.password) TextView password;
    private MainActivity activity;
    private Boolean doLogin = false;
    private LoginViewModel loginViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        activity = (MainActivity) getActivity();
        loginViewModel = activity.loginViewModel;
        return view;
    }

    @OnClick(R.id.login)
    void Login() {
        if(!doLogin) {
            doLogin = true;
            if (!validateUser(user.getText().toString())) {
                user_layout.setError("Não é um usuário válido!");
                doLogin = false;
            }else user_layout.setErrorEnabled(false);
            if (!validatePassword(password.getText().toString())) {
                password_layout.setError("Não é uma senha válida!");
                doLogin = false;
            }else password_layout.setErrorEnabled(false);
            if(doLogin){
                hideKeyboard();
                activity.Loading(true);
                loginViewModel.DoLogin(user.getText().toString(), password.getText().toString())
                        .observe(this, new Observer<LoginModel>() {
                    @Override
                    public void onChanged(@Nullable final LoginModel login) {
                        if(login!=null) {
                            activity.updateFragment(new StatementsFragment(), "STATMENTS");
                            doLogin = false;
                        }
                    }
                });
            }
        }
    }

    private void hideKeyboard() {
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean validateUser(String user) {
        return Validator.isValidEmail(user) || Validator.isValidCPF(user);
    }

    public boolean validatePassword(String password) {
        return Validator.isValidPassword(password);
    }


}
