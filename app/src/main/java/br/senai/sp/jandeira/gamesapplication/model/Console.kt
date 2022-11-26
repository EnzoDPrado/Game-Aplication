package br.senai.sp.jandeira.gamesapplication.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_console")
data class Console (
    var consoleCodigo: Int,
    var consoleNome: String,
    var consoleFabricante: String,
    var consoleDescricao: String,
    var consoleFoto:ByteArray?,

    var consoleAnoLancamento: Int,

    ){
    @PrimaryKey(autoGenerate = true )
    var consoleId = 0
}
