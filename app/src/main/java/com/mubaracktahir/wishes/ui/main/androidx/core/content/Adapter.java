package com.mubaracktahir.wishes.ui.main.androidx.core.content;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mubaracktahir.wishes.MainActivity;
import com.mubaracktahir.wishes.R;
import com.mubaracktahir.wishes.ui.main.Friends;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter{
    ArrayList<Friends> friendList = new ArrayList<>();
    Context context;
    int resource;
    TextView name;
    TextView dob;
    TextView days;


    public Adapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.from(context).inflate(resource, parent);
        }

        name = convertView.findViewById(R.id.name);
        dob = convertView.findViewById(R.id.date_of_birth);
        days = convertView.findViewById(R.id.days);
        return convertView;

    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addFriend(Friends friend){
        friendList.add(friend);
        notifyDataSetChanged();
    }
}
