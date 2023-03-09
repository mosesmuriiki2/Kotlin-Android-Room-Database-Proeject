package com.example.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name="first_name") var firstName:String?,
    @ColumnInfo(name="last_name") var lastName:String?,
    @ColumnInfo (name="roll_no") var rollNo:Int?

)
