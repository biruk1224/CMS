package com.example.cmss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cmss.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private String Names;

    private String email;
    TextView T;
    TextView T2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Names = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace("@gmail.com", "");
  //      email =  FirebaseAuth.getInstance().getCurrentUser().getEmail();
      //  T = findViewById(R.id.name);
        //T2 = findViewById(R.id.email);
        //T2.setText(email);
        //T.setText(Names);
        final NavigationView nav_view =findViewById(R.id.nv);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.events)
                {
                   startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                }
                if(id == R.id.logout)
                {
                    // Sign out
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                if(id == R.id.attendance)
                {
                    startActivity(new Intent(getApplicationContext(),Attendance.class));
                }
                return true;
            }
        });
    }
}