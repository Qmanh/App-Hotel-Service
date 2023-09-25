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
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersMenu extends AppCompatActivity {

    EditText editRoom,editQty;
    Button btnOrderMenu,btnBack;
    ViewPager2 viewPager2;

    String userName,nameFood,room,strToday;
    int qty;
    double totalPrice;
    String price;
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
        setContentView(R.layout.book_menu);

        editRoom = findViewById(R.id.editRoom);
        editQty = findViewById(R.id.editQty);
        btnOrderMenu = findViewById(R.id.btnOrderMenu);
        btnBack = findViewById(R.id.btnBack);

        Bundle extras = getIntent().getExtras();
        userName = extras.getString("userName");
        nameFood = extras.getString("NameFood");
        price = extras.getString("Price");
        room = editRoom.getText().toString();



        Date today = new Date();
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        strToday = null;

        strToday = outputFormat.format(today);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        dbRef = mFirebaseDatabase.getReference("Order");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


         btnOrderMenu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 final Order order = new Order(uid,nameFood,Double.parseDouble(price),Integer.parseInt(editQty.getText().toString()),strToday,editRoom.getText().toString(),Double.parseDouble(price)*Integer.parseInt(editQty.getText().toString()));

                 mDatabase.child("order").child(uid).push().setValue(order);

                 Toast.makeText(OrdersMenu.this, "Order Successfully", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(OrdersMenu.this,ManagerActivityNav.class);
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });
    }
}
