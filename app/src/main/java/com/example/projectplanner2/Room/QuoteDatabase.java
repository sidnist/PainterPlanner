package com.example.projectplanner2.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projectplanner2.presentation.invoice.model.InsertUpdateDataModel;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;

@Database(entities = {InsertHomeItemModel.class, InsertUpdateDataModel.class}, version = 1)
public abstract class QuoteDatabase extends RoomDatabase {
    public abstract  QuoteDataDao getDao();
    public abstract  UpdateDataDao getUpdateDao();
    public  static  QuoteDatabase INSTANCE;
    public  static QuoteDatabase getInstance(Context context){
        if(INSTANCE ==  null){
            INSTANCE = Room.databaseBuilder(context,
                    QuoteDatabase.class, "quote_data")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return  INSTANCE;
    }
//    public  static QuoteDatabase getUpdateInstance(Context context){
//        if(INSTANCE ==  null){
//            INSTANCE = Room.databaseBuilder(context,
//                    QuoteDatabase.class, "update_data")
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
//                    .build();
//
//        }
//        return  INSTANCE;
//    }


}
