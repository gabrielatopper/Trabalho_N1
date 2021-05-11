package br.com.gabriela.applivros;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private ListView lvLivros;
    //private ArrayAdapter adapter;
    private AdapterLivro adapter;
    private List<Livro> listaLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity2.class);
                intent.putExtra("acao", "novo");
                startActivity( intent );

            }
        });

        lvLivros = findViewById(R.id.lvLivros);

        carregarLivros();
        configurarListView();
    }

    private void configurarListView() {
        lvLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livro livroSelecionado = listaLivros.get(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity2.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idLivro", livroSelecionado.id);
                startActivity( intent );
            }
        });

        lvLivros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Livro livroSelecionado = listaLivros.get(position);
                excluirLivro( livroSelecionado);
                return true;
            }
        });
    }

    private void excluirLivro( Livro livro){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage("Você quer excluir este livro " + livro.nome + "?");
        alerta.setNeutralButton("Não", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LivroDAO.excluir( livro.id, MainActivity.this);
                carregarLivros();
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarLivros();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregarLivros(){
        listaLivros = LivroDAO.getLivros(this);
       // adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaLivros);

        adapter  = new AdapterLivro( this, listaLivros);
        lvLivros.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}