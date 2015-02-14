package thiago.com.br.myapplication.activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import thiago.com.br.myapplication.R;

/**
 * Created by Samsung on 11/02/2015.
 */
public class Splash extends Activity implements Runnable{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Handler handler = new Handler();
        handler.postDelayed(this,4000);

    }

    @Override
    public void run() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
