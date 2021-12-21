package tw.tcnr08.m0802;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class M0802 extends AppCompatActivity {

    private TextView time;
    private Handler handler = new Handler();
    private long starttime;
    private long m;
    private long stime;
    private long s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802);
        setupviewcomponetn();

    }


    private void setupviewcomponetn() {
        time = (TextView) findViewById(R.id.m0802_timer);
        starttime = System.currentTimeMillis();
        handler.postDelayed(updatetime, 1000);



    }


    private Runnable updatetime=new Runnable() {   //Runnable是工作命令單執行你所要做的內容 與java不同
        @Override
        public void run() {

            //經過時間
            stime = System.currentTimeMillis() - starttime;

            m=(stime/1000)/60;
            s=(stime/1000)%60;

            time.setText(String.format("%02d",m)+":"+String.format("%02d",s));
            handler.postDelayed(this, 1000);


        }
    };




    @Override
    protected void onStop() {
        super.onStop();

        handler.removeCallbacks(updatetime);
        this.finish();


    }




    @Override
    public void onBackPressed() {
//      super.onBackPressed();

    }
}