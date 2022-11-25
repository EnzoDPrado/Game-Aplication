package br.senai.sp.jandeira.gamesapplication.repository

import android.content.Context
import br.senai.sp.jandeira.gamesapplication.database.GamesBd
import br.senai.sp.jandeira.gamesapplication.model.Console

class ConsoleRepository(context: Context) {
    private val db = GamesBd.getDatabase(context).consoleDAO()

    fun getAll(): List<Console>{
        return db.getAll()
    }

    fun getConsoleById(id:Int):Console{
        return db.getConsoleById(id);
    }
    fun update(console: Console): Int{
        return db.update(console)
    }
    fun save(console: Console): Long{
        return db.save(console)
    }

    fun delete(console: Console): Int{
        return db.delete(console);
    }

    fun getConsolebyName(name:String): Console{
        return db.getConsolebyName(name)
    }

}