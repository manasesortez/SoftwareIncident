package com.amtodev.hospitalReservations;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amtodev.hospitalReservations.admin.Admin;


public class Login extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;
    Boolean valid = true;
    //FirebaseAuth fAuth;
    //FirebaseFirestore fStore;

    @SuppressLint({"CutPasteId", "ObsoleteSdkInt"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //fAuth = FirebaseAuth.getInstance();
        //fStore = FirebaseFirestore.getInstance();
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editTextEmailLogin);
        password = (EditText) findViewById(R.id.editTextPasswordLogin);
        loginBtn = (Button) findViewById(R.id.cirLoginButtonLogin);
        loginBtn.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkField(email);
                checkField(password);
                startActivity(new Intent(getApplicationContext(), Admin.class));
                finish();
                /*
                if (valid){
                    fAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login.this, "Loggedin Successfully", Toast.LENGTH_SHORT).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener(){
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, "Failed to Login" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }*/
            }
        });
    }

    /*
    private void checkUserAccessLevel(String uid) {
        DocumentReference df =  fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());
                if(documentSnapshot.getString("isAdmin") != null){
                    startActivity(new Intent(getApplicationContext(), Admin.class));
                    finish();
                }if (documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(getApplicationContext(), UserMain.class));
                    finish();
                }
            }
        });
    }*/

    public void onLoginClick(View View){
        startActivity(new Intent(this,Register.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        finish();
    }

    public void onFogetPasswordClick(View View){
        startActivity(new Intent(this,forget_password.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        finish();
    }

    public boolean checkField(EditText textfield){
        if(textfield.getText().toString().isEmpty()){
            textfield.setError("Error");
            valid = false;
        }else{
            valid = true;
        }
        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.getString("isAdmin") != null){
                        startActivity(new Intent(getApplicationContext(), Admin.class));
                        finish();
                    }
                    if (documentSnapshot.getString("isUser") != null){
                        startActivity(new Intent(getApplicationContext(), UserMain.class));
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener(){
                @Override
                public void onFailure(@NonNull Exception e) {

                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            });

        }*/
    }
}