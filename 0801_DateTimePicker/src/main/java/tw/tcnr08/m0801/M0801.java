package tw.tcnr08.m0801;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class M0801 extends AppCompatActivity implements View.OnClickListener {

    private DatePicker mdate;
    private TimePicker time;
    private Button b001;
    private TextView ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.ListActivityActionBar);//可以更改表頭  日間 夜間模式抓手機時間用
        setContentView(R.layout.m0801);
        setupviewcomponetn();
    }

    private void setupviewcomponetn() {
        mdate = (DatePicker) findViewById(R.id.m0801_date);
        time = (TimePicker) findViewById(R.id.m0801_time);
        b001 = (Button) findViewById(R.id.m0801_b001);
        ans = (TextView) findViewById(R.id.m0801_t001);

        b001.setOnClickListener(this);
        mdate.setOnClickListener(this);
        time.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String s = getString(R.string.m0801_t001);
        String ss = Connvert24to12(time.getHour() +":"+ time.getMinute()+":00");

        ans.setText(s + "\n" +
                mdate.getYear() + getString(R.string.m0801_t003) +
                mdate.getMonth() + getString(R.string.m0801_t004) +
                mdate.getDayOfMonth() + getString(R.string.m0801_t005) + "\n" + ss

        );

    }


    private String Connvert24to12(String hour) {
        String convertTime = "";

        try {
            SimpleDateFormat input = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("hh:mm a");

            Date date = input.parse(hour);
            convertTime = output.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertTime;
    }


}