package com.resourceit.app.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.resourceit.app.dao.LoginDao;
import com.resourceit.app.interfaces.APIService;
import com.resourceit.app.models.LoginModel;
import com.resourceit.app.models.StatmentModel;
import com.resourceit.app.tools.AppDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {
    private APIService API;
    private MutableLiveData<LoginModel> login = new MutableLiveData<>();
    LoginDao loginDao;


    public LoginRepository(Application application) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API = retrofit.create(APIService.class);
        AppDatabase db = AppDatabase.getDatabase(application);
        loginDao = db.loginDao();


    }

    public void Login(String user, String password){
        API.DoLogin(user, password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel res = response.body();
                if(res!=null) {
                    insert(res.getUserAccount());
                    new getAsyncTask(loginDao, output -> login.postValue(output)).execute(res.getUserAccount().getUserId());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }

    public MutableLiveData
            <LoginModel> getlogin() {
        return login;
    }

    public void insert (LoginModel login) {
        new insertAsyncTask(loginDao).execute(login);
    }

    private static class insertAsyncTask extends AsyncTask<LoginModel, Void, Void> {

        private LoginDao asyncTaskDao;

        insertAsyncTask(LoginDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final LoginModel... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void DeleteAll () {
        new DeleteAllAsyncTask(loginDao).execute();
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private LoginDao asyncTaskDao;

        DeleteAllAsyncTask(LoginDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            asyncTaskDao.deleteall();
            return null;
        }
    }

    private static class getAsyncTask extends AsyncTask<Integer, LoginModel, LoginModel> {

        private LoginDao asyncTaskDao;
        public AsyncResponse delegate = null;

        getAsyncTask(LoginDao dao, AsyncResponse delegate) {
            asyncTaskDao = dao;
            this.delegate = delegate;
        }

        @Override
        protected LoginModel doInBackground(final Integer... params) {
            LoginModel login = asyncTaskDao.findById(params[0]);
            return login;

        }



        @Override
        protected void onPostExecute(LoginModel result) {
            delegate.processFinish(result);
        }
    }

    interface AsyncResponse {
        void processFinish(LoginModel output);
    }
}
