package lomo.hust.dictvocab.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import lomo.hust.dictvocab.R;
import lomo.hust.dictvocab.adapter.FragmentAdapter;
import lomo.hust.dictvocab.adapter.WordAdapter;
import lomo.hust.dictvocab.base.BaseActivity;
import lomo.hust.dictvocab.fragment.ProfileFragment;
import lomo.hust.dictvocab.fragment.QuizFragment;
import lomo.hust.dictvocab.fragment.RequestFragment;
import lomo.hust.dictvocab.fragment.SearchFragment;
import lomo.hust.dictvocab.fragment.VocabularyFragment;
import lomo.hust.dictvocab.object.Word;
import lomo.hust.dictvocab.utils.Constant;
import lomo.hust.dictvocab.utils.SharedPreferencesSingleton;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    BottomNavigationView botNavView;
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViewById() {
        botNavView = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.view_pager);
    }


    @Override
    protected void setupUI() {
        final boolean isLoggedIn = SharedPreferencesSingleton.getInstance().get(Constant.LOG_IN_STATE, Boolean.class);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_vocab:
                        viewPager.setCurrentItem(isLoggedIn? 0: 4);
                        return true;
                    case R.id.navigation_search:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_quiz:
                        viewPager.setCurrentItem(isLoggedIn? 2: 4);
                        return true;
                    case R.id.navigation_profile:
                        viewPager.setCurrentItem(isLoggedIn? 3: 4);
                        return true;
                }
                return false;

            }
        });

        // Setup view pager
        setupFragment(getSupportFragmentManager(), viewPager);
        botNavView.setSelectedItemId(R.id.navigation_search);
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new PageChange());
    }

    public static void setupFragment(FragmentManager fragmentManager, ViewPager viewPager) {
        FragmentAdapter Adapter = new FragmentAdapter(fragmentManager);
        Adapter.add(new VocabularyFragment(), "Vocabulary");
        Adapter.add(new SearchFragment(), "Dictionary");
        Adapter.add(new QuizFragment(), "Quiz");
        Adapter.add(new ProfileFragment(), "Profile");
        Adapter.add(new RequestFragment(), "Request Login");
        viewPager.setAdapter(Adapter);
    }

    public class PageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    botNavView.setSelectedItemId(R.id.navigation_vocab);
                    break;
                case 1:
                    botNavView.setSelectedItemId(R.id.navigation_search);
                    break;
                case 2:
                    botNavView.setSelectedItemId(R.id.navigation_quiz);
                    break;
                case 3:
                    botNavView.setSelectedItemId(R.id.navigation_profile);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
