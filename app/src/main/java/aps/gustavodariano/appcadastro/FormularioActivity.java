package aps.gustavodariano.appcadastro;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import aps.gustavodariano.appcadastro.databinding.ActivityFormularioBinding;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEndereco;
    private EditText etTelefone;
    private Button btnSalvar;
    private String acao;
    private Funcionario funcionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById( R.id.etNome );
        etEndereco = findViewById( R.id.etEndereco );
        etTelefone = findViewById( R.id.etTelefone );
        btnSalvar = findViewById( R.id.btnSalvar );

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void salvar() {

        if(etNome.getText().toString().isEmpty()) {

            Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

        }else{

            if (acao.equals("novo")) {
                funcionario = new Funcionario();
            }

            funcionario.nome = etNome.getText().toString();
            funcionario.endereco = etEndereco.getText().toString();
            funcionario.telefone = etTelefone.getText().toString();

            if( acao.equals("editar")){
                FuncionarioDAO.editar(funcionario, this);
                finish();
            }else {
                FuncionarioDAO.inserir(funcionario, this);
                etNome.setText("");
                etEndereco.setText("");
                etTelefone.setText("");
            }
        }
    }

    private void carregarFormulario(){
        int idFuncionario = getIntent().getIntExtra("idFuncionario", 0);
        if( idFuncionario == 0) {
            funcionario = FuncionarioDAO.getFuncionarioById(this, idFuncionario);

            etNome.setText( funcionario.nome );
            etEndereco.setText(funcionario.endereco);
            etTelefone.setText(funcionario.telefone);

        }
    }

}