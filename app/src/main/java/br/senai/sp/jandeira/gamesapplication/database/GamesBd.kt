package br.senai.sp.jandeira.gamesapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandeira.gamesapplication.dao.ConsoleDAO
import br.senai.sp.jandeira.gamesapplication.dao.GameDAO
import br.senai.sp.jandeira.gamesapplication.dao.UserDAO
import br.senai.sp.jandeira.gamesapplication.model.Console
import br.senai.sp.jandeira.gamesapplication.model.Game
import br.senai.sp.jandeira.gamesapplication.model.User
import br.senai.sp.jandeira.gamesapplication.repository.ConsoleRepository


@Database(entities = [User::class, Console::class, Game::class], version = 1)
abstract class GamesBd : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun consoleDAO(): ConsoleDAO
    abstract fun GameDAO(): GameDAO

    companion object {
        private lateinit var instance: GamesBd


        fun getDatabase(context: Context): GamesBd {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, GamesBd::class.java, "db_games")
                    .allowMainThreadQueries().build()
            }
//

            return instance
        }


    }

}
