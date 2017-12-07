package com.incon.recycleviewjsonvolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PC on 12/7/2017.
 */

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public MyAdapter(List<Data_list> data_lists, Context context) {
        this.data_lists = data_lists;
        this.context = context;
    }

    List<Data_list>data_lists;
    private Context context;

    @Override    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        return new ViewHolder(view);
    }

    @Override    public void onBindViewHolder(ViewHolder holder, int position) {

        Data_list data_list=data_lists.get(position);
        holder.t1.setText(data_list.getRank());
        holder.t2.setText(data_list.getCountry());
        holder.t3.setText(data_list.getPopulation());
        Picasso.with(context).load(data_list.getFlag()).into(holder.im1);

    }

    @Override    public int getItemCount() {
        return data_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView t1,t2,t3;
        public ImageView im1;
        public ViewHolder(View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.text1);
            t2=(TextView)itemView.findViewById(R.id.text2);
            t3=(TextView)itemView.findViewById(R.id.text3);
            im1=(ImageView)itemView.findViewById(R.id.image);

        }
    }
}
