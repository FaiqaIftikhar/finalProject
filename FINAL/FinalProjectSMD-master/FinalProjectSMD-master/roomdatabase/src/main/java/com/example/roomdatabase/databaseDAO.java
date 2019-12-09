package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface databaseDAO {
    @Query("select*from Fvaourites")
    List<database> getList();

    @Insert
    public void addItem(database db);

    @Update
    public void update(database db);
    @Delete
    public void delete(database db);
}
