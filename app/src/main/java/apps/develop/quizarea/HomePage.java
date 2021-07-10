package apps.develop.quizarea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomePage extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);

        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.item,true);


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

                        break;
                    case R.id.item1:
                        Intent intent = new Intent(HomePage.this,Quiz.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        Intent intent1 = new Intent(HomePage.this,Profile.class);
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


}