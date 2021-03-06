package com.hydragmes.paulo.atletafeliz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hydragmes.paulo.atletafeliz.R
import android.support.v4.app.Fragment

class CompaniesListFragment: Fragment() {

    companion object {
        val TAG: String = CompaniesListFragment::class.java.simpleName
        fun newInstance() = CompaniesListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_dashboard)
        val view = inflater.inflate(R.layout.fragment_companys, container, false)
        return view
    }
}