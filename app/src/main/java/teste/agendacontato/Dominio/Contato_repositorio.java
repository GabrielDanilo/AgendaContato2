package teste.agendacontato.Dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import java.util.Date;
import teste.agendacontato.Dominio.entidades.Contato;

/**
 * Created by LuizFilipeFerreira on 1/23/2017.
 */

public class Contato_repositorio {


    private static SQLiteDatabase conn;

    public Contato_repositorio(SQLiteDatabase conn)
    {
        this.conn= conn;
    }

    public static void inserir(Contato contato)
    {
        ContentValues value = new ContentValues();
        value.put("NOME",contato.getNome());
        value.put("TELEFONE", contato.getTelefone());
        value.put("TIPOTELEFONE",contato.getTipoTelefone());
        value.put("EMAIL", contato.getEmail());
        value.put("TIPOEMAIL",contato.getTipoEmail());
        value.put("ENDERECO",contato.getEndereco());
        value.put("TIPOENDERECO", contato.getTipoEndereco());
        value.put("DATASESPECIAIS",contato.getDatasEspeciais().getTime());
        value.put("TIPODATASESPECIAIS",contato.getTipoDatasEspeciais());
        value.put("GRUPOS",contato.getGrupos());

        conn.insertOrThrow("CONTATO",null,value);
    }



public ArrayAdapter<Contato> buscacontatos(Context context)
{
ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context,android.R.layout.simple_list_item_1);

    Cursor cursor = conn.query("CONTATO",null,null,null,null,null,null);

    if (cursor.getCount()>0)

    {

        cursor.moveToFirst();
        do {
            Contato contato = new Contato();

            contato.setNome(cursor.getString(1));
          /*  contato.setTelefone(cursor.getString(2));
            contato.setTipoTelefone(cursor.getString(3));
            contato.setEmail(cursor.getString(4));
            contato.setTipoEmail(cursor.getString(5));
            contato.setEndereco(cursor.getString(6));
            contato.setTipoEndereco(cursor.getString(7));
            contato.setDatasEspeciais( new Date(cursor.getLong(8)));
            contato.setTipoDatasEspeciais(cursor.getString(9));
            contato.setGrupos(cursor.getString(10));  ta crashando aqui tb... :/ */

           adpContatos.add(contato);

        }while (cursor.moveToNext());

    }
    return adpContatos;



}










}
