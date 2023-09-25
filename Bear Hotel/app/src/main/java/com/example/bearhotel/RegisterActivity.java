package com.example.bearhotel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText editTextmail, editTextPassword, editTextConfirm, editTextPhone,editTextFirstName,editTextLastName;
    ProgressBar progressBar;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth =  FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.create_an_account);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        editTextmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirm = findViewById(R.id.editTextConfirm);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        progressBar = findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(v-> finish());


        btnRegister.setOnClickListener(v-> createAccount());


    }
    void createAccount(){
        String email = editTextmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirm.getText().toString();
        String phone = editTextPhone.getText().toString();
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();

        boolean isValidated = validateData(email,password,confirmPassword,phone);
        if(!isValidated){
            return;
        }
        createAccountInFirebase(email,password,firstName,lastName,phone);
    }

    void createAccountInFirebase(final String email,final String password,final String firstName,final String lastName,final String phone)
    {
        changeInProgress(true);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this,
                 new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this,"Successfully create account",Toast.LENGTH_SHORT).show();
                            String uid =firebaseAuth.getCurrentUser().getUid().toString();
                            User user = new User(email, firstName, lastName,phone);
                            mDatabase.child("users").child(uid).setValue(user);
                            firebaseAuth.signOut(); // Signs out the user, as we only signed in to add it to the DB
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    void changeInProgress(boolean inProgress){
        if(inProgress)
        {
            progressBar.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            btnRegister.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email, String password, String confirmPassword, String phone){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextmail.setError("Email is invalid");
            return false;
        }

        if(password.length()<6)
        {
            editTextPassword.setError("Password length is invalid");
            return false;
        }
        if(!password.equals(confirmPassword))
        {
            editTextConfirm.setError("Password not matched");
            return false;
        }
        if(phone.length()<10)
        {
            editTextPhone.setError("Phone length is invalid");
            return false;
        }
        return true;
    }
}