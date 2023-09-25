package com.example.bearhotel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    TextView textViewB1,textViewB2, textViewB3, textViewDrink1,textViewDrink2,textViewDrink3, textViewDessert1,textViewDessert2,textViewDessert3;
    TextView textPrice1,textPrice2, textPrice3, textDrinkPrice1,textDrinkPrice2,textDrinkPrice3, textDessertPrice1,textDessertPrice2,textDessertPrice3;
    Button btnOrderB1, btnOrderB2,btnOrderB3, btnOrderDrink1, btnOrderDrink2,btnOrderDrink3, btnOrderDessert1, btnOderDessert2, btnOrderDessert3;

    String userName;

    ImageButton IBLeft, IBMiddle, IBRight;
    LinearLayout linear1,linear2,linear3,linear4,linear5,linear6,linear7,linear8,linear9;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Bundle extras = getActivity().getIntent().getExtras();
        userName = extras.getString("userName");

        textViewB1 = view.findViewById(R.id.textFood1);
        textViewB2 = view.findViewById(R.id.textFood2);
        textViewB3 = view.findViewById(R.id.textFood3);


        textViewDrink1 = view.findViewById(R.id.textDrink1);
        textViewDrink2 = view.findViewById(R.id.textDrink5);
        textViewDrink3 = view.findViewById(R.id.textDrink7);

        textViewDessert1 = view.findViewById(R.id.textDessert1) ;
        textViewDessert2 = view.findViewById(R.id.textDessert4) ;
        textViewDessert3 = view.findViewById(R.id.textDessert7) ;

         textPrice1 = view.findViewById(R.id.textPrice1);
         textPrice2 = view.findViewById(R.id.textPrice2);
         textPrice3 =  view.findViewById(R.id.textPrice3);

         textDrinkPrice1  = view.findViewById(R.id.textDrinkPrice1);
         textDrinkPrice2 = view.findViewById(R.id.textDrinkPrice2);
         textDrinkPrice3 = view.findViewById(R.id.textDrinkPrice3);

         textDessertPrice1 = view.findViewById(R.id.textDrinkPrice1);
         textDessertPrice2 = view.findViewById(R.id.textDrinkPrice2);
         textDessertPrice3 = view.findViewById(R.id.textDrinkPrice3);

         btnOrderB1 = view.findViewById(R.id.btnOrder1);
         btnOrderB1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewB1.getText().toString());
                 intent.putExtra("Price",textPrice1.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });


         btnOrderB2 = view.findViewById(R.id.btnOrder2);
         btnOrderB2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewB2.getText().toString());
                 intent.putExtra("Price",textPrice2.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });
         btnOrderB3 = view.findViewById(R.id.btnOrder3);
         btnOrderB3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewB3.getText().toString());
                 intent.putExtra("Price",textPrice3.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });

         btnOrderDrink1 =  view.findViewById(R.id.btnOrderDrink1);
         btnOrderDrink1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewDrink1.getText().toString());
                 intent.putExtra("Price",textDrinkPrice1.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });
         btnOrderDrink2 =  view.findViewById(R.id.btnOrderDrink2);
         btnOrderDrink2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewDrink2.getText().toString());
                 intent.putExtra("Price",textDrinkPrice2.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });
         btnOrderDrink3 =  view.findViewById(R.id.btnOrderDrink3);
         btnOrderDrink3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewDrink3.getText().toString());
                 intent.putExtra("Price",textDrinkPrice3.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });

         btnOrderDessert1 =  view.findViewById(R.id.btnOrderDessert1);
         btnOrderDessert1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewDessert1.getText().toString());
                 intent.putExtra("Price",textDessertPrice1.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });
         btnOderDessert2 =  view.findViewById(R.id.btnOrderDessert2);
         btnOderDessert2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewDessert2.getText().toString());
                 intent.putExtra("Price",textDessertPrice2.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });
         btnOrderDessert3 =  view.findViewById(R.id.btnOrderDessert3);
         btnOrderDessert3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),OrdersMenu.class);
                 intent.putExtra("NameFood",textViewDessert3.getText().toString());
                 intent.putExtra("Price",textDessertPrice3.getText().toString());
                 intent.putExtra("userName",userName);
                 startActivity(intent);
             }
         });

         linear1 = view.findViewById(R.id.linear1);
         linear2 = view.findViewById(R.id.linear2);
         linear3 = view.findViewById(R.id.linear3);
         linear4 = view.findViewById(R.id.linear4);
         linear5 = view.findViewById(R.id.linear5);
         linear6 = view.findViewById(R.id.linear6);
         linear7 = view.findViewById(R.id.linear7);
         linear8 = view.findViewById(R.id.linear8);
         linear9 = view.findViewById(R.id.linear9);



         IBLeft = view.findViewById(R.id.imageLeft);
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

         IBMiddle = view.findViewById(R.id.imageMiddle);
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

         IBRight = view.findViewById(R.id.imageRight);
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





        return view;
    }
}