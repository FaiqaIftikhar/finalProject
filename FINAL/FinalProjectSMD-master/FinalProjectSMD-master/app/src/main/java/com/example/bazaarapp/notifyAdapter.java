package com.example.bazaarapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class notifyAdapter extends RecyclerView.Adapter<notifyAdapter.viewHolderMe5> {
    private String [] data;
    private int[] img;
    // private Context context;
    public class viewHolderMe5 extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView t1;
        LinearLayout linearLayout;
        CardView c1;
        //       TextView t2;
//        TextView t3;

        public viewHolderMe5(@NonNull View itemView) {

            super(itemView);
            imgIcon=itemView.findViewById(R.id.n3);
            t1=itemView.findViewById(R.id.n4);
            linearLayout=itemView.findViewById(R.id.n1);
            //  linearLayout.setOrientation();
            c1=itemView.findViewById(R.id.n2);
            // t2=itemView.findViewById(R.id.me3);
            //t3=itemView.findViewById(R.id.me4);

        }
    }
    public notifyAdapter(String[]data,int[]img){
        this.data=data;
        this.img=img;
        //  this.context=context;

    }
    @NonNull
    @Override
    public viewHolderMe5 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.notification_item,parent,false);

        return new viewHolderMe5(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderMe5 holder, int position) {
        String title=data[position];
        holder.t1.setText(title);
        int loc=img[position];
        holder.imgIcon.setImageResource(loc);
        //  holder.imgIcon.setBackgroundResource(R.drawable.pucitlogo);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


}

