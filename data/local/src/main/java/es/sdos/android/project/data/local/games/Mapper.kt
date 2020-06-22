package es.sdos.android.project.data.local.games

import es.sdos.android.project.data.local.games.dbo.GameDbo
import es.sdos.android.project.data.local.games.dbo.RoundDbo
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.RoundBo
import java.util.Date

fun GameDbo.toBo() = GameBo(
    id,
    date,
    rounds.map { it.toBo() },
    totalScore,
    finished
)

fun GameBo.toDbo() = GameDbo(
    id,
    date,
    rounds.map{ it.toDbo()},
    totalScore,
    finished
)

fun RoundDbo.toBo() = RoundBo(
    id,
    gameId,
    roundNum,
    firstShot,
    secondShot,
    thirdShot,
    score
)

fun RoundBo.toDbo() = RoundDbo(
    gameId,
    roundNum,
    firstShot,
    secondShot,
    thirdShot,
    score
)
