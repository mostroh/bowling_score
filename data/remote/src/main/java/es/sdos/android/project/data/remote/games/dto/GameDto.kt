package es.sdos.android.project.data.remote.games.dto

import java.util.Date

data class GameDto(
    val id: Long,
    val date: Date,
    val shoots: String
)