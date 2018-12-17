package com.hydragmes.paulo.atletafeliz.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.hydragmes.paulo.atletafeliz.R
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment: Fragment() {

    companion object {
        val TAG: String = MenuFragment::class.java.simpleName
        fun newInstance() = MenuFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_notifications)
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()

        button.setOnTouchListener{ view: View, motionEvent: MotionEvent ->
            FirebaseAuth.getInstance().signOut()
            activity!!.finish()
            true
        }
    }
}