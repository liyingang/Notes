#python

解释器路径

```#!/usr/bin/env python```

编码

```# -*- coding:utf8 -*-```

input的用法，永远等待，直到用户输入了值，就会将输入的值赋值给一个东西

1. 变量

只能由  字母 数字 下划线 组成

Python关键字，也不能使用 'and', 'as', 'assert', 'break', 'class', 'continue', 'def', 'del', 'elif', 'else', 'except', 'exec', 'finally', 'for', 'from', 'global', 'if', 'import', 'in', 'is', 'lambda', 'not', 'or', 'pass', 'print', 'raise', 'return', 'try', 'while', 'with', 'yield

2. 注释
    	"""   """
        	#

3. 语法
    	条件语句：
        		if conditions :
        			statements
        		else:
        			statements
     
     ​	pass与java的continue语义相同但作用域不同
     
     ​			if conditions :
     ​				statements
     ​			elif conditions:
     ​				statements
     ​			else:
     ​				statements
     
     循环语句：
     			while conditions:
     				statements

	​			for child in parents:
	​				statements
    ​			range(x)函数	表示从0到99
    
4. 基本数据类型：
    	type() 得到类型
        	数字：int
        		num=123
        		转换数字：
        			字符串 --> 数字
        			a="123"
        			b=int(a)
        			b=int(a,base=16)
        	字符串：str
        		'and'  "and"  '''and'''  """and"""
        		加法同java
        		乘法
        			"2"*2="22"
        		format替换{}
        		format_map({...:...})
        		基本用法：
        		join、split、find、strip、upper、lower
     
    ​		列表：list
    ​            无类型扩充数组，类似于js数组，类似于元胞​	方括号初始化
    ​		元组：tuple
    ​          相当于const list，但二级元素不具备此属性  圆括号初始化
    ​		字典：dict
    ​	       map的性质  json的数据格式
    ​		布尔值：bool



5.  运算符
    	基本运算符：
        		加减乘除 +-*/    乘方**  取余%  取整 //
        		注意取余： -13%3=-5
        		取整： -13//3=2
        	比较运算符：
        		== !=  <> > < >= <=
        	赋值运算符：
        		= += -= *= /= %= //= **=
        	逻辑运算符：
        		and or not
        	成员运算符：
        		in  not in 判断字符串子串

