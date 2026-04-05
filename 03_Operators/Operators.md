# Operators in Kotlin

## Table of Contents
1. [Arithmetic Operators](#arithmetic-operators)
2. [Augmented Assignment](#augmented-assignment)
3. [Comparison Operators](#comparison-operators)
4. [Equality Operators](#equality-operators)
5. [Logical Operators](#logical-operators)
6. [Bitwise Operations](#bitwise-operations)
7. [Range & In Operators](#range--in-operators)
8. [Null Safety Operators](#null-safety-operators)
9. [Type Cast Operators](#type-cast-operators)
10. [Operator Overloading](#operator-overloading)
11. [Practice Questions](#practice-questions)
12. [Interview Questions](#interview-questions)

---

## Arithmetic Operators

| Operator | Description | Example | Result |
|----------|-------------|---------|--------|
| `+` | Addition | `10 + 3` | `13` |
| `-` | Subtraction | `10 - 3` | `7` |
| `*` | Multiplication | `10 * 3` | `30` |
| `/` | Division | `10 / 3` | `3` (integer div) |
| `%` | Modulus (remainder) | `10 % 3` | `1` |

```kotlin
val a = 10
val b = 3
println(a + b)   // 13
println(a - b)   // 7
println(a * b)   // 30
println(a / b)   // 3  (integer division — truncates)
println(a % b)   // 1

// Double division preserves decimals
println(10.0 / 3)  // 3.3333333333333335
println(10 / 3.0)  // 3.3333333333333335
```

> **Important:** Integer division truncates the decimal part. Use `Double` for precise division.

---

## Augmented Assignment

```kotlin
var x = 10
x += 5   // x = x + 5  → 15
x -= 3   // x = x - 3  → 12
x *= 2   // x = x * 2  → 24
x /= 4   // x = x / 4  → 6
x %= 4   // x = x % 4  → 2
```

> **Note:** There are no `++` and `--` in augmented assignment, but Kotlin does support increment/decrement operators: `x++`, `++x`, `x--`, `--x`.

---

## Comparison Operators

| Operator | Description | Example |
|----------|-------------|---------|
| `>` | Greater than | `5 > 3` → `true` |
| `<` | Less than | `5 < 3` → `false` |
| `>=` | Greater or equal | `5 >= 5` → `true` |
| `<=` | Less or equal | `3 <= 5` → `true` |

```kotlin
println(10 > 5)    // true
println(10 < 5)    // false
println(10 >= 10)  // true
println(10 <= 9)   // false

// Strings are compared lexicographically
println("apple" > "banana")  // false ('a' < 'b')
println("Kotlin" > "Java")   // true ('K' > 'J')
```

---

## Equality Operators

### Structural Equality (`==` / `!=`)
Checks if values are **equal** (calls `equals()` internally):

```kotlin
val a = "Hello"
val b = "Hello"
println(a == b)   // true — same content
println(a != b)   // false
```

### Referential Equality (`===` / `!==`)
Checks if two references point to the **same object in memory**:

```kotlin
val a = "Hello"
val b = "Hello"
val c = a

println(a === b)   // true (string pool optimization)
println(a === c)   // true (same reference)

// With boxed nullable types
val x: Int? = 128
val y: Int? = 128
println(x == y)    // true  (structural: same value)
println(x === y)   // false (referential: different objects — outside cache range)

val p: Int? = 100
val q: Int? = 100
println(p === q)   // true  (within -128..127 cache range)
```

> **Key rule:** Use `==` for value comparison (almost always). Use `===` only when you specifically need to check object identity.

---

## Logical Operators

| Operator | Description | Example |
|----------|-------------|---------|
| `&&` | Logical AND (short-circuit) | `true && false` → `false` |
| `\|\|` | Logical OR (short-circuit) | `true \|\| false` → `true` |
| `!` | Logical NOT | `!true` → `false` |

```kotlin
val a = true
val b = false

println(a && b)   // false
println(a || b)   // true
println(!a)       // false
println(!b)       // true

// Short-circuit evaluation
val list = listOf(1, 2, 3)
if (list.isNotEmpty() && list[0] > 0) {
    println("First element is positive")
    // list[0] is only accessed if list is not empty
}
```

---

## Bitwise Operations

Kotlin uses **named functions** instead of symbols for bitwise operations:

| Function | Description | Java Equivalent |
|----------|-------------|-----------------|
| `and(bits)` | Bitwise AND | `&` |
| `or(bits)` | Bitwise OR | `\|` |
| `xor(bits)` | Bitwise XOR | `^` |
| `inv()` | Bitwise inversion (NOT) | `~` |
| `shl(bits)` | Shift left | `<<` |
| `shr(bits)` | Signed shift right | `>>` |
| `ushr(bits)` | Unsigned shift right | `>>>` |

```kotlin
val a = 0b1010  // 10
val b = 0b1100  // 12

println(a and b)    // 0b1000 = 8
println(a or b)     // 0b1110 = 14
println(a xor b)    // 0b0110 = 6
println(a.inv())    // inverts all bits

println(1 shl 3)    // 8  (1 shifted left by 3 = 2^3)
println(16 shr 2)   // 4  (16 shifted right by 2)
println(-1 ushr 28) // 15 (unsigned shift)
```

### Use with infix notation
```kotlin
// These are infix functions, so you can use them naturally:
val flags = 0b0001 or 0b0100   // Set bits 0 and 2
val check = flags and 0b0100   // Check if bit 2 is set
val toggle = flags xor 0b0001  // Toggle bit 0
```

---

## Range & In Operators

### Range Operator (`..`)
```kotlin
val range = 1..10     // IntRange: 1, 2, 3, ..., 10 (inclusive)
val until = 1 until 10  // 1, 2, 3, ..., 9 (exclusive end)
val down = 10 downTo 1  // 10, 9, 8, ..., 1
val step = 1..10 step 2 // 1, 3, 5, 7, 9
```

### In Operator
```kotlin
val x = 5
println(x in 1..10)       // true  — x is in range
println(x !in 10..20)     // true  — x is NOT in range
println('c' in 'a'..'z')  // true  — works with Chars
println("apple" in listOf("apple", "banana"))  // true — works with collections
```

### Range `..<` Operator (Kotlin 1.8+)
```kotlin
val range = 0..<10  // 0, 1, 2, ..., 9 (exclusive end, alternative to until)
```

---

## Null Safety Operators

### Safe Call Operator (`?.`)
```kotlin
val name: String? = null
println(name?.length)         // null (no NPE!)
println(name?.uppercase())    // null

val name2: String? = "Kotlin"
println(name2?.length)        // 6

// Chaining safe calls
val city: String? = user?.address?.city?.uppercase()
```

### Elvis Operator (`?:`)
Provides a default value when the left side is null:

```kotlin
val name: String? = null
val length = name?.length ?: 0     // 0 (default)
val display = name ?: "Anonymous"  // "Anonymous"

// With return/throw
fun getLength(s: String?): Int {
    return s?.length ?: throw IllegalArgumentException("Null string")
}
```

### Not-Null Assertion (`!!`)
Converts nullable to non-null, throws `NullPointerException` if null:

```kotlin
val name: String? = "Kotlin"
println(name!!.length)  // 6 — OK

val nullName: String? = null
// println(nullName!!.length)  // THROWS NullPointerException!
```

> **Warning:** Avoid `!!` whenever possible. It defeats the purpose of null safety.

---

## Type Cast Operators

### Unsafe Cast (`as`)
```kotlin
val obj: Any = "Hello"
val str: String = obj as String  // OK
// val num: Int = obj as Int      // ClassCastException!
```

### Safe Cast (`as?`)
```kotlin
val obj: Any = "Hello"
val str: String? = obj as? String   // "Hello"
val num: Int? = obj as? Int         // null (no exception)
```

### Type Check (`is` / `!is`)
```kotlin
val obj: Any = "Hello"
if (obj is String) {
    println(obj.length)  // Smart cast — no explicit cast needed
}
if (obj !is Int) {
    println("Not an Int")
}
```

---

## Operator Overloading

Kotlin allows overloading operators using the `operator` keyword:

```kotlin
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)
    operator fun times(scalar: Int) = Point(x * scalar, y * scalar)
    operator fun unaryMinus() = Point(-x, -y)
}

val p1 = Point(1, 2)
val p2 = Point(3, 4)
println(p1 + p2)    // Point(4, 6)
println(p1 - p2)    // Point(-2, -2)
println(p1 * 3)     // Point(3, 6)
println(-p1)        // Point(-1, -2)
```

---

## Operator Precedence (High to Low)

| Precedence | Operators |
|------------|-----------|
| Highest | `()` (parentheses), `.` (member access), `?.`, `!!` |
| | `++`, `--` (postfix) |
| | `+`, `-`, `!`, `++`, `--` (prefix) |
| | `*`, `/`, `%` |
| | `+`, `-` |
| | `..` |
| | `in`, `!in`, `is`, `!is` |
| | `<`, `>`, `<=`, `>=` |
| | `==`, `!=`, `===`, `!==` |
| | `&&` |
| | `\|\|` |
| | `?:` |
| Lowest | `=`, `+=`, `-=`, `*=`, `/=`, `%=` |

---

## Tips

1. **Use `==` for equality** — Kotlin's `==` calls `equals()` and is null-safe
2. **Avoid `!!`** — use `?.`, `?:`, or `let {}` instead
3. **Bitwise operations are named** — `and`, `or`, `xor`, `shl`, `shr` (not `&`, `|`, `^`)
4. **`===` is rarely needed** — only for identity checks (not for value comparison)
5. **Elvis `?:` is powerful** — combine with `return`, `throw`, or `continue`
6. **Smart casts** after `is` checks eliminate the need for explicit `as` casts

---

## Practice Questions

### Q1. What is the result of `10 / 3` and `10.0 / 3` in Kotlin?
<details><summary>Answer</summary>

`10 / 3` = `3` (integer division truncates). `10.0 / 3` = `3.3333333333333335` (double division preserves decimals).
</details>

### Q2. What is the difference between `==` and `===`?
<details><summary>Answer</summary>

`==` checks structural equality (calls `equals()`, null-safe). `===` checks referential equality (same object in memory). Use `==` for value comparison, `===` for identity.
</details>

### Q3. What does the Elvis operator (`?:`) do? Give an example.
<details><summary>Answer</summary>

Returns the left operand if it's not null, otherwise returns the right operand:
```kotlin
val name: String? = null
val display = name ?: "Default"  // "Default"
```
</details>

### Q4. Write code to check if a number is between 1 and 100 using the `in` operator.
<details><summary>Answer</summary>

```kotlin
val num = 42
if (num in 1..100) {
    println("$num is between 1 and 100")
}
```
</details>

### Q5. How do you perform a left bit shift by 3 in Kotlin?
<details><summary>Answer</summary>

```kotlin
val result = 1 shl 3  // 8
```
Kotlin uses named functions (`shl`, `shr`, `ushr`, `and`, `or`, `xor`, `inv`) instead of symbols.
</details>

### Q6. What is the safe call operator and why is it important?
<details><summary>Answer</summary>

`?.` calls a method only if the object is not null, returning null otherwise:
```kotlin
val name: String? = null
println(name?.length)  // null (no NullPointerException)
```
It prevents NPEs without verbose null checks.
</details>

### Q7. What happens with `val x: Int? = 128; val y: Int? = 128; println(x === y)`?
<details><summary>Answer</summary>

Prints `false`. Nullable `Int?` values are boxed as `Integer` objects. JVM caches integers only from -128 to 127. Values of 128 create separate objects, so referential equality (`===`) is false.
</details>

### Q8. Write code using `as?` to safely cast an `Any` to `String`.
<details><summary>Answer</summary>

```kotlin
val obj: Any = 42
val str: String? = obj as? String
println(str)  // null (no ClassCastException)
```
</details>

### Q9. What is short-circuit evaluation? Which operators use it?
<details><summary>Answer</summary>

Short-circuit evaluation stops evaluating as soon as the result is determined. `&&` stops on first `false`. `||` stops on first `true`.
```kotlin
val list = emptyList<Int>()
if (list.isNotEmpty() && list[0] > 0) { /* safe: list[0] never executes */ }
```
</details>

### Q10. Explain the difference between `as` and `as?`.
<details><summary>Answer</summary>

`as` is an unsafe cast — throws `ClassCastException` if cast fails. `as?` is a safe cast — returns `null` if cast fails.
</details>

### Q11. What is the `!!` operator and when should you use it?
<details><summary>Answer</summary>

`!!` converts a nullable type to non-null, throwing `NullPointerException` if the value is null. Use it ONLY when you're absolutely certain the value isn't null and a runtime exception would indicate a programming error.
</details>

### Q12. Write an expression using the range operator to create: 1 to 20, step 3.
<details><summary>Answer</summary>

```kotlin
val range = 1..20 step 3  // 1, 4, 7, 10, 13, 16, 19
```
</details>

### Q13. What does `inv()` do on an integer?
<details><summary>Answer</summary>

`inv()` performs bitwise inversion (flips all bits). For an Int, `0.inv()` = `-1` (all bits flipped from 0000...0000 to 1111...1111 in two's complement).
</details>

### Q14. How does Kotlin's `==` handle null values?
<details><summary>Answer</summary>

Kotlin's `==` is null-safe. `null == null` returns `true`. `null == "hello"` returns `false` (no NPE). Under the hood, `a == b` translates to `a?.equals(b) ?: (b === null)`.
</details>

### Q15. Write code demonstrating operator overloading for a `Vector2D` class with `+` and `*`.
<details><summary>Answer</summary>

```kotlin
data class Vector2D(val x: Double, val y: Double) {
    operator fun plus(other: Vector2D) = Vector2D(x + other.x, y + other.y)
    operator fun times(scalar: Double) = Vector2D(x * scalar, y * scalar)
}

val v1 = Vector2D(1.0, 2.0)
val v2 = Vector2D(3.0, 4.0)
println(v1 + v2)    // Vector2D(4.0, 6.0)
println(v1 * 2.5)   // Vector2D(2.5, 5.0)
```
</details>

---

## Interview Questions

### Q1. How does Kotlin differentiate between structural and referential equality?
<details><summary>Answer</summary>

- `==` (structural equality): Calls `equals()`. Null-safe → `a == b` compiles to `a?.equals(b) ?: (b === null)`.
- `===` (referential equality): Checks if two references point to the same object in memory. No `equals()` call.
On JVM, `===` checks pointer identity. Due to boxing, `Int?` values > 127 may have `===` return false even for equal values.
</details>

### Q2. Explain Kotlin's null safety operators with examples.
<details><summary>Answer</summary>

1. **Safe call `?.`**: `name?.length` — returns null if name is null
2. **Elvis `?:`**: `name ?: "default"` — provides fallback for null
3. **Not-null assertion `!!`**: `name!!` — throws NPE if null
4. **Safe cast `as?`**: `obj as? String` — returns null if cast fails

Best practice: Prefer `?.` and `?:`. Avoid `!!`. Use `let` for multiple operations on nullable.
</details>

### Q3. Why does Kotlin use named functions for bitwise operations instead of symbols?
<details><summary>Answer</summary>

Kotlin designers chose named functions (`and`, `or`, `xor`, `shl`, `shr`) for readability and to avoid ambiguity with other operators. As infix functions, they read naturally: `flags and mask`. This also avoids confusion with logical operators (`&&`, `||`). The named approach makes intent clearer, especially for complex bit manipulations.
</details>

### Q4. What is operator overloading in Kotlin? How does it differ from Java?
<details><summary>Answer</summary>

Kotlin supports operator overloading via the `operator` keyword on specific function names (`plus`, `minus`, `times`, `get`, `invoke`, etc.). Java doesn't support operator overloading. In Kotlin, `a + b` calls `a.plus(b)`. You can define custom behavior for operators on your classes, but overloading is limited to predefined operator functions — you can't create new operators.
</details>

### Q5. Explain the Elvis operator and show a practical use case.
<details><summary>Answer</summary>

The Elvis operator `?:` returns the left operand if not null, otherwise the right:
```kotlin
// Practical: Early return in functions
fun processUser(user: User?) {
    val name = user?.name ?: return  // Returns if user or name is null
    val email = user.email ?: throw IllegalStateException("Email required")
    // Process with non-null name and email
}
```
It's commonly used with `return`, `throw`, and `continue` for early exits.
</details>

### Q6. How does smart casting work with the `is` operator?
<details><summary>Answer</summary>

After a type check with `is`, the compiler automatically casts the variable within the checked scope:
```kotlin
fun process(obj: Any) {
    if (obj is String) {
        println(obj.length)  // Smart-cast to String
    }
}
```
Limitations: Won't work with mutable `var` properties (may change between check and use), open properties, or custom getters.
</details>

### Q7. What is the difference between `until` and `..` in ranges?
<details><summary>Answer</summary>

- `1..10` creates range 1 to 10 **inclusive** (includes 10)
- `1 until 10` creates range 1 to 9 **exclusive** (excludes 10)
- `0..<10` is the newer syntax (Kotlin 1.8+) equivalent to `until`

Use `until` / `..<` for zero-based indexing: `for (i in 0 until list.size)`.
</details>

### Q8. Can you chain safe calls? What happens?
<details><summary>Answer</summary>

Yes. Each `?.` short-circuits to null if the receiver is null:
```kotlin
val city = user?.address?.city?.uppercase()
// If any part is null, the entire chain returns null
// Equivalent to:
// if (user != null && user.address != null && user.address.city != null)
//     user.address.city.uppercase()
// else null
```
</details>

### Q9. What is the `in` operator and how does it work internally?
<details><summary>Answer</summary>

`in` checks containment:
- `x in collection` calls `collection.contains(x)`
- `x in range` checks if x is within the range bounds
- `x !in collection` negates the check
- In `when` expressions: `in 1..10` checks range membership

You can overload it with `operator fun contains(element: T): Boolean`.
</details>

### Q10. Explain augmented assignment operators and a potential pitfall.
<details><summary>Answer</summary>

`+=`, `-=`, `*=`, `/=`, `%=` combine operation with assignment. Pitfall: With `val` collections, `+=` can mean two things:
```kotlin
val list = mutableListOf(1, 2)
list += 3  // Calls list.plusAssign(3) — modifies in place

var list2 = listOf(1, 2)
list2 += 3  // Creates new list, reassigns — list2 = list2.plus(3)
```
If both `plus` and `plusAssign` are available, it causes ambiguity with `var` and mutable collections.
</details>

### Q11. How is `==` null-safe in Kotlin?
<details><summary>Answer</summary>

`a == b` compiles to: `a?.equals(b) ?: (b === null)`. So:
- If `a` is null: checks if `b` is also null (returns `true`) or not (returns `false`)
- If `a` is not null: calls `a.equals(b)`
- No `NullPointerException` is ever thrown, unlike Java's `a.equals(b)` when `a` is null.
</details>

### Q12. What is the `..` operator under the hood?
<details><summary>Answer</summary>

`a..b` calls `a.rangeTo(b)` which creates an `IntRange` (or `LongRange`, `CharRange`). These implement `Iterable` and `ClosedRange`. The compiler optimizes for-loops over ranges to primitive-based counting loops with no object allocation.
</details>

### Q13. When would you use `===` (referential equality)?
<details><summary>Answer</summary>

Rarely. Use cases: singleton checks, object identity in caching, checking if two variables point to the exact same instance (e.g., verifying flyweight/pool patterns). For value comparison, always use `==`.
</details>

### Q14. What operators can be overloaded in Kotlin?
<details><summary>Answer</summary>

Arithmetic: `plus`, `minus`, `times`, `div`, `rem`, `unaryPlus`, `unaryMinus`, `inc`, `dec`
Comparison: `compareTo`
Equality: `equals`
Index: `get`, `set`
Invocation: `invoke`
Destructuring: `component1()`, `component2()`, etc.
Range: `rangeTo`, `rangeUntil`
Containment: `contains` (for `in`)
Iteration: `iterator`
</details>

### Q15. Compare Kotlin's type cast operators with Java's casting.
<details><summary>Answer</summary>

| Feature | Kotlin | Java |
|---------|--------|------|
| Unsafe cast | `as` — throws ClassCastException | `(Type)` — throws ClassCastException |
| Safe cast | `as?` — returns null | No equivalent (must check first) |
| Type check | `is` with smart cast | `instanceof` (no smart cast until Java 16+) |
| Negated check | `!is` | `!(x instanceof Type)` |

Kotlin's smart casting after `is` eliminates the redundant explicit cast that Java traditionally requires.
</details>
