package br.senai.sp.jandeira.gamesapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandeira.gamesapplication.dao.ConsoleDAO
import br.senai.sp.jandeira.gamesapplication.dao.GameDAO
import br.senai.sp.jandeira.gamesapplication.dao.UserDAO
import br.senai.sp.jandeira.gamesapplication.model.Console
import br.senai.sp.jandeira.gamesapplication.model.Game
import br.senai.sp.jandeira.gamesapplication.model.User
import br.senai.sp.jandeira.gamesapplication.repository.ConsoleRepository


@Database(entities = [User::class, Console::class, Game::class], version = 1)
abstract class GamesBd : RoomDatabase(){
    abstract fun userDAO(): UserDAO
    abstract fun consoleDAO(): ConsoleDAO
    abstract fun GameDAO(): GameDAO

    companion object{
        private lateinit var instance : GamesBd
        private fun populateConsoles(context: Context){
            val console1 = Console()
            console1.consoleCodigo = 123;
            console1.consoleNome = "Playstation 5";
            console1.consoleFabricante = "SONY";
            console1.consoleDescricao = "O PS5 Edição Digital oferece novas possibilidades de jogabilidade que você nunca imaginou. Uma versão totalmente digital do console PS5 sem unidade de disco. Você pode baixar jogos digitais para PS5 e PS4 pela PlayStation Store. Inicie sua sessão na conta da PlayStation Network e vá à PlayStationStore para comprar e baixar jogos.*"
            console1.consoleFoto = null;
            console1.consoleAnoLancamento = 2020;

            val console2 = Console()
            console2.consoleCodigo = 456;
            console2.consoleNome= "Xbox Series X";
            console2.consoleFabricante = "Microsoft";
            console2.consoleDescricao = "Apresentamos o Xbox Series X, nosso console mais rápido e poderoso de todos os tempos. Jogue milhares de títulos de quatro gerações de consoles- todos os jogos têm melhor aparência e são melhor executados no Xbox Series X.";
            console2.consoleFoto = null;
            console2.consoleAnoLancamento = 2020;
            ConsoleRepository(context).save(console1);
            ConsoleRepository(context).save(console2);
        }

        fun getDatabase(context: Context) :GamesBd {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, GamesBd::class.java, "db_games").allowMainThreadQueries().build()
            }
            if(ConsoleRepository(context).getAll().size < 0 )
                populateConsoles(context);

            return instance
        }
    }
}