package com.resourceit.app.repositories;

import android.app.Application;

import com.resourceit.app.interfaces.APIService;
import com.resourceit.app.models.StatmentModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatmentsRepository {

    private APIService API;
    private MutableLiveData<List<StatmentModel>> statments = new MutableLiveData<>();

    public StatmentsRepository(Application application, String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API = retrofit.create(APIService.class);

        API.GetStatments(id).enqueue(new Callback<StatmentModel>() {
            @Override
            public void onResponse(Call<StatmentModel> call, Response<StatmentModel> response) {
                StatmentModel res = response.body();
                if(res!=null)
                    statments.postValue(res.getStatementList());
            }

            @Override
            public void onFailure(Call<StatmentModel> call, Throwable t) {

            }
        });
    }

    public LiveData<List<StatmentModel>> getStatments() {
        return statments;
    }

}
