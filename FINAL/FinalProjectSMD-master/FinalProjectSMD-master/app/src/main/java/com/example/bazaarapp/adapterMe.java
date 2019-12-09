package com.example.bazaarapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class adapterMe extends RecyclerView.Adapter<adapterMe.viewHolderMe> {
    private String [] data;
    private int[] img;
    // private Context context;
    public adapterMe(String[]data,int[]img){
        this.data=data;
        this.img=img;
    }
    @NonNull
    @Override
    public viewHolderMe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.type_item,parent,false);

        return new viewHolderMe(view);
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolderMe holder, int position) {
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

    public class viewHolderMe extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView t1;
        LinearLayout linearLayout;
        CardView c1;
        public viewHolderMe(@NonNull final View itemView) {

            super(itemView);
            imgIcon=itemView.findViewById(R.id.image1);
            t1=itemView.findViewById(R.id.text1);
            linearLayout=itemView.findViewById(R.id.linearLayout);
            c1=itemView.findViewById(R.id.cardview1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), category.class);
                    intent.putExtra("categoryName", t1.getText());
             itemView.getContext().startActivity(intent);
                    //itemView.getContext().startActivity(new Intent(itemView.getContext(), category.class));
                }
            });

        }
    }
}
