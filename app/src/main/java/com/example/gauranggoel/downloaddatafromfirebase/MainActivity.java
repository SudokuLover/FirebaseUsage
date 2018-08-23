package com.example.gauranggoel.downloaddatafromfirebase;

//so far -> dont know how many images are at firebase ->
/*
*
* question arises -> as we download all the images obviously -> so need to do some different task -> lets see what to do
*
*       now our concern is with database instead storage -> as name and url of images are at ->  firebase
*
*       We use here RecyclerView -> Important from interview point of view
*
*       what is differnce between recycler view vs listview
*
*       as both look similar -> but they have huge difference in processing
*
*       as in listview -> whenever it processes the data -> always get the id
*
*       i.e if it shows -> 10 times then get id of viewing object 10 timeas
*       (getInflator() -> load layout and get id)
*
*       where as Recycler view uses view holder -> it holds the viewed data so that second time -> it dont hit system but rather takes data from holder
*
*       fast the processing speed
*
*       need to add some libraries
*
   *     compile 'com.github.bumptech.glide:glide:3.7.0'
        need to add this lib
*
        compile 'com.android.support:cadview-v7:26.+'
*
* i.e using recycler view and card view to implement this ->
*
*        so we have put a recycler view in main xml , then create an another xml -> which involves card view (relative layout)
*          then create customAdapter .java class
*
* */


import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //recyclerView object
    RecyclerView recyclerView;
   //adapter object

    RecyclerView.Adapter adapter;
    //databse refrence

    DatabaseReference mDatabase;
    //progress dialog refrence
    ProgressDialog progressDialog;
    //refrence of list of Upload class
    List<Upload> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog=new ProgressDialog(this);

        uploads = new ArrayList<>();

        progressDialog.setMessage("please wait ..");
        progressDialog.show();

        mDatabase= FirebaseDatabase.getInstance().getReference(Refrences.DATABASE_REFRENCE);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                progressDialog.dismiss();

                for(DataSnapshot postSnapShot : dataSnapshot.getChildren())
                {
                    Upload upload = postSnapShot.getValue(Upload.class);
                    uploads.add(upload);
                }

                adapter= new CustomAdaptor(getApplicationContext(),uploads);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
