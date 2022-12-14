package com.example.owndiary.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.owndiary.model.Diary
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Query("" +
            "SELECT * FROM diary_table " +
            "WHERE id = :id")
    suspend fun getDiaryById(id: Int): Diary

    @Insert
    suspend fun insert(diary: Diary)

    @Update
    suspend fun update(diary: Diary)

    @Delete
    suspend fun delete(diary: Diary)

    //테이블의 모든 값 불러오는 메소드
    @Query("SELECT * FROM diary_table")
    fun getAll(): Flow<List<Diary>>

    @Query("DELETE FROM diary_table")
    suspend fun deleteAll()
}