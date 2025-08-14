# batch脚本

## 命令

Windows 命令行不区分大小写
Ctrl+C组合键来强行终止运行脚本

常用命令

- `VER`   显示正在使用的 MS-DOS 版本
- `ASSOC`   将扩展名与文件类型 (FTYPE) 关联、显示现有关联或删除关联
- `cd` 更改不同的目录，或显示当前目录
  - `CD /d [盘符][路径]` 不同分区的切换
- `CLS` 清除屏幕
- `COPY` 将文件从一个位置复制到另一个位置,`Copy [source] [destination]`,Copy只能复制文件，不能复制文件夹
  - `/y` 覆盖、不覆盖还是全部覆盖(Yes/No/All)
  - `/b 文件1＋文件2＋……文件N 合并后的文件名` 合并文件
- `del` 删除文件而不是目录
  - `/P` 删除每一个文件之前提示确认
  - `/F` 强制删除只读文件。
  - `/S` 从所有子目录删除指定文件
  - `/Q` 安静模式。删除全局通配符时，不要求确认
  - `/A` 根据属性选择要删除的文件
- `DIR` 列出目录的内容
  - `/A` 显示具有指定属性的文件。`D`  目录`R`  只读文件`H`  隐藏文件`A`  准备存档的文件`S`  系统文件`-`  表示“否”的前缀
  - `/B` 使用空格式(没有标题信息或摘要)。
  - `/O` 用分类顺序列出文件。`N`  按名称(字母顺序)`S`按大小(从小到大)`E`按扩展名(字母顺序)`D`按日期/时间(从先到后)`G` 组目录优先`-`颠倒顺序的前缀
  - `/P` 在每个信息屏幕后暂停。
  - `/S` 显示指定目录和所有子目录中的文件
- `DATE` 查找系统日期
- `ECHO` 显示消息，或打开或关闭命令回显
- `EXIT` 退出 DOS 控制台
- `MD` 在当前位置创建一个新目录
- `MOVE` 在目录之间移动文件或目录,`move [source] [destination]`
- `PATH` 显示或设置路径变量
- `PAUSE` 提示用户并等待输入一行内容
- `PROMPT` 更改或重置 cmd.exe 提示符
- `RD` 删除目录，但目录必须为空才能删除
  - rd /s
- `REN` 重命名文件和目录,`ren [oldfile/dirname] [newfile/dirname]`
- `REM` 备注，防止备注内容被执行
- `START` 在新窗口中启动程序，或打开文档
- `TIME` 设置或显示时间
- `TYPE` 将一个或多个文件的内容打印到输出
- `VOL` 显示卷标签
- `ATTRIB` 设置当前目录下文件的属性
  - `attrib –?` 消除属性
  - `attrib +?` 设置属性
  - `/S`  处理当前文件夹及其子文件夹中的匹配文件
  - `/D`  也处理文件夹
- `CHKDSK` 检查磁盘是否存在任何问题
- `CHOICE` 向用户提供选项列表,`CHOICE /c [Options] /m [Message]`
- `CMD` 调用命令提示符的另一个实例
- `COMP` 根据文件大小比较 2 个文件
- `CONVERT` 将卷从 FAT16 或 FAT32 文件系统转换为 NTFS 文件系统
- `DRIVERQUERY` 显示所有已安装的设备驱动程序及其属性
- `EXPAND` 从压缩的 .cab Cabinet 文件中提取文件
- `FIND` 在文件或输入中搜索字符串，输出匹配的行,`FIND [text] [destination]`
- `FORMAT` 将磁盘格式化为使用 Windows 支持的文件系统，例如 FAT、FAT32 或 NTFS，从而覆盖磁盘以前的内容
- `HELP` 显示 Windows 提供的命令列表
- `IPCONFIG` 显示 Windows IP 配置。 按连接显示配置以及该连接的名称
- `LABEL` 添加、设置或删除磁盘标签
- `MORE` 一次显示一个或多个文件的内容，一次一屏
- `NET` 提供各种网络服务,下面是变体
  - `net accounts`
  - `net computer`
  - `net config`
  - `net continue`
  - `net file`
  - `net group`
  - `net help`
  - `net helpmsg`
  - `net localgroup`
  - `net name`
  - `net pause`
  - `net print`
  - `net send`
  - `net session`
  - `net share`
  - `net start`
  - `net statistics`
  - `net stop`
  - `net time`
  - `net use`
  - `net user`
  - `net view`
- `PING` 通过网络将 ICMP/IP"回显"数据包发送到指定地址
- `SHUTDOWN` 关闭计算机，或注销当前用户
- `SORT` 从源文件中获取输入，并按字母顺序（从 A 到 Z 或 Z 到 A）对其内容进行排序。它在控制台上打印输出
- `SUBST` 将驱动器号分配给本地文件夹、显示当前分配或删除分配
- `SYSTEMINFO` 显示计算机及其操作系统的配置
- `TASKKILL` 结束一项或多项任务
- `TASKLIST` 列出任务，包括任务名称和进程 ID (PID)
- `XCOPY` 以更高级的方式复制文件和目录，复制文件和目录树。
  - `/S` 复制非空的目录和子目录,省略 /s，xcopy 将在一个目录中工作,不包括空的子目录。（默认情况下，xcopy 不复制隐藏或系统文件）
  - `/E` 复制所有子目录，包括空目录。
  - `/H` 复制具有隐藏和系统文件属性的文件。
  - `/T` 只复制子目录结构（即目录树），不复制文件。要复制空目录，必须包含 /e 选项。
  - `/Y` 禁止提示您确认要覆盖现存的目标文件。
- `TREE` 以任何递归级别或深度显示当前目录的所有子目录的树
- `FC` 列出了两个文件之间的实际差异
- `DISKPART` 显示并配置磁盘分区的属性
- `TITLE` 设置控制台窗口中显示的标题
- `SET` 显示当前系统上的环境变量列表

### echo和@

- `echo`:
    1. 回显命令内容
        格式：echo [你的文字]
        在命令行窗口会打印命令内容

    2. 打开或关闭回显功能
        格式：echo [{ on|off }]

    3. 输出空行
        格式：echo. 或echo, 或echo: [>文件]
        相当于输入一个回车。可以作为其它命令的输入，比如echo.|time即相当于在TIME命令执行后给出一个回车。所以执行时系统会在显示当前时间后，自动返回到DOS提示符状态
        另外“.”可以用，：；”／]＋\ 这些任一符号替代

    4. 答复命令中的提问
        比如：`C:>ECHO Y|DEL A :.`，相当于执行`DEL A :*.*`命令时，系统确认框中直接返回Y，从而执行下一步，免去手动确认的过程

    5. 建立新文件或增加文件内容
        格式：ECHO >文件名或ECHO 文件内容>>文件名；

    6. 使喇叭鸣响
        格式：ECHO ^G,^G是在dos窗口中用Ctrl＋G或Alt＋007输入，输入多个^G可以产生多声鸣响

- `@`：表示本条命令不回显，仅在本条命令生效，优先级高于echo off；

### `rem`和`::`

- `rem`：注释，支持echo回显；
- `::`：注释，不支持echo回显，不能在()语句块中使用。
    第一,  任何以冒号:开头的字符行,在批处理中都被视作标号,  而直接忽略其后的所有内容。
    有效标号：冒号后紧跟一个以字母数字开头的字符串，goto 语句可以识别。
    无效标号：冒号后紧跟一个非字母数字的一个特殊符号，goto 无法识别的标号，可以起到注释
    作用，所以 :: 常被用作注释符号，其实 : 也可起注释作用。

- 如果 Rem 行太多，可能会减慢代码速度，因为最终仍然需要执行批处理文件中的每一行代码

### `call`和`start`

- `call`：调用另一个批处理文件，如果不用call而直接调用别的批处理文件，那么执行完那个批处理文件后将无法返回当前文件并执行当前文件的后续命令；
- `start`：批处理中调用外部程序的命令（该外部程序在新窗口中运行，批处理程序继续往下执行，不理会外部程序的运行状况），如果直接运行外部程序则必须等外部程序完成后才继续执行剩下的指令

### 创建文件

```batch
::在当前目录下建立了一个myfile.txt文件，文件的内容为123
echo 123 > newfile.txt

::建立一个空的新文件
echo. > newfile.txt
```

## 变量

1. 命令行参数
    可以在调用时传递到批处理文件。 可以通过变量 %1、%2、%3 等从批处理文件调用参数。
    eg. Test.bat 1 2 3

2. set 命令
    set /A variable-name=value
    /A 如果值本质上需要为数字，则使用此开关
    变量需要括在 % 符号内
    set /p var=请输入变量的值:
    执行上面的批处理时，系统会提示你：“请输入变量的值:

### 局部变量与全局变量

默认情况下，变量对于整个命令提示符会话是全局的。 调用 SETLOCAL 命令使变量成为脚本范围的本地变量。 调用 SETLOCAL 后，任何变量分配都会在调用 ENDLOCAL、调用 EXIT 或执行到脚本中的文件末尾 (EOF) 时恢复。 以下示例显示了在脚本中设置局部变量和全局变量时的差异。

## 字符串

```batch
:: 创建字符串，显示字符串
set message = Hello World 
echo %message%

:: 空字符串
SET a=
SET b=Hello
:: 是否存在空字符串
if [%a%]==[] echo "String A is empty"
if [%b%]==[] echo "String B is empty"

SET a = Hello 
SET b = World 
SET /A d = 50 
SET c=%a% and %b% %d%
echo %c%
:: Hello and World 50

::字字符串替换,将字符串变量%PATH%中的str1 替换为str2
%PATH:str1=str2%
set a= bbs. bathome. cn
echo  替换前的值: "%a%"
set var=%a: =%
echo  替换后的值: "%var%"
:: 替换前的值: " bbs. bathome. cn"
:: 替换后的值: "bbs.bathome.cn"

::字符串截取
%a:~[m[,n]]%
::方括号表示可选，%为变量标识符，a为变量名，不可少，
::冒号用于分隔变量名和说明部分，符号～可以简单理解为“偏移”即可，
::m 为偏移量（缺省为0），n 为截取长度（缺省为全部）
set a=bbs.bathome.cn
set var=%a:~1,3%
echo %var%
::执行显示为：bs.

set var=%a:~1,-3%
::执行显示：bs.bathome
::变量a 第2位起到倒数第3位前的值赋予var

set var=%a:~-3%
::执行显示：.cn
::把变量a 倒数 3 位的值赋予变量var

set var=%a:~-3,2%
::执行显示：.c
::把变量a 倒数 3 位的值的前2位赋予变量var

set var=%a:~3%
::执行显示：.bathome.cn
::把变量a 从第3 位开始后面全部的值给变量var


```

## 数组

```batch
set a[0]=1
set list=1 2 3 4 
(for %%a in (%list%) do ( 
   echo %%a 
))

```

## If 语句

```batch
if(condition) do_something

If (condition) (do_something) ELSE (do_something_else)
::每个"if else"代码都放在括号 () 中。 
::如果没有放置括号来分隔"if 和 else"代码，
::则这些语句将不是有效的正确的 if else 语句

::测试变量是否存在
if defined somevariable somecommand

::测试文件是否存在
If exist somefile.ext do_something

if(condition1) if (condition2) do_something

::测试最后运行的命令的退出代码
if errorlevel n somecommand
::其中"n"是整数退出代码之一

::goto语句示例
SET /A a = 5 

if %a%==5 goto :labela 
if %a%==10 goto :labelb
:labela
echo "The value of a is 5" 
exit /b 0

:labelb 
echo "The value of a is 10"

```

## 运算符

+-*/%

`EQU` 测试两个对象之间的相等性
`NEQ` 测试两个对象之间的差异
`LSS` 检查左侧对象是否小于右侧操作数
`LEQ` 检查左侧对象是否小于或等于右侧操作数
`GTR` 检查左侧对象是否大于右侧操作数
`GEQ` 检查左侧对象是否大于或等于右侧操作数

AND,OR,NOT

## for循环语句

在cmd窗口中：for %I in (command1) do command2
在批处理文件中：for %%I in (command1) do command2

### 注意

- for语句的形式变量I，可以换成26个字母中的任意一个，这些字母会区分大小写，也就是说，%%I和%%i会被认为不是同一个变量；
    形式变量I还可以换成其他的字符，但是，为了不与批处理中的%0～%9这10个形式变量发生冲突，请不要随意把%%I替换为%%0 ～%%9中的任意一个；
- in和do之间的command1表示的字符串或变量可以是一个，也可以是多个，每一个字符串或变量，我们称之为
    一个元素，每个元素之间，用空格键、跳格键、逗号、分号或等号分隔
- for语句依次提取command1中的每一个元素，把它的值赋予形式变量I,带到do后的command2中参与命令的执行；
    并且每次只提取一个元素，然后执行一次do后的命令语句，而无论这个元素是否被带到command2中参与了command2
    的运行；当执行完一次do后的语句之后，再提取command1中的下一个元素，再执行一次command2，如此循环，直到
    command1中的所有元素都已经被提取完毕，该for语句才宣告执行结束；

## for /f

```batch
::切分字符串 delims=
for /f "delims=," %%i in (test.txt) do echo %%i
::以逗号作为被处理的字符串的分隔符号

::定点提取：tokens=
for /f "delims=， tokens=3" %%i in (test.txt) do echo %%i
for /f "delims=， tokens=2,5" %%i in (test.txt) do echo %%i %%j
::连续的数字可以只写最小值和最大值
for /f "delims=， tokens=1,2-5" %%i in (test.txt) do echo %%i %%j %%k %%l %%m
::字符串的其余部分保持不变，整体被*所表示的一个变量接收
for /f "delims=， tokens=1,2-4,*" %%i in (test.txt) do echo %%i %%j %%k %%l %%m

::跳过无关内容，直奔主题：skip=n,n是一个正整数，表示要跳过的行数
for /f "skip=2" %%i in (test.txt) do echo %%i


```
