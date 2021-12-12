package com.example.guardiannewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.guardiannewsapp.network.models.Result
@Dao
interface GuardianAppDao {

    @Query("SELECT * FROM results")
    fun getAllResultsFromDb(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResult(result: Result)

}