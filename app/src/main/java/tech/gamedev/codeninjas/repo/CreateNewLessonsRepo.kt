package tech.gamedev.codeninjas.repo

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.data.models.LessonAndQuestion

class CreateNewLessonsRepo {
    private val fireStoreCollectionRef = FirebaseFirestore.getInstance()
    private val lessonRef = fireStoreCollectionRef.collection("lessons").document("java")

    fun addNewLesson() = CoroutineScope(Dispatchers.IO).launch {

        val newLesson = LessonAndQuestion(
                1,
                "Introduction to Java",
                false,
                "Java is a high level, modern programming language designed in the early 1990s by Sun Microsystems, and currently owned by Oracle. Java is Platform Independent, which means that you only need to write the program once to be able to run it on a number of different platforms! Java is portable, robust, and dynamic, with the ability to fit the needs of virtually any type of application.",
                false,
                "",
                false,
                "To distribute your application to different platforms, how many Java versions do you need to create?",
                true,
                "Just one version",
                "One for each platform",
                "Just one version",
                "Depends on the version of Java",
                "Two versions",
                10
        )

        val newLesson2 = LessonAndQuestion(
            2,
            "Java",
            false,
            "More than 3 billion devices run Java. Java is used to develop apps for Google's Android OS, various Desktop Applications, such as media players, antivirus programs, Web Applications, Enterprise Applications (i.e. banking), and many more!",
            false,
            "",
            false,
            "Which of the following statements is true?",
            true,
            "Java has a huge developer community",
            "Java has a huge developer community",
            "Java was developed for handling space applications",
            "Java is used only in web and mobile applications",
            "Java like C is a low level programming language",
            10
        )





        lessonRef.collection("lessons").document("lesson1").collection("java_introduction").document("1").set(newLesson)
        lessonRef.collection("lessons").document("lesson1").collection("java_introduction").document("2").set(newLesson2)

        val newLesson3 = LessonAndQuestion(
            3,
            "Your first Java program",
            false,
            "Let's start by creating a simple program that prints (Hello CodeNinjas) to the screen. <img> ",
            false,
            "",
            false,
            "Which method is the starting point for all Java programs?",
            false,
            "main",
            "",
            "",
            "",
            "",
            10,
            "As a summary:_n- Every program in Java must have a class._n- Every Java program starts from the main method"
        )

        val newLesson4 = LessonAndQuestion(
            4,
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

        lessonRef.collection("lessons").document("lesson2").collection("a_hello_world_program").document("3").set(newLesson3)
        lessonRef.collection("lessons").document("lesson2").collection("a_hello_world_program").document("4").set(newLesson4)
        lessonRef.collection("lessons").document("lesson2").collection("a_hello_world_program").document("5").set(newLesson5)
    }
}