package tech.gamedev.codeninjas.repo

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.data.models.lessons.LessonAndQuestion
import tech.gamedev.codeninjas.data.models.lessons.LessonCollectionLink
import tech.gamedev.codeninjas.data.models.lessonextensions.Lesson
import tech.gamedev.codeninjas.data.models.lessonextensions.Question

class CreateNewLessonsRepo {
    private val fireStoreCollectionRef = FirebaseFirestore.getInstance()
    private val lessonRef = fireStoreCollectionRef.collection("lessons").document("java")

    fun addNewLesson() = CoroutineScope(Dispatchers.IO).launch {




        val lesson3 = Lesson(
                5,
                "Your first Java program",
                false,
                "Let's start by creating a simple program that prints (Hello CodeNinjas) to the screen. <img> ",
                false,
                "",
                false,
                10,
                "As a summary:_n- Every program in Java must have a class._n- Every Java program starts from the main method"
        )

        val question3 = Question(
                6,
                "Which method is the starting point for all Java programs?",
                false,
                "main",
                "",
                "",
                "",
                "",
                false,
                "",

        )

        val newLesson4 = LessonAndQuestion(
            7,
            "The main method",
            false,
            "To run our program, the main method must be identical to this signature: <img> - public: anyone can access it_n- static: method can be run without creating an instance of the class containing the main method_n- void: method doesn't return any value_n- main: the name of the method<img>For example, the following code declares a method called test, which does not return anything and has no parameters.",
            false,
            "",
            false,
            "Fill in the blank to declare a method called hello()",
            false,
            "main",
            "",
            "",
            "",
            "",
            10,
            "As a summary:_n- The method's parameters are declared inside the parentheses that follow the name of the method. For main, it's an array of strings called args. We will use it in our next lesson, so don't worry if you don't understand it all now. ",
            "void",
            "()"
        )


        val lesson4 = Lesson(
                5,
                "Your first Java program",
                false,
                "Let's start by creating a simple program that prints (Hello CodeNinjas) to the screen. <img> ",
                false,
                "",
                false,
                10,
                "As a summary:_n- Every program in Java must have a class._n- Every Java program starts from the main method"
        )

        val question4 = Question(
                6,
                "Which method is the starting point for all Java programs?",
                false,
                "main",
                "",
                "",
                "",
                "",
                false,
                "")
        val newLesson5 = LessonAndQuestion(
            5,
            "System.out.println()",
            false,
            "Next is the body of the main method, enclosed in curly braces:<img>The println method prints a line of text to the screen. The System class and its out stream are used to access the println method.",
            false,
            "",
            false,
            "Which method prints text in a Java program",
            true,
            "System.out.println()",
            "out.writeText()",
            "System.out.println()",
            "System.printText()",
            "System.out()",
            10,
            "In classes, methods, and other flow-control structures code is always enclosed in curly braces { }.",
            "void",
            "()"
        )

        val lesson5 = Lesson(
                7,
                "System.out.println()",
                false,
                "Next is the body of the main method, enclosed in curly braces:<img>The println method prints a line of text to the screen. The System class and its out stream are used to access the println method.",
                false,
                "",
                false,
                10,
                "In classes, methods, and other flow-control structures code is always enclosed in curly braces { }."
        )

        val question5 = Question(
                8,
                "Which method prints text in a Java program",
                true,
                "System.out.println()",
                "out.writeText()",
                "System.out.println()",
                "System.printText()",
                "System.out()",
                false,
                "")


        lessonRef.collection("modules").document("module_three").collection("lessons").add(lesson3)
        lessonRef.collection("modules").document("module_three").collection("lessons").add(question3)
        lessonRef.collection("modules").document("module_three").collection("lessons").add(lesson4)
        lessonRef.collection("modules").document("module_three").collection("lessons").add(question4)
        lessonRef.collection("modules").document("module_three").collection("lessons").add(lesson5)
        lessonRef.collection("modules").document("module_three").collection("lessons").add(question5)

        val module2 = LessonCollectionLink(
                "module_three",
                "Create your first program",
                "2"
        )

        lessonRef.collection("modules").document("module_three").set(module2)


    }

}