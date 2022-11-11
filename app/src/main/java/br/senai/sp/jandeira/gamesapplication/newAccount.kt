package br.senai.sp.jandeira.gamesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.senai.sp.jandeira.gamesapplication.databinding.ActivityMainBinding
import br.senai.sp.jandeira.gamesapplication.databinding.ActivityNewAccountBinding

class newAccount : AppCompatActivity() {
    private lateinit var binding: ActivityNewAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBarRegister.value = gameLevel()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save){
            Toast.makeText(this, "Clicou no menu", Toast.LENGTH_SHORT).show()
            return true;
        }else if(item.itemId == R.id.menu_settings){
            Toast.makeText(this, "Clicou nas configurações", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
            return  true
        }

    }

    private fun gameLevel(value: Int): String {
        if(value == 1)
            return "Noob"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_new_user, menu);

        return true;
    }


}