package com.application.dake.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.application.dake.R;
import com.application.dake.controllers.AccountAdapter;
import com.application.dake.models.AccountItem;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    private RecyclerView accountRecycler;
    private RecyclerView.Adapter accountAdapter;
    private RecyclerView.LayoutManager accountLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ArrayList<AccountItem> AccountsList= new ArrayList<>();
        AccountsList.add(new AccountItem("Profile",R.drawable.ic_profile ));
        AccountsList.add(new AccountItem("Orders",R.drawable.ic_orders ));
        AccountsList.add(new AccountItem("Notifications",R.drawable.ic_notifications ));
        AccountsList.add(new AccountItem("Help",R.drawable.ic_help ));
        AccountsList.add(new AccountItem("Rewards",R.drawable.ic_rewards ));
        AccountsList.add(new AccountItem("About",R.drawable.ic_about ));

        accountRecycler= findViewById(R.id.recyclerView);
        accountRecycler.setHasFixedSize(true);
        accountLayoutManager= new LinearLayoutManager(this);
        accountAdapter= new AccountAdapter(AccountsList);

        accountRecycler.setLayoutManager(accountLayoutManager);
        accountRecycler.setAdapter(accountAdapter);

    }
}