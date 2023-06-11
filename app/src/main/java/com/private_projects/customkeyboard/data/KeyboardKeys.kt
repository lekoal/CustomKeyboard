package com.private_projects.customkeyboard.data

sealed class KeyboardKeys(
    val firstTextBlock: List<String>,
    val secondTextBlock: List<String>,
    val thirdTextBlock: List<String>
) {
    object Rus : KeyboardKeys(
        firstTextBlock = listOf(
            "й", "ц", "у", "к", "е", "н", "г", "ш", "щ", "з", "х"
        ),
        secondTextBlock = listOf(
            "ф", "ы", "в", "а", "п", "р", "о", "л", "д", "ж", "э"
        ),
        thirdTextBlock = listOf(
            "я", "ч", "с", "м", "и", "т", "ь", "б", "ю"
        )
    )
    object Eng : KeyboardKeys(
        firstTextBlock = listOf(
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p"
        ),
        secondTextBlock = listOf(
           "a", "s", "d", "f", "g", "h", "j", "k", "l"
        ),
        thirdTextBlock = listOf(
            "z", "x", "c", "v", "b", "n", "m"
        )
    )
}