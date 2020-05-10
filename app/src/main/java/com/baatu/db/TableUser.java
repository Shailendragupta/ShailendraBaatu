package com.baatu.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.baatu.model.User;

@Entity(tableName = "TableUser")
public class TableUser {

    @PrimaryKey(autoGenerate = false)
    private int id;

    private User user;

    public TableUser(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
