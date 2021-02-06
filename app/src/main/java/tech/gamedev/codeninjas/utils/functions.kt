package tech.gamedev.codeninjas.utils


import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.data.models.BattleQuestion
import tech.gamedev.codeninjas.data.models.User
import tech.gamedev.codeninjas.other.Constants.ASK_QUESTIONS
import tech.gamedev.codeninjas.other.Constants.BATTLE_NINJAS
import tech.gamedev.codeninjas.other.Constants.CPLUSPLUS
import tech.gamedev.codeninjas.other.Constants.JAVA
import tech.gamedev.codeninjas.other.Constants.JAVASCRIPT
import tech.gamedev.codeninjas.other.Constants.KOTLIN
import tech.gamedev.codeninjas.other.Constants.RATE_US
import tech.gamedev.codeninjas.other.Constants.SWIFT
import tech.gamedev.codeninjas.other.Constants.WATCH_VIDEOS

val userRef = FirebaseFirestore.getInstance().collection("users")
val battleQuestionsRef = FirebaseFirestore.getInstance().collection("battle_questions")

fun getWeapons() = arrayListOf(KOTLIN, JAVA, CPLUSPLUS, SWIFT, JAVASCRIPT)

fun getFeaturedItems() = arrayListOf(BATTLE_NINJAS, WATCH_VIDEOS, ASK_QUESTIONS, RATE_US)

fun setDummyUsers() = CoroutineScope(Dispatchers.IO).launch {
    userRef.document("dummy3").set(
            User(
                 "C0deB4se",
                 "",
                 "Apprentice",
                    0,
                    23,
                    18,
                    true,
                    ""

            )
    )

    userRef.document("dummy4").set(
            User(
                    "PytMaster73",
                    "",
                    "Apprentice",
                    0,
                    49,
                    26,
                    true,
                    ""

            )
    )

    userRef.document("dummy5").set(
            User(
                    "AhmedB",
                    "",
                    "Apprentice",
                    0,
                    11,
                    16,
                    true,
                    ""

            )
    )

    userRef.document("dummy6").set(
            User(
                    "BBBrian13",
                    "",
                    "Apprentice",
                    0,
                    19,
                    27,
                    true,
                    ""

            )
    )

    userRef.document("dummy7").set(
            User(
                    "DutchCode",
                    "",
                    "Apprentice",
                    0,
                    17,
                    15,
                    true,
                    ""

            )
    )

    userRef.document("dummy8").set(
            User(
                    "AndroidDev4Life",
                    "",
                    "Apprentice",
                    0,
                    13,
                    11,
                    true,
                    ""

            )
    )
}

fun setBattleQuestions() = CoroutineScope(Dispatchers.IO).launch {
    battleQuestionsRef.document("java").collection("java").add(
            BattleQuestion(
                    "What will be the output ?",
                    true,
                    "https://firebasestorage.googleapis.com/v0/b/codeninjas-b388a.appspot.com/o/questions%2Fjava_questions%2Fquestion1.png?alt=media&token=38c0b374-d133-416d-9158-0322997cde0c",
                    " ",
                    "Compile error",
                    "n",
                    " ",
                    "87",
                    true,
                    1

            )
    )

    battleQuestionsRef.document("java").collection("java").add(
            BattleQuestion(
                    "How many times will this loop run",
                    true,
                    "https://firebasestorage.googleapis.com/v0/b/codeninjas-b388a.appspot.com/o/questions%2Fjava_questions%2Fquestion2infinite.png?alt=media&token=77468b1f-7d35-433e-92e7-0bc875fdef3f",
                    "Infinite loop",
                    "1 time",
                    "2 times",
                    "3 times",
                    "Infinite loop",
                    true,
                    1

                    )
    )

    battleQuestionsRef.document("java").collection("java").add(
            BattleQuestion(
                    "What will be the output ?",
                    true,
                    "https://firebasestorage.googleapis.com/v0/b/codeninjas-b388a.appspot.com/o/questions%2Fjava_questions%2Fquestion3_418.png?alt=media&token=6ebaf1fe-16fd-4897-a682-fbfbde352719",
                    "418",
                    "java",
                    "j a v a",
                    "418",
                    "Error",
                    true,
                    1

                    )
    )

    battleQuestionsRef.document("java").collection("java").add(
            BattleQuestion(
                    "What will be the output ?",
                    true,
                    "https://firebasestorage.googleapis.com/v0/b/codeninjas-b388a.appspot.com/o/questions%2Fjava_questions%2Fquestion4_hello.png?alt=media&token=2c2614f3-34cf-4667-878d-751a42a0402c",
                    "Hello",
                    "HelloHello",
                    "Infinite loop",
                    "Error",
                    "Nothing",
                    true,
                    1

                    )
    )

    battleQuestionsRef.document("java").collection("java").add(
            BattleQuestion(
                    "What will be the output ?",
                    true,
                    "https://firebasestorage.googleapis.com/v0/b/codeninjas-b388a.appspot.com/o/questions%2Fjava_questions%2Fquestion5_Compiler%20Error.png?alt=media&token=0f1032bc-c4dc-4e43-a3dd-179f5e4b34f6",
                    "Compile error",
                    "20",
                    "ClassCastException",
                    "Compile error",
                    "Twenty",
                    true,
                    1

                    )
    )

    battleQuestionsRef.document("java").collection("java").add(
            BattleQuestion(
                    "What will be the output ?",
                    true,
                    "https://firebasestorage.googleapis.com/v0/b/codeninjas-b388a.appspot.com/o/questions%2Fjava_questions%2Fquestion6_634.png?alt=media&token=9cbc58fd-51af-4ce3-b712-0d438a4ca62a",
                    "634",
                    "634",
                    "0634",
                    "Integer can't start with 0",
                    "Runtime error",
                    true,
                    1

                    )
    )

    battleQuestionsRef.document("java").collection("java").add(
            BattleQuestion(
                    "What will be the output ?",
                    true,
                    "https://firebasestorage.googleapis.com/v0/b/codeninjas-b388a.appspot.com/o/questions%2Fjava_questions%2Fquestion7_-80.png?alt=media&token=5cf25e71-49b1-460a-b76c-d2b9355bf1c4",
                    "-80",
                    "10",
                    "110",
                    "-80",
                    "ArithmeticException",
                    true,
                    1

                    )
    )






}