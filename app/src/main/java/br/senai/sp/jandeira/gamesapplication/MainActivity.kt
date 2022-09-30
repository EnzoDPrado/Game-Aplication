package br.senai.sp.jandeira.gamesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandeira.gamesapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar!!.hide();

        val openNewAccount = Intent(this, newAccount::class.java);

        binding.textViewCreate.setOnClickListener{
            startActivity(openNewAccount);
        }





    }

}