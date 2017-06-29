//package com.app.kc.superwoman;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class signup extends AppCompatActivity {
//    private EditText inemail,inpassword,inuser,inphone,inlocation;
//    private Button insubmit;
//    private FirebaseAuth auth;
//    private FirebaseAuth.AuthStateListener mauth;
//
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login);
//        auth = FirebaseAuth.getInstance();
//
//        //email pass simple login
//        insubmit = (Button) findViewById(R.id.submit0);
//        inemail = (EditText) findViewById(R.id.email0);
//        inpassword = (EditText) findViewById(R.id.password0);
//        inuser = (EditText) findViewById(R.id.username0);
//        inphone = (EditText) findViewById(R.id.phone0);
//        //inlocation
//
//        insubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Startsignin();
//            }
//        });
//
//    }
//    private void Startsignin() {
//        String username = inuser.getText().toString();
//        String emal = inemail.getText().toString();
//        String passwrd = inpassword.getText().toString();
//
//        //create user
//        if (TextUtils.isEmpty(emal) || TextUtils.isEmpty(passwrd)) {
//            Toast.makeText(signup.this, "enta mada5altsh 7aga y 7omaarr ! ", Toast.LENGTH_SHORT).show();
//        } else {
//            auth.createUserWithEmailAndPassword(emal, passwrd).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    Toast.makeText(signup.this, "el password aw el email ghalatt , rage3 nafsak!", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//}