package com.resourceit.app.tools;

import android.os.AsyncTask;

import com.resourceit.app.dao.LoginDao;
import com.resourceit.app.models.LoginModel;

import java.util.List;

public class PopulateDbAsync extends AsyncTask<LoginModel, Void, Void> {
    private final LoginDao loginDao;
    List<LoginModel> loginModels;

    PopulateDbAsync(AppDatabase db) {
        loginDao = db.loginDao();
    }

    @Override
    protected Void doInBackground(final LoginModel... params) {

        loginDao.deleteall();

        //loginDao.insert(params[0]);

        return null;
    }
}
