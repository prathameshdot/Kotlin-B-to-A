// Compile-time constant (must be top-level or in object)
const val MAX_USERS = 100
const val APP_NAME = "KotlinDemo"

fun main() {
    println("=== Variables & Data Types Demo ===\n")

    // ─── val vs var ─────────────────────────────────────
    println("--- val vs var ---")
    val immutable = "Cannot change"
    var mutable = "Can change"
    mutable = "Changed!"
    println("val: $immutable")
    println("var: $mutable")

    // val list can be mutated internally
    val numbers = mutableListOf(1, 2, 3)
    numbers.add(4)
    println("val mutableList after add: $numbers")

    // ─── Type Inference ─────────────────────────────────
    println("\n--- Type Inference ---")
    val inferredInt = 42            // Int
    val inferredDouble = 3.14       // Double
    val inferredString = "Hello"    // String
    val inferredBoolean = true      // Boolean
    val inferredChar = 'K'          // Char
    val inferredLong = 10000000000  // Long (too big for Int)

    println("42 is ${inferredInt::class.simpleName}")
    println("3.14 is ${inferredDouble::class.simpleName}")
    println("\"Hello\" is ${inferredString::class.simpleName}")
    println("true is ${inferredBoolean::class.simpleName}")
    println("'K' is ${inferredChar::class.simpleName}")
    println("10000000000 is ${inferredLong::class.simpleName}")

    // ─── Explicit Types ─────────────────────────────────
    println("\n--- Explicit Types ---")
    val explicitInt: Int = 42
    val explicitLong: Long = 42L
    val explicitFloat: Float = 3.14f
    val explicitDouble: Double = 3.14
    println("Explicit Int: $explicitInt, Long: $explicitLong")
    println("Explicit Float: $explicitFloat, Double: $explicitDouble")

    // ─── All Numeric Types with Ranges ──────────────────
    println("\n--- Numeric Types & Ranges ---")
    println("Byte:   ${Byte.MIN_VALUE} to ${Byte.MAX_VALUE}")
    println("Short:  ${Short.MIN_VALUE} to ${Short.MAX_VALUE}")
    println("Int:    ${Int.MIN_VALUE} to ${Int.MAX_VALUE}")
    println("Long:   ${Long.MIN_VALUE} to ${Long.MAX_VALUE}")
    println("Float:  ${Float.MIN_VALUE} to ${Float.MAX_VALUE}")
    println("Double: ${Double.MIN_VALUE} to ${Double.MAX_VALUE}")

    val byte: Byte = 127
    val short: Short = 32767
    val int: Int = 2_147_483_647
    val long: Long = 9_223_372_036_854_775_807L
    val float: Float = 3.14159f
    val double: Double = 3.141592653589793
    println("byte=$byte, short=$short, int=$int")
    println("long=$long")
    println("float=$float, double=$double")

    // ─── Char and Boolean ───────────────────────────────
    println("\n--- Char & Boolean ---")
    val letter: Char = 'A'
    val digit: Char = '9'
    val unicode: Char = '\u0041'  // 'A'
    val newline: Char = '\n'
    println("letter='$letter', code=${letter.code}")
    println("digit='$digit', isDigit=${digit.isDigit()}")
    println("unicode='$unicode'")
    println("Char from code: ${65.toChar()}")  // 'A'

    val isKotlinAwesome: Boolean = true
    val isJavaBetter: Boolean = false
    println("Kotlin awesome? $isKotlinAwesome")
    println("AND: ${isKotlinAwesome && isJavaBetter}")
    println("OR:  ${isKotlinAwesome || isJavaBetter}")
    println("NOT: ${!isKotlinAwesome}")

    // ─── String Basics ──────────────────────────────────
    println("\n--- String Basics ---")
    val greeting = "Hello, Kotlin!"
    println("String: $greeting")
    println("Length: ${greeting.length}")
    println("Char at 0: ${greeting[0]}")
    println("Substring: ${greeting.substring(7)}")
    println("Uppercase: ${greeting.uppercase()}")
    println("Contains 'Kotlin': ${greeting.contains("Kotlin")}")

    // ─── Type Conversions ───────────────────────────────
    println("\n--- Type Conversions (No Implicit Widening!) ---")
    val intVal: Int = 42
    val toLong: Long = intVal.toLong()
    val toFloat: Float = intVal.toFloat()
    val toDouble: Double = intVal.toDouble()
    val toByte: Byte = intVal.toByte()
    val toShort: Short = intVal.toShort()
    val toChar: Char = intVal.toChar()
    val toStr: String = intVal.toString()
    println("Int $intVal -> Long: $toLong, Float: $toFloat, Double: $toDouble")
    println("Int $intVal -> Byte: $toByte, Short: $toShort, Char: '$toChar', String: \"$toStr\"")

    // Safe string to number
    val validNum = "123".toIntOrNull()
    val invalidNum = "abc".toIntOrNull()
    println("\"123\".toIntOrNull() = $validNum")
    println("\"abc\".toIntOrNull() = $invalidNum")

    // Truncation example
    val pi = 3.99
    println("3.99.toInt() = ${pi.toInt()} (truncated, not rounded)")

    // ─── Number Literal Formats ─────────────────────────
    println("\n--- Number Literal Formats ---")
    val decimal = 1_000_000           // Underscores for readability
    val hex = 0xFF                    // Hexadecimal
    val binary = 0b1010              // Binary
    val longLiteral = 100L           // Long
    val floatLiteral = 2.5f          // Float
    val scientific = 1.5e3           // Scientific notation
    println("Decimal: $decimal")
    println("Hex 0xFF: $hex")
    println("Binary 0b1010: $binary")
    println("Long 100L: $longLiteral")
    println("Float 2.5f: $floatLiteral")
    println("Scientific 1.5e3: $scientific")

    // ─── Any Type ───────────────────────────────────────
    println("\n--- Any Type ---")
    var anything: Any = "I'm a String"
    println("Any = $anything (${anything::class.simpleName})")
    anything = 42
    println("Any = $anything (${anything::class.simpleName})")
    anything = 3.14
    println("Any = $anything (${anything::class.simpleName})")
    anything = true
    println("Any = $anything (${anything::class.simpleName})")

    // ─── Unit Type ──────────────────────────────────────
    println("\n--- Unit Type ---")
    val unitValue: Unit = println("This returns Unit")
    println("Unit value: $unitValue")
    println("Unit type: ${unitValue::class.simpleName}")

    // ─── const val ──────────────────────────────────────
    println("\n--- const val ---")
    println("MAX_USERS = $MAX_USERS")
    println("APP_NAME = $APP_NAME")

    // ─── lateinit ───────────────────────────────────────
    println("\n--- lateinit ---")
    val profile = UserProfile()
    println("Is name initialized? ${profile.isNameInitialized()}")
    profile.setup("Alice")
    println("Is name initialized? ${profile.isNameInitialized()}")
    profile.printName()

    // ─── Type Checking with is and Smart Cast ───────────
    println("\n--- Type Checking & Smart Cast ---")
    checkType("Hello Kotlin")
    checkType(42)
    checkType(3.14)
    checkType(true)
    checkType(listOf(1, 2, 3))

    // ─── Nullable Types (brief intro) ───────────────────
    println("\n--- Nullable Types (intro) ---")
    var nullableName: String? = "Kotlin"
    println("Nullable: $nullableName, length: ${nullableName?.length}")
    nullableName = null
    println("Nullable: $nullableName, length: ${nullableName?.length}")
    println("With Elvis: ${nullableName?.length ?: "N/A"}")

    println("\n=== Demo Complete ===")
}

// Class demonstrating lateinit
class UserProfile {
    lateinit var name: String

    fun setup(userName: String) {
        name = userName
    }

    fun isNameInitialized(): Boolean = ::name.isInitialized

    fun printName() {
        if (::name.isInitialized) {
            println("User: $name")
        } else {
            println("Name not initialized!")
        }
    }
}

// Function demonstrating type checking and smart casting
fun checkType(value: Any) {
    when (value) {
        is String  -> println("String: '$value', length=${value.length}")
        is Int     -> println("Int: $value, doubled=${value * 2}")
        is Double  -> println("Double: $value, rounded=${value.toInt()}")
        is Boolean -> println("Boolean: $value, negated=${!value}")
        is List<*> -> println("List: $value, size=${value.size}")
        else       -> println("Unknown type: ${value::class.simpleName}")
    }
}
