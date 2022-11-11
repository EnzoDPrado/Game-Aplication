package br.senai.sp.jandeira.gamesapplication.dao

import androidx.room.*
import br.senai.sp.jandeira.gamesapplication.model.User

@Dao
interface UserDAO{
    @Query("SELECT * FROM tbl_user ORDER BY nome ASC")
    fun getAll(): List<User>;

    @Query("SELECT * FROM tbl_user where id = :id")
    fun getUserById(id:Int):User;

    @Update
    fun update(user:User): Int;

    @Insert
    fun save(user:User): Long;

    @Delete
    fun delete(user:User): Int;

}
