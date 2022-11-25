package br.senai.sp.jandeira.gamesapplication.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_console")
class Console {

    @PrimaryKey(autoGenerate = true )
    var consoleId = 0

    var consoleCodigo = 0;
    var consoleNome = "";
    var consoleFabricante = "";
    var consoleDescricao = "";
    var consoleFoto:ByteArray? = null;

    var consoleAnoLancamento = 0;

}