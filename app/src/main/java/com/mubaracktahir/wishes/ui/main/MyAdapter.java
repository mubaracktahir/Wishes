package com.mubaracktahir.wishes.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mubaracktahir.wishes.R;

import java.util.ArrayList;
// cahoot
public class MyAdapter extends BaseAdapter {
    private Context context;
    private TextView name;
    private TextView dob;
    private TextView days;
    private ImageView profilePicture;
    private  int resource;
    public  ArrayList<Friends> friendList;
    public MyAdapter(Context context,int resource, ArrayList fr){

        this.context=context;
        this.friendList =(ArrayList<Friends>) fr;
        this.resource = resource;

    }
    @Override
    public int getCount() {

        return friendList.size();

    }

    @Override
    public Object getItem(int i) {

        return friendList.get(i);

    }

    @Override
    public long getItemId(int i) {

        return i;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View r = view;
        ViewHolder viewHolder=null;
        Toast.makeText(context,"i view "+friendList.size(),Toast.LENGTH_LONG).show();
        if(r == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            r =inflater.inflate(resource,viewGroup,false);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }
        else{

            viewHolder= (ViewHolder) r.getTag();

        }


        Friends f = friendList.get(i);
        viewHolder.name.setText(f.getName());
        viewHolder.dob.setText(f.getDob());
        viewHolder.days.setText(f.getDaysRemaining());

        return r;
    }
    public void addFriend(Friends friend){

        friendList.add(friend);
        notifyDataSetChanged();
    }

    class ViewHolder{
        ImageView profilePicture;
        TextView name;
        TextView surname;
        TextView dob;
        TextView days;
        ViewHolder(View v){
            name = v.findViewById(R.id.name);
            dob = v.findViewById(R.id.days);
            days = v.findViewById(R.id.days);
            profilePicture = v.findViewById(R.id.profilePicture);
        }
    }

}
