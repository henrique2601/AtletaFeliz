package com.hydragmes.paulo.atletafeliz.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.firebase.database.*
import com.hydragmes.paulo.atletafeliz.Model.Athlete
import com.hydragmes.paulo.atletafeliz.R
import android.support.v4.app.Fragment

class AthleteListFragment: Fragment() {

    lateinit var mDatabase: DatabaseReference
    var provaList: MutableList<Athlete>? = null
    lateinit var adapter: ArrayAdapter<String>

    companion object {
        val TAG: String = AthleteListFragment::class.java.simpleName
        fun newInstance() = AthleteListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_home)
        val view = inflater.inflate(R.layout.fragment_athletes, container, false)

//        mDatabase = FirebaseDatabase.getInstance().reference
//        provaList = mutableListOf<Prova>()
//        FirebaseDatabase.getInstance().getReference("prova").addValueEventListener(provaListener)

        return view
    }

//    fun updateList() {
//        val provasNomes = provaList!!.map { prova -> prova.nome }.toTypedArray()
//        val adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, provasNomes)
//        listView.adapter = adapter
//    }

    override fun onResume() {
        super.onResume()

        Log.d("novatela", "AthleteListFragment")
//
//        fabDash.setOnClickListener { view ->
//            val intent = Intent(view.context, AddHGTestActivity::class.java)
//            startActivity(intent)
//        }
//
//        val intent = Intent(context, MyService::class.java)
//        activity!!.startService(intent)
    }

//    var provaListener: ValueEventListener = object : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            // Get Post object and use the values to update the UI
//            addDataToList(dataSnapshot)
//        }
//
//        override fun onCancelled(databaseError: DatabaseError) {
//            // Getting Item failed, log a message
//            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
//        }
//    }
//
//    private fun addDataToList(dataSnapshot: DataSnapshot) {
//        provaList!!.clear()
//        val itemIterator = dataSnapshot.children.iterator()
//        //Check if current database contains any collection
//        while (itemIterator.hasNext()) {
//            val toDoListindex = itemIterator.next()
//
//            val prova = toDoListindex.getValue(Prova::class.java)
//            provaList!!.add(prova!!)
//        }
//        //alert adapter that has changed
//        updateList()
//    }

}