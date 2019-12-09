package com.example.bazaarapp;


import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.CLIPBOARD_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class productInfo extends Fragment {



    String timings;
    Double address;
    String contact;
    Double address2;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    ImageButton arrow;


    public productInfo() {
        // Required empty public constructor
    }


    public productInfo(Double lat,String contact,String time,Double address2) {
        this.timings=time;
        this.address=lat;
        this.contact=contact;
        this.address2=address2;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public static productInfo newInstance() {
        productInfo fragment = new productInfo();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View view = inflater.inflate(R.layout.product_info, container, false);


        // recyclerView = (RecyclerView) view.findViewById(R.id.info_recycler_view);
        //info activity = (info) getActivity();
      // String myDataFromActivity = activity.getMyData();
        //String s = ((info) this.getApplication()).getSomeVariable();
        /////////////
        Geocoder geocoder;
        List<Address> addresses=null;
        geocoder = new Geocoder(getContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(this.address, this.address2, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String addresslab = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL


        arrow=view.findViewById(R.id.nextbtn);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", address,address2);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });


        ////////
        TextView tv=view.findViewById(R.id.content1);
        tv.setText(addresslab+" "+city+ " "+state);




        TextView tv1=view.findViewById(R.id.content12);
        tv1.setText(contact.toString());




        TextView tv2=view.findViewById(R.id.content13);
        tv2.setText(timings.toString());

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Text Label", contact.toString());
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();

                Uri u = Uri.parse("tel:" +contact.toString());
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    getContext().startActivity(i);
                }
                catch (SecurityException s)
                {
                    // show() method display the toast with
                    // exception message.
                    Toast.makeText(getContext() ,"issue check the code", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
//

        //recyclerView.setHasFixedSize(true);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
/*
        data = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.headingArray.length; i++) {
            data.add(new DataModel(
                    MyData.headingArray[i],
                    MyData.contetArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }*/
        //adapter = new CustomAdapter(data);
        //recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment



        return view;
    }


}
