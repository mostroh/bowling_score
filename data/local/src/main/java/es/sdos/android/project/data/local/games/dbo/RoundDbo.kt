package es.sdos.android.project.data.local.games.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoundDbo(
    val gameId: Long,
    val roundNum: Int,
    val firstShot: Int,
    val secondShot: Int?,
    val thirdShot: Int?,
    val score: Int?
){
    @PrimaryKey (autoGenerate = true) var id: Long? = null
}