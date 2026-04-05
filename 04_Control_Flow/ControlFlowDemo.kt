fun main() {
    println("=== Control Flow Demo ===\n")

    // ─── if as Expression ───────────────────────────────
    println("--- if as Expression ---")
    val a = 15
    val b = 23

    // if returning a value (replaces ternary operator)
    val max = if (a > b) a else b
    println("max($a, $b) = $max")

    val abs = if (a - b >= 0) a - b else b - a
    println("abs($a - $b) = $abs")

    // if with blocks
    val comparison = if (a > b) {
        println("  $a is greater")
        "$a > $b"
    } else if (a < b) {
        println("  $b is greater")
        "$b > $a"
    } else {
        "equal"
    }
    println("Comparison: $comparison")

    // ─── if-else-if Grade Calculator ────────────────────
    println("\n--- if-else-if Chain ---")
    val scores = listOf(95, 82, 71, 55, 43)
    for (score in scores) {
        val grade = if (score >= 90) "A"
                    else if (score >= 80) "B"
                    else if (score >= 70) "C"
                    else if (score >= 60) "D"
                    else "F"
        println("Score $score → Grade $grade")
    }

    // ─── when with Simple Values ────────────────────────
    println("\n--- when: Day of Week ---")
    for (day in 1..8) {
        val dayName = when (day) {
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            7 -> "Sunday"
            else -> "Invalid day ($day)"
        }
        println("Day $day = $dayName")
    }

    // ─── when with Multiple Values per Branch ───────────
    println("\n--- when: Multiple Values ---")
    for (day in 1..7) {
        val type = when (day) {
            1, 2, 3, 4, 5 -> "Weekday"
            6, 7 -> "Weekend"
            else -> "Invalid"
        }
        println("Day $day is a $type")
    }

    // ─── when with Ranges ───────────────────────────────
    println("\n--- when: Ranges ---")
    val testScores = listOf(100, 88, 73, 65, 42, -5, 105)
    for (s in testScores) {
        val result = when (s) {
            in 90..100 -> "Excellent (A)"
            in 80 until 90 -> "Good (B)"
            in 70 until 80 -> "Average (C)"
            in 60 until 70 -> "Below Average (D)"
            in 0 until 60 -> "Fail (F)"
            else -> "Invalid score"
        }
        println("Score $s → $result")
    }

    // Character classification with ranges
    println("\n--- when: Character Ranges ---")
    val chars = listOf('A', 'g', '5', '@', 'Z')
    for (ch in chars) {
        when (ch) {
            in 'A'..'Z' -> println("'$ch' → Uppercase")
            in 'a'..'z' -> println("'$ch' → Lowercase")
            in '0'..'9' -> println("'$ch' → Digit")
            else -> println("'$ch' → Special")
        }
    }

    // ─── when with Type Checks (is) ─────────────────────
    println("\n--- when: Type Checks ---")
    val items: List<Any> = listOf(42, "Kotlin", 3.14, true, listOf(1, 2), 'K', null as Any?)
    for (item in items) {
        val desc = when (item) {
            null -> "null value"
            is Int -> "Int: $item × 2 = ${item * 2}"
            is String -> "String: \"$item\" (${item.length} chars)"
            is Double -> "Double: ${"%.2f".format(item)}"
            is Boolean -> "Boolean: $item → negated: ${!item}"
            is List<*> -> "List: size=${item.size}"
            is Char -> "Char: '$item' (code: ${item.code})"
            else -> "Unknown: $item"
        }
        println("  $desc")
    }

    // ─── when without Argument ──────────────────────────
    println("\n--- when: Without Argument ---")
    val temperatures = listOf(-10, 0, 15, 22, 32, 45)
    for (temp in temperatures) {
        val category = when {
            temp < 0 -> "Freezing"
            temp < 10 -> "Very Cold"
            temp < 20 -> "Cold"
            temp < 25 -> "Comfortable"
            temp < 35 -> "Warm"
            else -> "Hot"
        }
        println("${temp}°C → $category")
    }

    // ─── when as Validation ─────────────────────────────
    println("\n--- when: Complex Conditions ---")
    val password = "Kotlin2024!"
    val strength = when {
        password.length < 6 -> "Weak"
        password.length < 8 -> "Fair"
        password.any { it.isUpperCase() } &&
            password.any { it.isDigit() } &&
            password.any { !it.isLetterOrDigit() } -> "Strong"
        password.any { it.isUpperCase() } &&
            password.any { it.isDigit() } -> "Good"
        else -> "Fair"
    }
    println("Password \"$password\" strength: $strength")

    // ─── when with Block Branches ───────────────────────
    println("\n--- when: Block Branches ---")
    val httpCode = 404
    val message = when (httpCode) {
        200 -> {
            println("  Request succeeded")
            "OK"
        }
        301 -> {
            println("  Resource moved")
            "Moved Permanently"
        }
        404 -> {
            println("  Resource not found")
            "Not Found"
        }
        500 -> {
            println("  Server error")
            "Internal Server Error"
        }
        else -> {
            println("  Unknown status")
            "Status: $httpCode"
        }
    }
    println("HTTP $httpCode: $message")

    // ─── Exhaustive when with Enum ──────────────────────
    println("\n--- Exhaustive when: Enum ---")
    for (dir in Direction.entries) {
        val arrow = when (dir) {
            Direction.NORTH -> "↑"
            Direction.SOUTH -> "↓"
            Direction.EAST -> "→"
            Direction.WEST -> "←"
            // No else needed!
        }
        println("${dir.name} → $arrow")
    }

    // ─── Exhaustive when with Sealed Class ──────────────
    println("\n--- Exhaustive when: Sealed Class ---")
    val results = listOf(
        Result.Success("User data loaded"),
        Result.Error(404, "Not Found"),
        Result.Loading
    )
    for (result in results) {
        val display = when (result) {
            is Result.Success -> "✓ ${result.data}"
            is Result.Error -> "✗ Error ${result.code}: ${result.message}"
            is Result.Loading -> "⟳ Loading..."
            // No else needed!
        }
        println("  $display")
    }

    // ─── when Capturing Subject ─────────────────────────
    println("\n--- when: Capturing Subject ---")
    when (val length = "Kotlin".length) {
        0 -> println("Empty string")
        in 1..5 -> println("Short string ($length chars)")
        in 6..10 -> println("Medium string ($length chars)")
        else -> println("Long string ($length chars)")
    }

    // ─── FizzBuzz with when ─────────────────────────────
    println("\n--- FizzBuzz (1-20) ---")
    for (i in 1..20) {
        val output = when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3 == 0 -> "Fizz"
            i % 5 == 0 -> "Buzz"
            else -> "$i"
        }
        print("$output ")
    }
    println()

    // ─── Nested if vs when ──────────────────────────────
    println("\n--- BMI Calculator ---")
    val weight = 75.0  // kg
    val height = 1.75  // meters
    val bmi = weight / (height * height)
    val category = when {
        bmi < 18.5 -> "Underweight"
        bmi < 25.0 -> "Normal"
        bmi < 30.0 -> "Overweight"
        else -> "Obese"
    }
    println("BMI: ${"%.1f".format(bmi)} → $category")

    println("\n=== Demo Complete ===")
}

// Enum for exhaustive when demo
enum class Direction { NORTH, SOUTH, EAST, WEST }

// Sealed class for exhaustive when demo
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val code: Int, val message: String) : Result()
    data object Loading : Result()
}
