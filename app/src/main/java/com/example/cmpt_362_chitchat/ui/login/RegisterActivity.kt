package com.example.cmpt_362_chitchat.ui.login

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cmpt_362_chitchat.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var accountManager: AccountManager
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance().reference

        val username = binding.registerusername
        val password = binding.registerpassword
        val register = binding.registerBtn

        register.setOnClickListener {
            addAccount(this, username.text.toString(), password.text.toString(), "123")
           // getAccount(this)

        }

    }

    fun addAccount(context: Context, email: String, password: String, token: String) {

        //add confirm pw?

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful){
                println("DEBUG REGISTER SUCCESS: email: $email, password: $password")
                addAccountToDatabase(auth.currentUser?.uid)
                finish()
            }else{
                println("REGISTER FAIL")
            }
        }
    }

    private fun getAccount(context: Context): Account? {
        accountManager = AccountManager.get(context)
        var acc: Account? = null
        try {
            acc = accountManager.getAccountsByType("com.login.example")[0]
            println("DEBUG: USERNAME: ${acc.name}")

        }catch (E: Throwable){

        }
        return acc
    }

    private fun addAccountToDatabase(userId: String?) {
        if (userId != null) {
            database.child("Users").child(userId).push()
        } else {
            println("Debug: user not added to db correctly")
        }
    }
}