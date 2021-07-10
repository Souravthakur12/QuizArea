package apps.develop.quizarea;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Quiz extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.item1,true);
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
                switch (i)
                {
                    case R.id.item:
                        Intent intent1 = new Intent(Quiz.this,HomePage.class);
                        startActivity(intent1);

                        break;
                    case R.id.item1:

                        break;
                    case R.id.item2:
                        Intent intent2 = new Intent(Quiz.this,Profile.class);
                        startActivity(intent2);
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