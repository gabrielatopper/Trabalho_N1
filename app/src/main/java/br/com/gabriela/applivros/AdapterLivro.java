package br.com.gabriela.applivros;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterLivro extends BaseAdapter {

    private List<Livro> livroList;
    private Context context;
    private LayoutInflater inflater;


    public AdapterLivro(Context context, List<Livro> listaLivros){
        this.livroList = listaLivros;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return livroList.size();
    }

    @Override
    public Object getItem(int position) {
        return livroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return livroList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuporte item;

        if (convertView == null ){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvAno = convertView.findViewById(R.id.tvListaAno);
            item.tvCategoria = convertView.findViewById(R.id.tvListaCat);
            item.layout = convertView.findViewById(R.id.llFundoItem);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        Livro livro = livroList.get( position );
        item.tvNome.setText( livro.nome );
        item.tvAno.setText( String.valueOf(livro.getAno()) );
        item.tvCategoria.setText( livro.categoria);

        if ( position % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor(Color.YELLOW);
        }

        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvAno, tvCategoria;
        LinearLayout layout;
    }

}
