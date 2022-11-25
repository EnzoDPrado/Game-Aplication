package br.senai.sp.jandeira.gamesapplication.repository

import android.content.Context
import br.senai.sp.jandeira.gamesapplication.database.GamesBd
import br.senai.sp.jandeira.gamesapplication.model.Game

class GameRepository(context: Context){
    private val db = GamesBd.getDatabase(context).GameDAO()

    fun getAll(): List<Game>{
        return db.getAll();
    }

    fun getGameById(id:Int):Game{
        return db.getGameById(id);
    }

    fun update(game: Game): Int{
        return db.update(game);
    }

    fun save(game: Game): Long{
        return db.save(game);
    }

    fun delete(game: Game): Int{
        return db.delete(game)
    }
}