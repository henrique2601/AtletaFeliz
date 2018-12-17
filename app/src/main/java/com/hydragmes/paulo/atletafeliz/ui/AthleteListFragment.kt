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
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_athletes.*

class AthleteListFragment: Fragment() {

    lateinit var mDatabase: DatabaseReference
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    companion object {
        val TAG: String = AthleteListFragment::class.java.simpleName
        fun newInstance() = AthleteListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_home)
        val view = inflater.inflate(R.layout.fragment_athletes, container, false)

        FirebaseDatabase.getInstance().getReference("users").addListenerForSingleValueEvent(athleteListener)
        return view
    }

    var athleteListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            addDataToList(dataSnapshot)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }

    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val itemIterator = dataSnapshot.children.iterator()
        val athleteList = mutableListOf<Athlete>()
        //Check if current database contains any collection
        while (itemIterator.hasNext()) {
            val toDoListindex = itemIterator.next()

            val athlete = toDoListindex.getValue(Athlete::class.java)
            athleteList!!.add(athlete!!)
        }
        //alert adapter that has changed

        layoutManager = LinearLayoutManager(activity)
        recycler_view!!.layoutManager = layoutManager

        adapter = RecyclerAdapter(athleteList)
        recycler_view!!.adapter = adapter
        //Log.w("MainActivity", athleteList.toString())
    }

}