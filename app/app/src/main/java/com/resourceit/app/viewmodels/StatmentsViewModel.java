package com.resourceit.app.viewmodels;

import android.app.Application;

import com.resourceit.app.models.StatmentModel;
import com.resourceit.app.repositories.StatmentsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StatmentsViewModel extends AndroidViewModel {

        private StatmentsRepository statmentsRepository;
        private LiveData<List<StatmentModel>> statments;
        private Application application;

        public StatmentsViewModel(@NonNull Application application) {
            super(application);
            this.application = application;
        }
        public void UpdateStatments(String id){
            statmentsRepository = new StatmentsRepository(application, id);
            statments = statmentsRepository.getStatments();
        }

        public LiveData<List<StatmentModel>> getStatments() { return statments; }

}
