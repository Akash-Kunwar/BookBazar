package navigationdrawer.sunnyapps.com.tatva;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static navigationdrawer.sunnyapps.com.tatva.R.id.ivimg;

/**
 * Created by DELL-PC on 8/4/2019.
 */

public class Adapterclass extends RecyclerView.Adapter<Adapterclass.MyViewHolder> {

   ArrayList<bookblog> list;
    private Context context;
    String uid=null;
    long post_key;

    public Adapterclass(ArrayList<bookblog> list,Context context)
   {
       this.list=list;
       this.context=context;
   }

    @NonNull
    @Override
    public Adapterclass.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookrow,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterclass.MyViewHolder myViewHolder, int i) {

        myViewHolder.tvname.setText("Name:"+list.get(i).getName());
        myViewHolder.tvsem.setText("Sem:"+list.get(i).getSem());
        myViewHolder.tvphone.setText("Phone:"+list.get(i).getPhone());
        myViewHolder.tvcondition.setText("Rating:"+list.get(i).getCondition());
        myViewHolder.tvprice.setText("Price"+list.get(i).getPrice());
        myViewHolder.tvsub.setText("Sub:"+list.get(i).getSub());
        Glide.with(myViewHolder.itemView.getContext()).load(list.get(i).getImageurl()!=null?list.get(i).getImageurl():R.drawable.team).into(myViewHolder.ivimg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView tvname,tvphone,tvprice,tvsem,tvsub,tvcondition;
        ImageView ivimg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvname);
            tvphone=itemView.findViewById(R.id.tvphone);
            tvprice=itemView.findViewById(R.id.tvprice);
            tvsem=itemView.findViewById(R.id.tvsem);
            tvsub=itemView.findViewById(R.id.tvsub);
            tvcondition=itemView.findViewById(R.id.tvcondition);
            ivimg=itemView.findViewById(R.id.ivimg);
        }
    }
}
