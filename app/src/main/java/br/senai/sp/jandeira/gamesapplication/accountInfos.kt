package br.senai.sp.jandeira.gamesapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.senai.sp.jandeira.gamesapplication.adapter.GameRegisteredAdapter
import br.senai.sp.jandeira.gamesapplication.databinding.ActivityAccountInfosBinding
import br.senai.sp.jandeira.gamesapplication.model.Game
import br.senai.sp.jandeira.gamesapplication.model.User
import br.senai.sp.jandeira.gamesapplication.repository.UserRepository
import java.io.ByteArrayInputStream
import java.time.Year

class accountInfos : AppCompatActivity() {
    private lateinit var binding: ActivityAccountInfosBinding;
    private lateinit var user: User
    private lateinit var adapterGameRegisterd: GameRegisteredAdapter
    private lateinit var gameList: List<Game>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountInfosBinding.inflate(layoutInflater);
        setContentView(binding.root);

        var USER_ID = intent.getIntExtra("id", -1)
        user = UserRepository(this).getUserById(USER_ID)
        setupGameRegisteredRecyclerView();
        binding.floatingCreateButton.setOnClickListener {
            val intent = Intent(this, activity_register_game::class.java);
            intent.putExtra("id", user.userId);
            startActivity(intent);
        }
        setupUserInfos();


    }

    private fun setupUserInfos() {
        binding.textViewAccountName.text = user.userName;
        binding.textViewAccountEmail.text = user.userEmail;
        binding.textViewLevel.text = user.userNivel.toString();
        binding.userImageView.setImageBitmap(getBitmapFromByteArray(user.userFoto));
        var gameFinished = 0;
        var gameUnfinished = 0;
        gameList.forEach { game ->
            if (game.gameFinalizado) {
                gameFinished++
            } else {
                gameUnfinished++
            }
        }
        binding.textViewGamesFinished.text = gameFinished.toString();
        binding.textViewCurrentlyPlaying.text = gameUnfinished.toString();


        val age =
            Year.now().value - ((user?.userDataNascimento?.substring(user?.userDataNascimento.toString().length - 4))?.toInt()
                ?: Year.now().value)

        binding.textViewAge.text = age.toString();


    }

    fun getBitmapFromByteArray(imageByteArray: ByteArray?): Bitmap {
        val arrayInputStream = ByteArrayInputStream(imageByteArray)
        return BitmapFactory.decodeStream(arrayInputStream)
    }

    private fun bindGameRegistered(data: List<Game>) {
        this.adapterGameRegisterd.updateList(data)
    }


    private fun setupGameRegisteredRecyclerView() {
        binding.recyclerGames.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        this.adapterGameRegisterd = GameRegisteredAdapter(this)
        print(user?.userId)
        gameList = updateGameList(user?.userId)
        bindGameRegistered(gameList)
        binding.recyclerGames.adapter = this.adapterGameRegisterd
    }

    private fun updateGameList(id: Int): List<Game> {
        return UserRepository(this).getGamesOfUserById(id)[0].games
    }

    override fun onResume() {
        super.onResume()

        // updating the user game list
        gameList = updateGameList(user?.userId)

        // updating the Rv
        bindGameRegistered(gameList)

        // updating the user info details
        setupUserInfos()

    }

}




