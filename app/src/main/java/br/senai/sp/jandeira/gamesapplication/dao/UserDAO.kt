package br.senai.sp.jandeira.gamesapplication.dao

import androidx.room.*
import br.senai.sp.jandeira.gamesapplication.model.User
import br.senai.sp.jandeira.gamesapplication.model.UserGames

@Dao
interface UserDAO{
    @Transaction
    @Query("SELECT * from tbl_user WHERE userId = :id")
    fun getGamesOfUserById(id: Int): List<UserGames>

    @Query("SELECT * FROM tbl_user ORDER BY userName ASC")
    fun getAll(): List<User>;

    @Query("SELECT * FROM tbl_user where userId = :id")
    fun getUserById(id:Int):User;

    @Update
    fun update(user:User): Int;

    @Insert
    fun save(user:User): Long;

    @Delete
    fun delete(user:User): Int;

    @Query("SELECT * FROM tbl_user WHERE userEmail = :email")
    fun getUserByEmail(email:String):String

}
