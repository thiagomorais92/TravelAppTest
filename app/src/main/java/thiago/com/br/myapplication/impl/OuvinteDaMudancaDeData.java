package thiago.com.br.myapplication.impl;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Samsung on 11/02/2015.
 */
public class OuvinteDaMudancaDeData implements DatePickerDialog.OnDateSetListener {

    private View v;


    public OuvinteDaMudancaDeData(View v) {
        this.v = v;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Button b = (Button) v;
        b.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

    }
}
