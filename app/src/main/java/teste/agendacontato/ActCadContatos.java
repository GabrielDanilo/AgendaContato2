package teste.agendacontato;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

import java.util.Date;

import teste.agendacontato.Database.DataBase;
import teste.agendacontato.Dominio.Contato_repositorio;
import teste.agendacontato.Dominio.entidades.Contato;

@RequiresApi(api = Build.VERSION_CODES.M)
public class ActCadContatos extends AppCompatActivity
{
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtDatasEspeciais;
    private EditText edtGrupos;


    private Spinner spnTipoEmail;
    private Spinner spnTipoTelefone;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDatasEspeciais;

    private ArrayAdapter<String> adtTipoEmail;
    private ArrayAdapter<String> adtTipoEndereco;
    private ArrayAdapter<String> adtTipoTelefone;
    private ArrayAdapter<String> adtTipoDatasEspeciais;

    private SQLiteDatabase conn;
    private DataBase erro;
    private Contato_repositorio repositorio;
    private Contato contato;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_contatos);


        /*edtNome=(EditText)findViewById(R.id.edtNome);
        edtEndereco=(EditText)findViewById(R.id.edtEndereco);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtDatasEspeciais=(EditText)findViewById(R.id.edtDatasEspeciais);
        edtGrupos=(EditText)findViewById(R.id.edtGrupos);
        edtTelefone=(EditText)findViewById(R.id.edtTelefone); */

        spnTipoEmail=(Spinner)findViewById(R.id.spnTipoEmail);
        spnTipoTelefone=(Spinner)findViewById(R.id.spnTipotelefone);
        spnTipoEndereco=(Spinner)findViewById(R.id.spnTipoEndereco);
        spnTipoDatasEspeciais=(Spinner)findViewById(R.id.spnDatasEspeciais);


        adtTipoEmail= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adtTipoTelefone= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adtTipoEndereco= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adtTipoDatasEspeciais= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);

        adtTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adtTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adtTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adtTipoDatasEspeciais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spnTipoEmail.setAdapter(adtTipoEmail);
        spnTipoEndereco.setAdapter(adtTipoEndereco);
        spnTipoTelefone.setAdapter(adtTipoTelefone);
        spnTipoDatasEspeciais.setAdapter(adtTipoDatasEspeciais);


        adtTipoEmail.add("Casa");
        adtTipoEmail.add("Trabalho");
        adtTipoEmail.add("Outros");


        adtTipoTelefone.add("Celular");
        adtTipoTelefone.add("Casa");
        adtTipoTelefone.add("Trabalho");
        adtTipoTelefone.add("Outros...?");

        adtTipoEndereco.add("Casa");
        adtTipoEndereco.add("Trabalho");
        adtTipoEndereco.add("Outros");


        adtTipoDatasEspeciais.add("Birthday");
        adtTipoDatasEspeciais.add("Data comemorativa");
        adtTipoDatasEspeciais.add("outros");


        try {
            erro = new DataBase(this);
            conn = erro.getWritableDatabase();

            repositorio=new Contato_repositorio(conn);


        }catch (SQLException ex) {
            AlertDialog.Builder dlg= new AlertDialog.Builder(this);
            dlg.setMessage("Failed" +ex.getMessage());
            dlg.setNeutralButton("ok",null);
            dlg.show();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_contatos,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())

        {
                case R.id.mni_acao1:
                    if (contato == null)
                    {
                        inserir();
                    }
                    finish();


                break;

                case R.id.mni_acao2:

                break;


        }



        return super.onOptionsItemSelected(item);
    }

    private void inserir()
    {

        try
        {
            contato = new Contato();

            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());
            contato.setEndereco(edtEndereco.getText().toString());


            Date date = new Date();
            contato.setDatasEspeciais(date);


            contato.setTipoTelefone("");
            contato.setTipoEmail("");
            contato.setTipoDatasEspeciais("");
            contato.setTipoEndereco("");
            Contato_repositorio.inserir(contato);
        }catch (Exception ex)
        {
            AlertDialog.Builder dlg= new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao Inserir os Dados" +ex.getMessage());
            dlg.setNeutralButton("ok",null);
            dlg.show();
        }


    }


}
