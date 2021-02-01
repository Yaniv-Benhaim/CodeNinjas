package tech.gamedev.codeninjas.other

import tech.gamedev.codeninjas.data.models.InterViewQuestion

object InterViewQuestions {

    fun getKotlinInterViewQuestions() : ArrayList<InterViewQuestion> {
        return arrayListOf(
            InterViewQuestion(
                "List Some Features of Kotlin that are Absent in Java",
                "Sometimes Kotlin interview questions are designed in a way that helps companies understand the potential of future employees. Below, we’re listing some functionalities of Kotlin that are simply unavailable in the Java programming language./1. Null Safety – a flagship feature of Kotlin/2. Co-Routines – enables asynchronous programming/3. Operator Overloading – a key feature missing in Java/4. Smart Casts – allows casting inferences/5.Companion Object – another useful functionality",
                false,
                "",
            ),
            InterViewQuestion(
                "Explain the Different Constructors in Kotlin",
                "Kotlin offers two different constructors for initializing class attributes. It varies from Java in this regard since the latter only provides a single constructor. These two constructors are known as primary constructors and secondary constructors in Kotlin. During many Kotlin interview questions, job seekers are asked to point out the differences between these two./1. Primary Constructor – resides in the class declaration header/2. Secondary Constructor – declared inside Kotlin class body and may have multiple instances ",
                false,
                "",
            ),
            InterViewQuestion(
                "Is It Possible to Execute Kotlin Code without JVM?",
                "As we’ve mentioned many times already, Kotlin compiles into bytecode and runs on top of the Java Virtual Machine(JVM). However, it’s also possible to compile Kotlin into native machine code and thus execute successfully without requiring any JVM at all.\n" +
                        "\n" +
                        "Developers can use the Kotlin Native tool for doing this effortlessly. It’s an effective LLVM backend that allows us to create standalone executables. It exposes some additional functionality as well. Consult their official documentation for more information.",
                false,
                "",
            ),
            InterViewQuestion(
                "How do Ranges Work in Kotlin?",
                "Ranges allow our programs to seamlessly iterate over a list or progression. It’s one of the many iterators available in Kotlin and enhances the readability of your program. The below code snippets demonstrate some basic functions of Kotlin ranges.",
                true,
                "",
            ),


        )
    }
}