fun main() {
    println("=== Kotlin Operators Demo ===")

    // -----------------------------------------------
    // Section 1: Arithmetic Operators
    // -----------------------------------------------
    println("\n--- Arithmetic Operators ---")
    val a = 17
    val b = 5
    println("a = $a, b = $b")
    println("a + b = ${a + b}")     // 22
    println("a - b = ${a - b}")     // 12
    println("a * b = ${a * b}")     // 85
    println("a / b = ${a / b}")     // 3 (integer division)
    println("a % b = ${a % b}")     // 2
    println("a.toDouble() / b = ${a.toDouble() / b}")  // 3.4

    // -----------------------------------------------
    // Section 2: Augmented Assignment
    // -----------------------------------------------
    println("\n--- Augmented Assignment ---")
    var x = 20
    println("x = $x")
    x += 5;  println("x += 5  -> $x")   // 25
    x -= 3;  println("x -= 3  -> $x")   // 22
    x *= 2;  println("x *= 2  -> $x")   // 44
    x /= 4;  println("x /= 4  -> $x")   // 11
    x %= 3;  println("x %%= 3  -> $x")  // 2

    // -----------------------------------------------
    // Section 3: Comparison Operators
    // -----------------------------------------------
    println("\n--- Comparison Operators ---")
    val p = 10
    val q = 20
    println("p = $p, q = $q")
    println("p == q: ${p == q}")   // false
    println("p != q: ${p != q}")   // true
    println("p < q:  ${p < q}")    // true
    println("p > q:  ${p > q}")    // false
    println("p <= 10: ${p <= 10}") // true
    println("q >= 20: ${q >= 20}") // true

    // -----------------------------------------------
    // Section 4: Structural (==) vs Referential (===) Equality
    // -----------------------------------------------
    println("\n--- Structural vs Referential Equality ---")
    val s1 = String("Hello".toCharArray())
    val s2 = String("Hello".toCharArray())
    val s3 = s1

    println("s1 = \"$s1\", s2 = \"$s2\"")
    println("s1 == s2:  ${s1 == s2}")    // true  — same content
    println("s1 === s2: ${s1 === s2}")   // false — different objects
    println("s1 === s3: ${s1 === s3}")   // true  — same reference

    // Null safety with ==
    val nullStr: String? = null
    println("null == null: ${null == null}")          // true
    println("null == \"hi\": ${nullStr == "hi"}")     // false (no NPE!)

    // -----------------------------------------------
    // Section 5: Logical Operators
    // -----------------------------------------------
    println("\n--- Logical Operators ---")
    val t = true
    val f = false
    println("true && false: ${t && f}")   // false
    println("true || false: ${t || f}")   // true
    println("!true: ${!t}")               // false
    println("!false: ${!f}")              // true

    // Short-circuit demonstration
    println("\nShort-circuit evaluation:")
    println("false && sideEffect(): ${f && sideEffect()}")  // sideEffect NOT called
    println("true || sideEffect(): ${t || sideEffect()}")   // sideEffect NOT called

    // -----------------------------------------------
    // Section 6: Bitwise Operations (Named Functions)
    // -----------------------------------------------
    println("\n--- Bitwise Operations ---")
    val m = 0b1010  // 10
    val n = 0b1100  // 12
    println("m = ${Integer.toBinaryString(m)} ($m)")
    println("n = ${Integer.toBinaryString(n)} ($n)")
    println("m and n  = ${Integer.toBinaryString(m and n)} (${m and n})")     // 1000 = 8
    println("m or n   = ${Integer.toBinaryString(m or n)} (${m or n})")       // 1110 = 14
    println("m xor n  = ${Integer.toBinaryString(m xor n)} (${m xor n})")     // 0110 = 6
    println("m.inv()  = ${m.inv()}")                                           // -11
    println("1 shl 4  = ${1 shl 4}")    // 16
    println("32 shr 2 = ${32 shr 2}")   // 8
    println("(-1).ushr(28) = ${(-1).ushr(28)}")  // 15

    // -----------------------------------------------
    // Section 7: Range Operator (..)
    // -----------------------------------------------
    println("\n--- Range Operator ---")
    val intRange = 1..10
    val charRange = 'a'..'f'
    println("IntRange 1..10: $intRange")
    println("CharRange 'a'..'f': $charRange")
    println("5 in 1..10: ${5 in intRange}")       // true
    println("15 in 1..10: ${15 in intRange}")      // false
    println("'c' in 'a'..'f': ${'c' in charRange}")  // true

    // -----------------------------------------------
    // Section 8: in Operator
    // -----------------------------------------------
    println("\n--- in Operator ---")
    val score = 85
    val grade = when (score) {
        in 90..100 -> "A"
        in 80..89  -> "B"
        in 70..79  -> "C"
        in 60..69  -> "D"
        else       -> "F"
    }
    println("Score: $score, Grade: $grade")

    val fruits = listOf("apple", "banana", "cherry")
    println("\"banana\" in fruits: ${"banana" in fruits}")     // true
    println("\"grape\" in fruits: ${"grape" in fruits}")       // false
    println("\"grape\" !in fruits: ${"grape" !in fruits}")     // true

    // -----------------------------------------------
    // Section 9: Elvis Operator (?:)
    // -----------------------------------------------
    println("\n--- Elvis Operator (?:) ---")
    val name: String? = null
    val displayName = name ?: "Guest"
    println("name = $name")
    println("displayName = $displayName")  // "Guest"

    val nonNullName: String? = "Kotlin"
    val display2 = nonNullName ?: "Guest"
    println("nonNullName = $nonNullName, elvis result = $display2")  // "Kotlin"

    // Elvis with length
    val length = name?.length ?: -1
    println("Length of null name: $length")  // -1

    // -----------------------------------------------
    // Section 10: Safe Call Operator (?.)
    // -----------------------------------------------
    println("\n--- Safe Call Operator (?.) ---")
    val str: String? = "Hello, Kotlin"
    val nullableStr: String? = null

    println("str?.length = ${str?.length}")             // 13
    println("nullableStr?.length = ${nullableStr?.length}")   // null
    println("str?.uppercase() = ${str?.uppercase()}")   // "HELLO, KOTLIN"

    // Chaining safe calls
    println("str?.uppercase()?.substring(0,5) = ${str?.uppercase()?.substring(0, 5)}")  // "HELLO"
    println("nullableStr?.uppercase()?.substring(0,5) = ${nullableStr?.uppercase()?.substring(0, 5)}")  // null

    // let with safe call
    str?.let {
        println("Inside let: $it (length: ${it.length})")
    }

    // -----------------------------------------------
    // Section 11: Not-Null Assertion (!!)
    // -----------------------------------------------
    println("\n--- Not-Null Assertion (!!) ---")
    val definitelyNotNull: String? = "I exist"
    println("!!: ${definitelyNotNull!!.length}")  // 7

    // WARNING: This would throw NullPointerException:
    // val boom: String? = null
    // println(boom!!.length)  // NPE!
    println("Avoid !! when possible — use ?. and ?: instead")

    // -----------------------------------------------
    // Section 12: Type Cast (as, as?)
    // -----------------------------------------------
    println("\n--- Type Cast Operators ---")
    val obj: Any = "Hello"

    // Unsafe cast
    val str2: String = obj as String
    println("Unsafe cast: $str2")

    // Safe cast
    val num: Int? = obj as? Int
    println("Safe cast to Int: $num")  // null (no exception)

    val num2: Int? = 42 as? Int
    println("Safe cast 42 to Int: $num2")  // 42

    // -----------------------------------------------
    // Section 13: Type Check (is, !is)
    // -----------------------------------------------
    println("\n--- Type Check (is) ---")
    checkType("Hello")
    checkType(42)
    checkType(3.14)
    checkType(true)

    // -----------------------------------------------
    // Section 14: Operator Overloading
    // -----------------------------------------------
    println("\n--- Operator Overloading ---")
    val v1 = Vector2D(3.0, 4.0)
    val v2 = Vector2D(1.0, 2.0)
    println("v1 = $v1")
    println("v2 = $v2")
    println("v1 + v2 = ${v1 + v2}")
    println("v1 - v2 = ${v1 - v2}")
    println("v1 * 2.0 = ${v1 * 2.0}")
    println("-v1 = ${-v1}")
    println("v1 == Vector2D(3.0, 4.0): ${v1 == Vector2D(3.0, 4.0)}")

    println("\n=== Program Complete ===")
}

// Side effect function for short-circuit demo
fun sideEffect(): Boolean {
    println("  [sideEffect() was called!]")
    return true
}

// Type checking function with smart cast
fun checkType(obj: Any) {
    when (obj) {
        is String  -> println("String: \"$obj\", length=${obj.length}")
        is Int     -> println("Int: $obj, hex=0x${Integer.toHexString(obj)}")
        is Double  -> println("Double: $obj, floor=${kotlin.math.floor(obj)}")
        is Boolean -> println("Boolean: $obj, negated=${!obj}")
        else       -> println("Unknown: $obj")
    }
}

// Operator overloading example
data class Vector2D(val x: Double, val y: Double) {
    operator fun plus(other: Vector2D) = Vector2D(x + other.x, y + other.y)
    operator fun minus(other: Vector2D) = Vector2D(x - other.x, y - other.y)
    operator fun times(scalar: Double) = Vector2D(x * scalar, y * scalar)
    operator fun unaryMinus() = Vector2D(-x, -y)
    override fun toString() = "Vector2D($x, $y)"
}
