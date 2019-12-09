package com.example.bazaarapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class pickAdapter extends RecyclerView.Adapter<pickAdapter.viewHolderMe3> {

    private String[] data1;
    private String[] data2;
    private int[] image1;
    private int[] image2;
    public class viewHolderMe3 extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        CardView cardView;
        ImageView imageView;
        ImageView imageView2;
        TextView textView;
        TextView textView2;
        public viewHolderMe3(@NonNull final View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.frame);
            cardView=itemView.findViewById(R.id.cardview2);
            imageView=itemView.findViewById(R.id.image2);
            imageView2=itemView.findViewById(R.id.log1);
            textView=itemView.findViewById(R.id.dealName);
            textView2=itemView.findViewById(R.id.name1);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /// Intent intent=new Intent(itemView.getContext(),info.class);
                    //intent.putExtra("heading",)
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), info.class));
                }
            });

        }


    }
    public pickAdapter(String[] data1,int[]image1,String[]data2,int[]image2){
        this.data1=data1;
        this.data2=data2;
        this.image1=image1;
        this.image2=image2;

    }
    public viewHolderMe3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pick_item, parent, false);

        //return new MyViewHolder(itemView)
        // LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        // View view=layoutInflater.inflate(R.layout.pick_item,parent,false);
        return  new viewHolderMe3(itemView);
    }




    @Override
    public void onBindViewHolder(final viewHolderMe3 holder,final int position) {

        String title=data1[position];
        holder.textView.setText(title);
        String title2=data2[position];
        holder.textView2.setText(title2);
        int loc=image1[position];
        holder.imageView.setImageResource(loc);
        int loc1=image2[position];
        holder.imageView2.setImageResource(loc1);

    }

    @Override
    public int getItemCount() {
        return data1.length;

    }

}
