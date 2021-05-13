package br.com.gabriela.applivros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity2 extends AppCompatActivity {

    private EditText etNome;
    private Spinner spAno, spCategoria;
    private Button btnSalvar;
    private String acao;
    private Livro livro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario2);

        etNome = findViewById(R.id.etNome);
        spAno = findViewById(R.id.spAno);
        spCategoria = findViewById(R.id.spCategoria);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                salvar();
            }
        });
    }

    private void carregarFormulario(){
        int idLivro = getIntent().getIntExtra("idLivro", 0 );
        if (idLivro != 0 ) {
            livro = LivroDAO.getLivroById(this, idLivro);
            etNome.setText(livro.nome);
           // spCategoria.setSelection(this);
            String[] arrayAno = getResources().getStringArray(R.array.arrayAno);
            for (int i = 1; i < arrayAno.length; i++){
                if (Integer.valueOf( arrayAno[1]) == livro.getAno()){
                    spAno.setSelection( i );
                }
            }

        }

    }

    private void salvar(){
        if(spAno.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty() ) {

            Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
        }else{
            if (acao.equals("novo")) {
                livro = new Livro();
            }

            livro.nome = etNome.getText().toString();
            livro.setAno( Integer.valueOf(spAno.getSelectedItem().toString() ));

            if (acao.equals( "editar")){
                LivroDAO.editar(livro, this);
                finish();
            }else {
                LivroDAO.inserir(livro, this);
                etNome.setText(" ");
                spAno.setSelection(0);
                spCategoria.setSelection(0);
        }

        }
}