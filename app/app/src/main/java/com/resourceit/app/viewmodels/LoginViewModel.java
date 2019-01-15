package com.resourceit.app.viewmodels;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.resourceit.app.models.LoginModel;
import com.resourceit.app.repositories.LoginRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    private MutableLiveData<LoginModel> login;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository(application);
        login = loginRepository.getlogin();
    }

    public LiveData<LoginModel> getLogin() {
        return login; }
    public LiveData<LoginModel> DoLogin(String user, String password) {
        loginRepository.Login(user,password);
        login = loginRepository.getlogin();
        return login;
    }
    public void insert(LoginModel loginModel) { loginRepository.insert(loginModel); }
    public void DeleteAll() { loginRepository.DeleteAll(); }

}
