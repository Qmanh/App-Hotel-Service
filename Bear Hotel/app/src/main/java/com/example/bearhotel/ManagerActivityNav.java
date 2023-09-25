package com.example.bearhotel;

import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManagerActivityNav extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavView;
    Button btnSignOut, btnAdmin;
    TextView textUsername;
    String userName;
    private FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_nav);

        viewPager2 = findViewById(R.id.view_pager);
        bottomNavView=findViewById(R.id.bottomNav);
        textUsername = findViewById(R.id.textUsername);

        Bundle extras = getIntent().getExtras();
        userName = extras.getString("userName");
        System.out.println(userName);

        textUsername.setText(userName);

        btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ManagerActivityNav.this, "Signed Out", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ManagerActivityNav.this, MainActivity.class);
                i.putExtra("userName",userName);
                startActivity(i);
                finish();
            }
        });



        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch(position){
                    case 0 :
                        bottomNavView.getMenu().findItem(R.id.homePage).setChecked(true);
                        break;
                    case 1 :
                        bottomNavView.getMenu().findItem(R.id.requestPage).setChecked(true);
                        break;
                    case 2 :
                        bottomNavView.getMenu().findItem(R.id.menuPage).setChecked(true);
                        break;
                    case 3 :
                        bottomNavView.getMenu().findItem(R.id.payments).setChecked(true);
                        break;
                }
            }
        });

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.homePage:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.requestPage:
                        viewPager2.setCurrentItem(1);

                        break;
                    case R.id.menuPage:
                        viewPager2.setCurrentItem(2);

                        break;
                    case R.id.payments:
                        viewPager2.setCurrentItem(3);

                        break;
                }
                return true;
            }
        });
    }
    private void toggleVisibility(Menu menu, int id, boolean visible){
        menu.findItem(id).setVisible(visible);
    }

}