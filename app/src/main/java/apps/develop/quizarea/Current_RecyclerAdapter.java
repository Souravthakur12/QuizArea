package apps.develop.quizarea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Current_RecyclerAdapter extends RecyclerView.Adapter<Current_RecyclerAdapter.MyViewHolder> {
ArrayList<LatestData> list;
Context context;

    public Current_RecyclerAdapter(ArrayList<LatestData> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.current_topics,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        LatestData data= list.get(position);
        holder.txt.setText(data.getTitle());
        Glide.with(context).load(data.getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txt = itemView.findViewById(R.id.txt);
        }
    }
}


