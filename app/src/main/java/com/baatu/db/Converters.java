package com.baatu.db;

import androidx.room.TypeConverter;

import com.baatu.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {

    @TypeConverter
    public static String UserToString(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        return json;
    }

    @TypeConverter
    public static User stringToUser(String value) {
        Type listType = new TypeToken<User>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
}
