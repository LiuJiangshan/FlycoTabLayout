package ljs.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ljs.tablayout.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    SlidingTabLayout tab_layout;
    ViewPager view_pager;
    List<String> pages = new ArrayList<>();
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pages.add(pages.size() + "");

        tab_layout = findViewById(R.id.tab_layout);

        view_pager = findViewById(R.id.view_pager);

        adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return pages.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                AppCompatTextView view = new AppCompatTextView(MainActivity.this);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                view.setText(pages.get(position) + "");
                view.setGravity(Gravity.CENTER);
                container.addView(view);
                return view;
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return pages.indexOf(object) == -1 ? POSITION_NONE : POSITION_UNCHANGED;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "tab_" + position;
            }
        };
        view_pager.setAdapter(adapter);
        tab_layout.setViewPager(view_pager);
    }

    public void addTab(View view) {
        pages.add(pages.size() + "");
        tab_layout.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }

    public void removeTab(View view) {
        if (pages.size() > 0)
            pages.remove(pages.size() - 1);
        tab_layout.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }
}
