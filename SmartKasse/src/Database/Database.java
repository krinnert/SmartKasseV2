package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Database {

	// The Android's default system path of your application database.
	protected static String DB_PATH = "/data/data/SmartKasse/databases/";
	protected static final int DB_VERSION = 1;
	protected static final String DB_NAME = "DBSmartKasse";
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;

	// Tabellen
	protected static final String TABLE_Artikel = "Artikel";
	protected static final String TABLE_Kasse = "Kasse";
	protected static final String TABLE_Kunde = "Kunde";
	protected static final String TABLE_KundeArtikel = "KundeArtikel";
	protected static final String TABLE_Kategorie = "Kategorie";

	// Artikel
	protected static final String KEY_ArtikelID = "ArtikelID";
	protected static final String ArtikelName = "Name";
	protected static final String ArtikelPreis = "Preis";
	protected static final String ArtikelBestand = "Bestand";
	protected static final String ArtikelKassenname = "Kassenbestand";
	protected static final String ArtikelKategorieID = "KategorieID";
	// Kasse
	protected static final String KEY_Kassename = "Kassename";
	protected static final String KasseErstellungsdatum = "Erstellungsdatum";
	protected static final String KasseBearbeitungsdatum = "Bearbeitungsdatum";
	// KundenID
	protected static final String KEY_KundenID = "KundenID";
	protected static final String KundenName = "Name";
	protected static final String KundenKassenname = "Klassenname";
	// KundenArtikel
	// Keys: KundenID und ArtikelID
	protected static final String KundenArtikelAnzahl = "Anzahl";
	protected static final String KundenArtikelZeitstempel = "Zeitstempel";
	// Kategorie
	protected static final String KEY_KategorieID = "KategorieID";
	protected static final String KategorieName = "Name";
	protected static final String KategorieFarbe = "Farbe";
	
	
	public Database(Context context) {
		dbHelper = new DatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
	}


	public void insertKasse(String kassenName, String erstellungsDatum, String bearbeitungsDatum) {
		ContentValues values = new ContentValues();
		values.put(KEY_Kassename, kassenName);
		values.put(KasseErstellungsdatum, erstellungsDatum);
		values.put(KasseBearbeitungsdatum, bearbeitungsDatum);
	}
	
	
	public String[] getKasse(String nameKasse) {
		Cursor cursor = db.query(TABLE_Kasse, new String[] { KEY_Kassename, KasseErstellungsdatum,
				KasseBearbeitungsdatum }, KEY_Kassename + "=?", new String[] { String.valueOf(nameKasse) },
				null, null, null, null);
		return cursor.getColumnNames();
	}
	
	
	public void insertArtikel(int id, String name, float preis, int bestand, String kassenname, int kategorieID) {
		ContentValues values = new ContentValues();
		values.put(KEY_ArtikelID, id);
		values.put(ArtikelName, name);
		values.put(ArtikelPreis, preis);
		values.put(ArtikelBestand, bestand);
		values.put(ArtikelKassenname, kassenname);
		values.put(ArtikelKategorieID, kategorieID);
	}
	
	
	public void insertKunde(int kundeID, String kategorie, String kassenname) {
		ContentValues values = new ContentValues();
		values.put(KEY_KundenID, kundeID);
		values.put(KundenName, kategorie);
		values.put(KundenKassenname, kassenname);
	}
	
	
	public void insertKundeArtikel(int anzahl, String zeitstempel) {
		ContentValues values = new ContentValues();
		values.put(KundenArtikelAnzahl, zeitstempel);
		values.put(KundenArtikelZeitstempel, zeitstempel);
	}
	
	
	public void insertKategorie(String name, int color) {
		ContentValues values = new ContentValues();
		values.put(KategorieName, name);
		values.put(KategorieFarbe, color);
	}
	
	
}

	//Helpful:
	//getBetrag(int kundenID)


	
//	public List<Currency> getAllCurrency() {
//	List<Currency> currencyList = new ArrayList<Currency>();
//	// Select All
//	String selectQuery = "SELECT * FROM " + TABLE_RATES;
//	SQLiteDatabase db = this.getWritableDatabase();
//	Cursor cursor = db.rawQuery(selectQuery, null);
//	// Looping through all rows and adding to list
//	if (cursor.moveToFirst()) {
//		do {
//			Currency currency = new Currency();
//			currency.setID(Integer.parseInt(cursor.getString(0)));
//			currency.setName(cursor.getString(1));
//			currency.setRate(Double.parseDouble(cursor.getString(2)));
//			// Add this row
//			currencyList.add(currency);
//		} while (cursor.moveToNext());
//	}
//	return currencyList;
//}
	
	
	
	
	
/*	


	public Database(Context context) {
		this.context = context;
	}
	
	  public void openWrite() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public void openRead() throws SQLException {
		    database = dbHelper.getReadableDatabase();
		  }

		  public void close() {
		    dbHelper.close();
		  }

	// Creating A New Table
	public void onCreate(SQLiteDatabase db) {
		// Appearance of the tables
		String CREATE_Artikel_TABLE = "CREATE TABLE " + TABLE_Artikel + "("
				+ KEY_ArtikelID + " INTEGER PRIMARY KEY," + ArtikelName
				+ " Name," + ArtikelPreis + " INTEGER," + ArtikelBestand
				+ " INTEGER" + ArtikelKassenname + " TEXT,"
				+ ArtikelKategorieID + " INTEGER" + ")";
		String CREATE_Kasse_TABLE = "CREATE TABLE " + TABLE_Kasse + "("
				+ KEY_Kassename + " INTEGER PRIMARY KEY,"
				+ KasseErstellungsdatum + " TEXT," + KasseBearbeitungsdatum
				+ " TEXT" + ")";
		String CREATE_Kunde_TABLE = "CREATE TABLE " + TABLE_Kunde + "("
				+ KEY_KundenID + " INTEGER PRIMARY KEY," + KundenName
				+ " TEXT," + KundenKlassenname + " TEXT" + ")";
		String CREATE_KundeArtikel_TABLE = "CREATE TABLE " + TABLE_KundeArtikel
				+ "(" + KEY_ArtikelID + " INTEGER PRIMARY KEY," + KEY_KundenID
				+ " INTEGER PRIMARY KEY," + KundenArtikelAnzahl + " INTEGER,"
				+ KundenArtikelZeitstempel + " TEXT" + ")"; // oder INTEGER?!
		String CREATE_Kategorie_TABLE = "CREATE TABLE " + TABLE_Kategorie + "("
				+ KEY_KategorieID + " INTEGER PRIMARY KEY," + KategorieName
				+ " TEXT," + KategorieFarbe + " INTEGER" + ")"; // oder COLOR?!
		db.execSQL(CREATE_Artikel_TABLE);
		db.execSQL(CREATE_Kasse_TABLE);
		db.execSQL(CREATE_Kunde_TABLE);
		db.execSQL(CREATE_KundeArtikel_TABLE);
		db.execSQL(CREATE_Kategorie_TABLE);
	}

//	// Upgrading Database
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		// Drop older table if existed
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Artikel);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Kasse);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Kunde);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_KundeArtikel);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Kategorie);
//		// Create tables again
//		onCreate(db);
//	}

	public void addKasse(String kassenName, String erstellungsDatum, String bearbeitungsDatum) {
		this.openWrite();
		ContentValues values = new ContentValues();
		values.put(KEY_Kassename, kassenName);
		values.put(KasseErstellungsdatum, erstellungsDatum);
		values.put(KasseBearbeitungsdatum, bearbeitungsDatum);
		this.close();
	}
	
	public String[] getKasse(String nameKasse) {
		this.openRead();
		Cursor cursor = database.query(TABLE_Kasse, new String[] { KEY_Kassename, KasseErstellungsdatum,
				KasseBearbeitungsdatum }, KEY_Kassename + "=?", new String[] { String.valueOf(nameKasse) },
				null, null, null, null);
		return cursor.getColumnNames();
	}/*
//	
//	public void addArtikel(Artikel artikel, Kategorie kategorie, Kasse kasse) {
//		this.openWrite();
//		ContentValues values = new ContentValues();
//		values.put(KEY_ArtikelID, artikel.getID());
//		values.put(ArtikelName, artikel.getName());
//		values.put(ArtikelPreis, artikel.getPreis());
//		values.put(ArtikelBestand, artikel.getBestand());
//		values.put(ArtikelKassenname, kasse.getKasseName());
//		values.put(ArtikelKategorieID, kategorie.getID());
//		this.close();
//	}
//
//	// Getting single Artikel
//	public Currency getArtikel(int id) {
//		this.openRead();
//		Cursor cursor = database.query(TABLE_Artikel, new String[] { KEY_ArtikelID, ArtikelName,
//				ArtikelPreis, ArtikelBestand, ArtikelKassenname }, KEY_ID + "=?", new String[] { String.valueOf(id) },
//				null, null, null, null);
//		if (cursor != null) {
//			cursor.moveToFirst();
//		}
//		Artikel artikel = new Currency(Integer.parseInt(cursor.getString(0)),
//				cursor.getString(1), Double.parseDouble(cursor.getString(2)));
//		return artikel;
//		this.close();
//	}
//
//	// Getting All Currency
//	public List<Currency> getAllCurrency() {
//		List<Currency> currencyList = new ArrayList<Currency>();
//		// Select All
//		String selectQuery = "SELECT * FROM " + TABLE_RATES;
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		// Looping through all rows and adding to list
//		if (cursor.moveToFirst()) {
//			do {
//				Currency currency = new Currency();
//				currency.setID(Integer.parseInt(cursor.getString(0)));
//				currency.setName(cursor.getString(1));
//				currency.setRate(Double.parseDouble(cursor.getString(2)));
//				// Add this row
//				currencyList.add(currency);
//			} while (cursor.moveToNext());
//		}
//		return currencyList;
//	}
//
//	// Getting contacts Count
//	public int getCurrencyCount() {
//		String countQuery = "SELECT  * FROM " + TABLE_RATES;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		cursor.close();
//		// return count
//		return cursor.getCount();
//	}
//
//	// Updating single contact
//	public int updateCurrency(Currency currency) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put(KEY_NAME, currency.getName());
//		values.put(KEY_RATE, currency.getRate());
//
//		// updating row
//		return db.update(TABLE_RATES, values, KEY_ID + "=?",
//				new String[] { String.valueOf(currency.getID()) });
//	}
//
//	// Deleting single contact
//	public void deleteCurrency(Currency currency) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		db.delete(TABLE_RATES, KEY_ID + "=?",
//				new String[] { String.valueOf(currency.getID()) });
//		db.close();
//	}
*/

