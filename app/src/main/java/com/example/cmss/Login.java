package com.example.cmss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmss.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText muser, mpassword;
    private Button mloginmember,mloginguest,mloginadmin;
    ProgressBar progressBar;
    TextView createaccount;
    private FirebaseAuth fAuth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        muser = findViewById(R.id.username);
        mpassword = findViewById(R.id.inputPassword);
        progressBar = findViewById(R.id.progressBar2);
        mloginmember = findViewById(R.id.btnLogin);
        mloginadmin = findViewById(R.id.btnsLogin);
        mloginguest = findViewById(R.id.btnLoginGuest);
        createaccount = findViewById(R.id.gotoRegister);
        fAuth = FirebaseAuth.getInstance();


        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), User.class));
            finish();
        }


            mloginmember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = muser.getText().toString().trim() + "@gmail.com";
                    final String password = mpassword.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        muser.setError("Email required");
                        return;

                    }
                    if (TextUtils.isEmpty(password)) {
                        mpassword.setError("Fill the password");
                        return;

                    }
                    if (password.length() < 8) {
                        mpassword.setError("Password must be atleast 8 character");
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    //autenticate the user
                    fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@ NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this,"user created successfull",Toast.LENGTH_SHORT).show();
                                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                //DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Password");
                                //ref.setValue(password);


                                startActivity(new Intent(getApplicationContext(),User.class));

                                progressBar.setVisibility(View.GONE);
                            }else {
                                Toast.makeText(Login.this, "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }
            });
            mloginadmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = muser.getText().toString().trim() + "@gmail.com";
                    final String password = mpassword.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        muser.setError("Email required");
                        return;

                    }
                    if (TextUtils.isEmpty(password)) {
                        mpassword.setError("Fill the password");
                        return;

                    }
                    if (password.length() < 8) {
                        mpassword.setError("Password must be atleast 8 character");
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    //autenticate the user
                    fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@ NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this,"Login successfull",Toast.LENGTH_SHORT).show();
                                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                startActivity(new Intent(getApplicationContext(),User.class));
                                progressBar.setVisibility(View.GONE);
                            }else {
                                Toast.makeText(Login.this, "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }
            });
            mloginguest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(getApplicationContext(),MainActivity2.class));

                }
            });
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });


    }

}