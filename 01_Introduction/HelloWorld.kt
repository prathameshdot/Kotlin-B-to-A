fun main() {
    println("=== Kotlin Introduction ===")
    println("Hello, Kotlin!")

    // Print with/without newline
    print("Hello ")
    println("World")

    // String templates
    val name = "Kotlin"
    println("Welcome to $name programming!")
    println("2 + 3 = ${2 + 3}")

    // Multi-line strings
    val multiLine = """
        |Kotlin is:
        |  - Concise
        |  - Safe
        |  - Interoperable
        |  - Tool-friendly
    """.trimMargin()
    println(multiLine)

    // String template expressions
    val language = "Kotlin"
    val year = 2016
    println("$language was released in $year, that's ${2026 - year} years ago!")

    // Escaped vs raw strings
    println("\n--- Escaped String ---")
    println("Path: C:\\Users\\Kotlin\\Projects")
    println("Tab:\tIndented")

    println("\n--- Raw String ---")
    val raw = """
        No need to escape: C:\Users\Kotlin\Projects
        Keeps    extra    spaces
        "Quotes" work directly
    """.trimIndent()
    println(raw)

    // Using trimIndent vs trimMargin
    println("\n--- trimIndent Example ---")
    val indented = """
        Line 1
        Line 2
            Indented Line 3
    """.trimIndent()
    println(indented)

    println("\n--- trimMargin with custom prefix ---")
    val custom = """
        #First item
        #Second item
        #Third item
    """.trimMargin("#")
    println(custom)

    // Read input (commented for non-interactive)
    // print("Enter your name: ")
    // val input = readLine() ?: "Unknown"
    // println("Hello, $input!")

    // KDoc-style documentation example
    println("\n--- Function Call ---")
    println(greet("World"))
    println(greet("Kotlin Developer"))

    println("\n=== Program Complete ===")
}

/**
 * A simple greeting function demonstrating KDoc.
 * @param name The name to greet
 * @return A greeting string
 */
fun greet(name: String): String = "Hello, $name! Welcome to Kotlin."
