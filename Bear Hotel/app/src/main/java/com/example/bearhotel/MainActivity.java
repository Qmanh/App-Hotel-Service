package com.example.bearhotel;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText editTextUser, editTextPassword;
    ProgressBar progressBarLogin;

    FirebaseFirestore firestore;

   private String userName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        editTextUser = findViewById(R.id.editTextUser);

        editTextPassword = findViewById(R.id.editTextPassword);
        progressBarLogin = findViewById(R.id.progressBarLogin);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);


            }
        });
        btnLogin.setOnClickListener(v -> loginUser());



    }

    private void loginUser() {
        String email = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        boolean isValidated = validateData(email, password);

        loginAccountInFirebase(email, password);
    }

    void loginAccountInFirebase(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                {
                    changeInProgress(false);
                    if (task.isSuccessful()) {

                        if(email.equals("admin@gmail.com"))
                        {
                            Intent intent = new Intent(MainActivity.this, AdminPage_Activity.class);
                            intent.putExtra("userName", editTextUser.getText().toString());
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent = new Intent(MainActivity.this, ManagerActivityNav.class);
                            intent.putExtra("userName", editTextUser.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    void changeInProgress(boolean inProgress) {
        if (inProgress) {
            progressBarLogin.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        } else {
            progressBarLogin.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email, String password) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextUser.setError("Email is invalid");
            return false;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Password length is invalid ");
            return false;
        }
        return true;
    }

    private void ReadDataFromFireStore()
    {
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("room")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                System.out.println(document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}