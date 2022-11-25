package br.senai.sp.jandeira.gamesapplication.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserGames(
    @Embedded var user:User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "gameOwnerUserId"
    )
    var games: List<Game>
)