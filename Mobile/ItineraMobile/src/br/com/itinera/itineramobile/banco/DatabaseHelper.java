package br.com.itinera.itineramobile.banco;

import java.util.ArrayList;
import java.util.List;

import br.com.itinera.itineramobile.bean.Despesa;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static Context context;
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "ItineraMobile";
	private static final String TABELA_DESPESA_NOME = "despesa";
	
	private static final String DESPESA_TABLE_CREATE =
			"CREATE TABLE " + TABELA_DESPESA_NOME + " (" +
			" _id integer primary key autoincrement, " +
			"codigo_despesa INT, " +
			"codigo_usuario INT, " +
			"nome_fornecedor TEXT, " +
			"numero_documento TEXT, " +
			"valor DOUBLE, " +
			"data TEXT, " +
			"tipo_despesa TEXT);";

	
	public DatabaseHelper (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Método que cria o banco de dados
		db.execSQL(DESPESA_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void salvar(Despesa despesa){
		
		long id = despesa.get_id();
		if (id != 0 ){
			update(despesa);
		}else{
			try {
				insert(despesa);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void update(Despesa despesa)
	{	    
		SQLiteDatabase sqlLite = new DatabaseHelper(context).getWritableDatabase();
		
		ContentValues cv = new ContentValues();
	     
    	cv.put("codigo_usuario", despesa.getCodigoUsuario());
    	cv.put("nome_fornecedor", despesa.getNomeFornecedor());
    	cv.put("numero_documento", despesa.getNumeroDocumento());
    	cv.put("valor", despesa.getValor());
    	cv.put("data", despesa.getData());
    	cv.put("tipo_despesa", despesa.getTipoDespesa());
    	cv.put("_id", despesa.get_id());
		
	    int count = sqlLite.update(DatabaseHelper.TABELA_DESPESA_NOME, cv, "_id=?", new String[] {String.valueOf(despesa.get_id())});
	}
	
    public long insert(Despesa despesa) throws Exception {
    	//Com permissão de escrita no banco de dados
    	SQLiteDatabase sqlLite = new DatabaseHelper(context).getWritableDatabase();
     
    	ContentValues cv = new ContentValues();
     
    	cv.put("codigo_usuario", despesa.getCodigoUsuario());
    	cv.put("nome_fornecedor", despesa.getNomeFornecedor());
    	cv.put("numero_documento", despesa.getNumeroDocumento());
    	cv.put("valor", despesa.getValor());
    	cv.put("data", despesa.getData());
    	cv.put("tipo_despesa", despesa.getTipoDespesa());
    
    	return sqlLite.insert(TABELA_DESPESA_NOME, null, cv);
    }
    
    public void delete(Despesa despesa) throws Exception {
    	SQLiteDatabase sqlLite = new DatabaseHelper(context).getWritableDatabase();
        
    	int count = sqlLite.delete(DatabaseHelper.TABELA_DESPESA_NOME, "nome_fornecedor=? and numero_documento=? and codigo_usuario=? ",	 new String[] {String.valueOf(despesa.getNomeFornecedor()),String.valueOf(despesa.getNumeroDocumento()),String.valueOf(despesa.getCodigoUsuario())});
    }
    
    public List<Despesa> pesquisarPorCampo(String campo, String valor){
    	List<Despesa> listaDespesa = new ArrayList<Despesa>();
    	Cursor cursor = null;
    	SQLiteDatabase sqlLite = new DatabaseHelper(context).getReadableDatabase();
    	
    	String where = "";
    	 
    	String[] colunas = new String[] { "codigo_despesa", "codigo_usuario", 
    			"nome_fornecedor", "numero_documento", "valor", "data", "tipo_despesa","_id"};
    	 
    	String argumentos[] = new String[] {valor};
    	
    	cursor = sqlLite.query(TABELA_DESPESA_NOME, colunas, null, null, null, null, null);
    	
    	Despesa d = null;
    	
    	
    	if (  cursor != null  && cursor.getCount() > 0) {
    		cursor.moveToFirst();
    		
    		do{
    			d = new Despesa();
    			d.set_id(cursor.getLong(cursor.getColumnIndex("_id")));
    			d.setCodigoDespesa(cursor.getInt(cursor.getColumnIndex("codigo_despesa")));
    			d.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex("codigo_usuario")));
    			d.setNomeFornecedor(cursor.getString(cursor.getColumnIndex("nome_fornecedor")));
    			d.setNumeroDocumento(cursor.getString(cursor.getColumnIndex("numero_documento")));
    			d.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
    			d.setData(cursor.getString(cursor.getColumnIndex("data")));
    			d.setTipoDespesa(cursor.getString(cursor.getColumnIndex("tipo_despesa")));
    			listaDespesa.add(d);
    		}while(cursor.moveToNext());
    		
    	}    	
    	
    	return listaDespesa;
    }
    
    public Despesa recuperarPorCodigoDespesa(int codigoDespesa){
    	Cursor cursor = null;
    	SQLiteDatabase sqlLite = new DatabaseHelper(context).getReadableDatabase();
    	
    	String where = "codigo_despesa = ?";
    	 
    	String[] colunas = new String[] { "codigo_despesa", "codigo_usuario", 
    			"nome_fornecedor", "numero_documento", "valor", "data", "tipo_despesa"};
    	 
    	String argumentos[] = new String[] { String.valueOf(codigoDespesa)};
    	
    	cursor = sqlLite.query(TABELA_DESPESA_NOME, colunas, where, argumentos, null, null, null);
    	
    	Despesa d = null;
    	
    	if (cursor != null && cursor.moveToFirst()) {
    		d = new Despesa();
    		d.setCodigoDespesa(cursor.getInt(cursor.getColumnIndex("codigo_despesa")));
    		d.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex("codigo_usuario")));
    		d.setNomeFornecedor(cursor.getString(cursor.getColumnIndex("nome_fornecedor")));
    		d.setNumeroDocumento(cursor.getString(cursor.getColumnIndex("numero_documento")));
    		d.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
    		d.setData(cursor.getString(cursor.getColumnIndex("data")));
    		d.setTipoDespesa(cursor.getString(cursor.getColumnIndex("tipo_despesa")));
    	}
    	
    	return d;
    }

}
