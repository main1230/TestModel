package com.zzl.top.ui

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.zzl.top.R
import com.zzl.top.common.base.BaseActivity


class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var usernameet: EditText
    private lateinit var passwordet: EditText
    private lateinit var loginbtn: Button

    private lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity = this

        this.loginbtn = findViewById(R.id.login_btn) as Button
        this.passwordet = findViewById(R.id.password_et) as EditText
        this.usernameet = findViewById(R.id.username_et) as EditText

        this.loginbtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val username = usernameet.text.toString()
        val password = passwordet.text.toString()

        if (TextUtils.isEmpty(username.trim())) {
            Toast.makeText(activity, "用户名不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(password.trim())) {
            Toast.makeText(activity, "密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }

    }
}
