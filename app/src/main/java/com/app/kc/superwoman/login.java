package com.app.kc.superwoman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText inputemail,inputpassword;
    private Button inputsubmit;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mauth;
    //private Button loginbutton;
    LoginButton loginbutton;
    CallbackManager callbackManager;
    TextView txtvew;

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mauth);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.login);
        auth = FirebaseAuth.getInstance();

        //email pass simple login
        inputsubmit = (Button) findViewById(R.id.submit1);
        inputemail = (EditText) findViewById(R.id.email1);
        inputpassword = (EditText) findViewById(R.id.password2);
        txtvew = (TextView) findViewById(R.id.txtvew);

        //el function dih hasta3mlha lama agy ahawel 3la phase 1
        mauth = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(login.this, AccountActivity.class));
                }
            }
        };
        inputsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Startsignin();
            }
        });

        //facebook login #1
        loginbutton = (LoginButton) findViewById(R.id.btnfacebook);
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                txtvew.setText("ed5ol b reglak el yemiin ybni!" + loginResult.getAccessToken().getUserId()
                        +"\n" + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                txtvew.setText("enta cancelt el login y roo7 omak!");
            }

            @Override
            public void onError(FacebookException error) {
                txtvew.setText("برجاء المحاولة مرة اخري تن تن تن !" + error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void Startsignin() {
        String emal = inputemail.getText().toString();
        String passwrd = inputpassword.getText().toString();

        //create user
        if (TextUtils.isEmpty(emal) || TextUtils.isEmpty(passwrd)) {
            Toast.makeText(login.this, "enta mada5altsh 7aga! ", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(emal, passwrd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(login.this, "el password aw el email ghalatt , rage3 nafsak!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}