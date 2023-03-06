# 注解
Java注解又称Java标注，是在 JDK5 时引入的新特性，注解（也被称为元数据）。

## 注解分类
1. 标准注解

    包括@Override、@Deprecated、@SuppressWarnings等，使用这些注解后编译器就会进行检查。

    + Override

        如果在子类中用 @Override 标记一个父类中没有的方法时，java 编译器会告警。

    + Deprecated

        @Deprecated 用于标识被修饰的代码是过时的，废弃的，不建议使用的代码。一般修饰的代码会出现删除线。
    
    + SuppressWarnings

        @SuppressWarnings 用于关闭对类、方法、成员编译时产生的特定警告。即不让编译器报警告。

        @SuppressWarnings 注解的常见参数值的简单说明:

        1. rawtypes 使用原始数据类型
            
            虽然是不推荐但是任然还是可以使用原始类型，如果有十足的把握不出错，那我们可以在使用原始类型的时候添加
        2. unchecked 未经检查:不会进行类型检查
        3. serial 未添加序列化字段

            消除实现了Serializable接口的类却没有声明SerializableSID在编译时发出的警告
        4. deprecated 被废弃

            消除了@Deprecated的警告
    + FunctionalInterface（JDK8）

        @FunctionalInterface 用于指示被修饰的接口是函数式接口

        函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
2. 元注解

    元注解是用于定义注解的注解，包括@Retention、@Target、@Inherited、@Documented、@Repeatable 等。（只用于修饰注解）

    + Retention

        @ Retention用来定义该注解在哪一个级别可用，在源代码中(SOURCE)、类文件中(CLASS)或者运行时(RUNTIME)。
        
        RetentionPolicy 是一个枚举类型，它定义了被 @Retention 修饰的注解所支持的保留级别：

        ```java
        @Documented@Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.ANNOTATION_TYPE)
        public @interface Retention {
        RetentionPolicy value();
        }

        public enum RetentionPolicy {
        //此注解类型的信息只会记录在源文件中，编译时将被编译器丢弃，也就是说
        //不会保存在编译好的类信息中
        SOURCE,
        //编译器将注解记录在类文件中，但不会加载到JVM中。如果一个注解声明没指定范围，则系统
        //默认值就是Class
        CLASS,
        //注解信息会保留在源文件、类文件中，在执行的时也加载到Java的JVM中，因此可以反射性的读取。
        RUNTIME
        }
        ```
    + Documented

        @Documented：生成文档信息的时候保留注解，对类作辅助说明

    + Target

        用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
        ![img](img\fe8d8ca6cd87239ac80dce2d6824e37a.png)

    + Inherited

        说明子类可以继承父类中的该注解

        表示自动继承注解类型。 如果注解类型声明中存在 @Inherited 元注解，则注解所修饰类的所有子类都将会继承此注解。
    + Repeatable(JDK8)

        表示注解可以重复使用。在对应类、方法、属性上多次使用该注解，并通过反射的方法获取注解数组，根据需要获取数据。
        ```java
        public class FindFiles {
            @Target( METHOD)
            @Retention( RetentionPolicy.RUNTIME )
            public @interface FileTypes {
                FileType[] value();//对应3
            }
    
            @Target(  METHOD )
            @Retention( RetentionPolicy.RUNTIME )
            @Repeatable( FileTypes.class )//对应1、2
            public @interface FileType {
                String value();
            };
    
            @FileType( ".java" )
            @FileType( ".html" )
            @FileType( ".css" )
            @FileType( ".js" )
            public void work(){
                
                try {
                    FileType[] fileTypes= this.getClass().getMethod("work").getAnnotationsByType(FileType.class);
                    System.out.println("将从如下后缀名的文件中查找文件内容");
                    for (FileType fileType : fileTypes) {
                        System.out.println(fileType.value());
                    }
                } catch (NoSuchMethodException | SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
    
            public static void main(String[] args) {
                new FindFiles().work();
            }
        }

        output:
        将从如下后缀名的文件中查找文件内容
        .java
        .html
        .css
        .js

        ```

3. 自定义注解

    用户可以根据自己的需求定义注解。

    注解的interface关键字需要以@符号开头，我们可以为注解声明方法。

    注意注解的使用需要搭配反射机制，需要反射机制来获取注解的属性值

## 反射

    Class c =Class.forName()