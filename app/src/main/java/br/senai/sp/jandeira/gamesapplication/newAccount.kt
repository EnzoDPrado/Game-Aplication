package br.senai.sp.jandeira.gamesapplication

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import br.senai.sp.jandeira.gamesapplication.databinding.ActivityNewAccountBinding
import br.senai.sp.jandeira.gamesapplication.model.Console
import br.senai.sp.jandeira.gamesapplication.model.Level
import br.senai.sp.jandeira.gamesapplication.model.User
import br.senai.sp.jandeira.gamesapplication.repository.ConsoleRepository
import br.senai.sp.jandeira.gamesapplication.repository.UserRepository
import java.io.ByteArrayOutputStream

class newAccount : AppCompatActivity() {
    private lateinit var binding: ActivityNewAccountBinding
    private lateinit var pictureBitmap:Bitmap;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ConsoleRepository(this).getAll().isEmpty()) {
            populateConsoles()

        }
        setupSpinner();
        setupSlider();

        binding.userIcon.setOnClickListener{
            pickImageGallery();
        }
    }


    fun getByteArrayFromBitmap(bitmap: Bitmap?): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }
    private fun getSpinnerValue(): String {
        return binding.spinner.selectedItem.toString()
    }

    private fun getFormData(): User {
        var email = binding.editTextEmailRegister.text.toString()
        var password = binding.editTextPasswordRegister.text.toString()
        var name = binding.editTextName.text.toString()
        var city = binding.editTextCity.toString()
        var birthday = binding.editTextTextBirth.text.toString()
        var level = getSliderText(binding.slider.value.toInt())
        var genre = binding.radioGroup.checkedRadioButtonId.toString()
        var console = ConsoleRepository(this).getConsolebyName(getSpinnerValue())
        var picture = getByteArrayFromBitmap(this.pictureBitmap)

        return User(userEmail = email, userSenha = password, userName = name, userCidade = city, userDataNascimento = birthday, userNivel = level, userSexo = genre[0], userconsole = console, userFoto = picture);
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(validateForm()){
            val user = getFormData();
            UserRepository(this).save(user);
            finish();
        }
        return false
    }

    private fun setupSpinner() {
        val list = ConsoleRepository(this).getAll().map { item -> item.consoleNome }
        binding.spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
    }

//    private fun gameLevel(value: Float): String {
//        if(value == )
//            return "Noob"
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_new_user, menu);

        return true;
    }


    private fun populateConsoles() {
        val console1 = Console(
            consoleCodigo = 123,
            consoleNome = "Playstation 5",
            consoleFabricante = "SONY",
            consoleDescricao =
            "O PS5 Edição Digital oferece novas possibilidades de jogabilidade que você nunca imaginou. Uma versão totalmente digital do console PS5 sem unidade de disco. Você pode baixar jogos digitais para PS5 e PS4 pela PlayStation Store. Inicie sua sessão na conta da PlayStation Network e vá à PlayStationStore para comprar e baixar jogos.*",
            consoleFoto = null,
            consoleAnoLancamento = 2020,
        )


        val console2 = Console(
            consoleCodigo = 456,
            consoleNome = "Xbox Series X",
            consoleFabricante = "Microsoft",
            consoleDescricao =
            "Apresentamos o Xbox Series X, nosso console mais rápido e poderoso de todos os tempos. Jogue milhares de títulos de quatro gerações de consoles- todos os jogos têm melhor aparência e são melhor executados no Xbox Series X.",
            consoleFoto = null,
            consoleAnoLancamento = 2020,
        )
        ConsoleRepository(this).save(console1)
        ConsoleRepository(this).save(console2)
    }

    private fun setupSlider() {
        binding.slider.addOnChangeListener { slider, value, fromUser ->
            binding.sliderRefText.text = getSliderText(binding.slider.value.toInt()).toString()
        }
    }

    private fun getSliderText(pos: Int): Level {
        if (pos <= 20) return Level.AMADOR
        if (pos in 21..40) return Level.CASUAL
        if (pos in 41..60) return Level.ENTUSIASTA
        if (pos in 61..80) return Level.TRYHARD
        return Level.PROFISSIONAL
    }

    private fun validateForm(): Boolean {
        var validate = true;
        val inputText = listOf(
            binding.editTextEmailRegister,
            binding.editTextPasswordRegister,
            binding.editTextCity,
            binding.editTextName,
            binding.editTextTextBirth
        );
        inputText.forEach { editText ->
            if (editText.text.isEmpty()) {
                editText.error = "Campo requirido"
                validate = false;
            }
        }


        if (binding.radioGroup.checkedRadioButtonId.toString().isEmpty()) {
            Toast.makeText(this, "Preencha o campo de genero", Toast.LENGTH_SHORT).show()
            return false
        }

        return validate;
    }


    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }


    private fun getBitmapFromUri(imageUri: Uri?, context: Context):Bitmap {
        return MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            pictureBitmap = getBitmapFromUri(data?.data, this)



        }
    }


}