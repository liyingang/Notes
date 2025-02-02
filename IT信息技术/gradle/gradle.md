# Gradle

## 基本概念

**项目**

Gradle项目是一个可以构建的软件，例如应用程序或库。单个项目称为根项目，多项目构建包括一个根项目和任意数量的子项目。

**构建脚本**

构建脚本向 Gradle 详细介绍了构建项目所需采取的步骤。每个项目可以包含一个或多个构建脚本。

**依赖管理**

依赖管理是一种用于声明和解析项目所需的外部资源的自动化技术。每个项目通常都包含许多外部依赖项，Gradle 将在构建过程中解决这些依赖项。

**任务**

任务是基本的工作单元，例如编译代码或运行测试。每个项目都包含在构建脚本或插件中定义的一个或多个任务。

**插件**

插件用于扩展 Gradle 的功能，并可选择向项目贡献任务。

## 项目结构

项目根目录中存在gradlew和文件是使用 Gradle 的明确标志。gradlew.bat

Gradle 项目将类似于以下内容：
```
project
├── gradle                              Ⅰ
│   ├── libs.versions.toml              Ⅱ
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew                             Ⅲ
├── gradlew.bat                         Ⅲ
├── settings.gradle(.kts)               Ⅳ
├── subproject-a
│   ├── build.gradle(.kts)              Ⅴ
│   └── src                             Ⅵ
└── subproject-b
    ├── build.gradle(.kts)              Ⅴ
    └── src                             Ⅵ
```

Ⅰ Gradle 目录用于存储包装文件等
Ⅱ 用于依赖管理的 Gradle 版本目录
Ⅲ Gradle 包装脚本
Ⅳ Gradle 设置文件用于定义根项目名称和子项目
Ⅴ 两个子项目的 Gradle 构建脚本 -subproject-a以及subproject-b
Ⅵ 项目的源代码和/或附加文件

## Gradle 包装器基础知识

执行任何 Gradle 构建的推荐方法是使用 Gradle Wrapper。Wrapper脚本调用声明的 Gradle 版本，并在必要时提前下载它。包装器可作为gradlew（linux）或gradlew.bat(Windows)文件使用。

Wrapper 具有以下优点：
- 在给定 Gradle 版本上标准化项目。
- 为不同用户提供相同的 Gradle 版本。
- 为不同的执行环境（IDE、CI 服务器……）配置 Gradle 版本。

Gradle 调用： *$ gradle build*

## 命令行基础知识

任务名称之前和之后允许使用选项。
gradle [taskName...] [--option-name...]
gradle [--option-name...] [taskName...]

如果指定了多个任务，则应该用空格分隔它们。
gradle [taskName1 taskName2...] [--option-name...]

接受值的选项可以=在选项和参数之间指定，也可以不指定。=推荐使用。
gradle [...] --console=plain

启用行为的选项具有长格式选项，其逆值用 指定--no-。以下是相反的情况。
gradle [...] --build-cache
gradle [...] --no-build-cache


执行任务
$ gradle :taskName

指定任务选项
$ gradle taskName --exampleOption=exampleValue

## 设置文件基础知识

设置文件是每个 Gradle 项目的入口点。对于单项目构建，设置文件是可选的。对于多项目构建，设置文件是必需的并声明所有子项目。

设置文件是一个脚本。它可以是settings.gradle用 Groovy 编写的文件，也可以是settings.gradle.kts用 Kotlin 编写的文件。

```Kotlin
// settings.gradle.kts or settings.gradle

rootProject.name = "root-project"   // 项目名

include("sub-project-a")            // 添加依赖
include("sub-project-b") 
include("sub-project-c") 
```

每个 Gradle 构建至少包含一个构建脚本。添加Gradle 和构建脚本所依赖的库和/或插件或项目源（即源代码）所依赖的库。

构建脚本可以是build.gradle用 Groovy 编写的文件，也可以是build.gradle.kts用 Kotlin 编写的文件。
<div style="display:inline-block;width:49%">

```Kotlin
// build.gradle.kts  Kotlin

plugins {
    id("application")               // 添加插件
}

application {
    mainClass = "com.example.Main"  // 主类的方法
}
```

</div>
<div style="display:inline-block;width:49%">

```Groovy
// build.gradle  Groovy

plugins {
    id 'application'                
}

application {
    mainClass = 'com.example.Main'  
}
```
</div>

  