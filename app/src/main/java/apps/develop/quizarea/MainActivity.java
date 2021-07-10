package apps.develop.quizarea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ViewPager2 viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager(),getLifecycle());
        pagerAdapter.addFragmet(new LoginFragment());
        pagerAdapter.addFragmet(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);
    }

    class AuthenticationPagerAdapter extends FragmentStateAdapter {

        private ArrayList<Fragment> fragmentList = new ArrayList<>();


        public AuthenticationPagerAdapter(@NonNull  FragmentManager fragmentManager,  Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            switch (position)
            {
                case 1:
                    return new RegisterFragment();

            }

            return new LoginFragment();

        }

        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }
        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }

}