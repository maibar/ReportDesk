package com.example.lenovo.reportdesk;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements OnInterface {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment fragment = HomeFragment.newInstance("param1","param2");
                    transaction.replace(R.id.display_container, fragment);
                    transaction.commit();
                    break;
                case R.id.navigation_dashboard:
                    ReportFormFragment fragment2 = ReportFormFragment.newInstance("param1","param2");
                    transaction.replace(R.id.display_container, fragment2);
                    transaction.commit();
                    break;
                case R.id.navigation_notifications:
                    DataDisplayFragment fragment3 = DataDisplayFragment.newInstance("param1","param2");
                    transaction.replace(R.id.display_container, fragment3);
                    transaction.commit();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Report> reports = realm.where(Report.class)
                .sort("asset_name", Sort.ASCENDING)
                .findAll();

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerView_data);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        ReportAdapter adapter = new ReportAdapter(reports, true, (ReportAdapter.OnTicketItemInteraction) this);
        myrv.setAdapter(adapter);
    }

    @Override
    public void OnInterface() {

    }
}
