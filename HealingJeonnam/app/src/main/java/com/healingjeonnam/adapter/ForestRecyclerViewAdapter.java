package com.healingjeonnam.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.healingjeonnam.views.ForestDetailActivity;
import com.healingjeonnam.R;
import com.healingjeonnam.models.Forest;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by econo110 on 2015-10-17.
 */
public class ForestRecyclerViewAdapter extends RecyclerView
        .Adapter<ForestRecyclerViewAdapter
        .DataObjectHolder>

{
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<Forest> mDataset;


    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView nametext;
        TextView addrtext;
        ImageView imageview;


        public DataObjectHolder(View itemView) {
            super(itemView);
            nametext = (TextView) itemView.findViewById(R.id.text1);
            addrtext = (TextView) itemView.findViewById(R.id.text2);
            imageview = (ImageView) itemView.findViewById(R.id.avatar);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public ForestRecyclerViewAdapter(List<Forest> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.nametext.setText(mDataset.get(position).getName());
        holder.addrtext.setText(mDataset.get(position).getAddr());
        ImageLoader.getInstance().displayImage(mDataset.get(position).getImages().get(0), holder.imageview);
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForestDetailActivity.class);
                Forest forest = mDataset.get(position);
                intent.putExtra("forest", forest);
                v.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}