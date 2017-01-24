package teste.agendacontato.Database;

/**
 * Created by LuizFilipeFerreira on 1/23/2017.
 */
import android.content.Context;
import android.database.sqlite.*;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context)
    {
        super(context,"agenda",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptSQL.getCreateconato());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int Old, int New) {

    }
}

