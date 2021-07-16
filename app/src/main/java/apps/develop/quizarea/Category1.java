package apps.develop.quizarea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import apps.develop.quizarea.Adapter.Quiz_CategoryAdapter;
import apps.develop.quizarea.Model.CategoryCard;

import static java.security.AccessController.getContext;

public class Category1 extends AppCompatActivity {

    RecyclerView recyclerView;

    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);

    database = FirebaseFirestore.getInstance();

    recyclerView = findViewById(R.id.cat1_rv);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final ArrayList<CategoryCard> categories = new ArrayList<>();

        final Quiz_CategoryAdapter adapter = new Quiz_CategoryAdapter(this, categories);


        recyclerView.setAdapter(adapter);

        database.collection("category1")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                        categories.clear();
                    for (DocumentSnapshot snapshot : value.getDocuments())
                    {
                        CategoryCard model = snapshot.toObject(CategoryCard.class);
                        model.setCatid(snapshot.getId());
                        categories.add(model);
                    }
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}