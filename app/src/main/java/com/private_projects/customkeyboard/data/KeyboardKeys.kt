package com.private_projects.customkeyboard.data

sealed class KeyboardKeys( //Класс значений наборов букв
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

    object RusCaps : KeyboardKeys(
        firstTextBlock = listOf(
            "Й", "Ц", "У", "К", "Е", "Н", "Г", "Ш", "Щ", "З", "Х"
        ),
        secondTextBlock = listOf(
            "Ф", "Ы", "В", "А", "П", "Р", "О", "Л", "Д", "Ж", "Э"
        ),
        thirdTextBlock = listOf(
            "Я", "Ч", "С", "М", "И", "Т", "Ь", "Б", "Ю"
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

    object EngCaps : KeyboardKeys(
        firstTextBlock = listOf(
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"
        ),
        secondTextBlock = listOf(
            "A", "S", "D", "F", "G", "H", "J", "K", "L"
        ),
        thirdTextBlock = listOf(
            "Z", "X", "C", "V", "B", "N", "M"
        )
    )
}