package com.rawtooth.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rawtooth.demoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var btnAdd:Button
    private lateinit var btnShow:Button

    private lateinit var sqLiteHelper:SQLiteHelper
    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter: DetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        idInit()
        recycleMain()
        sqLiteHelper=SQLiteHelper(this)
        btnAdd.setOnClickListener{addDetails()}
        btnShow.setOnClickListener{
            val dtsList=sqLiteHelper.getAllDetail()
            Log.e("code","${dtsList.size}")
            adapter.addItem(dtsList)
        }
    }

    private fun idInit() {
        name=findViewById(R.id.username)
        email=findViewById(R.id.email)
        btnAdd=findViewById(R.id.registerbtn)
        btnShow=findViewById(R.id.viewBtn)
        recyclerView=findViewById(R.id.recycleView)
    }


    private fun addDetails() {
        val name=name.text.toString()
        val email=email.text.toString()
        if(name.isEmpty()||email.isEmpty()){
            Toast.makeText(this,"Please enter required fields",Toast.LENGTH_SHORT).show()
        }else{
            val dts=DetailModel(name = name, email = email)
            val status=sqLiteHelper.insertDetails(dts)
            if(status>-1){
                Toast.makeText(this,"Your Details Added",Toast.LENGTH_SHORT).show()
                removeEdt()
            }
            else{
                Toast.makeText(this,"Your Details not Added",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun removeEdt() {
        name.setText("")
    email.setText("")
        name.requestFocus()
    }
    private fun recycleMain(){
        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter = DetailsAdapter()
        recyclerView.adapter=adapter
    }
}