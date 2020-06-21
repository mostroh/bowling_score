package es.sdos.android.project.data.model.game

import java.util.Date

data class GameBo (
    val id: Long?,
    val date: Date,
    val rounds: List<RoundBo>,
    val totalScore: Int,
    val finished: Boolean
)