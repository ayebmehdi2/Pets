package com.pets.data;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@SuppressLint("Registered")
public class PetProvider extends ContentProvider {


    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private PetDbHelper mDB;

    private static final int PET = 100;
    private static final int PET_ID = 101;

    static {
        uriMatcher.addURI(DataContract.CONTENT_AUTHORITY, DataContract.pets.TABLE_NAME , PET);
        uriMatcher.addURI(DataContract.CONTENT_AUTHORITY, DataContract.pets.TABLE_NAME + "/#", PET_ID);
    }


    @Override
    public boolean onCreate() {
        mDB = new PetDbHelper(getContext());
        return true;
    }



    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        SQLiteDatabase database = mDB.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = uriMatcher.match(uri);
        switch (match) {
            case PET:
                cursor = database.query(DataContract.pets.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case PET_ID:
                selection = DataContract._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(DataContract.pets.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
