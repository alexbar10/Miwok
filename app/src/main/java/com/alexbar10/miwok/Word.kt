package com.alexbar10.miwok

class Word (val englishVersion: String, val miwokVersion: String = "", val imageResourceId: Int? = null, val soundResourceId: Int? = null) {

    override fun toString(): String {
        return "Word {" +
        "\nEnglishVersion = $englishVersion" +
        "\nMiwokVersion = $miwokVersion" +
        "\nImageResourceId = $imageResourceId" +
        "\nSoundResourceId = $soundResourceId\n}"
    }
}
