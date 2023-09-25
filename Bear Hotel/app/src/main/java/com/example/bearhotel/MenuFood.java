package com.example.bearhotel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuFood extends AppCompatActivity {
    TextView textViewB1,textViewB2, textViewB3, textViewDrink1,textViewDrink2,textViewDrink3, textViewDessert1,textViewDessert2,textViewDessert3;
    TextView textPrice1,textPrice2, textPrice3, textDrinkPrice1,textDrinkPrice2,textDrinkPrice3, textDessertPrice1,textDessertPrice2,textDessertPrice3;
    Button btnOrderB1, btnOrderB2,btnOrderB3, btnOrderDrink1, btnOrderDrink2,btnOrderDrink3, btnOrderDessert1, btnOderDessert2, btnOrderDessert3;

    String userName;

    ImageButton IBLeft, IBMiddle, IBRight;
    LinearLayout linear1,linear2,linear3,linear4,linear5,linear6,linear7,linear8,linear9;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.fragment_menu);

        Bundle extras = getIntent().getExtras();
        userName = extras.getString("userName");

        textViewB1 = findViewById(R.id.textFood1);
        textViewB2 = findViewById(R.id.textFood2);
        textViewB3 = findViewById(R.id.textFood3);


        textViewDrink1 = findViewById(R.id.textDrink1);
        textViewDrink2 = findViewById(R.id.textDrink5);
        textViewDrink3 = findViewById(R.id.textDrink7);

        textViewDessert1 = findViewById(R.id.textDessert1) ;
        textViewDessert2 = findViewById(R.id.textDessert4) ;
        textViewDessert3 = findViewById(R.id.textDessert7) ;

        textPrice1 = findViewById(R.id.textPrice1);
        textPrice2 = findViewById(R.id.textPrice2);
        textPrice3 = findViewById(R.id.textPrice3);

        textDrinkPrice1  = findViewById(R.id.textDrinkPrice1);
        textDrinkPrice2 = findViewById(R.id.textDrinkPrice2);
        textDrinkPrice3 = findViewById(R.id.textDrinkPrice3);

        textDessertPrice1 = findViewById(R.id.textDrinkPrice1);
        textDessertPrice2 = findViewById(R.id.textDrinkPrice2);
        textDessertPrice3 = findViewById(R.id.textDrinkPrice3);

        btnOrderB1 = findViewById(R.id.btnOrder1);
        btnOrderB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewB1.getText().toString());
                intent.putExtra("Price",textPrice1.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });


        btnOrderB2 = findViewById(R.id.btnOrder2);
        btnOrderB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewB2.getText().toString());
                intent.putExtra("Price",textPrice2.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
        btnOrderB3 = findViewById(R.id.btnOrder3);
        btnOrderB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewB3.getText().toString());
                intent.putExtra("Price",textPrice3.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });

        btnOrderDrink1 =  findViewById(R.id.btnOrderDrink1);
        btnOrderDrink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewDrink1.getText().toString());
                intent.putExtra("Price",textDrinkPrice1.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
        btnOrderDrink2 =  findViewById(R.id.btnOrderDrink2);
        btnOrderDrink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewDrink2.getText().toString());
                intent.putExtra("Price",textDrinkPrice2.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
        btnOrderDrink3 =  findViewById(R.id.btnOrderDrink3);
        btnOrderDrink3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewDrink3.getText().toString());
                intent.putExtra("Price",textDrinkPrice3.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });

        btnOrderDessert1 = findViewById(R.id.btnOrderDessert1);
        btnOrderDessert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewDessert1.getText().toString());
                intent.putExtra("Price",textDessertPrice1.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
        btnOderDessert2 =  findViewById(R.id.btnOrderDessert2);
        btnOderDessert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewDessert2.getText().toString());
                intent.putExtra("Price",textDessertPrice2.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
        btnOrderDessert3 =  findViewById(R.id.btnOrderDessert3);
        btnOrderDessert3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuFood.this,OrdersMenu.class);
                intent.putExtra("NameFood",textViewDessert3.getText().toString());
                intent.putExtra("Price",textDessertPrice3.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });

        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        linear6 = findViewById(R.id.linear6);
        linear7 = findViewById(R.id.linear7);
        linear8 = findViewById(R.id.linear8);
        linear9 = findViewById(R.id.linear9);



        IBLeft = findViewById(R.id.imageLeft);
        IBLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear1.setVisibility(View.VISIBLE);
                linear4.setVisibility(View.VISIBLE);
                linear7.setVisibility(View.VISIBLE);

                linear2.setVisibility(View.GONE);
                linear5.setVisibility(View.GONE);
                linear8.setVisibility(View.GONE);

                linear3.setVisibility(View.GONE);
                linear6.setVisibility(View.GONE);
                linear9.setVisibility(View.GONE);
            }
        });

        IBMiddle = findViewById(R.id.imageMiddle);
        IBMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear2.setVisibility(View.VISIBLE);
                linear5.setVisibility(View.VISIBLE);
                linear8.setVisibility(View.VISIBLE);

                linear1.setVisibility(View.GONE);
                linear4.setVisibility(View.GONE);
                linear7.setVisibility(View.GONE);

                linear3.setVisibility(View.GONE);
                linear6.setVisibility(View.GONE);
                linear9.setVisibility(View.GONE);
            }
        });

        IBRight = findViewById(R.id.imageRight);
        IBRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear2.setVisibility(View.GONE);
                linear5.setVisibility(View.GONE);
                linear8.setVisibility(View.GONE);

                linear1.setVisibility(View.GONE);
                linear4.setVisibility(View.GONE);
                linear7.setVisibility(View.GONE);

                linear3.setVisibility(View.VISIBLE);
                linear6.setVisibility(View.VISIBLE);
                linear9.setVisibility(View.VISIBLE);
            }
        });

    }
}
