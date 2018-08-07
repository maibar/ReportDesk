package com.example.lenovo.reportdesk;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import java.util.Date;
import java.util.UUID;

import io.realm.Realm;

public class ReportNewActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_data_display);

//        Toolbar toolbar = (Toolbar) findViewById(this);
//        setSupportActionBar(toolbar);
    }

    public void onSubmit(View view){
        final Report report = new Report();
        report.id = UUID.randomUUID().toString();
        report.ticket_id = report.id;

        report.brand = ((TextView)findViewById(R.id.et_brand)).getText().toString();
        report.asset_name = ((TextView)findViewById(R.id.et_asset_name)).getText().toString();
        report.serial_number = ((TextView)findViewById(R.id.et_serial_number)).getText().toString();
        report.location = ((TextView)findViewById(R.id.et_location)).getText().toString();
        report.remarks = ((TextView)findViewById(R.id.et_remarks)).getText().toString();

        report.dateCreated = new Date();
        report.dateModified = report.dateCreated;

        Realm realm = Realm.getDefaultInstance();

        final String chatMessage = "{" +
                "\"id\": \"fb666f3a-ac1fa0cd-75ccaaa0-8366d819\"," +
                "\"message\": \"Alright go ahead.\","+
                "\"ticket_id\": \"80\","+
                "\"dateModified\": \"2018-08-02 16:09:12.0\","+
                "\"status\": \"UNREAD\","+
                "\"sender_id\": \"farooq.zaman@me.com\"," +
                "\"dateCreated\": \"2018-08-02 16:09:12.0\"," +
                "\"receiver_id\": \"\"" +
                "}";

        realm.beginTransaction();
        Report manageReport = realm.copyToRealmOrUpdate(report);
//        Message managedMessage = realm.createOrUpdateObjectFromJson(Message.class, chatMessage);
//        manageReport.allMessages.add(managedMessage);
//        manageReport.latestMessage = managedMessage;
        realm.commitTransaction();

        finish();
    }
}
