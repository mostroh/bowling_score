package es.sdos.android.project.data.remote.games

import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.RoundBo
import es.sdos.android.project.data.model.game.addShot
import es.sdos.android.project.data.remote.games.dto.GameDto
import java.util.Date

fun GameDto.toBo() = GameBo(
    0,
    Date(),
    listOf(),
    0,
    true
)

private fun calculateRounds(gameId: Long, shots: List<Int>): List<RoundBo> {
    var result = listOf<RoundBo>()
    for (shot in shots) {
        result = result.addShot(gameId, shot)
    }
    return result
}
