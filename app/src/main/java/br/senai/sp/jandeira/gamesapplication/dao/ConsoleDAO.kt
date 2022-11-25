package br.senai.sp.jandeira.gamesapplication.dao

import androidx.room.*
import br.senai.sp.jandeira.gamesapplication.model.Console
import br.senai.sp.jandeira.gamesapplication.model.User

@Dao
interface ConsoleDAO{
    @Query("SELECT * FROM tbl_console ORDER BY consoleNome ASC")
    fun getAll(): List<Console>;

    @Query("SELECT * FROM tbl_user where consoleId = :id")
    fun getConsoleById(id:Int):Console;

    @Update
    fun update(console: Console): Int;

    @Insert
    fun save(console: Console): Long;

    @Delete
    fun delete(console: Console): Int;

    @Query("SELECT * FROM tbl_console WHERE consoleNome = :name")
    fun getConsolebyName(name:String): Console

}