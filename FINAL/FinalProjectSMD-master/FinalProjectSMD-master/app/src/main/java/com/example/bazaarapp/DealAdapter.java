package com.example.bazaarapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {

    DatabaseReference mDatabaseRef;
    private Context mContext;
    private List<DealPoster> mdeals;
    public DealAdapter(Context context, List<DealPoster> deals){
        mContext=context;
        mdeals=deals;
    }

    @Override
    public DealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.pick_item, parent, false);
        return new DealViewHolder(v);
    }
    @Override
    public void onBindViewHolder(DealViewHolder holder, int position) {
        DealPoster uploadCurrent = mdeals.get(position);
        holder.textView.setText(uploadCurrent.getBrandName());
        holder.textView2.setText(uploadCurrent.getDescription());

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


        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });


       // Picasso.with(mContext)
              //  .load(uploadCurrent.getImageUrl())
               // .into(holder.imageView2);
    }
    @Override
    public int getItemCount() {
        return mdeals.size();
    }
    public class DealViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        CardView cardView;
        ImageView imageView;
        ImageView imageView2;
        TextView textView;
        TextView textView2;






        public DealViewHolder(@NonNull final View itemView) {
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

