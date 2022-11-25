package br.senai.sp.jandeira.gamesapplication.model

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "tbl_user")
class User {
    @PrimaryKey(autoGenerate = true)
    var userId = 0
    var userFoto: ByteArray? = null;
    var userEmail = "";
    var userSenha = "";
    var userName = "";
    var userCidade = "";
    var userDataNascimento: String? = null;
    var userSexo = 'I';
    var userNivel = NiveisEnum.Niveis.INICIANTE;

    @Embedded
    var Userconsole: Console? = null;
}