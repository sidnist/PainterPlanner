package com.example.projectplanner2.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;

import java.util.List;


@Dao
public interface QuoteDataDao {

    @Insert
    void insert(InsertHomeItemModel data);

    @Query("DELETE FROM INSERTHOMEITEMMODEL WHERE id=:id")
    void delete(int id);

    @Query("SELECT * FROM InsertHomeItemModel")
    List<InsertHomeItemModel> getAllData();

    @Query("SELECT * FROM InsertHomeItemModel WHERE date>=:startDate AND date <= :endDate")
    List<InsertHomeItemModel> getDataByDate(String startDate,String endDate);

    @Query("SELECT * FROM InsertHomeItemModel WHERE date>=:current" )
    List<InsertHomeItemModel> NowDate(String current);



}
