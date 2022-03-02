package com.nikolai.ihavepaws.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikolai.ihavepaws.Greeting
import android.widget.TextView
import com.nikolai.ihavepaws.localStorage.realm.RealmStorage

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        val st = RealmStorage()
        println("AHA : ${st.getAllGroups()}")
    }
}
