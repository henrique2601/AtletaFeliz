package com.hydragmes.paulo.atletafeliz.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import java.util.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import android.util.Log
import com.google.firebase.database.*


class LoginActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            handleUser(user)
        } else {
            showLoginOption()
        }
    }

    private fun showLoginOption() {
        // Choose authentication providers
        val providers = Arrays.asList(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build())

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN)
    }

    private fun handleUser(user: FirebaseUser?) {
        user?.let {
            val path = "users/" + user.uid
            FirebaseDatabase.getInstance().getReference(path).addValueEventListener(userListener)
        }
    }

    private fun showMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showCompleteSignup() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    var userListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            if (dataSnapshot.hasChildren()) {
                Log.w("LoginActivity", "hasChildren")
                showMain()
            } else {
                Log.w("LoginActivity", "not hasChildren")
                showCompleteSignup()
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, log a message
            Log.w("LoginActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }
}
