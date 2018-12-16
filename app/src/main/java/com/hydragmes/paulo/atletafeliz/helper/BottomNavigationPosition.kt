package com.hydragmes.paulo.atletafeliz.helper

import android.support.v4.app.Fragment
import com.hydragmes.paulo.atletafeliz.R
import com.hydragmes.paulo.atletafeliz.ui.AthleteListFragment
import com.hydragmes.paulo.atletafeliz.ui.CompaniesListFragment
import com.hydragmes.paulo.atletafeliz.ui.MenuFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    MENU(2, R.id.navigation_menu),
    COMPANYLIST(1, R.id.navigation_companys),
    ATLHETELIST(0, R.id.navigation_atlhetes);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.ATLHETELIST.id -> BottomNavigationPosition.ATLHETELIST
    BottomNavigationPosition.MENU.id -> BottomNavigationPosition.MENU
    BottomNavigationPosition.COMPANYLIST.id -> BottomNavigationPosition.COMPANYLIST
    else -> BottomNavigationPosition.ATLHETELIST
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.ATLHETELIST -> AthleteListFragment.newInstance()
    BottomNavigationPosition.MENU -> MenuFragment.newInstance()
    BottomNavigationPosition.COMPANYLIST -> CompaniesListFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.ATLHETELIST -> AthleteListFragment.TAG
    BottomNavigationPosition.MENU -> MenuFragment.TAG
    BottomNavigationPosition.COMPANYLIST -> CompaniesListFragment.TAG
}

