package com.example.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.*

@Dao
interface StudentDao {
    @Query("select * from student_table")
    fun getAll():List<Student>

    @Query("SELECT * FROM student_table WHERE roll_no like :roll limit 1")
    suspend fun  findByRoll(roll:Int):Student

    @Insert(onConflict = OnConflictStrategy.IGNORE )
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)
    
    @Query("delete from student_table")
    suspend fun deleteAll()

}