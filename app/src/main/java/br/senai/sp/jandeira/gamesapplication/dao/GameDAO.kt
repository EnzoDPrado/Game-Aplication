package br.senai.sp.jandeira.gamesapplication.dao

import androidx.room.*
import br.senai.sp.jandeira.gamesapplication.model.Console
import br.senai.sp.jandeira.gamesapplication.model.Game
import br.senai.sp.jandeira.gamesapplication.model.User

@Dao
interface GameDAO{
    @Query("SELECT * FROM tbl_game ORDER BY titulo ASC")
    fun getAll(): List<Game>;

    @Query("SELECT * FROM tbl_game where id = :id")
    fun getGameById(id:Int):Game;

    @Update
    fun update(game: Game): Int;

    @Insert
    fun save(game: Game): Long;

    @Delete
    fun delete(game: Game): Int;

}