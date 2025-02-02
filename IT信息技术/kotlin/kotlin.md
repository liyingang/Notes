# Kotlin  

## 程序入口点

```Kotlin
fun main() {
    println("Hello world!")
}

// main 的另一种形式接受可变数量的 String 参数
fun main(args: Array<String>) {
    print("Hello ")
    println(args.contentToString())
}

```

## 导入

```Kotlin
import org.example.Message // 现在 Message 可以不用限定符访问

import org.example.* // “org.example”中的一切都可访问

// 如果出现名字冲突，可以使用 as 关键字在本地重命名冲突项来消歧义
import org.example.Message // Message 可访问
import org.test.Message as TestMessage // TestMessage 代表“org.test.Message”

```

## 变量声明  

两个不同的关键字`val`和 `var` 声明变量

+ `val` 常量  
+ `var` 变量

```Kotlin
val languageName: String = "Kotlin" //不变
var count: Int = 10 //可变
count = 15

// 初始化变量，也可以先声明变量，然后再初始化它，但必须指定类型
val c: Int
c = 3

```

**Kotlin数值**有`Int`、`Byte`、`Short`、`Long`、`Float` 和 `Double`等  

kotlin可以自动进行类型判断，编译时完成

```Kotlin
val languageName = "Kotlin"
val upperCaseName = languageName.toUpperCase()

// Fails to compile
languageName.inc() //inc() 是一个 Int 运算符函数
```

默认情况下，Kotlin 变量不能持有 null 值。这意味着以下代码段无效：

```Kotlin
// Fails to compile
val languageName: String = null

// 可以在变量类型后面加上 ? 后缀，将变量指定为可为 null
val languageName: String? = null
```

## 基本类型

### 数字

#### 整数类型

| 类型 | 大小（比特数） | 最小值 | 最大值 |
| -------- | -------- | -------- | -------- |
| Byte | 8 | -128 | 127 |
|Short|16|-32768|32767|
|Int|32|-2,147,483,648 (-2^31^)|2,147,483,647 (2^31^ - 1)|
|Long|64| -2^63^|2^63^ - 1|

如需显式指定 Long 值，请给该值追加后缀 L

#### 浮点类型

|类型|大小（比特数）|有效数字比特数|指数比特数|十进制位数|
| -------- | -------- | -------- | -------- |-------- |
|Float|32|24|8|6-7|
|Double|64|53|11|15-16|

如需将一个值显式指定为 Float 类型，请添加 f 或 F 后缀

Kotlin 中的数字没有隐式拓宽转换。

```Kotlin
fun printDouble(d: Double) { print(d) }

val i = 1
val d = 1.0
val f = 1.0f

printDouble(d)
// printDouble(i) // 错误：类型不匹配
// printDouble(f) // 错误：类型不匹配

```

如需将数值转换为不同的类型，请使用显式转换。

#### 数字字面常量

+ 十进制: 123
  + Long 类型用大写 L 标记: 123L
+ 十六进制: 0x0F
+ 二进制: 0b00001011

!!! warning
    Kotlin 不支持八进制。

Kotlin 同样支持浮点数的常规表示方法:

+ 默认 double：123.5、123.5e10
+ Float 用 f 或者 F 标记: 123.5f

可以使用下划线使数字常量更易读

```Kotlin
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010
```

#### 显式数字转换

较小类型并不是较大类型的子类型。因此较小的类型**不能** 隐式转换为较大的类型。

```Kotlin
val b: Byte = 1 // OK, 字面值会静态检测
// val i: Int = b // 错误
val i1: Int = b.toInt()
```

所有数字类型都支持转换为其他类型：

+ toByte(): Byte
+ toShort(): Short
+ toInt(): Int
+ toLong(): Long
+ toFloat(): Float
+ toDouble(): Double

很多情况都不需要显式类型转换，因为类型会从上下文推断出来， 而算术运算会有重载做适当转换，例如：

```Kotlin
val l = 1L + 3 // Long + Int => Long

// 整数相除只能是整数

```

#### 位运算

完整的位运算列表：

+ shl(bits) – 有符号左移
+ shr(bits) – 有符号右移
+ ushr(bits) – 无符号右移
+ and(bits) – 位与
+ or(bits) – 位或
+ xor(bits) – 位异或
+ inv() – 位非

#### 浮点数比较

相等，大小略
区间实例以及区间检测：a..b、 x in a..b、 x !in a..b

!!! note 非静态类型比较
    NaN == NaN
    NaN > 任意数
    -0.0 < 0.0

```Kotlin
// 静态类型作为浮点数的操作数
println(Double.NaN == Double.NaN)                 // false
// 静态类型并非作为浮点数的操作数
// 所以 NaN 等于它本身
println(listOf(Double.NaN) == listOf(Double.NaN)) // true

// 静态类型作为浮点数的操作数
println(0.0 == -0.0)                              // true
// 静态类型并非作为浮点数的操作数
// 所以 -0.0 小于 0.0
println(listOf(0.0) == listOf(-0.0))              // false

println(listOf(Double.NaN, Double.POSITIVE_INFINITY, 0.0, -0.0).sorted())
// [-0.0, 0.0, Infinity, NaN]
```

### 布尔

Boolean 类型表示可以有 true 与 false 两个值的布尔对象。

```Kotlin
val myTrue: Boolean = true
val myFalse: Boolean = false
val boolNull: Boolean? = null

println(myTrue || myFalse)
// true
println(myTrue && myFalse)
// false
println(!myTrue)
// false
println(boolNull)
// null
```

### 字符

字符用 Char 类型表示。 字符字面值用单引号括起来: '1'。

编码其他字符要用 Unicode 转义序列语法：'\uFF00'

```Kotlin
val aChar: Char = 'a'

println(aChar)
println('\n') // 输出一个额外的换行符
println('\uFF00')
```

如果字符变量的值是数字，那么可以使用 digitToInt() 函数将其显式转换为 Int 数字。

### 字符串

Kotlin 中字符串用 String 类型表示。
如需连接字符串，可以用 + 操作符。只要表达式中的第一个元素是字符串。
字符可以使用索引运算符访问: s[i]，可以使用 for 循环遍历这些字符：

```Kotlin
val s = "abc" + 1
for (c in str) {
    println(c)
}
```

#### 多行字符串

多行字符串可以包含换行以及任意文本。 它使用三个引号（"""）分界符括起来，内部没有转义并且可以包含换行以及任何其他字符：

```Kotlin
val text = """
    for (c in "foo")
        print(c)
"""

// 如需删掉多行字符串中的前导空格，请使用 trimMargin() 函数
val text = """
|Tell me and I forget.
|Teach me and I remember.
|Involve me and I learn.
|(Benjamin Franklin)
    """.trimMargin()
// 默认以 | 作为边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">")
```

#### 字符串模板

模板表达式以美元符（$）开头，要么由一个变量名构成,用花括号括起来的表达式也可以:

```Kotlin
val i = 10
println("i = $i") 
// i = 10

val letters = listOf("a","b","c","d","e")
println("Letters: $letters") 
// Letters: [a, b, c, d, e]

val s = "abc"
println("$s.length is ${s.length}") 
// abc.length is 3

// 美元符 $
val price = """
${'$'}_9.99
"""
```

### 数组

Kotlin 中最常见的数组类型是对象类型数组，由 Array 类表示。

```Kotlin
var riversArray = arrayOf("Nile", "Amazon", "Yangtze")

// 使用 += 赋值操作创建了一个新的 riversArray，
// 复制了原始元素并添加了“Mississippi”
riversArray += "Mississippi"
println(riversArray.joinToString())
// Nile, Amazon, Yangtze, Mississippi

// 创建数组 arrayOf(), arrayOfNulls() or emptyArray(). Array constructor
val simpleArray = arrayOf(1, 2, 3)
println(simpleArray.joinToString())
// 1, 2, 3
val nullArray: Array<Int?> = arrayOfNulls(3)
println(nullArray.joinToString())
// null, null, null
var exampleArray = emptyArray<String>()
var exampleArray: Array<String> = emptyArray()

// Array constructor
val initArray = Array<Int>(3) { 0 }
println(initArray.joinToString())
// 0, 0, 0
val asc = Array(5) { i -> (i * i).toString() }
asc.forEach { print(it) }
// 014916

// 多维数组
val twoDArray = Array(2) { Array<Int>(2) { 0 } }
println(twoDArray.contentDeepToString())
// [[0, 0], [0, 0]]

val threeDArray = Array(2) { Array(2) { Array<Int>(2) { 0 } } }
// [[[0, 0], [0, 0], [0, 0]], [[0, 0], [0, 0], [0, 0]], [[0, 0], [0, 0], [0, 0]]]

// 比较数组
val simpleArray = arrayOf(1, 2, 3)
val anotherArray = arrayOf(1, 2, 3)

// Compares contents of arrays
println(simpleArray.contentEquals(anotherArray))
// true

// Using infix notation, compares contents of arrays after an element 
// is changed
simpleArray[0] = 10
println(simpleArray contentEquals anotherArray)
// false

// 常用转换
sumArray.sum()                  //求和
simpleArray.shuffle()           //随机变换，洗牌

// 转换为 List 或 Set
val simpleArray = arrayOf("a", "b", "c", "c")
// Converts to a Set
println(simpleArray.toSet())
// [a, b, c]

// Converts to a List
println(simpleArray.toList())
// [a, b, c, c]

```

## 类型检测与类型转换

使用 is 操作符或其否定形式 !is 在运行时检测对象是否符合给定类型。编译器跟踪不可变值的 is-检测以及显式转换，并在必要时自动插入（安全的）转换：

```Kotlin
if (x is String) {
    print(x.length) // x 自动转换为字符串
}
// 编译器足够聪明，能够知道如果反向检测导致返回那么该转换是安全的
if (x !is String) return

print(x.length) // x 自动转换为字符串

// “不安全的”转换操作符 中缀操作符 as
val x: String? = y as String?

// “安全的”（可空）转换操作符
val x: String? = y as? String
```

## 控制流程

### 条件

#### If 表达式

```Kotlin
if (a > b) {
    max = a
} else {
    max = b
}

// 作为表达式
max = if (a > b) a else b

val maxLimit = 1
val maxOrLimit = if (maxLimit > a) maxLimit else if (a > b) a else b

// if 表达式的分支可以是代码块，这种情况最后的表达式作为该块的值
val max = if (a > b) {
    print("Choose a")
    a
} else {
    print("Choose b")
    b
}

```

#### When 表达式

很像switch语句，复数性选择使用`when`语法

```Kotlin
when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    // 可以用多个值指向一个结果
    3, 4 -> print("x == 3 or x == 4")
    // 可以用任意表达式
    s.toInt() -> print("s encodes x")
    // 还可以检测一个值在（in）或者不在（!in）一个区间或者集合中
    in 1..10 -> print("x is in the range")
    in validNumbers -> print("x is valid")
    !in 10..20 -> print("x is outside the range")
    // 可以用is
    is String -> x.startsWith("prefix")
    else -> {
        print("otherwise")
    }
    // enum的情况下，完全列举是不用写else的
}

```

### 循环

#### For 循环

for 循环可以对任何提供迭代器（iterator）的对象进行遍历，foreach的感觉

```Kotlin
for (item in collection) print(item)

for (item: Int in ints) {
    // ……
}

for (i in 1..3) {
    println(i)
}
for (i in 6 downTo 0 step 2) {
    println(i)
}
// 对区间或者数组的 for 循环会被编译为并不创建迭代器的基于索引的循环
for (i in array.indices) {
    println(array[i])
}
for ((index, value) in array.withIndex()) {
    println("the element at $index is $value")
}
```

#### while 循环

```Kotlin
// while 先检查条件，如果满足，则执行主体，然后再返回到条件检查
while (x > 0) {
    x--
}

// do-while 先执行主体，然后检查条件。
// 所以 do-while 的主体至少执行一次，不管条件如何。
do {
    val y = retrieveData()
} while (y != null) // y 在此处可见
```

### 返回与跳转

Kotlin 有三种结构化跳转表达式：

+ `return` 默认从最直接包围它的函数或者匿名函数返回。
+ `break` 终止最直接包围它的循环。
+ `continue` 继续下一次最直接包围它的循环。

```Kotlin
val s = person.name ?: return
```

在 Kotlin 中任何表达式都可以用标签来标记。 标签的格式为标识符后跟 @ 符号，例如：abc@、fooBar@。

```Kotlin
loop@ for (i in 1..100) {
    // ……
}

loop@ for (i in 1..100) {
    for (j in 1..100) {
        if (……) break@loop
    }
}
```

## 异常

### 异常类

Kotlin 中所有异常类继承自 Throwable 类，使用 throw 表达式来抛出异常：
可以有零到多个 catch 块，finally 块可以省略。 但是 catch 与 finally 块至少需有一个。

```Kotlin
try {
    // 一些代码
} catch (e: SomeException) {
    // 处理程序
    // throw Exception("Hi There!")
} finally {
    // 可选的 finally 块
}

// Try 是一个表达式
val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }

```


## 函数

带有两个 Int 参数、返回 Int 的函数

```Kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}

// 函数体可以是表达式。其返回类型可以推断出来
fun sum(a: Int, b: Int) = a + b

fun maxOf(a: Int, b: Int) = if (a > b) a else b


// void返回值，Unit 返回类型可以省略
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

// 函数的默认实参
fun foo(a: Int = 0, b: String = "") { …… }

```

## 循环语句

### for 循环  

```Kotlin
fun main() {

    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }

    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}
```

### while 循环

```Kotlin
fun main() {

    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

}
```

## 创建类与实例  

类的属性可以在其声明或主体中列出，且具有类声明中所列参数的默认构造函数会自动可用

```Kotlin

class Rectangle(val height: Double, val length: Double) {
    val perimeter = (height + length) * 2 
}

fun main() {
    val rectangle = Rectangle(5.0, 2.0)
    println("The perimeter is ${rectangle.perimeter}")
}

```

类之间继承由冒号（`:`）声明。默认情况下类都是 `final` 的；如需让一个类可继承， 请将其标记为 `open`

```Kotlin
open class Shape

class Rectangle(val height: Double, val length: Double): Shape() {
    val perimeter = (height + length) * 2 
}
```

## 字符串模板

```Kotlin
fun main() {

    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a" 

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    println(s2)
}
```

## 使用区间

```Kotlin
fun main() {

    val x = 10
    val y = 9
    if (x in 1..y+1) {
        println("fits in range")
    }

}

// 检测某个数字是否在指定区间外
if (-1 !in 0..list.lastIndex) {
    println("-1 is out of range")
}

if (list.size !in list.indices) {
    println("list size is out of valid list indices range, too")
}

// 区间迭代
for (x in 1..5) {
    print(x)
}

// 数列迭代
for (x in 1..10 step 2) {
    print(x)
}

for (x in 9 downTo 0 step 3) {
    print(x)
}

```

## 类型检测与自动类型转换

`is` 操作符检测一个表达式是否某类型的一个实例。 如果一个不可变的局部变量或属性已经判断出为某类型，那么检测后的分支中可以直接当作该类型使用，无需显式转换：

```Kotlin
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // obj 在该条件分支内自动转换成 String
        return obj.length
    }

    // 在离开类型检测分支后，obj 仍然是 Any 类型
    return null
}

// 或者
fun getStringLength(obj: Any): Int? {
    if (obj !is String) return null

    // obj 在这一分支自动转换为 String
    return obj.length
}

```



## 创建 DTO（POJO/POCO）

```Kotlin
data class Customer(val name: String, val email: String)

// 会为 Customer 类提供以下功能
/*
    所有属性的 getter （对于 var 定义的还有 setter）
    equals()
    hashCode()
    toString()
    copy()
    所有属性的 component1()、 component2()……等等
*/
```

## 类

```Kotlin
class Person { /*……*/ }

//  如果一个类没有类体，可以省略花括号
class Empty


```

### 构造函数

一个主构造函数并可能有一个或多个次构造函数。主构造函数在类头中声明，它跟在类名与可选的类型参数后。

```Kotlin
class Person constructor(firstName: String) { /*……*/ }

//  如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
class Person(firstName: String) { /*……*/ }

// 初始动作只能在init 函数中，会按照书写顺序执行init方法内的代码
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

// 主构造的参数可以在初始化块中使用。它们也可以在类体内声明的属性初始化器中使用
class Customer(name: String) {
    val customerKey = name.uppercase()
}

// 简易声明
class Person(
    val firstName: String, 
    val lastName: String, 
    var isEmployed: Boolean = true
)

// 声明类属性时，可以使用尾部逗号
class Person(
    val firstName: String,
    val lastName: String,
    var age: Int, // 尾部逗号
) { /*……*/ }

```

### 次构造函数

类也可以声明前缀有 的次构造函数：constructor

```Kotlin
class Person(val pets: MutableList<Pet> = mutableListOf())

class Pet {
    constructor(owner: Person) {
        owner.pets.add(this) // adds this pet to the list of its owner's pets
    }
} 
```

委托到同一个类的另一个构造函数用 关键字即可：this

```Kotlin
// 初始化块中的代码实际上会成为主构造函数的一部分
// 所有初始化块与属性初始化器中的代码都会在次构造函数体之前执行
class Person(val name: String) {
    val children: MutableList<Person> = mutableListOf()
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}

// 构造函数的可见性是 public,如果不希望有公有构造函数，
// 那么要声明一个带有非默认可见性的空的主构造函数
class DontCreateMe private constructor() { /*……*/ }

```

### 创建类的实例

```Kotlin

val invoice = Invoice()

val customer = Customer("Joe Smith")
```

### 声明类属性

Kotlin 类中的属性既可以用关键字 var 声明为可变的， 也可以用关键字 val 声明为只读的。

```Kotlin
class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}

// 使用一个属性，以其名称引用它即可
fun copyAddress(address: Address): Address {
    val result = Address() // Kotlin 中没有“new”关键字
    result.name = address.name // 将调用访问器
    result.street = address.street
    // ……
    return result
}

// 声明一个属性的完整语法如下：
var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]

// 只读属性不允许 setter

```

### Getter 与 Setter

自定义 getter 的示例：

```Kotlin
class Rectangle(val width: Int, val height: Int) {
    val area: Int 
        get() = this.width * this.height
}

fun main() {
    val rectangle = Rectangle(3, 4)
    println("Width=${rectangle.width}, height=${rectangle.height}, area=${rectangle.area}")
}

// 如果可以从 getter 推断出属性类型，则可以省略它：
val area get() = this.width * this.height

```

自定义的 setter

```Kotlin
// setter 参数的名称是 value,他的规范
var stringRepresentation: String
    get() = this.toString()
    set(value) {
        setDataFromString(value) // 解析字符串并赋值给其他属性
    }

```

""" 延迟初始化属性与变量

为处理这种情况，你可以用 lateinit 修饰符标记该属性：


```Kotlin
public class MyTest {
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSubject()
    }

    @Test fun test() {
        subject.method()  // 直接解引用
    }
}
```

## 接口

```Kotlin
interface MyInterface {
    fun bar()
    fun foo() {
      // 可选的方法体
    }
}

class Child : MyInterface {
    override fun bar() {
        // 方法体
    }
}

// 可以在接口中定义属性
interface MyInterface {
    val prop: Int // 抽象的

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    override val prop: Int = 29
}

// 接口继承
interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
    // 不必实现“name”
    override val firstName: String,
    override val lastName: String,
    val position: Position
) : Person

// 解决覆盖冲突
interface A {
    fun foo() { print("A") }
    fun bar()
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}
```

