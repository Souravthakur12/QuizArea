package apps.develop.quizarea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    RecyclerView recView;
    Current_RecyclerAdapter adapter;
    ArrayList<LatestData> list;
    DatabaseReference db;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);
        db = FirebaseDatabase.getInstance().getReference().child("Images");
        list = new ArrayList<>();
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerlayout = findViewById(R.id.linear_shimmer);

        recView = (RecyclerView) findViewById(R.id.rv_storyf4);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.item, true);


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    LatestData data=dataSnapshot.getValue(LatestData.class);
                    list.add(data);
                }
                adapter = new Current_RecyclerAdapter(list, HomePage.this);
                recView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(HomePage.this, "Data not loaded", Toast.LENGTH_SHORT).show();
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
//        adapter.startListening();
        bottomMenu();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
        overridePendingTransition(0, 0);
    }


    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.item:

                        break;
                    case R.id.item1:
                        Intent intent = new Intent(HomePage.this, Quiz.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        Intent intent1 = new Intent(HomePage.this, Profile.class);
                        startActivity(intent1);
                        break;

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
        finishAffinity();
    }

    @Override
    protected void onStop() {
        super.onStop();

//        adapter.stopListening();
    }
}