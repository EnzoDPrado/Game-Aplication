package br.senai.sp.jandeira.gamesapplication.repository

import android.content.Context
import br.senai.sp.jandeira.gamesapplication.database.GamesBd
import br.senai.sp.jandeira.gamesapplication.model.User
import br.senai.sp.jandeira.gamesapplication.model.UserGames

class UserRepository(context:Context){
    private val db = GamesBd.getDatabase(context).userDAO()

    fun getGamesOfUserById(id: Int): List<UserGames>{
        return db.getGamesOfUserById(id);
    }

    fun getAll(): List<User>{
        return db.getAll();
    }

    fun getUserById(id:Int):User{
        return db.getUserById(id);
    }

    fun update(user:User): Int{
        return db.update(user);
    }

    fun save(user:User): Long{
        return db.save(user);
    }

    fun delete(user:User): Int{
        return db.delete(user);
    }

    fun getUserByEmail(email:String):String{
        return db.getUserByEmail(email);
    }

}