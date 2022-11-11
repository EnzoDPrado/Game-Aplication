package br.senai.sp.jandeira.gamesapplication.model

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "tbl_user")
class User {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    var foto: Bitmap? = null;
    var email = "";
    var senha = "";
    var nome = "";
    var cidade = "";
    var dataNascimento: LocalDate? = null;
    var sexo = 'I';
    var nivel = NiveisEnum.Niveis.INICIANTE;
    var console: Console? = null;
}