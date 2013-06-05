package Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Database {

	// Tabellen
	protected static final String TABLE_Artikel = "Artikel";
	protected static final String TABLE_Kasse = "Kasse";
	protected static final String TABLE_Kunde = "Kunde";
	protected static final String TABLE_KundeArtikel = "KundeArtikel";
	protected static final String TABLE_Kategorie = "Kategorie";

	// Kasse
	protected static final String KEY_KasseID = "KasseID";
	protected static final String Kassename = "Kassename";
	protected static final String KasseErstellungsdatum = "Erstellungsdatum";
	protected static final String KasseBearbeitungsdatum = "Bearbeitungsdatum";
	// Artikel
	protected static final String KEY_ArtikelID = "ArtikelID";
	protected static final String ArtikelName = "Name";
	protected static final String ArtikelPreis = "Preis";
	protected static final String ArtikelBestand = "Bestand";
	protected static final String ArtikelKassenname = "Kassenbestand";
	protected static final String ArtikelKategorieID = "KategorieID";
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

	// Database fields
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	
	public Database(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// ----------------------- KASSEN-METHODS ----------------------- 
	
	// Create a new entry with a user-specified name for the register
	// Day & time will be created automatically
	public long insertKasse(String kassenName) {
		open();
		ContentValues values = new ContentValues();
		values.put(Kassename, kassenName);
		values.put(KasseErstellungsdatum, new Country().getDateTime());
		values.put(KasseBearbeitungsdatum, "");
		long test = database.insert(TABLE_Kasse, null, values);
		close();
		return test;
	}
	
	// TEST IT
	// Change the name of a specified existed register and insert the changing-day automatically
	public String updateKasse(String kassenNameOld, String kassenNameNew) {
		// Find the expected name and replace it
		ContentValues values = new ContentValues();
		values.put(Kassename, kassenNameNew);
		values.put(KasseErstellungsdatum, "");
		values.put(KasseBearbeitungsdatum, "");
		Cursor cursor = database.rawQuery("UPDATE "+ TABLE_Kasse + " SET " + Kassename + " = " + kassenNameNew + " WHERE " + Kassename + " = ?", new String[] { kassenNameOld });
		cursor = database.rawQuery("UPDATE "+ TABLE_Kasse + " SET " + KasseBearbeitungsdatum + " = " + new Country().getDateTime() + " WHERE " + Kassename + " = ?", new String[] { kassenNameOld });
		cursor.moveToFirst();
		close();
		return cursor.getString(0); 
	}

	public List<String> getAllKassen() {
		open();
		List<String> kassen = new ArrayList<String>();
		//Old, because it doesn't work with the query-method: Cursor cursor = database.query(TABLE_Kasse, new String[]{Kassename}, null, null, null, null, null);
		Cursor cursor = database.rawQuery("SELECT " + Kassename + " FROM " + TABLE_Kasse, null);
		cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	kassen.add(cursor.getString(0)); 
	        cursor.moveToNext();
	    }
	    close();
		return kassen;
	}
	
	public String getCreatedDate(String kassenName) {
		open();
		Cursor cursor = database.rawQuery("SELECT " + KasseErstellungsdatum + " FROM "+ TABLE_Kasse + " WHERE " + Kassename + " = ?", new String[]{kassenName});
		cursor.moveToFirst();
		close();
		return cursor.getString(0); 
	}
	
	public String getProcessedDate(String kassenName) {
		open();
		Cursor cursor = database.rawQuery("SELECT " + KasseBearbeitungsdatum + " FROM "+ TABLE_Kasse + " WHERE " + Kassename + " = ?", new String[]{kassenName});
		cursor.moveToFirst();
		close();
		return cursor.getString(0); 
	}

	
	// ----------------------- ARTIKEL-METHODS ----------------------- 

	public long insertArtikel(int id, String name, float preis, int bestand, String kassenname, int kategorieID) {
		open();
		ContentValues values = new ContentValues();
		values.put(KEY_ArtikelID, id);
		values.put(ArtikelName, name);
		values.put(ArtikelPreis, preis);
		values.put(ArtikelBestand, bestand);
		values.put(ArtikelKassenname, kassenname);
		values.put(ArtikelKategorieID, kategorieID);
		long test = database.insert(TABLE_Artikel, null, values);
		close();
		return test;
	}
	
	public List<String> getAllArtikel(String kassenName) {
		open();
		List<String> artikel = new ArrayList<String>();
		Cursor cursor = database.rawQuery("SELECT " + ArtikelName + " FROM " + TABLE_Artikel + " WHERE " + Kassename + " = ?", new String[]{kassenName});
		cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	artikel.add(cursor.getString(0)); 
	        cursor.moveToNext();
	    }
	    close();
		return artikel;
	}
	
	// Get all Articles that belong to a specified category
	public List<String> getArtikelofCategory(String kassenName, String category) {
		open();
		List<String> artikel = new ArrayList<String>();
		
		
		
		Cursor cursorCategory = database.rawQuery("SELECT " + KEY_KategorieID + " FROM " + TABLE_Kategorie + " WHERE " + KategorieName + " = ?", new String[]{category});
		cursorCategory.moveToFirst();
		String categoryID = cursorCategory.toString();
		Cursor cursor = database.rawQuery("SELECT " + ArtikelName + " FROM " + TABLE_Artikel + " WHERE " + ArtikelKassenname + " = ?, " + ArtikelKategorieID + " = ?", new String[]{kassenName, categoryID});
		cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	artikel.add(cursor.getString(0)); 
	        cursor.moveToNext();
	    }
	    close();
		return artikel;
	}
	
	public String getArtikelpreis(String artikelName) {
		open();
		Cursor cursor = database.rawQuery("SELECT " + ArtikelPreis + " FROM "+ TABLE_Artikel + " WHERE " + ArtikelName + " = ?", new String[]{artikelName});
		cursor.moveToFirst();
		close();
		return cursor.getString(0); 
	}
	
	public String getArtikelBestand(String artikelName) {
		open();
		Cursor cursor = database.rawQuery("SELECT " + ArtikelBestand + " FROM "+ TABLE_Artikel + " WHERE " + ArtikelName + " = ?", new String[]{artikelName});
		cursor.moveToFirst();
		close();
		return cursor.getString(0); 
	}

	public String getArtikelKategorie(String artikelName) {
		open();
		Cursor cursor = database.rawQuery("SELECT " + ArtikelKategorieID + " FROM "+ TABLE_Kategorie + " WHERE " + ArtikelName + " = ?", new String[]{artikelName});
		cursor.moveToFirst();
		close();
		return cursor.getString(0); 
	}
	
	public long insertKunde(int kundeID, String name, String kassenName) {
		open();
		ContentValues values = new ContentValues();
		values.put(KEY_KundenID, kundeID);
		values.put(KundenName, name);
		values.put(KundenKassenname, kassenName);
		long test = database.insert(TABLE_Kunde, null, values);
		close();
		return test;
	}

	public long insertKundeArtikel(int anzahl, String zeitstempel) {
		open();
		ContentValues values = new ContentValues();
		values.put(KundenArtikelAnzahl, zeitstempel);
		values.put(KundenArtikelZeitstempel, zeitstempel);
		long test = database.insert(TABLE_KundeArtikel, null, values);
		close();
		return test;
	}
	
/*	public String getKundeArtikelAnzahl(int kundenID, int artikelID) {
		open();
		Cursor cursor = database.rawQuery("SELECT " + KundenArtikelAnzahl + " FROM "+ TABLE_Artikel + " WHERE " + Kun + " = ?", new String[]{String.valueOf(kundenID), String.valueOf(artikelID)});
		cursor.moveToFirst();
		close();
		return cursor.getString(0); 
	}
*/
	public long insertKategorie(String name, int color) {
		open();
		ContentValues values = new ContentValues();
		values.put(KategorieName, name);
		values.put(KategorieFarbe, color);
		long test = database.insert(TABLE_Artikel, null, values);
		close();
		return test;
	}

}

// Helpful:
// getBetrag(int kundenID)
// IMPROVEMENT: ONLY OPEN AND CLOSE THE DATABASE IN THE IMPLEMENTATION CLASS --> PERFORMANCE
