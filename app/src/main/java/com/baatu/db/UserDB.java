package com.baatu.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities= {TableUser.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class UserDB extends RoomDatabase {

    public abstract DaoUser daoUser();

    private static volatile UserDB INSTANCE;

    public static UserDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "User.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
