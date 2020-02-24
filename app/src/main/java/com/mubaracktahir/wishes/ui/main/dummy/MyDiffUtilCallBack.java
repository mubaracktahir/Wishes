package com.mubaracktahir.wishes.ui.main.dummy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mubaracktahir.wishes.R;
import com.mubaracktahir.wishes.ui.main.Friends;
import com.mubaracktahir.wishes.ui.main.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */


public class MyDiffUtilCallBack extends DiffUtil.Callback {
    private List<Friends> oldList;
    private List<Friends> newList;

    public MyDiffUtilCallBack(List<Friends> oldList, List<Friends> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return oldPosition == newPosition;
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition) == newList.get(newPosition);
    }
}