package com.example.a215021437.takemehome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerloginActivity extends AppCompatActivity {

    private EditText eEmail, ePassword;
    private Button bLogin, bRegistrtion;

    private FirebaseAuth nAuth;

    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        nAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(CustomerloginActivity.this, CustomerMapActivity.class);
                    startActivity(intent);
                    finish();
                    return;

                }

            }
        };


        eEmail = (EditText) findViewById(R.id.email_address);
        ePassword = (EditText) findViewById(R.id.password);

        bLogin = (Button) findViewById(R.id.Login);
        bRegistrtion = (Button) findViewById(R.id.Registration);



        bRegistrtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eEmail.toString() != ""  && ePassword.toString() != "") {
                    final String email = eEmail.getText().toString();
                    final String pasword = ePassword.getText().toString();
                    nAuth.createUserWithEmailAndPassword(email, pasword).addOnCompleteListener(CustomerloginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(CustomerloginActivity.this, "Couldn't register, check internet connection", Toast.LENGTH_SHORT).show();

                            } else {
                                String user_id = nAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(user_id);
                                current_user_db.setValue(true);
                                Toast.makeText(CustomerloginActivity.this, "Registered successfully, proceed to login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(CustomerloginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();

                }

            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eEmail.toString() != ""  && ePassword.toString() != "") {
                    final String email = eEmail.getText().toString();
                    final String pasword = ePassword.getText().toString();
                    nAuth.signInWithEmailAndPassword(email, pasword).addOnCompleteListener(CustomerloginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(CustomerloginActivity.this, "Credentials doesn't match", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(CustomerloginActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }else{
                    Toast.makeText(CustomerloginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        nAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        nAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
