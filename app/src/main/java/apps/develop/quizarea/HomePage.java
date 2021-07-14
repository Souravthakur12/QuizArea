package apps.develop.quizarea;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePage extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    RecyclerView recView;
    Current_RecyclerAdapter adapter;
    Button btnLogout;

    CircleImageView circleprofileiv;
    TextView username;

    FirebaseAuth firebaseAuth;
    CircleImageView circleImageView;
    TextView user_name;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerlayout;

    GoogleApiClient googleApiClient;
    GoogleSignInOptions sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);
        username=findViewById(R.id.user_name);
        circleprofileiv=findViewById(R.id.circleprofileiv);
        btnLogout=findViewById(R.id.btnLogout);
        firebaseAuth=FirebaseAuth.getInstance();
        checkUser();


     /*   shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerlayout = findViewById(R.id.shimmer_layout_pdf);

*/

        circleImageView = findViewById(R.id.circleprofileiv);
        user_name = findViewById(R.id.user_name);






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

    private void checkUser() {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser==null){
            startActivity(new Intent(
            HomePage.this,LoginActivity.class));
        }
        else{
            GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);

        /*    user_name.setText(account.getDisplayName());*/
            Glide.with(this).load(account.getPhotoUrl()).into(circleprofileiv);

            String displayName=firebaseUser.getDisplayName();

            username.setText("Hi "+displayName);

        }
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