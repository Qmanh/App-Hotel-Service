package com.example.bearhotel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayFragment extends Fragment {

    private String userName,name;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference dbRef,dbBookRef,dbActRef,dbOrdRef,dbReqRef;
    private String uid ;
    private TextView tvName;
    private double overallCharges;
    private ListView invoiceList;
    private TextView overallTxt;
    private TextView totalTxt;
    private TextView gstTxt;
    //list of type InvoiceHandler that holds invoice list
    List<InvoiceHandler> invoices= new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OfferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayFragment newInstance(String param1, String param2) {
        PayFragment fragment = new PayFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        userName = extras.getString("userName");

        //giving values to global variables
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference("users");
        FirebaseUser user=mAuth.getCurrentUser();
        uid=user.getUid();
        //getting exact nodes for invoice data
        dbBookRef=mFirebaseDatabase.getReference("bookings").child(uid);
        dbActRef=mFirebaseDatabase.getReference("activities").child(uid);
        dbOrdRef=mFirebaseDatabase.getReference("order").child(uid);
        dbReqRef=mFirebaseDatabase.getReference("requests").child(uid);
        tvName= view.findViewById(R.id.name);
        overallTxt= view.findViewById(R.id.overall);
        gstTxt=view.findViewById(R.id.gst);
        totalTxt=view.findViewById(R.id.totalcost);
        //list to populate global list
        invoiceList= view.findViewById(R.id.invoiceList);

        dbRef.addValueEventListener(new ValueEventListener() { //calling method to set data(user name)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        dbBookRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//calling method to get bookings and fill list
                fillBookings(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbOrdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//calling method to fill  orders
                fillOrders(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        dbActRef.addValueEventListener(new ValueEventListener() {//calling method to fill activities
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fillActivities(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        dbReqRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fillRequests(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    private void setData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {//retrieves user name from 'user' node using current user's uid.
            User user = dataSnapshot.child(uid).getValue(User.class);
            name=user.getFirstName()+" "+user.getLastName();
        }
        tvName.setText(name);

    }
    private void fillOrders(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Order order = ds.getValue(Order.class);

            InvoiceHandler invoiceHandler = new InvoiceHandler();

            invoiceHandler.setDate(order.date);
            BigDecimal bd = new BigDecimal(order.totalPrice);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double d = bd.doubleValue();
            invoiceHandler.setCharges(d);
            invoiceHandler.setService(order.item + " ( " + order.price + "$ * " + order.quantity + " )");
            invoices.add(invoiceHandler);
        }
        fillInvoice();
    }


    private void fillBookings(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {//retrieves details of current user's bookings
            Bookings book = ds.getValue(Bookings.class);
            InvoiceHandler invoiceHandler = new InvoiceHandler();
            invoiceHandler.setDate(book.date);
            invoiceHandler.setService(book.type);
            BigDecimal bd = new BigDecimal(book.priceOfBooking);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double d = bd.doubleValue();
            invoiceHandler.setCharges(d);
            invoices.add(invoiceHandler);
        }
        fillInvoice();
    }
    private void fillActivities(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            //retrieves details of the activites booked by current user
            Activities activity = ds.getValue(Activities.class);
            InvoiceHandler invoiceHandler = new InvoiceHandler();
            invoiceHandler.setDate(activity.dateOfPurchase);
            invoiceHandler.setService(activity.activity);
            BigDecimal bd = new BigDecimal(activity.price);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double d = bd.doubleValue();
            invoiceHandler.setCharges(d);
            invoices.add(invoiceHandler);
        }
        fillInvoice();
    }
    public void fillInvoice()
    {//calculating charges
        overallCharges=0;
        for (int i = 0; i < invoices.size(); i++) {
            overallCharges+= invoices.get(i).getCharges();
        }
        //formatting charges
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String overall = format.format(overallCharges);
        String total = format.format((overallCharges* 0.12)+overallCharges);
        overallTxt.setText("Overall Charges: " + overall);
        gstTxt.setText("GST + PST: 12%");
        totalTxt.setText("Grand Total: " + total);

        InvoiceAdpater adpater= new InvoiceAdpater(getActivity(), invoices);
        invoiceList.setAdapter(adpater);
    }
    private void fillRequests(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {//retrieves details of current user's bookings
            Request request = ds.getValue(Request.class);
            InvoiceHandler invoiceHandler = new InvoiceHandler();
            invoiceHandler.setDate(request.date);
            invoiceHandler.setService(request.request);
            double d = 0;
            invoiceHandler.setCharges(d);
            invoices.add(invoiceHandler);
        }
        fillInvoice();
    }
}