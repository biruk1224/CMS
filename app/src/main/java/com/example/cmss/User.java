package com.example.cmss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cmss.Adapter.RecyclerAdapter;
import com.example.cmss.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User extends AppCompatActivity {
    private DatabaseReference myRef;
    //Variables
    private ArrayList<Users> usersArrayList;
    //Widget
    private RecyclerView recyclerView;
    //Adapter
    private RecyclerAdapter recyclerAdapter;

    private String FullName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        recyclerView =findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);





// Firebase
        myRef = FirebaseDatabase.getInstance().getReference();
//Arraylist
        usersArrayList = new ArrayList<>();
//Get Data Method
        GetDataFromFirebase();
        //Clear Arraylist
        ClearALL();









    }
    private void GetDataFromFirebase() {
        Query query = myRef.child("Users");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearALL();

                if(snapshot.exists()){
                    for (DataSnapshot snapshot2 : snapshot.getChildren()) {
                        if(snapshot2.exists()) {
                              snapshot2.getKey().toString();
                              FullName = snapshot2.getKey();
                            //Names = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@gmail.com", "");



                            Users user = new Users();
                       //     if(snapshot2.child("DISTANCE" + Names).exists())


                       user.setName(snapshot2.child("name").getValue().toString().toUpperCase());
                          //  Intent intent = new Intent(getApplicationContext(),GroupChatActivity.class);
                          //  intent.putExtra("name",Name);

                            usersArrayList.add(user);



                        }

                    }

                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(),usersArrayList);
                recyclerView.setAdapter(recyclerAdapter);

                recyclerAdapter.notifyDataSetChanged();




            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"error " + error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private  void ClearALL(){
        if(usersArrayList!=null){
            usersArrayList.clear();
        }
        if(recyclerAdapter!=null){
            recyclerAdapter.notifyDataSetChanged();

        }
        usersArrayList = new ArrayList<>();
    }


}