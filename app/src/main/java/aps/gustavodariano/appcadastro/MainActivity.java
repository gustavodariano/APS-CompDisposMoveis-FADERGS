package aps.gustavodariano.appcadastro;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import aps.gustavodariano.appcadastro.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import static aps.gustavodariano.appcadastro.FuncionarioDAO.excluir;

public class MainActivity extends AppCompatActivity {

    private ListView lvFuncionarios;
    public ArrayAdapter adapter;
    private List<Funcionario> listaFuncionarios;

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
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "novo");
                startActivity(intent);
            }
        });

        lvFuncionarios = findViewById(R.id.lvFuncionarios);

        carregarFuncionarios();

        configurarListView();

    }

    private void carregarFuncionarios() {
        listaFuncionarios = FuncionarioDAO.getFuncionarios(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listaFuncionarios);
        lvFuncionarios.setAdapter(adapter);
    }

    private void configurarListView() {
        lvFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Funcionario funcionarioSelecionado = listaFuncionarios.get(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idFuncionario", funcionarioSelecionado.id);
                startActivity(intent);
            }
        });

        lvFuncionarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Funcionario funcionarioSelecionado = listaFuncionarios.get(position);
                excluirFuncionario(funcionarioSelecionado);
                return true;
            }
        });

    }

    private void excluirFuncionario(Funcionario funcionario) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage("Confirma a exclusão do funcionario " + funcionario.nome +"?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FuncionarioDAO.excluir( funcionario.id, MainActivity.this);
                carregarFuncionarios();
            }
        });
        alerta.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        carregarFuncionarios();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarFuncionarios();
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