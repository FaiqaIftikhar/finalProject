package com.example.bazaarapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;
public class ThirdRecyclerViewInHomeAdapter extends RecyclerView.Adapter<ThirdRecyclerViewInHomeAdapter.ShowViewHolder> {
    DatabaseReference mDatabaseRef;
    private Context mContext;
    private List<ThirdRecyclerViewInHome> mdeals;
    public ThirdRecyclerViewInHomeAdapter(Context context, List<ThirdRecyclerViewInHome> deals){
        mContext=context;
        mdeals=deals;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cardview_vertical_list, parent, false);
        return new ShowViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        ThirdRecyclerViewInHome uploadCurrent = mdeals.get(position);
        holder.textView.setText(uploadCurrent.getBrandName());
        holder.textView2.setText(uploadCurrent.getDescription());

        String cat=uploadCurrent.getDescription();

        if(cat.contains("Footwear")){
            holder.imageView2.setImageResource(R.drawable.shopping_bag);
        }
        else if(cat.contains("Health")){
            holder.imageView2.setImageResource(R.drawable.medicine);
        }
        else if(cat.contains("Electronics")){
            holder.imageView2.setImageResource(R.drawable.electricicon);
        }
        else if(cat.contains("Home")){
            holder.imageView2.setImageResource(R.drawable.homeicon);
        }
        else if(cat.contains("Food")){
            holder.imageView2.setImageResource(R.drawable.dish);
        }
        else if(cat.contains("Other")){
            holder.imageView2.setImageResource(R.drawable.robotic);
        }

        //holder.imageView.setim
        // Load the image using Glide

        Picasso.get().load(uploadCurrent.getImageUrl())
                .error(R.drawable.home).into(holder.imageView);

        /*
        Picasso.with(mContext).load(uploadCurrent.getImageUrl()).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.home)
                .into(holder.imageView);

         */

        // Toast.makeText(mContext, uploadCurrent.getImageUrl() , Toast.LENGTH_SHORT ).show();

/*
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });
        */



        // Picasso.with(mContext)
        //  .load(uploadCurrent.getImageUrl())
        // .into(holder.imageView2);
    }
    @Override
    public int getItemCount() {
        return mdeals.size();
    }
    public class ShowViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        CardView cardView;
        FrameLayout frameLayout;
        ImageView imageView;
        TextView textView3;
        ImageView imageView2;
        TextView textView;
        TextView textView2;






        public ShowViewHolder(@NonNull final View itemView) {
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
                    //  Toast.makeText(mContext, textView.getText() , Toast.LENGTH_SHORT ).show();

                    Intent intent = new Intent(itemView.getContext(), info.class);
                    intent.putExtra("brandName", textView.getText());


                    itemView.getContext().startActivity(intent);

                    //itemView.getContext().startActivity(new Intent(itemView.getContext(), info.class));
                }
            });
        }
    }
}

