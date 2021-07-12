package apps.develop.quizarea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomePage extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    RecyclerView recView;
    Current_RecyclerAdapter adapter;

    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);


     /*   shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerlayout = findViewById(R.id.shimmer_layout_pdf);

*/
        recView = (RecyclerView) findViewById(R.id.rv_storyf4);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.item, true);

        FirebaseRecyclerOptions<LatestData> options =
                new FirebaseRecyclerOptions.Builder<LatestData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Images"), LatestData.class)
                        .build();

        adapter = new Current_RecyclerAdapter(options);
        recView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        bottomMenu();
    }


    @Override
    public void onPause() {
       // shimmerFrameLayout.stopShimmer();
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
        adapter.stopListening();
    }
}