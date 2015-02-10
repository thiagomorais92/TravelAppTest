package thiago.com.br.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import thiago.com.br.myapplication.R;
import thiago.com.br.myapplication.model.OpcaoDashBoard;

/**
 * Created by Samsung on 09/02/2015.
 */
public class AdapterDalista extends BaseAdapter {

    private final Activity context;
    private List<OpcaoDashBoard> opc;


    public AdapterDalista(Activity context,List<OpcaoDashBoard> opc) {
        this.context = context;
        this.opc = opc;
    }

    @Override
    public int getCount() {
        return opc.size();
    }

    @Override
    public Object getItem(int position) {
        return opc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OpcaoDashBoard linha = opc.get(position);

        LayoutInflater inflater = context.getLayoutInflater();
        View view =  inflater.inflate(R.layout.layout_linha,null);

        ImageView iconLinha = (ImageView) view.findViewById(R.id.iv_imgLinha);
        TextView titleLinha  = (TextView) view.findViewById(R.id.tv_titleLinha);

        iconLinha.setImageResource(linha.getImagen());
        titleLinha.setText(linha.getTitle());

        return view;
    }
}
