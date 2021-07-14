package apps.develop.quizarea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Profile extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    Button btnLogout;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();



        btnLogout=findViewById(R.id.btnLogout);
        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.item2,true);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                GoogleSignInOptions gso = new GoogleSignInOptions.
                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                        build();

                GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(Profile.this,gso);
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            FirebaseAuth.getInstance().signOut(); // very important if you are using firebase.
                            Intent login_intent = new Intent(getApplicationContext(),LoginActivity.class);
                            login_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); // clear previous task (optional)
                            startActivity(login_intent);
                        }
                    }
                });

            }
        });
    }
/*

    private void signOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        firebaseAuth.signOut();
                        startActivity(new Intent(Profile.this,LoginActivity.class));
                        finish();
                    }
                });
*/


    @Override
    protected void onStart() {
        super.onStart();
        bottomMenu();
    }


    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.item:
                        Intent intent1 = new Intent(Profile.this, HomePage.class);
                        startActivity(intent1);

                        break;
                    case R.id.item1:

                        Intent intent2 = new Intent(Profile.this, Quiz.class);
                        startActivity(intent2);
                        break;
                    case R.id.item2:
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
}
