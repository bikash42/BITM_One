package com.example.bikash.bitmfirstproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.bikash.bitmfirstproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmail;
    private EditText mPass;
    private EditText mRePass;
    private Button signupBtn;
    private Button loginBtn;

    private ProgressDialog signUpProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.userET);
        mPass = findViewById(R.id.passET);
        mRePass = findViewById(R.id.repassET);
        signupBtn = findViewById(R.id.SignUpBtn);
        loginBtn = findViewById(R.id.loginBtn);

        signUpProgress = new ProgressDialog(this);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = mEmail.getText().toString();
                String pass = mPass.getText().toString();
                String rePass = mRePass.getText().toString();

                if(user.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Enter Your Mail/UserName", Toast.LENGTH_SHORT).show();
                }
                if (pass.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Enter Your PASSWORD", Toast.LENGTH_SHORT).show();
                }
                if (rePass.isEmpty() || !rePass.equals(pass))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Your RE-PASSWORD", Toast.LENGTH_SHORT).show();
                }
                else{
                    signUpProgress.setTitle("Create a new user");
                    signUpProgress.setMessage("Please wait while we create your account...!");
                    signUpProgress.setCanceledOnTouchOutside(false);
                    signUpProgress.show();
                    register_user(user,pass,rePass);
                }


            }
        });
    }

    //TODO:CREATE A NEW ACCOUNT
    private void register_user(String user, String pass, String rePass) {

        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            signUpProgress.dismiss();
                            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignUpActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            signUpProgress.hide();
                            Toast.makeText(SignUpActivity.this, "Authentication failed!!!" + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void LoginSignUp(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}