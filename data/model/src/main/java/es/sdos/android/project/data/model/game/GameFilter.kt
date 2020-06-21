package es.sdos.android.project.data.model.game

sealed class GameFilter {
    object FINISHED: GameFilter()
    object NOT_FINISHED: GameFilter()
}