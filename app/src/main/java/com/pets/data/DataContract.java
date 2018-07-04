package com.pets.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class DataContract implements BaseColumns{

    private DataContract() {}

    public static final String CONTENT_AUTHORITY = "com.pets";

    public static final Uri BASE_CONTENT_URI =Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PETS = "pets";

    public static final class pets{

        public static final String TABLE_NAME = "pets";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COYUMN_BREED = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";

        public static final Integer  GENDER_UNKNOW = 0;
        public static final Integer  GENDER_MALE = 1;
        public static final Integer  GENDER_FAMMEL = 2;


        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);


    }

}
