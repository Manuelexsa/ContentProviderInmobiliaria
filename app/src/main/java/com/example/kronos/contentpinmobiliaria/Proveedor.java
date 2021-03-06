package com.example.kronos.contentpinmobiliaria;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by kronos on 23/02/2015.
 */
public class Proveedor extends ContentProvider {
    private Ayudante abd;
    public static String AUTORIDAD = "com.example.kronos.contentpinmobiliaria.proveedor";
    private static final UriMatcher convierteUri2Int;
    private static final int INMUEBLES = 1;
    private static final int INMUEBLE_ID = 2;

    static {
        convierteUri2Int = new UriMatcher(UriMatcher.NO_MATCH);
        convierteUri2Int.addURI(AUTORIDAD, Contrato.TablaInmueble.TABLA, INMUEBLES);
        convierteUri2Int.addURI(AUTORIDAD, Contrato.TablaInmueble.TABLA + "/#", INMUEBLE_ID);
    }

    public Proveedor(){}

    @Override
    public int delete(Uri uri, String condicion, String[] parametros) {
        SQLiteDatabase db = abd.getWritableDatabase();
        switch (convierteUri2Int.match(uri)) {
            case INMUEBLES:
                break;
            case INMUEBLE_ID:
                condicion = Contrato.TablaInmueble._ID+" = ?";
                parametros = new String[]{uri.getLastPathSegment()};
                break;
            default: throw new IllegalArgumentException("URI " + uri);
        }
        int cuenta = db.delete(Contrato.TablaInmueble.TABLA, condicion, parametros);
        getContext().getContentResolver().notifyChange(uri, null);
        return cuenta;
    }

    @Override
    public String getType(Uri uri) {
        switch (convierteUri2Int.match(uri)) {
            case INMUEBLES:
                return Contrato.TablaInmueble.CONTENT_TYPE_INMUEBLES;
            default:
                throw new IllegalArgumentException("URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues valores) {
        if (convierteUri2Int.match(uri) != INMUEBLES) {
            throw new IllegalArgumentException("URI " + uri);
        }
        SQLiteDatabase db = abd.getWritableDatabase();
        long id = db.insert(Contrato.TablaInmueble.TABLA, null, valores);
        if (id > 0) {
            Uri uriElemento = ContentUris.withAppendedId(Contrato.TablaInmueble.CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(uriElemento, null);
            return uriElemento;
        }
        throw new SQLException("Insert" + uri);
    }

    @Override
    public boolean onCreate() {
        abd = new Ayudante(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] proyeccion, String condicion, String[] parametros, String orderBy) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Contrato.TablaInmueble.TABLA);
        switch (convierteUri2Int.match(uri)) {
            case INMUEBLES: break;
            default:
                throw new IllegalArgumentException("URI " + uri);
        }
        SQLiteDatabase db = abd.getReadableDatabase();
        Cursor c = qb.query(db, proyeccion, condicion,parametros, null, null, orderBy);
        return c;
    }

    public Cursor queryDos(Uri uri, String[] proyeccion, String condicion,String[] parametros, String orderBy){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Contrato.TablaInmueble.TABLA);
        switch (convierteUri2Int.match(uri)) {
            case INMUEBLES: break;
            default:
                throw new IllegalArgumentException("URI " + uri);
        }
        SQLiteDatabase db = abd.getReadableDatabase();
        Cursor c = qb.query(db, proyeccion, condicion,
                parametros, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues valores, String condicion, String[] parametros) {
        SQLiteDatabase db = abd.getWritableDatabase();
        int cuenta;
        switch (convierteUri2Int.match(uri)) {
            case INMUEBLE_ID:
                condicion = Contrato.TablaInmueble._ID + " = ?";
                parametros = new String[]{uri.getLastPathSegment()};
            case INMUEBLES:
                break;
            default:
                throw new IllegalArgumentException("URI " + uri);
        }
        cuenta = db.update(Contrato.TablaInmueble.TABLA,valores,condicion,parametros);
        getContext().getContentResolver().notifyChange(uri, null);
        return cuenta;
    }
}

