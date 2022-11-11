package br.senai.sp.jandeira.gamesapplication.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tbl_game")
class Game {
    @PrimaryKey(autoGenerate = true) var id = 0;

    var foto:Bitmap? = null;
    var titulo = "";
    var descricao = "";
    var estudio = "";
    var anoLancamento:LocalDate? = null;
    var finalizado = false;
}