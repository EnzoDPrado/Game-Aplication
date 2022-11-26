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
import android.widget.RadioButton
import android.widget.Toast
import br.senai.sp.jandeira.gamesapplication.databinding.ActivityRegisterGameBinding
import br.senai.sp.jandeira.gamesapplication.model.Game
import br.senai.sp.jandeira.gamesapplication.model.User
import br.senai.sp.jandeira.gamesapplication.repository.GameRepository
import br.senai.sp.jandeira.gamesapplication.repository.UserRepository
import java.io.ByteArrayOutputStream

class activity_register_game : AppCompatActivity() {
    lateinit var binding: ActivityRegisterGameBinding;
    private lateinit var pictureBitmap:Bitmap;
    private var userId = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterGameBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.imageGame.setOnClickListener{
            pickImageGallery();
        }
        userId = intent.getIntExtra("id", -1);


    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_new_user, menu);

        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (validateForm()) {
            val game = getFormData();
            GameRepository(this).save(game);
            finish();
        }
        return false
    }

    fun getByteArrayFromBitmap(bitmap: Bitmap?): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }

    private fun getFormData(): Game {
        val (name, description, studio, releaseDate) = listOf(
            binding.ediTextGame.text.toString(),
            binding.editTextGameDescription.text.toString(),
            binding.editTextGameStudio.text.toString(),
            binding.editTextGameRelease.text.toString(),
        );

        val inputRadio = binding.radioGroup.checkedRadioButtonId
        val gameFinished = findViewById<RadioButton>(inputRadio).text.toString()[0]
        var finished = false;
        if(gameFinished == 'Y'){
            finished = true;
        }

        return Game(gameTitulo = name, gameDescricao = description, gameStudio = studio, gameAnoLancamento = releaseDate, gameFoto = getByteArrayFromBitmap(pictureBitmap), gameOwnerUserId = userId, gameFinalizado = finished);
    }



    private fun validateForm(): Boolean {
        var validate = true;
        val inputs = listOf(
            binding.ediTextGame,
            binding.editTextGameDescription,
            binding.editTextGameStudio,
            binding.editTextGameRelease
        );
        inputs.forEach { editText ->
            if (editText.text.isEmpty()) {
                editText.error = "Campo requirido"
                validate = false;
            }
        }

        if(binding.radioGroup.checkedRadioButtonId.toString().isEmpty()){
            Toast.makeText( this, "Preencha se você terminou o jogo!", Toast.LENGTH_SHORT).show()
            return false;
        }

        return validate;

    }


    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }


    private fun getBitmapFromUri(imageUri: Uri?, context: Context): Bitmap {
        return MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            pictureBitmap = getBitmapFromUri(data?.data, this)



        }
    }
}