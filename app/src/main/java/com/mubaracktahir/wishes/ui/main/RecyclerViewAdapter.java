package com.mubaracktahir.wishes.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.mubaracktahir.wishes.MainActivity;
import com.mubaracktahir.wishes.R;
import com.mubaracktahir.wishes.ui.main.dummy.MyDiffUtilCallBack;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Friends> friends;
    private FragmentManager fragmentManager;
    private Dialog dialog;
    TextView txt2;
    TextView tt;

    CircularImageView imageView;
    private SparseBooleanArray selected_item;
    //private OnClickListener onClickListener = null;
    private int current_selected_index = -1;

   /* public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }*/

    public RecyclerViewAdapter(Context context, List<Friends> friends,FragmentManager fragmentManager){
        this.mContext = context;
        this.friends = friends;
        this.fragmentManager = fragmentManager;
        selected_item = new SparseBooleanArray();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.friendlist,parent,false);
        final MyViewHolder myViewHolder = new MyViewHolder(v);
        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.popup);
        imageView = dialog.findViewById(R.id.desc);
        Dialog dialog2 = new Dialog(mContext);
        dialog2.setContentView(R.layout.popup2);
        myViewHolder.lyt_checked.setOnClickListener( view ->{
            Friends fff = friends.get(myViewHolder.getAdapterPosition());

            ImageView imageViews = dialog2.findViewById(R.id.desc);

            TextView txt1 =(TextView) dialog2.findViewById(R.id.close);
            txt1.setText(fff.getName());
            if(!(fff.getImage() == R.drawable.profile)){
                imageViews.setImageResource(fff.getImage());

            }
            dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog2.show();

        });
        myViewHolder.relay.setOnClickListener(view -> {
            Friends fff = friends.get(myViewHolder.getAdapterPosition());

            ImageView cancle = dialog.findViewById(R.id.close);
            Button btn = dialog.findViewById(R.id.end);
            TextView txt1 =(TextView) dialog.findViewById(R.id.message);
            txt2 =(TextView) dialog.findViewById(R.id.nameofcelebrant);


            txt2.setText(fff.getName());
            txt1.setText(""+(fff.getDaysRemaining()>1?""+fff.getDaysRemaining()+" day(s) remaining to celebrate with "
                    +fff.getName():" Today is "+fff.getName()+"'s birthday, be the first to wish before anyone else!"));

           //imageView.setImageResource(fff.getImage() == -1? R.drawable.cake : fff.getImage());
            imageView.setImageResource(fff.getImage());

            cancle.setOnClickListener(dodo ->{
                dialog.dismiss();

            });
            btn.setOnClickListener( dodis ->{
                dialog.dismiss();

            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        myViewHolder.layout.setOnLongClickListener(view -> {
            MainActivity.i = 0;
            class AlertDialog extends DialogFragment{
                @NonNull
                @Override
                public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                    Friends selectedFriend = friends.get(myViewHolder.getAdapterPosition());
                    builder.setMessage("Do you want to remove  \"" + selectedFriend.getName() +"\" ?")
                            .setPositiveButton("Remove", (dialog,v) ->{

                                Toast.makeText(mContext,""+selectedFriend.getName()  +
                                        " has been removed!",Toast.LENGTH_LONG).show();
                                friends.remove(selectedFriend);
                                notifyDataSetChanged();
                            })
                            .setNegativeButton("cancle", (dialog,v) -> {

                                dismiss();

                            });

                    return builder.create();
                }
            }
            AlertDialog alertDialog = new AlertDialog();
            alertDialog.show(fragmentManager,"");
            return true;
        });

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Friends frnd = friends.get(position);
        holder.name.setText(friends.get(position).getName());
        holder.dob.setText(friends.get(position).getDob());
        holder.daysRemaining.setText(""+friends.get(position).getDaysRemaining());
        //holder.image.setImageResource(friends.get(position).getImage());
        int f = friends.get(position).getDaysRemaining();
        holder.day.setText(f > 1? "days": "day");
      /*  holder.relay.setActivated(selected_item.get(position,false));
        holder.relay.setOnClickListener(v ->{
            if(onClickListener == null) return;
            onClickListener.onItemClick(v,frnd,position);
        });
        holder.relay.setOnLongClickListener(view -> {
            if(onClickListener == null) return false;
            onClickListener.onItemLongClick(view,frnd,position);
            return true;
        });

        toggleCheckIcon(holder,position);*/
        displayImage(holder,frnd);


    }

    private void displayImage(MyViewHolder holder, Friends frnd) {
        if(frnd.getImage() == -1 ){
            frnd.setImage(R.drawable.profile);
            holder.image.setColorFilter(null);
            holder.image.setImageResource(frnd.getImage());
        }
        else{
            imageView.setColorFilter(Color.TRANSPARENT);
            holder.image.setImageResource(frnd.getImage());
            holder.image.setColorFilter(frnd.getColor());
        }
    }

    private void toggleCheckIcon(MyViewHolder holder, int position) {
        if(selected_item.get(position,false)){
            //holder.lyt_img.setVisibility(View.GONE);
            holder.lyt_checked.setVisibility(View.VISIBLE);
            if(current_selected_index == position) resetCurrentIndex();
        }
        else
        {
            //holder.lyt_img.setVisibility(View.VISIBLE);
            holder.lyt_checked.setVisibility(View.GONE);
            if(current_selected_index == position) resetCurrentIndex();
        }
    }

    private void resetCurrentIndex() {
        current_selected_index = -1;
    }
    public void toggleSelection(int pos){
        current_selected_index = pos;
        if (selected_item.get(pos,false))
            selected_item.delete(pos);
        else
            selected_item.put(pos,true);
        notifyItemChanged(pos);
    }
    public void clearSelection(){
        selected_item.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount(){
        return selected_item.size();
    }

    public List<Integer> getSelectedItems(){
        List<Integer> items =  new ArrayList<>(selected_item.size());
        for(int i = 0; i<selected_item.size();i++){
            items.add(selected_item.keyAt(i));
        }
        return items;
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
    public  void insertData(List<Friends> insetNewData){
        MyDiffUtilCallBack myDiffUtilCallBack = new MyDiffUtilCallBack(friends,insetNewData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(myDiffUtilCallBack);
        friends.addAll(insetNewData);
        diffResult.dispatchUpdatesTo(this);
    }
    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        private TextView dob;
        private TextView daysRemaining;
        private TextView day;
        private CircularImageView image;
        private RelativeLayout relay;
        private LinearLayout layout;
        private TextView image_text;
        private RelativeLayout lyt_checked;
        private RelativeLayout lyt_img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            relay = itemView.findViewById(R.id.innerlyt);
            image = itemView.findViewById(R.id.profilePicture);
            name = itemView.findViewById(R.id.name);
            dob = itemView.findViewById(R.id.date_of_birth);
            daysRemaining = itemView.findViewById(R.id.days);
            day = itemView.findViewById(R.id.d);
            lyt_checked = itemView.findViewById(R.id.lyt_checked);
            //lyt_img = itemView.findViewById(R.id.lyt_img);
            layout = itemView.findViewById(R.id.layout);
        }
    }
    public interface OnClickListener{
        void onItemClick(View v,Friends f,int position);
        void onItemLongClick(View v,Friends f,int position);
    }
    public void removeData(int pos){
        friends.remove(pos);
        resetCurrentIndex();
    }
}
