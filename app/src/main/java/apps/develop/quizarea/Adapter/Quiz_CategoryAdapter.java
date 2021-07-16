package apps.develop.quizarea.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;


import org.jetbrains.annotations.NotNull;

import java.security.AccessControlContext;
import java.util.ArrayList;

import apps.develop.quizarea.Model.CategoryCard;
import apps.develop.quizarea.Model.LatestData;
import apps.develop.quizarea.R;


public class Quiz_CategoryAdapter extends RecyclerView.Adapter<Quiz_CategoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<CategoryCard> categoryModels;

    public Quiz_CategoryAdapter( Context context, ArrayList<CategoryCard> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.quiz_card_layout,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {




        if (position ==0) {
            holder.cardView.setBackgroundResource(R.color.card1);

        } else if (position ==1)
        {
            holder.cardView.setBackgroundResource(R.color.skin);

        }
        else if (position ==2)
        {
            holder.cardView.setBackgroundResource(R.color.cyan);

        }
        else if (position ==3)
        {
            holder.cardView.setBackgroundResource(R.color.amber);

        }
        else if (position ==4) {
            holder.cardView.setBackgroundResource(R.color.brown);

        }
        else if (position ==5) {
            holder.cardView.setBackgroundResource(R.color.card1);

        }
        else if (position ==6) {
            holder.cardView.setBackgroundResource(R.color.cyan);

        }

        CategoryCard data= categoryModels.get(position);
        holder.title_card.setText(data.getTitle());
        holder.questions_card.setText(data.getQuestions()+" Questions");
        holder.difficulty_card.setText("Difficulty: "+data.getDifficulty());

        Glide.with(context).load(data.getImage1()).into(holder.image1_card);

        Glide.with(context).load(data.getImage2()).into(holder.image2_card);




    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout cardView;
        TextView title_card,questions_card,difficulty_card;
        ImageView image1_card,image2_card;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.expandable_view);
            cardView = itemView.findViewById(R.id.card);
            title_card = itemView.findViewById(R.id.title_card);
            questions_card = itemView.findViewById(R.id.questions_card);
            difficulty_card = itemView.findViewById(R.id.difficulty_card);
            image1_card = itemView.findViewById(R.id.image1_card);
            image2_card = itemView.findViewById(R.id.image2_card);


          cardView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  if (linearLayout.getVisibility() == View.GONE)
                  {
                      TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                      linearLayout.setVisibility(View.VISIBLE);
                  }
                  else {
                      TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                      linearLayout.setVisibility(View.GONE);

                  }
              }
          });



        }
    }
}


