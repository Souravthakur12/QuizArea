package apps.develop.quizarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Profile extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.item2,true);
    }


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
