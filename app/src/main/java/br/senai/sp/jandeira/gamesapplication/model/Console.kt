package br.senai.sp.jandeira.gamesapplication.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_console")
class Console {

    @PrimaryKey(autoGenerate = true ) var id = 0
    var codigo = 0;
    var nome = "";
    var fabricante = "";
    var descricao = "";
    var foto:Bitmap? = null;
    var anoLancamento = 0;
}