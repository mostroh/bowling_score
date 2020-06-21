package es.sdos.android.project.data.local.games.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity
data class GameDbo(
    @PrimaryKey val id: Long?,
    val date: Date,
    val rounds: List<RoundDbo>,
    val totalScore: Int,
    val finished: Boolean
)