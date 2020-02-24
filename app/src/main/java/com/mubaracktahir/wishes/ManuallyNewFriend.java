package com.mubaracktahir.wishes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mubaracktahir.wishes.ui.main.FriendFregment;
import com.mubaracktahir.wishes.ui.main.Friends;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ManuallyNewFriend extends AppCompatActivity {
    TextView pickdate;
    TextView name;
    TextView surname;
    TextView emailAddress;
    TextView mobileNo;
    TextView dob;
    String firstName;
    String lastName;
    String friendEmailAddress;
    String mobileNumber;
    String dateOfBirth;
    static String regex;
    final long NUMBERS_OF_MILLISECONDS_IN_A_DAY = 86400000;
    long daysremain;

    Spinner spinner;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manually_new_friend);
        pickdate = findViewById(R.id.clickme);
        spinner = findViewById(R.id.relationship_selector);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        mobileNo = findViewById(R.id.mobile);
        emailAddress = findViewById(R.id.email);
        dob = findViewById(R.id.clickme);
        String relationship[] = {"Friends","Family", "Best Friends", "Partner"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, relationship);
        spinner.setAdapter(adapter);


        mDateSetListener = (datePicker, year, month, day) -> {

            Calendar c = Calendar.getInstance();
            c.set(2020,month,day);

            String[] months = {"January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December"};
            String[] days ={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

            regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{3,3}$";

            String date = "" ;
            Date todaysDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.get(Calendar.YEAR),month,day);

            Date choose = calendar.getTime();
            Calendar calendar2 = Calendar.getInstance();

            long difference = 0;

            if (month < calendar2.get(Calendar.MONTH)) {

                Calendar cal1 = Calendar.getInstance();
                int yr = cal1.get(Calendar.YEAR);
                cal1.set(yr,11,31);
                Date d = cal1.getTime();
                long ans = Math.abs(d.getTime() - todaysDate.getTime())/NUMBERS_OF_MILLISECONDS_IN_A_DAY;


                Calendar cal2 = Calendar.getInstance();
                cal2.set(yr+1,0,1);
                Date d1 = cal2.getTime();

                Calendar cal3 = Calendar.getInstance();
                cal3.set(yr+1,month,day);
                Date d2 = cal3.getTime();

                long ans2 = Math.abs(d1.getTime()- d2.getTime())/NUMBERS_OF_MILLISECONDS_IN_A_DAY;
                difference = ans + ans2+1;

                Calendar cc = Calendar.getInstance();
                cc.set(calendar2.get(Calendar.YEAR)+1,month,day);

                date = days[(c.get(Calendar.DAY_OF_WEEK)) - 1]
                        + ", " +months[c.get(Calendar.MONTH)]+" " + day;

            }
            else if(month == calendar2.get(Calendar.MONTH) && day < calendar2.get(Calendar.DAY_OF_MONTH) ){
                Calendar cal1 = Calendar.getInstance();
                int yr = cal1.get(Calendar.YEAR);
                cal1.set(yr,11,31);
                Date d = cal1.getTime();
                long ans = Math.abs(d.getTime() - todaysDate.getTime())/NUMBERS_OF_MILLISECONDS_IN_A_DAY;

                Calendar cal2 = Calendar.getInstance();
                cal2.set(2024,0,1);
                Date d1 = cal2.getTime();

                Calendar cal3 = Calendar.getInstance();
                cal3.set(2024,month,day);
                Date d2 = cal3.getTime();

                long ans2 = Math.abs(d1.getTime()- d2.getTime())/NUMBERS_OF_MILLISECONDS_IN_A_DAY;



                difference = ans + ans2+1;
                date = days[c.get(Calendar.DAY_OF_WEEK)-1] + ", " +months[c.get(Calendar.MONTH)]+" " +
                        day;
            }
            else  {
                difference = Math.abs((todaysDate.getTime() - choose.getTime()) / NUMBERS_OF_MILLISECONDS_IN_A_DAY);
                date =days[c.get(Calendar.DAY_OF_WEEK)-1] + ", " +months[c.get(Calendar.MONTH)]+" " +
                        day;

            }
           // Toast.makeText(this,""+difference+"<==>"+day+" "+(month +1)+" "+ year,Toast.LENGTH_LONG).show();

            daysremain =  difference;

            pickdate.setText(date);
            pickdate.setTextColor(Color.rgb(0, 0, 0));
        };


    }

      public void getAndSaveFriendsData() {

        if (name.getText().toString().trim().isEmpty() || (surname.getText().toString().trim().isEmpty())
                || (mobileNo.getText().toString().trim().isEmpty()) || (dob.getText().toString().trim()
                .equals("Add your date of birth"))) {
            if(name.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"Name is required",Toast.LENGTH_LONG).show();
            }
            if(surname.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"Surname is required",Toast.LENGTH_LONG).show();
            }
            if(mobileNo.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"Mobile Number is required",Toast.LENGTH_LONG).show();
            }
            if (dob.getText().toString().trim().equals("Add your date of birth")){
                Toast.makeText(this,"Date of birth is required at least month and day",
                        Toast.LENGTH_LONG).show();
            }

        } else {

            firstName = name.getText().toString();
            lastName = surname.getText().toString();
            friendEmailAddress = emailAddress.getText().toString();
            mobileNumber = mobileNo.getText().toString();
            dateOfBirth = dob.getText().toString();
            Friends f = new Friends();
            if(Regex.isItValidEmail(friendEmailAddress)) {
                f.setDob(dateOfBirth);
                f.setName("" + firstName + " " + lastName);
                f.setDaysRemaining((int) daysremain);
                f.setEmail(friendEmailAddress);
                f.setMobile_number(mobileNumber);
                f.setImage(9);
                FriendFregment ff = new FriendFregment();
                ff.addNewFriend(f);
                Toast.makeText(this, "" + firstName + " has been added!", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(this,"invalid email address",Toast.LENGTH_LONG).show();
        }
    }

    public void click(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ManuallyNewFriend.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getAndSaveFriendsData();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addfriendsmenu, menu);
        return true;
    }
}
