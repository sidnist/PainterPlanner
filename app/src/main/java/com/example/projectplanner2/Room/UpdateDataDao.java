package com.example.projectplanner2.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.projectplanner2.presentation.invoice.model.InsertUpdateDataModel;

import java.util.List;

@Dao
public interface UpdateDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InsertUpdateDataModel data);

    @Query("SELECT * FROM InsertUpdateDataModel WHERE id=:id")
    List<InsertUpdateDataModel> getUpdateData(int id);


}
