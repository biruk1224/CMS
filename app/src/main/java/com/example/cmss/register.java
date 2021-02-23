package com.example.cmss;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cmss.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    EditText    mfullname,memail,mpassword,mconfirmpassword;
    Button      msignup;
    private   FirebaseAuth    fAuth;
    TextView    mlogin;
    ProgressBar progressBar;
    DatabaseReference reference;
    FirebaseDatabase rootnode;

    private String email,password,confirmPassword,fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mfullname = findViewById(R.id.fullname);
        memail   = findViewById(R.id.inputemail);
        mpassword    =   findViewById(R.id.inputPassword);
        mconfirmpassword =   findViewById(R.id.inputConfirmPassword);
        msignup  =   findViewById(R.id.btnRegister);
        mlogin=  findViewById(R.id.gotoLogin);
        progressBar = findViewById(R.id.progressBar);


        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();

        }

        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = memail.getText().toString().trim();
                fullname = mfullname.getText().toString().toLowerCase();
                password =   mpassword.getText().toString().trim();
                confirmPassword = mconfirmpassword.getText().toString().trim();



                if(TextUtils.isEmpty(email)){
                    memail.setError("Email required");
                    return;

                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Fill the password");
                    return;

                }
                if(password.length()<8) {
                    mpassword.setError("Password must be atleast 8 character");
                    return;
                }
                if(!password.equals(confirmPassword)) {
                    mpassword.setError("password not match");
                    return;
                }

                if(email.contains(" ")){
                    memail.setError("No space allowed");
                    return;
                }



                progressBar.setVisibility(View.VISIBLE);


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {


                           // Toast.makeText(register.this, "login successfull", Toast.LENGTH_SHORT).show();


                            //startActivity(new Intent(getApplicationContext(), login_layout.class));





                            FirebaseUser firebaseUser = fAuth.getCurrentUser();
                            Users u = new Users();
                            u.setEmail(email);
                            u.setName(fullname);
                            u.setPassword(password);


                            reference.child(firebaseUser.getUid()).setValue(u)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {



                                                Toast.makeText(getApplicationContext(),"member register successfully",Toast.LENGTH_SHORT).show();

                                                progressBar.setVisibility(View.GONE);

                                                Intent i = new Intent(register.this,Login.class);
                                                //startActivity(i);
                                                //finish();

                                            } else {

                                                Toast.makeText(getApplicationContext(), "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility((View.GONE));
                                            }

                                        }
                                    });
                        }
                        else {
                            Toast.makeText(register.this, "error username taken", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }




                    }
                });
            }




        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }



}

