package com.example.bazaarapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.MyViewHolder> {

    private ArrayList<categoryData> dataSet;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;
        ImageView imageViewback;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.head1);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.head2);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.verLogo);
            this.imageViewback= (ImageView) itemView.findViewById(R.id.verBack);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /// Intent intent=new Intent(itemView.getContext(),info.class);
                    //intent.putExtra("heading",)
                    //  Toast.makeText(mContext, textView.getText() , Toast.LENGTH_SHORT ).show();

                    Intent intent = new Intent(itemView.getContext(), info.class);
                    intent.putExtra("brandName", textViewName.getText());


                    itemView.getContext().startActivity(intent);

                    //itemView.getContext().startActivity(new Intent(itemView.getContext(), info.class));
                }
            });



            ///////////////
        }

    }


    public categoryAdapter(ArrayList<categoryData> data) {

        this.dataSet = data;
    }
/////////////////

    @Override
    public categoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_vertical_list, parent, false);

        categoryAdapter.MyViewHolder myViewHolder = new categoryAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    //////


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        //ImageView imageView = holder.imageViewIcon;
        //ImageView imageView1 = holder.imageViewback;

        textViewName.setText(dataSet.get(listPosition).getHeading());
        textViewVersion.setText(dataSet.get(listPosition).getContet());
        //imageView.setImageResource(dataSet.get(listPosition).getImage1());

        Picasso.get().load(dataSet.get(listPosition).getImage1())
                .error(R.drawable.home).into(holder.imageViewIcon);
        Picasso.get().load(dataSet.get(listPosition).getImage2())
                .error(R.drawable.home).into(holder.imageViewback);

        // imageView1.setImageResource(dataSet.get(listPosition).getImage2());
    }

    ///////\

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}