package utils

object PreferencesFactory {

    private var instance: Preferences? = null

    fun getInstance(): Preferences {
        return instance ?: throw IllegalStateException("Preferences not initialized")
    }

    fun setInstance(preferences: Preferences) {
        instance = preferences
    }

}