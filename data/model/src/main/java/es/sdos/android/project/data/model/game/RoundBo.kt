package es.sdos.android.project.data.model.game

data class RoundBo (
    val id: Long?,
    val gameId: Long,
    val roundNum: Int,
    val firstShot: Int,
    val secondShot: Int?,
    val thirdShot: Int?,
    val score: Int?
)
