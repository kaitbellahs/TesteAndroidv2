package com.resourceit.app.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.resourceit.app.Adaptera.StatmentsAdapter;
import com.resourceit.app.R;
import com.resourceit.app.holders.StatmentHolder;
import com.resourceit.app.models.LoginModel;
import com.resourceit.app.models.StatmentModel;
import com.resourceit.app.viewmodels.LoginViewModel;
import com.resourceit.app.viewmodels.StatmentsViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementsFragment extends Fragment {

    @BindView(R.id.statments)
    RecyclerView statments;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.accountNum) TextView account;
    @BindView(R.id.balanceVal) TextView balance;
    private Integer userId;

    private MainActivity activity;
    private List<StatmentModel> statmentsList;
    StatmentsAdapter mAdapter;
    private Boolean working = false;
    public StatmentsViewModel statmentsViewModel;
    private LoginViewModel loginViewModel;
    private StatementsFragment ctx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ctx = this;
        View view = inflater.inflate(R.layout.fragment_statements, container, false);
        ButterKnife.bind(this, view);

        statmentsViewModel = ViewModelProviders.of(this).get(StatmentsViewModel.class);
        activity = (MainActivity) getActivity();

        loginViewModel = activity.loginViewModel;
        statments.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        statments.setLayoutManager(mLayoutManager);
        mAdapter = new StatmentsAdapter(getActivity());
        statments.setAdapter(mAdapter);
        loginViewModel.getLogin().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(@Nullable final LoginModel login) {

                name.setText(login.getName());
                account.setText(login.getBankAccount() + " / "+login.getAgency());
                NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
                balance.setText("R$"+nf.format(Double.parseDouble(login.getBalance()
                        .replace(".", "")
                        .replace(",", "."))));
                userId = login.getUserId();
                statmentsViewModel.UpdateStatments(userId.toString());
                statmentsViewModel.getStatments().observe(ctx, new Observer<List<StatmentModel>>() {
                    @Override
                    public void onChanged(@Nullable final List<StatmentModel> statments) {
                        mAdapter.insertItem(statments);
                        activity.Loading(false);
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.logout)
    void Logout() {

        loginViewModel.DeleteAll();
        activity.updateFragment(new LoginFragment(), "LOGIN");
    }


}
