package com.mubaracktahir.wishes.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.mubaracktahir.wishes.MainActivity;
import com.mubaracktahir.wishes.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotificationFregment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";



    public static NotificationFregment newInstance(int index) {
        NotificationFregment fragment = new NotificationFregment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.notification_fragment_main, container, false);
        //MainActivity.fab.hide();


        return root;
    }
}