package com.example.projectplanner2.shared;

import androidx.room.TypeConverter;

import com.example.projectplanner2.presentation.invoice.model.DropDownModel;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<DropDownModel> fromString(String value) {
        Type listType = new TypeToken<List<DropDownModel>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<DropDownModel> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}