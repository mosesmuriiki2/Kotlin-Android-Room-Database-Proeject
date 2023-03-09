package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.example.roomdb.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)
        binding.btnWritedata.setOnClickListener {
            writeData()
        }

        binding.btnreaddata.setOnClickListener {
            readData()
        }

    }
    private fun writeData(){
        val firstName = binding.etfirstname.text.toString()
        val lastname = binding.etlastname.text.toString()
        val rollNo = binding.etrollNo.text.toString()

        if (firstName.isNotEmpty() && lastname.isNotEmpty() && rollNo.isNotEmpty()){
            val student = Student(
                null,firstName,lastname,rollNo.toInt()
            )
            GlobalScope.launch(Dispatchers.IO) {
                appDb.studentDao().insert(student)

            }
            binding.etfirstname.text.clear()
            binding.etlastname.text.clear()
            binding.etrollNo.text.clear()

            Toast.makeText(this@MainActivity, "Successfully written", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@MainActivity, "Please enter Data", Toast.LENGTH_SHORT).show()
        }

    }





    private fun readData(){

        val rollNoSearch = binding.rollNoSearch.text.toString()

        if (rollNoSearch.isNotEmpty()){
            lateinit var student: Student
            GlobalScope.launch {
                student = appDb.studentDao().findByRoll(rollNoSearch.toInt())
                displayData(student)
            }
        }else{
            Toast.makeText(this@MainActivity, "Please enter Data", Toast.LENGTH_SHORT).show()
        }

    }
    private suspend fun displayData(student: Student){

        withContext(Dispatchers.Main){

            binding.etfirstname.text = student.firstName
            binding.etlastname.text = student.lastName
            binding.etrollNo.text = student.rollNo.toInt()

        }

    }


}