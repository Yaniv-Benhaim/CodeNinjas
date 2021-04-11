package tech.gamedev.codeninjas.data.quickvids

import tech.gamedev.codeninjas.other.Constants.ARRAYS
import tech.gamedev.codeninjas.other.Constants.CASTING
import tech.gamedev.codeninjas.other.Constants.CLASSES
import tech.gamedev.codeninjas.other.Constants.FUNCTIONS
import tech.gamedev.codeninjas.other.Constants.IF
import tech.gamedev.codeninjas.other.Constants.LOOPS
import tech.gamedev.codeninjas.other.Constants.OBJECTS
import tech.gamedev.codeninjas.other.Constants.THREADS
import tech.gamedev.codeninjas.other.Constants.TRY
import tech.gamedev.codeninjas.other.Constants.VARIABLES

object QuickVids {

    fun getJavaVids(str: String): String {
        return when (str) {
            FUNCTIONS -> "SkVDfaHQwRU"
            LOOPS -> "3jMaKlNBjug"
            VARIABLES -> "W1GEMdHnCQE"
            IF -> "yvWnj_HfG6s"
            CLASSES -> "vjjjGkXpX_I"
            OBJECTS -> "j0lBrYSlYaU"
            CASTING -> "H0LNjF9PSeM"
            THREADS -> "eQk5AWcTS8w"
            ARRAYS -> "xzjZy-dHHLw"
            TRY -> "ceGnVDrMy1A"

            else -> ""
        }
    }

}