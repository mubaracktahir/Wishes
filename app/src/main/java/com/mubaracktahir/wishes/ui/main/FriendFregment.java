package com.mubaracktahir.wishes.ui.main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mubaracktahir.wishes.R;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */


public class FriendFregment extends Fragment {
    private RecyclerView recyclerView;
    private static List<Friends> friendsList = new ArrayList<>();
    private ActionMode actionMode;
    private  ActionModeCallBack actionModeCallBack;
    private RecyclerViewAdapter recyclerViewAdapter;


    private TextView taped ;
    View root;


    public FriendFregment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public  static void addNewFriend(Friends f) {

        friendsList.add(0,new Friends(f.getName(),f.getDob(),124,
                R.drawable.profile_picture,R.color.colorPrimary));
        friendsList.add(0,new Friends(f.getName(),f.getDob(),84,
                R.drawable.farofrn,R.color.colorPrimary));
        friendsList.add(0,new Friends(f.getName(),f.getDob(),22,
                R.drawable.mubby,R.color.colorPrimary));
        friendsList.add(0,new Friends("Mubarack Tahir","Sunday, June 14",113,"08165359670",R.drawable.profile_picture,
                "mubarack.tahir@yahoo.com",R.color.colorPrimary));
        friendsList.add(0,new Friends(f.getName(),f.getDob(),195,
                -1,R.color.colorPrimary));
        friendsList.add(0,new Friends(f.getName(),f.getDob(),9,
                -1,R.color.colorPrimary));
        friendsList.add(0,new Friends("Mubarack Tahir","Sunday, June 14",113,"08165359670",R.drawable.profile_picture,
                "mubarack.tahir@yahoo.com",R.color.colorPrimary));
        friendsList.add(0,new Friends("Farida Tahir","Sunday, November 13",211,"08165359670",R.drawable.profile_picture,
                "mubarack.tahir@yahoo.com",R.color.colorPrimary));
        friendsList.add(0,new Friends(f.getName(),f.getDob(),219,
                -1,R.color.colorPrimary));
        friendsList.add(0,new Friends(f.getName(),f.getDob(),123,
                -1,R.color.colorPrimary));
        friendsList.add(0,new Friends("Rahimat Idris","Monday,November 7",205,"08165359670",R.drawable.mubby,
                "mubarack.tahir@yahoo.com",R.color.colorPrimary));
        friendsList.add(0,new Friends("Farida Muhammad","Monday, June 15",114,"08165359670",R.drawable.profile_picture,
                "mubarack.tahir@yahoo.com",R.color.colorPrimary));

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.friend_fragment_main, container, false);
        recyclerViewAdapter  = new RecyclerViewAdapter(getContext(),friendsList,getFragmentManager());
        recyclerView =  root.findViewById(R.id.recyclerview);
        taped = root.findViewById(R.id.tap);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);
       /* recyclerViewAdapter.setOnClickListener(new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onItemClick(View v, Friends f, int position) {
                if(recyclerViewAdapter.getSelectedItemCount() > 0){
                    enableActionMode(position);
                }
            }

            @Override
            public void onItemLongClick(View v, Friends f, int position) {
                enableActionMode(position);
            }
        });*/
       checkNote();
       actionModeCallBack =new ActionModeCallBack();
        return root;
    }

    private void enableActionMode(int position) {
        if(actionMode == null){
            //actionMode = startSupportActionMode(actionModeCallBack);
        }
        toggleSelection(position);
    }
    private void toggleSelection(int position){
        recyclerViewAdapter.toggleSelection(position);
        int count = recyclerViewAdapter.getItemCount();

        if(count == 0){
            actionMode.finish();
        }
        else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }
    private void deleteFriends(){
        List<Integer> selectedItemPosition = recyclerViewAdapter.getSelectedItems();
        for (int i = selectedItemPosition.size() -1 ; i >= 0; i--){
            recyclerViewAdapter.removeData(selectedItemPosition.get(i));

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void checkNote() {
        if (!(friendsList.isEmpty())) {
            taped.setVisibility(View.GONE);
        } else taped.setVisibility(View.VISIBLE);
    }
    private class ActionModeCallBack implements ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            Tool.setSystemBarColor(getActivity(),R.color.colorPrimary);
            actionMode.getMenuInflater().inflate(R.menu.delete_menu,menu);

            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int id = menuItem.getItemId();
            if(id == R.id.delete){
                deleteFriends();
                actionMode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            recyclerViewAdapter.clearSelection();
            actionMode = null;
            Tool.setSystemBarColor(getActivity(),R.color.colorPrimary);
        }
    }
}