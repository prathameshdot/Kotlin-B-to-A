fun main() {
    println("=== Kotlin Loops & Ranges Demo ===")

    // -----------------------------------------------
    // Section 1: Range Types
    // -----------------------------------------------
    println("\n--- Range Types ---")
    val inclusive = 1..5          // 1, 2, 3, 4, 5
    val exclusive = 1 until 5    // 1, 2, 3, 4
    val descending = 5 downTo 1  // 5, 4, 3, 2, 1
    val stepped = 1..20 step 3   // 1, 4, 7, 10, 13, 16, 19
    val charRange = 'a'..'f'     // a, b, c, d, e, f

    println("1..5:          ${inclusive.toList()}")
    println("1 until 5:     ${exclusive.toList()}")
    println("5 downTo 1:    ${descending.toList()}")
    println("1..20 step 3:  ${stepped.toList()}")
    println("'a'..'f':      ${charRange.toList()}")

    // Range properties
    println("\nRange properties (1..10 step 2):")
    val r = 1..10 step 2
    println("  first=${r.first}, last=${r.last}, step=${r.step}")

    // -----------------------------------------------
    // Section 2: for Loop with Ranges
    // -----------------------------------------------
    println("\n--- for Loop with Ranges ---")
    print("1..5: ")
    for (i in 1..5) print("$i ")
    println()

    print("1 until 5: ")
    for (i in 1 until 5) print("$i ")
    println()

    print("10 downTo 1 step 2: ")
    for (i in 10 downTo 1 step 2) print("$i ")
    println()

    print("Chars 'A'..'F': ")
    for (c in 'A'..'F') print("$c ")
    println()

    // -----------------------------------------------
    // Section 3: for with Collections
    // -----------------------------------------------
    println("\n--- for with Collections ---")
    val fruits = listOf("Apple", "Banana", "Cherry", "Date")

    // Direct iteration
    print("Fruits: ")
    for (fruit in fruits) print("$fruit ")
    println()

    // With index using withIndex()
    println("With index:")
    for ((index, fruit) in fruits.withIndex()) {
        println("  [$index] $fruit")
    }

    // Using indices property
    print("Using indices: ")
    for (i in fruits.indices) {
        print("${fruits[i]} ")
    }
    println()

    // Map iteration
    println("Map iteration:")
    val capitals = mapOf("India" to "New Delhi", "Japan" to "Tokyo", "France" to "Paris")
    for ((country, capital) in capitals) {
        println("  $country -> $capital")
    }

    // String iteration
    print("String chars: ")
    for (ch in "Kotlin") print("$ch ")
    println()

    // -----------------------------------------------
    // Section 4: while Loop
    // -----------------------------------------------
    println("\n--- while Loop ---")
    var count = 5
    print("Countdown: ")
    while (count > 0) {
        print("$count ")
        count--
    }
    println("Go!")

    // Sum of digits
    var number = 12345
    var digitSum = 0
    while (number > 0) {
        digitSum += number % 10
        number /= 10
    }
    println("Sum of digits of 12345: $digitSum")  // 15

    // -----------------------------------------------
    // Section 5: do-while Loop
    // -----------------------------------------------
    println("\n--- do-while Loop ---")
    var x = 0
    do {
        println("  do-while: x = $x")
        x++
    } while (x < 3)

    // Executes at least once even if condition is false
    var y = 10
    do {
        println("  Executes once: y = $y")
        y++
    } while (y < 5)

    // -----------------------------------------------
    // Section 6: break and continue
    // -----------------------------------------------
    println("\n--- break ---")
    print("Break at 5: ")
    for (i in 1..10) {
        if (i == 5) break
        print("$i ")
    }
    println()

    println("\n--- continue ---")
    print("Skip even: ")
    for (i in 1..10) {
        if (i % 2 == 0) continue
        print("$i ")
    }
    println()

    // -----------------------------------------------
    // Section 7: Labeled break and continue
    // -----------------------------------------------
    println("\n--- Labeled break ---")
    print("break@outer at (2,3): ")
    outer@ for (i in 1..5) {
        for (j in 1..5) {
            if (i == 2 && j == 3) break@outer
            print("($i,$j) ")
        }
    }
    println()

    println("\n--- Labeled continue ---")
    print("continue@outer at j==2: ")
    outer@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) continue@outer
            print("($i,$j) ")
        }
    }
    println()

    // -----------------------------------------------
    // Section 8: repeat() Function
    // -----------------------------------------------
    println("\n--- repeat() ---")
    repeat(5) { print("* ") }
    println()

    repeat(3) { index ->
        println("  Attempt #${index + 1}")
    }

    // -----------------------------------------------
    // Section 9: Nested Loops — Multiplication Table
    // -----------------------------------------------
    println("\n--- Multiplication Table (1-5) ---")
    for (i in 1..5) {
        for (j in 1..5) {
            print("${(i * j).toString().padStart(4)}")
        }
        println()
    }

    // -----------------------------------------------
    // Section 10: Patterns
    // -----------------------------------------------
    println("\n--- Right Triangle Pattern ---")
    val size = 5
    for (i in 1..size) {
        repeat(i) { print("* ") }
        println()
    }

    println("\n--- Number Pyramid ---")
    for (i in 1..size) {
        // Leading spaces
        repeat(size - i) { print(" ") }
        // Numbers
        for (j in 1..i) print("$j ")
        println()
    }

    println("\n--- Diamond Pattern ---")
    val n = 5
    // Upper half
    for (i in 1..n) {
        repeat(n - i) { print(" ") }
        repeat(2 * i - 1) { print("*") }
        println()
    }
    // Lower half
    for (i in n - 1 downTo 1) {
        repeat(n - i) { print(" ") }
        repeat(2 * i - 1) { print("*") }
        println()
    }

    // -----------------------------------------------
    // Section 11: Practical Examples
    // -----------------------------------------------
    println("\n--- Prime Numbers (1-50) ---")
    print("Primes: ")
    for (num in 2..50) {
        var isPrime = true
        for (div in 2..Math.sqrt(num.toDouble()).toInt()) {
            if (num % div == 0) {
                isPrime = false
                break
            }
        }
        if (isPrime) print("$num ")
    }
    println()

    println("\n--- Fibonacci (first 10) ---")
    var fib1 = 0
    var fib2 = 1
    print("Fibonacci: $fib1 $fib2 ")
    repeat(8) {
        val next = fib1 + fib2
        print("$next ")
        fib1 = fib2
        fib2 = next
    }
    println()

    println("\n--- Reverse a Number ---")
    var original = 123456
    var reversed = 0
    var temp = original
    while (temp > 0) {
        reversed = reversed * 10 + temp % 10
        temp /= 10
    }
    println("Original: $original -> Reversed: $reversed")

    // -----------------------------------------------
    // Section 12: Functional Loop Alternatives
    // -----------------------------------------------
    println("\n--- Functional Alternatives ---")
    print("forEach: ")
    (1..5).forEach { print("$it ") }
    println()

    print("forEachIndexed: ")
    listOf("a", "b", "c").forEachIndexed { i, v -> print("$i:$v ") }
    println()

    println("Sum 1..100: ${(1..100).sum()}")
    println("Even count 1..20: ${(1..20).count { it % 2 == 0 }}")

    println("\n=== Program Complete ===")
}
