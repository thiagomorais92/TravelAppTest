package thiago.com.br.myapplication.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import thiago.com.br.myapplication.activitys.DashBoardActivity;

/**
 * Created by Samsung on 11/02/2015.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

    //crio uma nova instancia de do DatePickerDialog e retorno ela
        return new DatePickerDialog(getActivity(),this,ano,dia,mes);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        DashBoardActivity dash = (DashBoardActivity) getActivity();
        Bundle bundle = new Bundle();
        bundle.putInt("dia",dayOfMonth);
        bundle.putInt("mes",monthOfYear);
        bundle.putInt("ano",year);

        dash.defineData(view,year,monthOfYear,dayOfMonth);


    }
}
