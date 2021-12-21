package tw.tcnr08.m0802a;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.CollationElementIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;


public class M0802a extends AppCompatActivity implements View.OnClickListener {

    private DatePicker mdate;
    private TimePicker time;
    private Button b001;
    private TextView ans01;
    private TextView timer;
    private MediaPlayer startmusic;
    private long endTime;
    private Handler handler = new Handler();
    private long spenttime;

    private long m;
    private long s;
    private long h;
    private int years01;
    private int months01;
    private int dates01;
    private int hours01;
    private int minius01;
    private Calendar cg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.ListActivityActionBar);//可以更改表頭  日間 夜間模式抓手機時間用
        setContentView(R.layout.m0802a);
        setupviewcomponetn();
    }

    private void setupviewcomponetn() {
        mdate = (DatePicker) findViewById(R.id.date01);
        time = (TimePicker) findViewById(R.id.time01);
        b001 = (Button) findViewById(R.id.m0802a_b001);
        ans01 = (TextView) findViewById(R.id.m0802a_ans01);

        timer = (TextView) findViewById(R.id.m0802a_timer);

        startmusic = MediaPlayer.create(getApplicationContext(),R.raw.s01);

        b001.setOnClickListener(this);

        years01 = mdate.getYear();//取得畫面的"年"
        months01 = mdate.getMonth();//取得畫面的"月"
        dates01 = mdate.getDayOfMonth();//取得畫面的"日"
        hours01 = time.getHour();// 取得畫面的"小時"
        minius01 = time.getMinute();// 取得畫面的"分鐘"

        // 顯示選擇的日期和時間
        ans01.setText(s +
                years01 + getString(R.string.n_yy) +
                (months01 + 1) + getString(R.string.n_mm) +
                dates01 + getString(R.string.m_dd) +
                hours01 + getString(R.string.d_hh) +
                minius01 + getString(R.string.d_mm));
        //--------------------------------------

    }

    @Override
    public void onClick(View v) {

        String s = getString(R.string.m0802a_t001);

         cg = Calendar.getInstance();

        cg.set(years01,months01,dates01,hours01,minius01);

        endTime = cg.getTimeInMillis();

        handler.postDelayed(updatetime, 100);

    }


    private Runnable updatetime = new Runnable() {
        @Override
        public void run() {

            spenttime = endTime - System.currentTimeMillis();

            h=(hours01/1000)/60;
            m=(minius01/1000)/60;
            s=(minius01/1000)%60;

            if (endTime < 0 || hours01 > 99) {
                Toast.makeText(getApplicationContext(),getString(R.string.m0802a_err), Toast.LENGTH_SHORT).show();



                handler.removeCallbacks(updatetime);

            }else {


                timer.setText(String.format("%02d",h)+":"+String.format("%02d",m)+":"+String.format("%02d",s));

                music_set();
                handler.postDelayed(this, 1000);

            }


            if (h ==0 && m==0 && s==0){
                startmusic.start();

                handler.removeCallbacks(updatetime);
            }else {

            }


        }
    };

    private void music_set() {



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