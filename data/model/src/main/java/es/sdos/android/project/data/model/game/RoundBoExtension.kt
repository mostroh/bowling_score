package es.sdos.android.project.data.model.game

fun List<RoundBo>.addShot(gameId: Long, shotScore: Int): List<RoundBo> {
    val result = this.toMutableList()
    if (result.isEmpty()) {
        result.add(RoundBo(null, gameId, 1, shotScore, null, null, null))
    } else {
        val lastRound = result.last()
        if (lastRound.isComplete()) {
            result.add(RoundBo(null, gameId, lastRound.roundNum + 1, shotScore, null, null, null))
        } else {
            result[result.size - 1] = if (lastRound.secondShot == null) {
                lastRound.copy(secondShot = shotScore)
            } else {
                lastRound.copy(thirdShot = shotScore)
            }
        }

        updateScores(result)
    }

    return result
}

private fun RoundBo.isStrike() = firstShot == 10

private fun RoundBo.isSpare() = (!isStrike() && secondShot != null && firstShot + secondShot == 10)

/**
 * Indica que la ronda esta finalizada, no quedan lanzamientos pendientes
 */
fun RoundBo.isComplete(): Boolean {
    return if (roundNum == 10) {
        if (firstShot == 10) {
            return secondShot!=null && thirdShot !=null
        } else if (secondShot!= null && firstShot + secondShot == 10){
            return thirdShot != null
        } else {
            false
        }
    } else {
        secondShot != null
    }
}

private fun updateScores(result: MutableList<RoundBo>) {
    for (i in 0 until result.size) {
        val roundBo = result[i]
        if (!roundBo.isComplete() && roundBo.roundNum!=10) {
            break
        }
        val previousScore = result.getOrNull(i - 1)?.score ?: 0

        val roundScore = roundBo.firstShot + (roundBo.secondShot ?: 0) + (roundBo.thirdShot ?: 0)
        val extraScore = if (roundBo.roundNum != 10 && roundBo.isStrike()) {
            getNextShotsScore(result, i + 1, 2)
        } else if (roundBo.roundNum != 10 && roundBo.isSpare()) {
            getNextShotsScore(result, i + 1, 1)
        } else {
            0
        }

        if (extraScore != null) {
            result[i] = roundBo.copy(score = previousScore + roundScore + extraScore)
        }
    }
}

/**
 * Obtiene la puntuación acumulada de los 'x' siguientes lanzamientos a partir de 'startIndex'
 */
private fun getNextShotsScore(roundList: List<RoundBo>, startIndex: Int, numberOfShots: Int): Int? {
    return if (startIndex <= roundList.size){
        null
    } else {
        val nextRound = roundList[startIndex]
        if (numberOfShots >= 2) {
            nextRound.firstShot + (nextRound.secondShot ?: 0)
        } else {
            nextRound.firstShot
        }
    }
}