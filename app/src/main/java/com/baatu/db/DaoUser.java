package com.baatu.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.baatu.model.User;

import java.util.List;

@Dao
public interface DaoUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(TableUser user);

    @Query("SELECT * from TableUser WHERE id=:id")
    TableUser getUser(String id);

    @Query("SELECT * from TableUser")
    LiveData<List<TableUser>> getAllUser();

    @Query("DELETE FROM TableUser")
    void deleteAll();
}
