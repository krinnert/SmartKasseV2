package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	

	private static final String DATABASE_NAME = "DBSmartKasse.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// Appearance of the tables
		String CREATE_Artikel_TABLE = "CREATE TABLE " + Database.TABLE_Artikel + "("
				+ Database.KEY_ArtikelID + " INTEGER PRIMARY KEY,"
				+ Database.ArtikelName + " TEXT," 
				+ Database.ArtikelPreis + " INTEGER," 
				+ Database.ArtikelBestand + " INTEGER," 
				+ Database.ArtikelKassenname + " TEXT,"
				+ Database.ArtikelKategorieID + " INTEGER" + ");";
		String CREATE_Kasse_TABLE = "CREATE TABLE " + Database.TABLE_Kasse + "("
				//+ Database.KEY_KasseID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ Database.Kassename + " TEXT PRIMARY KEY," 
				+ Database.KasseErstellungsdatum + " TEXT," 
				+ Database.KasseBearbeitungsdatum + " TEXT" + ");";
		String CREATE_Kunde_TABLE = "CREATE TABLE " + Database.TABLE_Kunde + "("
				+ Database.KEY_KundenID + " INTEGER PRIMARY KEY,"
				+ Database.KundenName + " TEXT,"
				+ Database.KundenKassenname + " TEXT" + ");";
		String CREATE_KundeArtikel_TABLE = "CREATE TABLE " + Database.TABLE_KundeArtikel + "(" 
				+ Database.KEY_ArtikelID + " INTEGER," 
				+ Database.KEY_KundenID + " INTEGER,"
				+ Database.KundenArtikelAnzahl + " INTEGER,"
				+ Database.KundenArtikelZeitstempel + " TEXT" + ");";
		String CREATE_Kategorie_TABLE = "CREATE TABLE " + Database.TABLE_Kategorie + "("
				+ Database.KEY_KategorieID + " INTEGER PRIMARY KEY,"
				+ Database.KategorieName + " TEXT,"
				+ Database.KategorieFarbe + " INTEGER" + ");";
		database.execSQL(CREATE_Artikel_TABLE);
		database.execSQL(CREATE_Kasse_TABLE);
		database.execSQL(CREATE_Kunde_TABLE);
		database.execSQL(CREATE_KundeArtikel_TABLE);
		database.execSQL(CREATE_Kategorie_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}