package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, Database.DB_NAME, null, Database.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Appearance of the tables
		String CREATE_Artikel_TABLE = "CREATE TABLE " + Database.TABLE_Artikel
				+ "(" + Database.KEY_ArtikelID + " INTEGER PRIMARY KEY,"
				+ Database.ArtikelName + " Name," + Database.ArtikelPreis
				+ " INTEGER," + Database.ArtikelBestand + " INTEGER"
				+ Database.ArtikelKassenname + " TEXT,"
				+ Database.ArtikelKategorieID + " INTEGER" + ")";
		String CREATE_Kasse_TABLE = "CREATE TABLE " + Database.TABLE_Kasse
				+ "(" + Database.KEY_Kassename + " INTEGER PRIMARY KEY,"
				+ Database.KasseErstellungsdatum + " TEXT,"
				+ Database.KasseBearbeitungsdatum + " TEXT" + ")";
		String CREATE_Kunde_TABLE = "CREATE TABLE " + Database.TABLE_Kunde
				+ "(" + Database.KEY_KundenID + " INTEGER PRIMARY KEY,"
				+ Database.KundenName + " TEXT," + Database.KundenKassenname
				+ " TEXT" + ")";
		String CREATE_KundeArtikel_TABLE = "CREATE TABLE "
				+ Database.TABLE_KundeArtikel + "(" + Database.KEY_ArtikelID
				+ " INTEGER PRIMARY KEY," + Database.KEY_KundenID
				+ " INTEGER PRIMARY KEY," + Database.KundenArtikelAnzahl
				+ " INTEGER," + Database.KundenArtikelZeitstempel + " TEXT"
				+ ")"; // oder INTEGER?!
		String CREATE_Kategorie_TABLE = "CREATE TABLE "
				+ Database.TABLE_Kategorie + "(" + Database.KEY_KategorieID
				+ " INTEGER PRIMARY KEY," + Database.KategorieName + " TEXT,"
				+ Database.KategorieFarbe + " INTEGER" + ")"; // oder COLOR?!
		db.execSQL(CREATE_Artikel_TABLE);
		db.execSQL(CREATE_Kasse_TABLE);
		db.execSQL(CREATE_Kunde_TABLE);
		db.execSQL(CREATE_KundeArtikel_TABLE);
		db.execSQL(CREATE_Kategorie_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Not useful for SmartKasse yet
	}

}
