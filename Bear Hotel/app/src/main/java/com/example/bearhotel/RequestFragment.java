package com.example.bearhotel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView textBooking, textRepair,textClean,textBellman,textParking;
    Button btnTaxi,btnRepair,btnClean,btnBellman,btnParking;
    String userName;

    public RequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestFragment newInstance(String param1, String param2) {
        RequestFragment fragment = new RequestFragment();
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
        View view =  inflater.inflate(R.layout.fragment_request, container, false);

        textBooking = view.findViewById(R.id.textBooking);
        textRepair = view.findViewById(R.id.textRepair);
        textClean = view.findViewById(R.id.textClean);
        textBellman = view.findViewById(R.id.textBellman);
        textParking = view.findViewById(R.id.textParking);

        Bundle extras = getActivity().getIntent().getExtras();
        userName = extras.getString("userName");

        btnTaxi = view.findViewById(R.id.btnTaxi);
        btnTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BookingRequest.class);
                intent.putExtra("Booking",textBooking.getText().toString());
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
        btnRepair = view.findViewById(R.id.btnRepair);
        btnRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BookingRequest.class);
                intent.putExtra("Booking",textRepair.getText().toString());
                startActivity(intent);
            }
        });
        btnClean = view.findViewById(R.id.btnClean);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BookingRequest.class);
                intent.putExtra("Booking",textClean.getText().toString());
                startActivity(intent);
            }
        });
        btnBellman = view.findViewById(R.id.btnBellman);
        btnBellman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BookingRequest.class);
                intent.putExtra("Booking",textBellman.getText().toString());
                startActivity(intent);
            }
        });
        btnParking = view.findViewById(R.id.btnParking);
        btnParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BookingRequest.class);
                intent.putExtra("Booking",textParking.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }
}