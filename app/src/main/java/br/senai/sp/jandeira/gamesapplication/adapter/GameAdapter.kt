package br.senai.sp.jandeira.gamesapplication.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandeira.gamesapplication.R
import br.senai.sp.jandeira.gamesapplication.model.Game
import java.io.ByteArrayInputStream

class GameRegisteredAdapter(context: Context) : RecyclerView.Adapter<GameRegisteredAdapter.GameRegisteredHolder>() {

    private var list = listOf<Game>(); // list of characters

    fun updateList(list: List<Game>) {
        this.list = list
        notifyDataSetChanged()
    }

    class GameRegisteredHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.textViewGameName)
        private val owner = view.findViewById<TextView>(R.id.textViewProdutorGame)
        private val description = view.findViewById<TextView>(R.id.textViewGameDescription)
        private val imgBanner = view.findViewById<ImageView>(R.id.imageViewGamePicture)


        fun getBitmapFromByteArray(imageByteArray: ByteArray?): Bitmap {
            val arrayInputStream = ByteArrayInputStream(imageByteArray)
            return BitmapFactory.decodeStream(arrayInputStream)
        }

        fun bind(data: Game) {
            name.text = data.gameTitulo
            owner.text = data.gameStudio
            description.text = data.gameDescricao
            imgBanner.setImageBitmap(getBitmapFromByteArray(data.gameFoto))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameRegisteredHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_layout, parent, false)
        return GameRegisteredHolder(view)
    }

    override fun onBindViewHolder(holder: GameRegisteredHolder, position: Int) {
        holder.bind(this.list[position])
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}
