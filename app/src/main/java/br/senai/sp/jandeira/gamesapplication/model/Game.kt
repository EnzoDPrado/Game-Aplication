package br.senai.sp.jandeira.gamesapplication.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tbl_game")
data class Game (


    var gameFoto:ByteArray?,
    var gameTitulo: String,
    var gameDescricao: String,
    var gameStudio: String,
    var gameAnoLancamento:String?,
    var gameFinalizado: Boolean,
    var gameOwnerUserId:Int?,
){
    @PrimaryKey(autoGenerate = true)
    var gameId = 0;
}
