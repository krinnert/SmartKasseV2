package Database;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Comment;

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

	public long insertKasse(String kassenName, String erstellungsDatum, String bearbeitungsDatum) {
		open();
		ContentValues values = new ContentValues();
		values.put(Kassename, kassenName);
		values.put(KasseErstellungsdatum, erstellungsDatum);
		values.put(KasseBearbeitungsdatum, bearbeitungsDatum);
		long id = database.insert(TABLE_Kasse, null, values);
		close();
		return id;
	}

	public List<String> getAllKassen() {
		open();
		List<String> kassen = new ArrayList<String>();
		//Cursor cursor = database.query(TABLE_Kasse, new String[]{Kassename}, null, null, null, null, null);
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


	public void insertArtikel(int id, String name, float preis, int bestand, String kassenname, int kategorieID) {
		open();
		ContentValues values = new ContentValues();
		values.put(KEY_ArtikelID, id);
		values.put(ArtikelName, name);
		values.put(ArtikelPreis, preis);
		values.put(ArtikelBestand, bestand);
		values.put(ArtikelKassenname, kassenname);
		values.put(ArtikelKategorieID, kategorieID);
		close();
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
	
	public void insertKunde(int kundeID, String name, String kassenName) {
		open();
		ContentValues values = new ContentValues();
		values.put(KEY_KundenID, kundeID);
		values.put(KundenName, name);
		values.put(KundenKassenname, kassenName);
		close();
	}

	public void insertKundeArtikel(int anzahl, String zeitstempel) {
		open();
		ContentValues values = new ContentValues();
		values.put(KundenArtikelAnzahl, zeitstempel);
		values.put(KundenArtikelZeitstempel, zeitstempel);
		close();
	}
	
/*	public String getKundeArtikelAnzahl(int kundenID, int artikelID) {
		open();
		Cursor cursor = database.rawQuery("SELECT " + KundenArtikelAnzahl + " FROM "+ TABLE_Artikel + " WHERE " + Kun + " = ?", new String[]{String.valueOf(kundenID), String.valueOf(artikelID)});
		cursor.moveToFirst();
		close();
		return cursor.getString(0); 
	}
*/
	public void insertKategorie(String name, int color) {
		open();
		ContentValues values = new ContentValues();
		values.put(KategorieName, name);
		values.put(KategorieFarbe, color);
		close();
	}

}

// Helpful:
// getBetrag(int kundenID)
// IMPROVEMENT: ONLY OPEN AND CLOSE THE DATABASE IN THE IMPLEMENTATION CLASS --> PERFORMANCE
