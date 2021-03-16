package com.example.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.ArrayList
import kotlin.Number

class MainActivity : AppCompatActivity() {



    var mRef: DatabaseReference? = null
    var mNotList: ArrayList<Nots>?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        mRef = database.getReference("Notes")
        mNotList = ArrayList()


        fun onStart() {

            super.onStart()
            mRef!!.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (n in snapshot.children) {
                        var note  = n.getValue(Nots::class.java)
                        mNotList?.add(note!!)
                    }

                    var noteAddapter = NoteAddapter(applicationContext, mNotList!!)
                    list.adapter = noteAddapter
                }
            })
        }



        saveToFirebase.setOnClickListener {
            var name = Name.text.toString()
            var number = Number.text.toString()
            var address = Address.text.toString()
            if (name.isNotEmpty() && number.isNotEmpty() && address.isNotEmpty()) {
                var id = mRef!!.push().key
                var MyNote = id?.let { it1 -> Nots(it1,name, number, address) }
                if (id != null) {
                    mRef!!.child(id).setValue(MyNote)
                }
            } else {
                Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()
            }
        }

    }
}