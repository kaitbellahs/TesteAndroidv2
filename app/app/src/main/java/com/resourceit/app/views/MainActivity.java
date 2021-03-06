package com.resourceit.app.views;

import android.os.Bundle;
import android.view.View;

import com.resourceit.app.R;
import com.resourceit.app.dao.LoginDao;
import com.resourceit.app.interfaces.APIService;
import com.resourceit.app.tools.AppDatabase;
import com.resourceit.app.viewmodels.LoginViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    @BindView(R.id.loading) ConstraintLayout loading;
    public APIService API;
    private AppDatabase db;
    public LoginDao loginDao;
    public LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        updateFragment(new LoginFragment(), "LOGIN");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "santander").allowMainThreadQueries().build();
        loginDao = db.loginDao();
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }


    public void updateFragment(Fragment destFragment, Boolean back, String TAG) {
        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments, destFragment);
        if(back)fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void updateFragment(Fragment destFragment, String TAG) {

        fragmentManager = this.getSupportFragmentManager();
        Fragment myFragment = (Fragment)fragmentManager.findFragmentByTag(TAG);
        if (myFragment == null || !myFragment.isVisible()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(!destFragment.isAdded()){
                for (Fragment fragment:getSupportFragmentManager().getFragments()) {
                    if (fragment!=null) {
                        getSupportFragmentManager().beginTransaction().detach(fragment).commit();
                    }
                }
                fragmentTransaction.add(R.id.fragments, destFragment);
            }
            if(destFragment.isDetached()){
                for (Fragment fragment:getSupportFragmentManager().getFragments()) {
                    if (fragment!=null) {
                        getSupportFragmentManager().beginTransaction().detach(fragment).commit();
                    }
                }
                fragmentTransaction.attach(destFragment);
            }
            fragmentTransaction.commit();
        }
    }

    public void Loading(Boolean visibility) {
        loading.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }
}
