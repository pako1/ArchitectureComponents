package com.example.pakos.myapplication.DB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.pakos.myapplication.POJO.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM word_table ORDER by word ASC")
    LiveData<List<Word>> getAllWords();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();


}
