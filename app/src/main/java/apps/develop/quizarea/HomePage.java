package apps.develop.quizarea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import apps.develop.quizarea.Adapter.Current_RecyclerAdapter;
import apps.develop.quizarea.Adapter.SliderAdapter;
import apps.develop.quizarea.Model.LatestData;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomePage extends AppCompatActivity {

    SliderView sliderView;
    int[] images ={R.drawable.quiz3,
    R.drawable.quiz,R.drawable.quiz4,R.drawable.quiz1};
    ChipNavigationBar chipNavigationBar;
    RecyclerView recView;
    Current_RecyclerAdapter adapter;
    ArrayList<LatestData> list;
    DatabaseReference db;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerlayout;

    FirebaseAuth firebaseAuth;

    CircleImageView circleprofileiv;
    TextView username,useremail;

    LinearLayout cat1,cat2,cat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);
        db = FirebaseDatabase.getInstance().getReference().child("Images");


        username=findViewById(R.id.user_name);
        useremail=findViewById(R.id.user_email);
        circleprofileiv=findViewById(R.id.circleImageView);

        firebaseAuth= FirebaseAuth.getInstance();
        checkUser();

        sliderView = findViewById(R.id.slider);
        list = new ArrayList<>();
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerlayout = findViewById(R.id.linear_shimmer);


        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        cat1 = findViewById(R.id.cat1);
        cat2 = findViewById(R.id.cat2);
        cat3 = findViewById(R.id.cat3);

        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,Category1.class);
                startActivity(intent);
            }
        });

        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomePage.this,Category2.class);
                startActivity(intent1);
            }
        });


        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(HomePage.this,Category3.class);
                startActivity(intent2);
            }
        });

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

            String userEmail = firebaseUser.getEmail();

            username.setText("Hi,"+displayName);
            useremail.setText(userEmail);

        }
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