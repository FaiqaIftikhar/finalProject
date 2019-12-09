package com.example.roomdatabase;

import android.content.Context;

public class MainActivity {

    database db=new database(" email"," name");

    private static database addItem(final mydatabase mydb, database db) {
        mydb.databaseDAO().addItem(db);
        return db;
    }

    private static void populateWithTestData(mydatabase mydb) {
        database user = new database("email","name");
        addItem(mydb, user);
    }

    Context context;
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
