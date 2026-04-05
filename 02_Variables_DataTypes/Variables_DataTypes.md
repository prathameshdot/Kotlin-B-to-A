# Variables & Data Types in Kotlin

## Table of Contents
1. [val vs var](#val-vs-var)
2. [Type Inference](#type-inference)
3. [Basic Data Types](#basic-data-types)
4. [Type Conversion](#type-conversion)
5. [Number Literals](#number-literals)
6. [Unsigned Types](#unsigned-types)
7. [Special Types](#special-types)
8. [Nullable Types](#nullable-types)
9. [const val vs val](#const-val-vs-val)
10. [lateinit var](#lateinit-var)
11. [Type Checking](#type-checking)
12. [Practice Questions](#practice-questions)
13. [Interview Questions](#interview-questions)

---

## val vs var

Kotlin has two keywords for declaring variables:

```kotlin
val name = "Kotlin"   // Immutable (read-only) — like Java's final
var count = 0         // Mutable — can be reassigned

// name = "Java"      // ERROR: Val cannot be reassigned
count = 10            // OK: var can be reassigned
```

### Key Rules
- **Prefer `val`** over `var` — promotes immutability, thread safety, and predictability
- `val` means the **reference** is immutable, not the object itself:
  ```kotlin
  val list = mutableListOf(1, 2, 3)
  list.add(4)         // OK — modifying the list's contents
  // list = mutableListOf()  // ERROR — can't reassign the reference
  ```

---

## Type Inference

Kotlin has powerful type inference — the compiler deduces types automatically:

```kotlin
val name = "Kotlin"       // Inferred as String
val age = 25              // Inferred as Int
val pi = 3.14             // Inferred as Double
val isActive = true       // Inferred as Boolean
val letter = 'K'          // Inferred as Char
```

### Explicit Type Declaration
```kotlin
val name: String = "Kotlin"
val age: Int = 25
val pi: Double = 3.14

// Deferred assignment (type required)
val result: String
result = computeValue()   // Assigned later, but only once
```

> **Tip:** Use explicit types when the type isn't obvious from the right-hand side, or for public API return types.

---

## Basic Data Types

### Numeric Types

| Type | Size (bits) | Min Value | Max Value |
|------|-------------|-----------|-----------|
| `Byte` | 8 | -128 | 127 |
| `Short` | 16 | -32,768 | 32,767 |
| `Int` | 32 | -2,147,483,648 | 2,147,483,647 |
| `Long` | 64 | -9.2 × 10¹⁸ | 9.2 × 10¹⁸ |
| `Float` | 32 | ~1.4 × 10⁻⁴⁵ | ~3.4 × 10³⁸ |
| `Double` | 64 | ~4.9 × 10⁻³²⁴ | ~1.8 × 10³⁰⁸ |

```kotlin
val byte: Byte = 127
val short: Short = 32767
val int: Int = 2_147_483_647
val long: Long = 9_223_372_036_854_775_807L   // L suffix for Long
val float: Float = 3.14f                       // f suffix for Float
val double: Double = 3.141592653589793
```

> **Note:** In Kotlin, there are **no primitive types** at the source level — everything is an object. But the compiler optimizes to JVM primitives where possible.

### Char
```kotlin
val letter: Char = 'A'
val digit: Char = '1'
val unicode: Char = '\u0041'  // 'A'
// val c: Char = 65            // ERROR: Char is NOT a number in Kotlin
val c: Char = 65.toChar()     // OK: explicit conversion
```

### Boolean
```kotlin
val isKotlinFun: Boolean = true
val isJavaFun: Boolean = false
println(isKotlinFun && !isJavaFun)   // true
```

### String
```kotlin
val greeting: String = "Hello, Kotlin!"
val char: Char = greeting[0]          // 'H'
val length: Int = greeting.length     // 14

// Strings are immutable
// greeting[0] = 'h'  // ERROR
```

---

## Type Conversion

**Kotlin has NO implicit type widening.** You must convert explicitly:

```kotlin
val intVal: Int = 42
// val longVal: Long = intVal       // ERROR: Type mismatch
val longVal: Long = intVal.toLong() // OK: explicit conversion

// Available conversion functions:
// toByte(), toShort(), toInt(), toLong()
// toFloat(), toDouble(), toChar()
// toString()
```

### Conversion Examples
```kotlin
val i: Int = 100
val l: Long = i.toLong()
val f: Float = i.toFloat()
val d: Double = i.toDouble()
val b: Byte = i.toByte()      // Truncates if out of range!
val s: String = i.toString()

// String to number
val num = "42".toInt()
val numOrNull = "abc".toIntOrNull()  // Returns null instead of exception
```

> **Best practice:** Use `toIntOrNull()`, `toDoubleOrNull()` etc. for safe parsing from strings.

---

## Number Literals

```kotlin
// Decimal (default)
val million = 1_000_000          // Underscores for readability

// Hexadecimal (0x prefix)
val hex = 0xFF                   // 255
val hexColor = 0xFF00FF          // Magenta

// Binary (0b prefix)
val binary = 0b1010              // 10
val flags = 0b0000_1111          // 15

// Long literal (L suffix)
val bigNumber = 100L
val reallyBig = 9_999_999_999L

// Float literal (f or F suffix)
val pi = 3.14f

// Double (default for decimals)
val e = 2.718281828
val scientific = 1.5e10          // 1.5 × 10¹⁰
```

---

## Unsigned Types

Kotlin supports unsigned integer types (experimental in older versions, stable since 1.5):

```kotlin
val uByte: UByte = 255u          // 0 to 255
val uShort: UShort = 65535u      // 0 to 65,535
val uInt: UInt = 42u              // 0 to 4,294,967,295
val uLong: ULong = 100uL         // 0 to 18,446,744,073,709,551,615

// Useful for bit manipulation and interop
val mask: UInt = 0xFFFF_FFFFu
```

---

## Special Types: Any, Unit, Nothing

### Any
- Supertype of **all non-nullable types** (like Java's `Object`)
```kotlin
val anything: Any = "Hello"
val number: Any = 42
println(anything is String)  // true
```

### Unit
- Equivalent to Java's `void`, but it's an actual type with a single value `Unit`
```kotlin
fun printHello(): Unit {       // Explicit Unit return
    println("Hello")
}

fun printWorld() {             // Unit is implied when omitted
    println("World")
}

val u: Unit = Unit             // Has a singleton value
```

### Nothing
- Represents a value that **never exists**
- Used for functions that never return (always throw)
```kotlin
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

// Nothing is a subtype of every other type
val x: String = fail("error")  // Compiles — Nothing is a subtype of String
```

---

## Nullable Types (Introduction)

By default, Kotlin types **cannot hold null**:

```kotlin
var name: String = "Kotlin"
// name = null                // ERROR: Null cannot be a value of String

var nullableName: String? = "Kotlin"  // ? makes it nullable
nullableName = null                    // OK

// Must handle null before using
println(nullableName?.length)          // Safe call → null
println(nullableName?.length ?: 0)     // Elvis operator → 0
```

---

## const val vs val

```kotlin
const val MAX_SIZE = 100          // Compile-time constant (must be top-level or in object)
val currentTime = System.currentTimeMillis()  // Runtime constant

// const val rules:
// - Must be top-level, or in an object/companion object
// - Must be String or primitive type
// - No custom getter
// - Value known at compile time
```

```kotlin
object Config {
    const val API_KEY = "abc123"    // OK in object
    val timestamp = System.nanoTime() // OK but not const
}
```

---

## lateinit var

For non-null properties that are initialized after construction:

```kotlin
class UserProfile {
    lateinit var name: String       // Will be initialized later
    
    fun setup() {
        name = "Alice"
    }
    
    fun printName() {
        if (::name.isInitialized) { // Check if initialized
            println(name)
        }
    }
}
```

### lateinit Rules
- Only with `var` (not `val`)
- Only non-nullable types
- Only non-primitive types (no `Int`, `Boolean`, etc.)
- No custom getter/setter
- Accessing before initialization throws `UninitializedPropertyAccessException`

---

## Type Checking with `is`

```kotlin
val obj: Any = "Hello"

if (obj is String) {
    // Smart cast — obj is automatically cast to String here
    println(obj.length)      // No explicit cast needed!
    println(obj.uppercase())
}

// Negation
if (obj !is Int) {
    println("Not an Int")
}

// When expression with type checks
when (obj) {
    is String -> println("String of length ${obj.length}")
    is Int    -> println("Int with value $obj")
    is Boolean -> println("Boolean: $obj")
    else      -> println("Unknown type")
}
```

---

## Tips

1. **Always prefer `val`** — only use `var` when reassignment is truly needed
2. **Let the compiler infer types** — explicit types add clutter when the type is obvious
3. **Use `toIntOrNull()`** family for safe string-to-number conversion
4. **Underscores in literals** make large numbers readable: `1_000_000`
5. **`lateinit`** is great for dependency injection and test setup, but avoid overusing it
6. **`const val`** is more efficient than `val` for true constants (inlined at compile time)
7. **Remember:** `Char` is NOT a number in Kotlin — no implicit conversion

---

## Practice Questions

### Q1. What is the difference between `val` and `var`?
<details><summary>Answer</summary>

`val` declares a read-only (immutable) variable — it can only be assigned once. `var` declares a mutable variable — it can be reassigned. `val` is like Java's `final`.
```kotlin
val x = 10    // Can't reassign
var y = 10    // Can reassign: y = 20
```
</details>

### Q2. Declare variables of all basic numeric types.
<details><summary>Answer</summary>

```kotlin
val b: Byte = 100
val s: Short = 10000
val i: Int = 100000
val l: Long = 10000000000L
val f: Float = 3.14f
val d: Double = 3.14159265358979
```
</details>

### Q3. Why does `val x: Long = 42` fail? How do you fix it?
<details><summary>Answer</summary>

It actually works! Kotlin allows assigning `Int` literals to `Long`. But `val i: Int = 42; val x: Long = i` fails because Kotlin has no implicit widening conversion. Fix: `val x: Long = i.toLong()`.
</details>

### Q4. What is the output: `println("abc".toIntOrNull() ?: -1)`?
<details><summary>Answer</summary>

Output: `-1`. `"abc".toIntOrNull()` returns `null` because "abc" is not a valid integer. The Elvis operator `?:` then provides the default value `-1`.
</details>

### Q5. Write code to convert a Double to Int and explain what happens to the decimal part.
<details><summary>Answer</summary>

```kotlin
val d = 3.99
val i = d.toInt()  // i = 3, decimal part is TRUNCATED (not rounded)
```
The decimal part is simply dropped (truncated toward zero). Use `d.roundToInt()` for rounding.
</details>

### Q6. What are unsigned types? Give an example use case.
<details><summary>Answer</summary>

Unsigned types (`UByte`, `UShort`, `UInt`, `ULong`) hold only non-negative values with double the positive range. Use case: representing RGBA colors, byte-level protocols, interop with C/native APIs.
```kotlin
val color: UInt = 0xFF0000FFu  // Red with full alpha
```
</details>

### Q7. What is the `Any` type?
<details><summary>Answer</summary>

`Any` is the root of the Kotlin class hierarchy — the supertype of all non-nullable types (like `Object` in Java). Every class implicitly inherits from `Any`. It has three methods: `equals()`, `hashCode()`, and `toString()`.
</details>

### Q8. Explain the difference between `Unit` and `Nothing`.
<details><summary>Answer</summary>

- `Unit` has exactly one value (`Unit`) and represents "no meaningful return value" (like `void`). Functions that don't return anything return `Unit`.
- `Nothing` has **no values** and represents computations that never complete successfully (throwing exceptions, infinite loops). `Nothing` is a subtype of every type.
</details>

### Q9. What happens if you access a `lateinit` variable before initialization?
<details><summary>Answer</summary>

It throws `UninitializedPropertyAccessException`. You can check with `::property.isInitialized` before accessing.
</details>

### Q10. What is smart casting? Give an example.
<details><summary>Answer</summary>

After a type check with `is`, Kotlin automatically casts the variable to that type within the scope:
```kotlin
val obj: Any = "Hello"
if (obj is String) {
    println(obj.length)  // No cast needed — smart cast to String
}
```
</details>

### Q11. What is the difference between `const val` and `val`?
<details><summary>Answer</summary>

`const val` is a compile-time constant — its value is inlined at every usage site. It must be `String` or primitive, at top-level or in `object`. `val` is a runtime constant — computed when first accessed. Example: `const val PI = 3.14` vs `val time = System.currentTimeMillis()`.
</details>

### Q12. Write code using number literals in decimal, hex, and binary formats.
<details><summary>Answer</summary>

```kotlin
val decimal = 255
val hex = 0xFF
val binary = 0b1111_1111
println(decimal == hex)    // true
println(hex == binary)     // true
```
</details>

### Q13. Can you store null in a `String` variable? How do you make it nullable?
<details><summary>Answer</summary>

No, `String` is non-nullable. Add `?` to make it nullable:
```kotlin
// var s: String = null   // ERROR
var s: String? = null      // OK
```
</details>

### Q14. What restrictions apply to `lateinit` properties?
<details><summary>Answer</summary>

1. Must be `var` (not `val`)
2. Must be non-nullable type
3. Cannot be primitive type (`Int`, `Boolean`, etc.)
4. Cannot have custom getter/setter
5. Must be initialized before first access
</details>

### Q15. What is the output of this code?
```kotlin
val a: Int = 128
val b: Int = 128
println(a == b)   // ?
println(a === b)  // ?
```
<details><summary>Answer</summary>

Both print `true`. For `Int` variables (not nullable), Kotlin uses structural equality for both `==` and `===`. However, if they were `Int?` (nullable/boxed), `===` might be `false` for values outside -128..127 cache range.
</details>

---

## Interview Questions

### Q1. How does Kotlin handle null safety at the type system level?
<details><summary>Answer</summary>

Kotlin's type system distinguishes nullable and non-nullable types. `String` cannot hold null; `String?` can. The compiler enforces null checks at compile time. Operators: `?.` (safe call), `?:` (Elvis), `!!` (not-null assertion). This eliminates most NullPointerExceptions that plague Java code.
</details>

### Q2. Explain type inference in Kotlin. Is it always recommended?
<details><summary>Answer</summary>

Kotlin's compiler deduces types from context (`val x = 42` → `Int`). It's recommended for local variables where the type is obvious. For public API return types and properties, explicit types improve readability and prevent accidental type changes from breaking callers.
</details>

### Q3. Why doesn't Kotlin have implicit type widening like Java?
<details><summary>Answer</summary>

Java's implicit widening (`int` → `long`) can cause subtle bugs and unexpected behavior. Kotlin requires explicit conversion (`toInt()`, `toLong()`) to make conversions visible and intentional. This prevents accidental precision loss and makes code more predictable.
</details>

### Q4. What is the boxed vs unboxed representation of Kotlin types on JVM?
<details><summary>Answer</summary>

At source level, everything is an object. On JVM, the compiler optimizes:
- `Int` → JVM `int` (primitive) when possible
- `Int?` → `java.lang.Integer` (boxed) — nullable types always boxed
- `IntArray` → `int[]` (primitive array)
- `Array<Int>` → `Integer[]` (boxed array)
Identity (`===`) comparisons on boxed types may fail for values outside the -128..127 cache.
</details>

### Q5. What is `Nothing` type and where is it used?
<details><summary>Answer</summary>

`Nothing` is a type with no instances. It's the return type of functions that never return normally (always throw or loop forever). Since `Nothing` is a subtype of every type, it works in any context: `val x: String = throw Exception()` compiles because `throw` returns `Nothing`, which is a subtype of `String`. `Nothing?` only has one value: `null`.
</details>

### Q6. How does `lateinit` differ from `lazy`?
<details><summary>Answer</summary>

| Feature | `lateinit var` | `by lazy` |
|---------|---------------|-----------|
| Mutability | `var` only | `val` only |
| Initialization | Manual, anytime | Automatic on first access |
| Primitives | Not allowed | Allowed |
| Thread safety | Not guaranteed | Thread-safe by default |
| Check | `::prop.isInitialized` | Always initialized on access |
| Use case | DI, Android views | Expensive computation |
</details>

### Q7. Explain `const val` vs `val` vs `var` in terms of compilation.
<details><summary>Answer</summary>

- `const val`: Compile-time constant. Value inlined at call sites. Restricted to primitives/String at top-level or in `object`.
- `val`: Runtime immutable reference. Computed during initialization. Can have custom getter.
- `var`: Mutable reference. Can be reassigned. Can have custom getter/setter.
At bytecode level, `const val` becomes a `static final` field with its value inlined wherever used.
</details>

### Q8. What is smart casting and what are its limitations?
<details><summary>Answer</summary>

After an `is` check, Kotlin automatically casts a variable to the checked type. Limitations:
1. Doesn't work with `var` properties that could be changed between check and use (by another thread)
2. Doesn't work with properties that have custom getters
3. Doesn't work with open properties in base classes
In these cases, use explicit casts with `as` or `as?`.
</details>

### Q9. How are Kotlin's data types different from Java's primitive types?
<details><summary>Answer</summary>

In Java, there are 8 primitive types (`int`, `boolean`, etc.) and their wrapper classes (`Integer`, `Boolean`). Kotlin unifies them — `Int` is used for both. At the source level, everything is an object with methods (`42.toString()`). The JVM backend automatically uses primitives where possible for performance.
</details>

### Q10. What is the `Any` class hierarchy in Kotlin?
<details><summary>Answer</summary>

`Any` is the root of all non-nullable types (equivalent to `Object` but with only 3 methods: `equals`, `hashCode`, `toString`). `Any?` is the root of all types (nullable and non-nullable). Every class implicitly extends `Any`. On JVM, `Any` maps to `java.lang.Object`.
</details>

### Q11. How would you safely convert a String to an Int in Kotlin?
<details><summary>Answer</summary>

```kotlin
val input = "42abc"
val number = input.toIntOrNull() ?: 0  // Returns null if invalid, then default 0
// OR
val number2 = input.toIntOrNull() ?: throw IllegalArgumentException("Invalid number")
```
Never use `toInt()` on untrusted input — it throws `NumberFormatException`.
</details>

### Q12. What happens when you use `===` on boxed types?
<details><summary>Answer</summary>

`===` checks referential equality (same object in memory). JVM caches boxed integers from -128 to 127, so `===` returns `true` in that range but may return `false` for larger values:
```kotlin
val a: Int? = 127
val b: Int? = 127
println(a === b)  // true (cached)

val c: Int? = 128
val d: Int? = 128
println(c === d)  // false (different objects)
```
</details>

### Q13. Can a `val` property change its value? Explain.
<details><summary>Answer</summary>

The **reference** is immutable, but the **object** it points to can be mutable:
```kotlin
val list = mutableListOf(1, 2, 3)
list.add(4)       // OK — mutating the list
// list = mutableListOf()  // ERROR — can't reassign

// Also, val with custom getter returns different values:
val time: Long get() = System.currentTimeMillis()  // Different each call
```
</details>

### Q14. Explain the difference between `is` and `as` operators.
<details><summary>Answer</summary>

- `is` — Type check (returns Boolean): `if (x is String)`
- `!is` — Negated type check: `if (x !is String)`
- `as` — Unsafe cast (throws ClassCastException if fails): `x as String`
- `as?` — Safe cast (returns null if fails): `x as? String`

After `is`, smart casting is applied automatically. `as?` is preferred over `as` for safety.
</details>

### Q15. What are unsigned types in Kotlin and when would you use them?
<details><summary>Answer</summary>

`UByte` (0-255), `UShort` (0-65535), `UInt` (0-4.29B), `ULong` (0-18.4 quintillion). Use cases:
- Byte-level protocols and binary data
- Color representation (ARGB)
- Interop with C/native APIs that use unsigned types
- When you need the full positive range without sign bit

Suffix: `42u` for UInt, `42uL` for ULong.
</details>
