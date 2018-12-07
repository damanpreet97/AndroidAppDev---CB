package com.example.delllatitude.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lv = listView;
        var al = ArrayList<String>()
        populate(al)

        val arrayAdapter = ArrayAdapter<>(this, R.layout.item_row, R.id.tv, al )

        lv.setAdapter(arrayAdapter)
    }

    private fun populate(a1: ArrayList<String>){
        a1.add("f1");
        a1.add("f2");
        a1.add("f3");
        a1.add("f4");
        a1.add("f5");
        a1.add("f6");
        a1.add("f7");
    }


}
