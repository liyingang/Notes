# Groovy

Groovy是一门基于JVM的脚本语言。它在兼容Java语法的同时，借鉴了Ruby、Python等语言的特性，有自己一套简洁而灵活的语法。同时，运行在JVM上也意味着它也可以使用Java语言编写的库。这两点结合，让Groovy极其适合编写Java代码的测试脚本。

## Hello World

新建一个HelloWorld.groovy文件，写入以下代码：

```groovy
public class HelloWorld {
    public static void main(String []args) {
        System.out.println("Hello, World!");
    }
}
```

之后，用groovy命令执行：

```bash
PS D:\groovy_example> groovy ./HelloWorld.groovy
Hello, world!
```

**Groovy是兼容Java的语法的，所以你可以直接编写Java的代码**。因为Groovy兼容Java的语法，所以**Groovy中if、switch、while、for等语句的语法与Java是一样的**，这里我们不再赘述。下面让我们来看一看Groovy中富有特色的语法。

Groovy的Hello world：

```groovy
static main(args) {
    println "Hello, world!"
}

// Groovy的main方法不需要定义在一个类中（实际上你甚至不需要定义main方法）；
// Groovy的方法调用可以用空格代替括号；
// 分号可以省略
```

## Groovy中变量的定义与输出

```groovy

// 变量不需要写明具体的类型（实际上def关键字也可以省略）

def num = 1
def str = 'Hello'

// 可以很轻松地定义Java中的List和Map

def list = [1,2,3,4,5]
def map = [ one : 1, two : 2, three : 3]

// 可以通过形如${var}的方式来进行字符串格式化（花括号也可以省略，字符串的两端用单引号的话则不会进行格式化）

println "number is ${num}, string is ${str}, list is ${list}, map is ${map}"

// number is 1, string is Hello, list is [1, 2, 3, 4, 5], map is [one:1, two:2, three:3]
```

## 类的定义

```groovy
class Person {
    private String name
    int age
    def greet(String otherPerson) {
        // 方法定义中可以省略return关键字
        "Hello ${otherPerson}, my name is ${name}"
    }
}

static main(args) {
    // 调用构造函数时可以用形如(name: value)的方式给特定参数传参
    Person alice = new Person(age: 20)

    // Groovy会自动为变量加上get与set方法,下面实际上调用了set方法
    alice.name = 'Alice'
    println alice.greet('Bob')
    println alice.age
}

// Hello Bob, my name is Alice
// 20
```

## 闭包与函数式编程

```groovy
numbers = 1..10
sum = numbers.findAll { num -> num  % 2 == 0}
        .each { num -> print "${num} " }
        .sum()
println "sum is ${sum}"

// 2 4 6 8 10 sum is 30

```

闭包形式定义: { [closureParameters -> ] statements }

**Groovy中，闭包可以作为对象、参数和返回值**

```groovy

// 闭包addX会返回另外一个闭包。之后我们直接调用闭包addX，得到了闭包add1，再直接调用闭包add1进行输出
def addX = { int x ->
    return { int y ->
        println "x is $x, y is $y"
        return x + y
    }
}
def add1 = addX(1)
println add1(2)

// x is 1, y is 2
// 3

// Groovy甚至可以动态地给一个类或对象添加方法
class Cat {
    def name
}
Cat kitty = new Cat(name: 'Kitty')
kitty.metaClass.meow = { println "Meow" }
kitty.meow()

```

## 语法糖

语法糖实在是太甜了

### 灵活的Range定义

```groovy

// 定义List
def arrayList = 'A'..'D' as ArrayList
// 利用 ..< 运算符可以定义半开区间,as关键字可以详细指定List或Map的类型
def linkedList = 1..<5 as LinkedList
println arrayList
println linkedList
// assert可以检查表达式是否为true
assert arrayList instanceof ArrayList       // [A, B, C, D]
assert linkedList instanceof LinkedList     // [1, 2, 3, 4]

// 往List里添加元素
def list1 = []
// 相加是返回的一个新的List
def list2 = list1 + 'Hello'
println "$list1 $list2"         // [] [Hello]

// 使用 << 号，修改原有List
def list3 = list2 << 'World'
println "$list2 $list3"         // [Hello, World] [Hello, World]
println([1,2]+[3,4])            // [1, 2, 3, 4]


```

### times方法

```groovy
3.times { i -> print "$i " }
def list = [1,2,3]
list.size().times { i -> print "$i "}

// 0 1 2 0 1 2
```

### 正则表达式

```groovy
def text = "some text to match"
def word = "text"
// 用~运算符来定义一个正则的pattern
def pattern1 = ~/match/
def pattern2 = ~"$word"
// 用=~运算符匹配
def matcher1 = (text =~ pattern1)
def matcher2 = (text =~ pattern2)
if (matcher1) {
    println "Matched"
}
if (matcher2) {
    println "Matched"
}
if (text =~ /some/) {
    println "Matched"
}

// Matcher
def pattern = ~/A is (\d+), B is (\d+)/
def matcher = ("A is 10, B is 15. A is 20, B is 25" =~ pattern)
// pattern第一次匹配到的是”A is 10, B is 15”
// 因此matcher[0][1]是10而matcher[0][2]是15（matcher[0][0]是匹配到的”A is 10, B is 15”）
assert matcher[0][1] == "10" && matcher[0][2] == "15"
assert matcher[1][1] == "20" && matcher[1][2] == "25"
```

### with方法

通过with方法，我们在调用对象方法时可以省略对象名。

```groovy
StringBuilder stringBuilder = new StringBuilder()
stringBuilder.with {
    append "W"
    append "o"
    append "w"
}
println stringBuilder.toString()        // Wow
```

### 增强的switch case

和Java类似，但支持List、Range、整数、数字、正则、闭包的判断

```groovy
def switchFunction(def x)
{
    def res
    switch ( x ) {
        case [1, 2, 3]:
            res = "in list"
            break
        case 10..30:
            res = "in range"
            break
        case Integer:
            res = "integer"
            break
        case Number:
            res = "number"
            break
        case ~/fo*/:
            res = "match regex"
            break
        case { str -> (str in String && str.length() == 1) }:
            res = "closure returns true"
            break
        default:
            println "default"
    }
}
assert switchFunction(1) == "in list"
assert switchFunction(11) == "in range"
assert switchFunction(4) == "integer"
assert switchFunction(0.1) == "number"
assert switchFunction("foooo") == "match regex"
assert switchFunction("a") == "closure returns true"
```

### 其他语法糖

```groovy
// 同时定义两个变量
def (var1, var2) = [1, 2]

// 交换两个变量
int a = 1, b = 2
(a, b) = [b, a]

// 快速创建一个新线程
Thread thr = Thread.start {
    println "Hello, world!"
}
thr.join()

// 通过子进程执行shell命令
def process = "cmd /c dir".execute()
println process.text

// 函数默认参数
int function ( int a = 1, int b = 2 ) { a + b }
println function()

// for in语法
for (i in 1..10){
    println "$i"
}

// 在闭包中递归调用自身
def fibonacci = { int n ->
    n == 1 ? 1 : n + call(n - 1)
}
println fibonacci(10)

// 使用Range访问List
list = [1, 2, 3, 4, 5]
assert list[0..2] == [1, 2, 3]
assert list[0..-2] == [1, 2, 3, 4]

// 文件读写
def file = new File('test.txt')
file.newOutputStream() << "Hello, world!"
assert file.text == "Hello, world!"
```