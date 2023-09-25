package com.example.bearhotel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingRequest extends AppCompatActivity {
    EditText editTextRoom,editTextPhonne, editTextTime;
    Button btnBooking;

    String userName,Booking,room,time,phone,strToday;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String uid = mAuth.getCurrentUser().getUid().toString();

    // Variables / objects which will be used to add data to the database
    private DatabaseReference mDatabase;
    private DatabaseReference dbRef;
    private FirebaseDatabase mFirebaseDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.book_request);

        editTextRoom = findViewById(R.id.editTextRoom);
        editTextPhonne = findViewById(R.id.editTextPhonne);
        editTextTime = findViewById(R.id.editTextTime);
        btnBooking = findViewById(R.id.btnBooking);

        Bundle extras = getIntent().getExtras();
        userName = extras.getString("userName");
        Booking = extras.getString("Booking");



        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        dbRef = mFirebaseDatabase.getReference("Request");

        FloatingActionButton fabHome = (FloatingActionButton) findViewById(R.id.floatingActionButtonRequest);
        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BookingRequest.this, ManagerActivityNav.class);
                i.putExtra("userName", userName);
                startActivity(i);
                finish();
            }
        });



        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date today = new Date();
                String outputPattern = "dd/MM/yyyy";
                SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
                Date date = null;
                strToday = null;

                strToday = outputFormat.format(today);
                final Request request = new Request(uid,editTextRoom.getText().toString(),Booking,editTextPhonne.getText().toString(),editTextTime.getText().toString(),strToday);

                mDatabase.child("requests").child(uid).push().setValue(request);
                editTextPhonne.setText("");
                editTextRoom.setText("");
                editTextTime.setText("");
                Toast.makeText(BookingRequest.this, "Booking Successfully", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
