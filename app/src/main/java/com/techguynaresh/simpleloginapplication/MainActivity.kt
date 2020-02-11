package com.techguynaresh.simpleloginapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.StringDef
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.annotation.RetentionPolicy


class MainActivity : AppCompatActivity() {

    lateinit var myPreference: MyPreference
    lateinit var context: Context

    val languageList = arrayOf("en","hi")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        myPreference = MyPreference(this)

        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, languageList)

        val lang = myPreference.getLoginCount()
        val index = languageList.indexOf(lang)
        if(index >= 0){
            spinner.setSelection(index)
        }

        setSupportActionBar(toolbar)

        loginButton.setOnClickListener {
            if (usernameText.text.toString() != "" && passwordText.text.toString() != "") {
                val userHome = Intent(this, UserHomeActivity :: class.java)
                userHome.putExtra("username", usernameText.text.toString())
                userHome.putExtra("selectedItemPosition", languageList[spinner.selectedItemPosition])
                startActivity(userHome)
            }
        }

        changeLangButton.setOnClickListener {
            myPreference.setLoginCount(languageList[spinner.selectedItemPosition])
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        myPreference = MyPreference(newBase!!)
        val lang:String = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase, lang))
    }
}
