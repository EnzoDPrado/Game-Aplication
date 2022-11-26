package br.senai.sp.jandeira.gamesapplication.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "tbl_user")
data class User(
    var userFoto: ByteArray?,
    var userEmail: String,
    var userSenha: String,
    var userName: String,
    var userCidade: String,
    var userDataNascimento: String?,
    var userSexo: Char,
    var userNivel: Level,


    @Embedded
    var userconsole: Console?
){
    @PrimaryKey(autoGenerate = true)
    var userId = 0
}