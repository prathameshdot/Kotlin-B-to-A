# Operators in Kotlin

## Arithmetic Operators

| Operator | Description | Example |
|----------|-------------|---------|
| `+` | Addition | `5 + 3` → `8` |
| `-` | Subtraction | `5 - 3` → `2` |
| `*` | Multiplication | `5 * 3` → `15` |
| `/` | Division | `7 / 2` → `3` (integer division) |
| `%` | Modulus (remainder) | `7 % 2` → `1` |

```kotlin
val a = 10
val b = 3
println(a + b)   // 13
println(a / b)   // 3 (integer division — truncates)
println(a % b)   // 1
println(a.toDouble() / b)  // 3.3333... (floating-point division)
```

---

## Augmented Assignment Operators

```kotlin
var x = 10
x += 5   // x = x + 5 → 15
x -= 3   // x = x - 3 → 12
x *= 2   // x = x * 2 → 24
x /= 4   // x = x / 4 → 6
x %= 4   // x = x % 4 → 2
```

---

## Comparison Operators

| Operator | Description |
|----------|-------------|
| `==` | Structural equality (calls `equals()`) |
| `!=` | Structural inequality |
| `<` | Less than |
| `>` | Greater than |
| `<=` | Less than or equal |
| `>=` | Greater than or equal |

```kotlin
val a = 10
val b = 20
println(a == b)   // false
println(a != b)   // true
println(a < b)    // true
println(a >= 10)  // true
```

---

## Structural vs Referential Equality

This is a critical distinction in Kotlin:

| Operator | Type | Checks |
|----------|------|--------|
| `==` | Structural | Are the **values** equal? (calls `equals()`) |
| `===` | Referential | Is it the **same object** in memory? |

```kotlin
val s1 = String("Hello".toCharArray())
val s2 = String("Hello".toCharArray())

println(s1 == s2)   // true  — same content
println(s1 === s2)  // false — different objects

val s3 = s1
println(s1 === s3)  // true  — same reference
```

> **Note:** `==` in Kotlin is equivalent to Java's `.equals()`, and `===` is equivalent to Java's `==`.

---

## Logical Operators

| Operator | Description | Short-circuit? |
|----------|-------------|----------------|
| `&&` | Logical AND | Yes |
| `\|\|` | Logical OR | Yes |
| `!` | Logical NOT | N/A |

```kotlin
val a = true
val b = false
println(a && b)   // false
println(a || b)   // true
println(!a)       // false
```

---

## Bitwise Operations

Kotlin uses **named functions** instead of symbols for bitwise operations:

| Function | Description | Java Equivalent |
|----------|-------------|-----------------|
| `and(bits)` | Bitwise AND | `&` |
| `or(bits)` | Bitwise OR | `\|` |
| `xor(bits)` | Bitwise XOR | `^` |
| `inv()` | Bitwise NOT (inversion) | `~` |
| `shl(count)` | Shift left | `<<` |
| `shr(count)` | Signed shift right | `>>` |
| `ushr(count)` | Unsigned shift right | `>>>` |

```kotlin
val a = 0b1010  // 10
val b = 0b1100  // 12

println(a and b)   // 0b1000 = 8
println(a or b)    // 0b1110 = 14
println(a xor b)   // 0b0110 = 6
println(a.inv())   // inverts all bits
println(1 shl 3)   // 8 (1 shifted left by 3)
println(16 shr 2)  // 4 (16 shifted right by 2)
```

> These are **infix functions** on Int and Long, so you can use them without the dot: `a and b`.

---

## Range Operator (`..`)

Creates a range (inclusive):

```kotlin
val range = 1..10           // 1, 2, 3, ..., 10
val charRange = 'a'..'z'   // a, b, c, ..., z

println(5 in range)         // true
println(11 in range)        // false
println('m' in charRange)   // true
```

---

## `in` Operator

Checks membership in a range or collection:

```kotlin
val x = 5
if (x in 1..10) println("In range")       // In range
if (x !in 20..30) println("Not in range")  // Not in range

val fruits = listOf("apple", "banana", "cherry")
if ("banana" in fruits) println("Found!")   // Found!
```

---

## Elvis Operator (`?:`)

Returns the left operand if it's non-null, otherwise returns the right operand:

```kotlin
val name: String? = null
val displayName = name ?: "Guest"
println(displayName)  // "Guest"

val length = name?.length ?: 0  // 0 if name is null
```

---

## Safe Call Operator (`?.`)

Calls a method/property only if the receiver is non-null; returns `null` otherwise:

```kotlin
val str: String? = "Hello"
println(str?.length)   // 5

val nullStr: String? = null
println(nullStr?.length)  // null (not NPE!)

// Chaining safe calls
val result = str?.uppercase()?.substring(0, 3)  // "HEL"
```

---

## Not-Null Assertion (`!!`)

Asserts that a value is non-null. Throws `NullPointerException` if it is null:

```kotlin
val str: String? = "Hello"
println(str!!.length)  // 5

val nullStr: String? = null
// println(nullStr!!.length)  // THROWS NullPointerException!
```

> **Warning:** Avoid `!!` when possible. Use safe calls (`?.`) or Elvis (`?:`) instead.

---

## Type Cast Operators

| Operator | Description |
|----------|-------------|
| `as` | Unsafe cast (throws ClassCastException if fails) |
| `as?` | Safe cast (returns null if fails) |

```kotlin
val obj: Any = "Hello"
val str: String = obj as String      // OK
// val num: Int = obj as Int          // ClassCastException!
val num: Int? = obj as? Int           // null (safe)

println(str)   // "Hello"
println(num)   // null
```

---

## Operator Overloading

Kotlin allows overloading operators using the `operator` keyword:

```kotlin
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun times(scale: Int) = Point(x * scale, y * scale)
}

val p1 = Point(1, 2)
val p2 = Point(3, 4)
println(p1 + p2)   // Point(x=4, y=6)
println(p1 * 3)    // Point(x=3, y=6)
```

---

## Tips

1. **Use `==` for value comparison** — it's null-safe and calls `equals()`
2. **Avoid `!!`** — prefer `?.` and `?:` for null handling
3. Bitwise operations use **named functions** in Kotlin, not symbols
4. The `in` operator works with ranges AND collections
5. **Elvis operator** is great for providing default values for nullable types

---

## Practice Questions

1. What is the result of `7 / 2` in Kotlin? How do you get a decimal result?
2. What is the difference between `==` and `===`?
3. Write code using all augmented assignment operators.
4. Demonstrate all bitwise operations on two integers.
5. What does the `in` operator do? Show usage with both a range and a collection.
6. Explain the Elvis operator with an example.
7. What is the difference between `?.` (safe call) and `!!` (not-null assertion)?
8. Write code that uses the range operator to check if a number is between 1 and 100.
9. What is the difference between `as` and `as?` for type casting?
10. Why does Kotlin use named functions for bitwise ops instead of symbols?
11. What happens if you use `!!` on a null value?
12. Write operator overloading for a `Vector2D` class supporting `+` and `-`.
13. Demonstrate short-circuit evaluation with `&&` and `||`.
14. What is the precedence: `&&` vs `||`? How do you control it?
15. How does `==` handle null values in Kotlin?

---

## Interview Questions & Answers

**1. How does `==` differ from `===` in Kotlin?**
`==` checks structural equality by calling `equals()`, like Java's `.equals()`. `===` checks referential equality (same object in memory), like Java's `==`.

**2. How does Kotlin handle null in `==` comparisons?**
`a == b` is translated to `a?.equals(b) ?: (b === null)`. It's completely null-safe — `null == null` is `true`, `null == "hello"` is `false`, no NPE.

**3. Why does Kotlin use named functions for bitwise operations?**
To improve readability and avoid confusion. Named functions like `shl`, `shr`, `and`, `or` are clearer than `<<`, `>>`, `&`, `|`. They're defined as infix functions so syntax is still clean.

**4. What is the Elvis operator and when is it used?**
`?:` returns the left operand if non-null, otherwise the right operand. Used for providing defaults: `val name = input ?: "default"`. Can also throw: `val name = input ?: throw IllegalArgumentException()`.

**5. Explain safe call vs not-null assertion.**
`?.` returns null if the receiver is null (safe). `!!` throws NPE if the receiver is null (unsafe). Prefer `?.` with `?:` for safe null handling.

**6. What is the difference between `as` and `as?`?**
`as` is an unsafe cast that throws `ClassCastException` on failure. `as?` is a safe cast that returns `null` on failure.

**7. What is operator overloading in Kotlin? How does it work?**
Kotlin allows defining custom behavior for operators by implementing functions with the `operator` modifier. E.g., `operator fun plus(other: T)` enables `+`. Each operator maps to a specific function name.

**8. Does Kotlin support ternary operator (`? :`)?**
No. Kotlin uses `if` as an expression instead: `val max = if (a > b) a else b`. This is more readable than the ternary operator.

**9. What is the `in` operator used for?**
Checking membership in a range (`x in 1..10`) or collection (`x in list`). It calls `contains()` under the hood. `!in` checks non-membership.

**10. How does integer division work in Kotlin?**
Dividing two integers performs integer division (truncation toward zero). `7 / 2` = `3`. For decimal results, cast at least one operand: `7.0 / 2` or `7 / 2.toDouble()`.

**11. What is the `..` operator?**
The range operator. `1..10` creates an `IntRange` from 1 to 10 (inclusive). Works with Int, Long, Char. Used in for loops and membership checks.

**12. Can you chain safe calls? Give an example.**
Yes: `person?.address?.city?.name` — if any part is null, the entire expression evaluates to null without throwing an exception.

**13. What happens with `==` on different types?**
`==` calls `equals()` which may handle cross-type comparison. For numbers, comparing `Int` and `Long` directly with `==` won't compile — you must convert first. This prevents subtle bugs.

**14. Explain short-circuit evaluation in Kotlin.**
`&&` stops evaluating if the left side is `false`. `||` stops if the left side is `true`. This means the right side may not execute, which matters if it has side effects.

**15. Does Kotlin support unsigned shift right?**
Yes, via the `ushr()` function. It shifts bits right and fills the leftmost bits with zeros regardless of the sign bit. E.g., `(-1).ushr(28)` → `15`.
