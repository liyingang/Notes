# java

>   ### 数组拷贝
>
>   1.arraycopy(Object src,int srcPos,Object dest,int destPos,int length)    **<font color=red>native 方法</font>**
>
>   ​	**参数：**
>
>   ​		*src*-源数组。
>
>   ​		*srcPos*—源数组中的起始位置。
>
>   ​		*dest*-目标数组。
>
>   ​		*destPos*—目标数据中的起始位置。
>
>   ​		*length*—要复制的数组元素数。
>
>   ​	**投掷：**
>
>   ​		IndexOutboundsException-如果复制会导致访问数组边界之外的数据。
>
>   ​		ArrayStoreException-如果src数组中的元素由于类型不匹配而无法存储到dest数组中。
>
>   ​		NullPointerException-如果src或dest为空。
>
>   ​	从指定的源数组（从指定位置开始）将数组复制到目标数组的指定位置。数组的子序列从src引用的源数组复制到dest引用的目标数组。复制数等于length参数。源数组中srcPos到srcPos+length-1位置的内容分别复制到目标数组的destPos到destPos+length-1位置。
>
>   ​	如果src和dest参数引用的是同一个数组对象，则执行复制时，就好像srcPos到srcPos+length-1位置的内容首先复制到一个具有length内容的临时数组中，然后临时数组的内容通过destPos+length-1复制到destPos位置一样。
>
>   ​	如果src或dest为空，则抛出一个NullPointerException，src为空不修改目标数组。
>
>   ​	如果以下任何一项为真，则会引发ArrayStoreException，并且不会修改目标：
>
>   ​		src、dest参数引用的对象不是数组。
>
>   ​		src和dest引用类型为不同基元类型的数组。即src与dest子元素类型不同。
>
>   ​		src与dest类型不同（基本和引用）。
>
>   ​	如果以下任何一项为真，将引发IndexOutboundsException，并且不会修改目标：
>
>   ​		srcPos参数、destPos参数或长度参数为负。
>
>   ​		srcPos+length大于源数组的长度src.length。
>
>   ​		destPos+length大于dest.length，即目标数组的长度。
>
>   ​	如果源数组的任何实际组件（从位置srcPos到srcPos+length-1）无法通过赋值转换为目标数组的组件类型，则会引发ArrayStoreException。在这种情况下，让k是小于长度的最小非负整数，这样src[srcPos+k]就不能转换为目标数组的组件类型；当抛出异常时，从srcPos位置到srcPos+k-1位置的源数组组件已经通过destPos+k-1复制到目标数组位置destPos目标数组的任何其他位置都不会被修改。（由于已经逐项列出了限制，本段实际上仅适用于两个数组都具有引用类型的组件类型的情况。）

>   ## Arrays方法
>
>   1.  **toString(T[] a)**
>
>       ​	一共9个重载方法，包括8个基本类型和1个引用类型
>
>       ​	返回指定数组内容的字符串表示形式。如果数组包含其他数组作为元素，则它们将由从Object继承的Object.toString()方法转换为字符串，该方法描述它们的标识而不是内容。
>
>       ​	此方法返回的值等于Arrays.asList(a).toString()返回的值，除非a为空，在这种情况下返回“null”。
>
>       ​	参数：
>
>       ​		a-要返回其字符串表示形式的数组
>
>       ​	返回：
>
>       ​		字符串表示
>
>   2.  排序
>
>       +   **sort(T[] a)**
>
>           基本类型数据排序：将指定的数组按数字升序排序。
>
>           对象类型排序：数组中的所有元素都必须实现可比较接口。此外，数组中的所有元素必须是相互可比的（即e1.compareTo（e2）不能对数组中的任何元素e1和e2抛出ClassCastException）。这种排序被保证是稳定的：相等的元素不会因为排序而重新排序。
>
>           实现说明：基本类型排序算法是Vladimir Yaroslavskiy、Jon Bentley和Joshua Bloch的双轴快速排序。此算法在许多数据集上提供O（n log（n））性能，这些数据集会导致其他快速排序降级为二次性能，并且通常比传统（单轴）快速排序实现更快。对象类型排序实现是一个稳定的、自适应的、迭代的mergesort，当输入数组被部分排序时，它需要的比较远远少于n lg（n），而当输入数组被随机排序时，它提供了传统mergesort的性能。如果输入数组接近排序，则实现需要大约n个比较。临时存储需求从几乎排序的输入数组的一个小常量到随机排序的输入数组的n/2对象引用不等。
>
>       +   **sort(T[] a,int fromIndex,int toIndex)**
>
>           将数组的指定范围按升序排序。要排序的范围从索引fromIndex到索引toIndex。如果fromIndex==toIndex，则要排序的范围为空。实现说明同上。
>
>   3.  二分查找
>
>       +   **binarySearch(T[] a,T key)**
>
>           使用二进制搜索算法在指定的数组中搜索指定的值。在进行此调用之前，必须对数组进行排序（按sort（T[]）方法排序）。如果未排序，则结果未定义。如果数组包含多个具有指定值的元素，则无法保证将找到哪个元素。
>
>           **参数：**
>
>           ​	a-要搜索的数组
>
>           ​	key-要搜索的值
>
>           **返回**：
>
>           ​	搜索键的索引（如果它包含在数组中）；否则，（-（插入点）-1）。插入点定义为将键插入数组的点：第一个元素的索引大于键，如果数组中的所有元素都小于指定键，则为a.length。注意，这保证了当且仅当找到值时，返回索引将>=0。
>
>       +   **binarySearch(int[] a,int fromIndex,int toIndex,int key)**

>   ## transient 关键字总结
>
>   1）transient修饰的变量不能被序列化；
>
>   2）transient只作用于实现 Serializable 接口；
>
>   3）transient只能用来修饰普通成员变量字段；
>
>   4）不管有没有 transient 修饰，静态变量都不能被序列化；

>## equals重写的规则
>
>1.  自反性：对任意引用值 X， x.equals(x)的返回值一定为 true.
>2.  对称性：对于任何引用值 x,y,当且仅当 y.equals(x)返回值为 true 时， x.equals(y)
>    的返回值一定为 true;
>3.  传递性：如果 x.equals(y)=true, y.equals(z)=true,则 x.equals(z)=true
>4.  一致性：如果参与比较的对象没任何改变，则对象比较的结果也不应该有任何
>      改变
>5.  非空性：任何非空的引用值 X， x.equals(null)的返回值一定为 false。 
>

