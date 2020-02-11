package com.techguynaresh.simpleloginapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_user_home.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_user_home.*

class UserHomeActivity : AppCompatActivity() {

    lateinit var myPreference: MyPreference
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        myPreference = MyPreference(this)
        setContentView(R.layout.activity_user_home)
        setSupportActionBar(toolbar)

        setTitle("User Home")
        var username: String = intent.getStringExtra("username")
        userTextView.setText(username)

        logoutButton.setOnClickListener {
            val loginPage = Intent(this, MainActivity :: class.java)
            startActivity(loginPage)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        myPreference = MyPreference(newBase!!)
        val lang:String = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase, lang))
    }

}
