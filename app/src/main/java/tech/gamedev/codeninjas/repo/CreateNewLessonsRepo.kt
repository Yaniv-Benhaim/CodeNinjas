package tech.gamedev.codeninjas.repo

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import tech.gamedev.codeninjas.data.models.LessonAndQuestion
import tech.gamedev.codeninjas.data.models.LessonCollectionLink
import tech.gamedev.codeninjas.data.models.lessonextensions.Lesson
import tech.gamedev.codeninjas.data.models.lessonextensions.Question

class CreateNewLessonsRepo {
    private val fireStoreCollectionRef = FirebaseFirestore.getInstance()
    private val lessonRef = fireStoreCollectionRef.collection("lessons").document("java")

    fun addNewLesson() = CoroutineScope(Dispatchers.IO).launch {



        val lesson1 = Lesson(
                1,
                "Introduction to Java",
                false,
                "Java is a high level, modern programming language designed in the early 1990s by Sun Microsystems, and currently owned by Oracle. Java is Platform Independent, which means that you only need to write the program once to be able to run it on a number of different platforms! Java is portable, robust, and dynamic, with the ability to fit the needs of virtually any type of application.",
                false,
                "",
                false,
                10
        )

        val question1 = Question(
                2,
                "To distribute your application to different platforms, how many Java versions do you need to create?",
                true,
                "Just one version",
                "One for each platform",
                "Just one version",
                "Depends on the version of Java",
                "Two versions",
                false,
                ""
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

        val lesson2 = Lesson(
                3,
                "Java",
                false,
                "More than 3 billion devices run Java. Java is used to develop apps for Google's Android OS, various Desktop Applications, such as media players, antivirus programs, Web Applications, Enterprise Applications (i.e. banking), and many more!",
                false,
                "",
                false,
                10
        )

        val question2 = Question(
                4,
                "Which of the following statements is true?",
                true,
                "Java has a huge developer community",
                "Java has a huge developer community",
                "Java was developed for handling space applications",
                "Java is used only in web and mobile applications",
                "Java like C is a low level programming language",
                false,
                ""
        )

        val module = LessonCollectionLink(
                "module_one",
                "Java Introduction",
                "1"
        )



        lessonRef.collection("modules").document("module_one").collection("lessons").add(lesson1)
        lessonRef.collection("modules").document("module_one").collection("lessons").add(question1)
        lessonRef.collection("modules").document("module_one").collection("lessons").add(lesson2)
        lessonRef.collection("modules").document("module_one").collection("lessons").add(question2)

        //lessons -> java doc -> modules col -> module1, module2, module2









        val newLesson3 = LessonAndQuestion(
            5,
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


        lessonRef.collection("modules").document("module_two").collection("lessons").add(lesson3)
        lessonRef.collection("modules").document("module_two").collection("lessons").add(question3)
        lessonRef.collection("modules").document("module_two").collection("lessons").add(lesson4)
        lessonRef.collection("modules").document("module_two").collection("lessons").add(question4)
        lessonRef.collection("modules").document("module_two").collection("lessons").add(lesson5)
        lessonRef.collection("modules").document("module_two").collection("lessons").add(question5)

        val module2 = LessonCollectionLink(
                "module_two",
                "Create your first program",
                "2"
        )

        lessonRef.collection("modules").document("module_two").set(module2)


    }

}