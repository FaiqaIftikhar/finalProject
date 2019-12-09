package com.example.roomdatabase;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="Fvaourites")
public class database {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String user_email;
    private String brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Ignore
    public database(String e,String n)
    {
        this.brand=n;
        this.user_email=e;
    }

    public database(int i,String n,String e)
    {
        this.id=i;
        this.user_email=e;
        this.brand=n;
    }
}
