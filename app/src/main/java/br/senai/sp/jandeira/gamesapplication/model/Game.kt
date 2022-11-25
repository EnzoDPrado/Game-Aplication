package br.senai.sp.jandeira.gamesapplication.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tbl_game")
class Game {
    @PrimaryKey(autoGenerate = true)
    var gameId = 0;

    var gameFoto:ByteArray? = null;
    var gameTitulo = "";
    var gameDescricao = "";
    var gameStudio = "";
    var gameAnoLancamento:String? = null;
    var gameFinalizado = false;
    var gameOwnerUserId:Int? = null;
}