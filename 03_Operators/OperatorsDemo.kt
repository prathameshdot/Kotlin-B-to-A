fun main() {
    println("=== Kotlin Operators Demo ===\n")

    // ─── Arithmetic Operators ───────────────────────────
    println("--- Arithmetic Operators ---")
    val a = 17
    val b = 5
    println("$a + $b = ${a + b}")       // 22
    println("$a - $b = ${a - b}")       // 12
    println("$a * $b = ${a * b}")       // 85
    println("$a / $b = ${a / b}")       // 3 (integer division)
    println("$a % $b = ${a % b}")       // 2 (remainder)
    println("$a.0 / $b = ${a.toDouble() / b}")  // 3.4

    // Unary operators
    var num = 5
    println("\nUnary operators:")
    println("num = $num")
    println("++num = ${++num}")   // 6 (pre-increment)
    println("num++ = ${num++}")   // 6 (post-increment, then becomes 7)
    println("num = $num")         // 7
    println("--num = ${--num}")   // 6
    println("-num = ${-num}")     // -6

    // ─── Augmented Assignment ───────────────────────────
    println("\n--- Augmented Assignment ---")
    var x = 20
    println("x = $x")
    x += 5;  println("x += 5  → $x")   // 25
    x -= 3;  println("x -= 3  → $x")   // 22
    x *= 2;  println("x *= 2  → $x")   // 44
    x /= 4;  println("x /= 4  → $x")   // 11
    x %= 3;  println("x %= 3  → $x")   // 2

    // ─── Comparison Operators ───────────────────────────
    println("\n--- Comparison Operators ---")
    val p = 10
    val q = 20
    println("$p > $q  = ${p > q}")     // false
    println("$p < $q  = ${p < q}")     // true
    println("$p >= 10 = ${p >= 10}")   // true
    println("$p <= 9  = ${p <= 9}")    // false

    // String comparison (lexicographic)
    println("\"apple\" > \"banana\" = ${"apple" > "banana"}")      // false
    println("\"Kotlin\" > \"Java\" = ${"Kotlin" > "Java"}")        // true

    // ─── Equality: Structural (==) vs Referential (===) ─
    println("\n--- Structural vs Referential Equality ---")
    val s1 = "Hello"
    val s2 = "Hello"
    val s3 = String("Hello".toCharArray())

    println("s1 == s2:  ${s1 == s2}")    // true  (same content)
    println("s1 === s2: ${s1 === s2}")   // true  (string pool)
    println("s1 == s3:  ${s1 == s3}")    // true  (same content)
    println("s1 === s3: ${s1 === s3}")   // false (different objects)

    // Boxed Int identity
    val i1: Int? = 127
    val i2: Int? = 127
    println("\nBoxed 127: i1 === i2 = ${i1 === i2}")   // true (cached)

    val i3: Int? = 128
    val i4: Int? = 128
    println("Boxed 128: i3 === i4 = ${i3 === i4}")     // false (not cached)
    println("Boxed 128: i3 == i4  = ${i3 == i4}")      // true (value equal)

    // Null-safe ==
    val n1: String? = null
    val n2: String? = null
    println("\nnull == null: ${n1 == n2}")               // true
    println("null == \"hi\": ${n1 == "hi"}")            // false (no NPE!)

    // ─── Logical Operators ──────────────────────────────
    println("\n--- Logical Operators ---")
    val t = true
    val f = false
    println("true && false = ${t && f}")   // false
    println("true || false = ${t || f}")   // true
    println("!true = ${!t}")               // false
    println("!false = ${!f}")              // true

    // Short-circuit demonstration
    println("\nShort-circuit AND:")
    val result1 = false && run { println("  This won't print"); true }
    println("  Result: $result1")

    println("Short-circuit OR:")
    val result2 = true || run { println("  This won't print"); false }
    println("  Result: $result2")

    // ─── Bitwise Operations ─────────────────────────────
    println("\n--- Bitwise Operations (Named Functions) ---")
    val ba = 0b1010  // 10
    val bb = 0b1100  // 12

    println("a = ${Integer.toBinaryString(ba).padStart(4, '0')} ($ba)")
    println("b = ${Integer.toBinaryString(bb).padStart(4, '0')} ($bb)")
    println("a and b = ${Integer.toBinaryString(ba and bb).padStart(4, '0')} (${ba and bb})")   // 1000 = 8
    println("a or  b = ${Integer.toBinaryString(ba or bb).padStart(4, '0')} (${ba or bb})")     // 1110 = 14
    println("a xor b = ${Integer.toBinaryString(ba xor bb).padStart(4, '0')} (${ba xor bb})")   // 0110 = 6

    println("\nShift operations:")
    println("1 shl 3  = ${1 shl 3}")     // 8
    println("16 shr 2 = ${16 shr 2}")    // 4
    println("1 shl 10 = ${1 shl 10}")    // 1024

    println("\nBit flags example:")
    val READ = 1 shl 0    // 001 = 1
    val WRITE = 1 shl 1   // 010 = 2
    val EXEC = 1 shl 2    // 100 = 4
    var permissions = READ or WRITE  // 011 = 3
    println("READ=$READ, WRITE=$WRITE, EXEC=$EXEC")
    println("Permissions (R+W): $permissions")
    println("Has READ?  ${permissions and READ != 0}")    // true
    println("Has EXEC?  ${permissions and EXEC != 0}")    // false
    permissions = permissions or EXEC
    println("After adding EXEC: $permissions")             // 7
    println("Has EXEC?  ${permissions and EXEC != 0}")    // true

    // ─── Range & In Operators ───────────────────────────
    println("\n--- Range & In Operators ---")
    val range1 = 1..10
    val range2 = 1 until 10
    println("1..10:       ${range1.toList()}")
    println("1 until 10:  ${range2.toList()}")
    println("10 downTo 1: ${(10 downTo 1).toList()}")
    println("1..20 step 3: ${(1..20 step 3).toList()}")

    val score = 85
    println("\nscore = $score")
    println("score in 0..100: ${score in 0..100}")        // true
    println("score !in 90..100: ${score !in 90..100}")    // true
    println("'k' in 'a'..'z': ${'k' in 'a'..'z'}")       // true

    // in with collections
    val fruits = listOf("apple", "banana", "cherry")
    println("\"apple\" in fruits: ${"apple" in fruits}")   // true
    println("\"grape\" in fruits: ${"grape" in fruits}")   // false

    // ─── Null Safety Operators ──────────────────────────
    println("\n--- Null Safety Operators ---")

    // Safe call ?.
    val name: String? = "Kotlin"
    val nullName: String? = null
    println("Safe call: name?.length = ${name?.length}")           // 6
    println("Safe call: nullName?.length = ${nullName?.length}")   // null

    // Chaining safe calls
    data class Address(val city: String?)
    data class Person(val address: Address?)
    val person = Person(Address("Tokyo"))
    val nullPerson: Person? = null
    println("Chained: ${person.address?.city?.uppercase()}")          // TOKYO
    println("Chained null: ${nullPerson?.address?.city?.uppercase()}") // null

    // Elvis operator ?:
    println("\nElvis operator:")
    val length1 = name?.length ?: -1
    val length2 = nullName?.length ?: -1
    println("name?.length ?: -1 = $length1")       // 6
    println("nullName?.length ?: -1 = $length2")   // -1

    val displayName = nullName ?: "Anonymous"
    println("nullName ?: \"Anonymous\" = $displayName")

    // Not-null assertion !! (use with caution!)
    println("\nNot-null assertion:")
    println("name!!.length = ${name!!.length}")  // 6
    // println(nullName!!.length)  // Would throw NullPointerException!
    println("(Skipping !! on null to avoid crash)")

    // ─── Type Cast Operators ────────────────────────────
    println("\n--- Type Cast Operators ---")

    val obj: Any = "Hello Kotlin"

    // is / !is (type check with smart cast)
    if (obj is String) {
        println("Smart cast: obj.length = ${obj.length}")  // No explicit cast needed
    }

    // Safe cast as?
    val str: String? = obj as? String
    val intVal: Int? = obj as? Int
    println("as? String: $str")     // "Hello Kotlin"
    println("as? Int: $intVal")     // null

    // Unsafe cast as
    val forcedStr: String = obj as String  // OK — obj IS a String
    println("as String: $forcedStr")

    // when with is (type matching)
    println("\nType matching with when:")
    printTypeInfo(42)
    printTypeInfo("Hello")
    printTypeInfo(3.14)
    printTypeInfo(true)
    printTypeInfo(listOf(1, 2, 3))

    // ─── Operator Overloading ───────────────────────────
    println("\n--- Operator Overloading ---")
    val v1 = Vector(3.0, 4.0)
    val v2 = Vector(1.0, 2.0)
    println("v1 = $v1")
    println("v2 = $v2")
    println("v1 + v2 = ${v1 + v2}")
    println("v1 - v2 = ${v1 - v2}")
    println("v1 * 2.0 = ${v1 * 2.0}")
    println("-v1 = ${-v1}")
    println("v1[0] = ${v1[0]}, v1[1] = ${v1[1]}")

    println("\n=== Demo Complete ===")
}

// Type info function for demonstrating when + is
fun printTypeInfo(value: Any) {
    when (value) {
        is Int     -> println("  Int: $value (doubled: ${value * 2})")
        is String  -> println("  String: '$value' (length: ${value.length})")
        is Double  -> println("  Double: $value (rounded: ${value.toInt()})")
        is Boolean -> println("  Boolean: $value (negated: ${!value})")
        is List<*> -> println("  List: $value (size: ${value.size})")
        else       -> println("  Unknown: $value")
    }
}

// Class demonstrating operator overloading
data class Vector(val x: Double, val y: Double) {
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)
    operator fun times(scalar: Double) = Vector(x * scalar, y * scalar)
    operator fun unaryMinus() = Vector(-x, -y)
    operator fun get(index: Int): Double = when (index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Vector has only 2 components")
    }

    override fun toString() = "Vector($x, $y)"
}
