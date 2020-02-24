package com.mubaracktahir.wishes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.mubaracktahir.wishes.ui.main.FriendFregment;
import com.mubaracktahir.wishes.ui.main.NotificationFregment;
import com.mubaracktahir.wishes.ui.main.QouteFregment;
import com.mubaracktahir.wishes.ui.main.RecyclerViewAdapter;
import com.mubaracktahir.wishes.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager viewPager;
    public static FloatingActionButton fab;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private float translatiionY = 100f;
    public static Context context;
    private OvershootInterpolator interpolator = new OvershootInterpolator();
    private boolean isFabOpen = false;

    public static int i = 0;


    public void setUpViewPager(ViewPager viewPager){
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new FriendFregment(),"");
        sectionsPagerAdapter.addFragment(new QouteFregment(), "");
        sectionsPagerAdapter.addFragment(new NotificationFregment(), "");
        viewPager.setAdapter(sectionsPagerAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.friends_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        //this dude down here makes this particular activity remain portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //the viewpager changes from one fragment to another using the swipe gesture
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);

        //allowing the tabs to be able to change from one fragment to another using the tabs
        tabs.setupWithViewPager(viewPager);

        //calling the setupViewPager method
        setUpViewPager(viewPager);

        tabs.getTabAt(0).setIcon(R.drawable.friends);
        tabs.getTabAt(1).setIcon(R.drawable.quotes);
        tabs.getTabAt(2).setIcon(R.drawable.notification);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        tabs.addOnTabSelectedListener(new MyTabListener());

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.contact);
        fab2 = findViewById(R.id.sync);
        fab3 = findViewById(R.id.add);
        fab1.setAlpha(0f);
        fab2.setAlpha(0f);
        fab3.setAlpha(0f);
        fab1.setTranslationY(translatiionY);
        fab2.setTranslationY(translatiionY);
        fab3.setTranslationY(translatiionY);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);



    }

    public void openFabButton(){
        i = 0;
        isFabOpen = !isFabOpen;
        fab1.animate().translationY(8f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationY(8f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab3.animate().translationY(8f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();
    }

    public void closeFabButton(){

        isFabOpen = !isFabOpen;
        fab1.animate().translationY(translatiionY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationY(translatiionY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab3.animate().translationY(translatiionY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:

                if(isFabOpen){
                    closeFabButton();
                }
                else {
                    openFabButton();
                }
                break;
            case R.id.sync:
                Intent syncActivity = new Intent(MainActivity.this, SyncActivity.class);
                startActivity(syncActivity);

                /*Todo:  i will properly implement this activity later,  that will
                         allow the user choose what ever platform they want to sync there friends
                         birthday date from
                 */

                break;
            case R.id.contact:
                Intent intent = new Intent(MainActivity.this, PhoneContact.class);
                startActivity(intent);

                /*Todo: i will properly implement this activity later, will load the users
                        contact and allow them select whosoever contact they want to add to the app
                 */

                break;

            case R.id.add:
                Intent manuallyAddNewFriend = new Intent(MainActivity.this, ManuallyNewFriend.class);
                startActivity(manuallyAddNewFriend);

                /*Todo:  i will properly implement this activity later that will
                         allow the user manually add new friends by inputing there data explicitly
                 */

                break;
        }
    }

    public void hideFab(){
        fab.hide();
        fab1.hide();
        fab2.hide();
        fab3.hide();
        closeFabButton();
        isFabOpen = false;
    }

    public void showFab(){

            fab.show();
            fab1.show();
            fab2.show();
            fab3.show();
            closeFabButton();
            isFabOpen = false;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    public void onBackPressed(){
        if (i == 0)
            Toast.makeText(this,"press again to exit",Toast.LENGTH_SHORT).show();
        else
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                finishAndRemoveTask();
        i++;
    }

    class MyTabListener implements TabLayout.BaseOnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int f = tab.getPosition();
            switch(f){
                case 0:
                    showFab();
                    i=0;
                    break;
                case 1:
                    hideFab();
                    i=0;
                    break;

                case 2:
                    hideFab();
                    i=0;
                    break;

            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }



}
