package com.example.bazaarapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class verticalAdapter extends RecyclerView.Adapter<verticalAdapter.viewHolderMe4> {
    private String [] data1;
    private String [] data2;
    private int[] img1;
    private int[] img2;
    public class viewHolderMe4 extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        CardView cardView;
        FrameLayout frameLayout;
        ImageView imageView;
        TextView textView3;
        ImageView imageView2;
        TextView textView;
        TextView textView2;
        public viewHolderMe4(@NonNull final View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.vertiCard);
            cardView=itemView.findViewById(R.id.cardviewVerlist);
            frameLayout=itemView.findViewById(R.id.frameverti);
            imageView=itemView.findViewById(R.id.verBack);
            textView3=itemView.findViewById(R.id.vertiText);
            imageView2=itemView.findViewById(R.id.verLogo);
            textView=itemView.findViewById(R.id.head1);
            textView2=itemView.findViewById(R.id.head2);


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

   public verticalAdapter(String[]data1,int[]img1,String[]data2,int[]img2){
        this.data1=data1;
        this.data2=data2;
        this.img1=img1;
        this.img2=img2;
    }

    @NonNull
    @Override
    public viewHolderMe4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_vertical_list, parent, false);

        //return new MyViewHolder(itemView)
        // LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        // View view=layoutInflater.inflate(R.layout.pick_item,parent,false);
        return  new verticalAdapter.viewHolderMe4(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderMe4 holder, int position) {

        String title=data1[position];
        holder.textView.setText(title);
        String title2=data2[position];
        holder.textView2.setText(title2);
        int loc=img1[position];
        holder.imageView.setImageResource(loc);
        int loc1=img2[position];
        holder.imageView2.setImageResource(loc1);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

}
