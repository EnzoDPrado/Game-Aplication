package br.senai.sp.jandeira.gamesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandeira.gamesapplication.databinding.ActivityMainBinding
import br.senai.sp.jandeira.gamesapplication.model.User
import br.senai.sp.jandeira.gamesapplication.repository.UserRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var user: User? = null;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar!!.hide();

        val openNewAccount = Intent(this, newAccount::class.java);

        binding.textViewCreate.setOnClickListener{
            startActivity(openNewAccount);
        }

        Toast.makeText(this, "${UserRepository(this).getAll()}", Toast.LENGTH_SHORT).show()


        binding.buttonLoggin.setOnClickListener{
            if(validateForm()){
                if(authenticate()){
                    val openAcountInfo = Intent(this, accountInfos::class.java);
                    openAcountInfo.putExtra("id", user?.userId);
                    startActivity(openAcountInfo);
                }
            }
        }


    }

    private fun authenticate(): Boolean {
        user = UserRepository(this).getUserByEmail(binding.editTextEmailLogin.text.toString())

        if (user === null) {
            Toast.makeText(this, "Email nao encontrado, faca um cadastro!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.ediTextPasswordLogin.text.toString() != user?.userSenha) {
            Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
    private fun validateForm():Boolean {
        if (binding.ediTextPasswordLogin.text.isEmpty()) {
            binding.ediTextPasswordLogin.error = "teste"
            return false
        }
        if(binding.editTextEmailLogin.text.isEmpty()) {
            binding.editTextEmailLogin.error = "teste"
            return false
        }
        return true
    }


}