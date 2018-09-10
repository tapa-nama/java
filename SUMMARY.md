# Summary for Week 1

### 1. 数字中的下划线(Underscore)

增强可读性, 可以添加多个,但不能将`_`加在如下地方:

* 不能放在数字的开头和结尾

* 浮点数中不能与小数点相邻
* 不能置于`F`或`L`后缀的前面
* 不能放在`0x`中间(`0_x`无效)

### 2. Primitive Data Type

|                  |                          |              Min              |              Max               | Default(for fields) |
| :--------------: | :----------------------: | :---------------------------: | :----------------------------: | :-----------------: |
|       byte       |       signed 8-bit       |             -128              |              127               |          0          |
|      short       |      signed 16-bit       |    -2^15/0x8000    |         2^15-1/0x7fff          |          0          |
|  int(默认存储)   |      signed 32-bit       |       -2^31/0x8000_0000       |       2^31-1/0x7fff_ffff       |          0          |
|       long       |      signed 64-bit       | -2^63/ 0x8000_0000_0000_0000L | 2^63-1/ 0x7fff_ffff_ffff_ffffL |         0L          |
|      float       | single-precision  32-bit |                               |                                |        0.0f         |
| double(默认存储) | double-precision 64-bit  |                               |                                |        0.0d         |
|     blooean      |                          |                               |                                |        false        |
|       char       |  signed 16-bit Unicode   |           '\u0000'            |            '\uffff'            |      '\u0000'       |

Object 的默认值为null.

整型上溢: 最大值 + 1 == 最小值

整型下溢: 最小值 - 1 == 最大值

使用`Math.addExct(a, b)`可以抛出溢出异常(通过判断两个实参均与结果符号相反)

`0x8000_0000 == -0x8000_0000`

`0x1 != -0x1`

### 3. 有符号移位(>>,<<)与无符号移位(>>>)

1) <<: 丢弃左边指定位数,右边补0

2) >>: 丢弃右边指定位数，左边补上符号位

3) >>>: 丢弃右边指定位数,左边补0

没有无符号左移(<<<)是因为左移都是在右边补0,而符号位位于首位,对于左移来说没有signed和unsigned之分.

### 4. 逻辑运算符(Logical Operator)和按位运算符(Bitwise Operator)

**a && b:** a与b均为true时才为true, 若a为false则返回false不再判断b(短路效应). 

**a || b:** a与b均为false时才为false, 若a为true则返回true不再判断b(短路效应).

**! a:** 逻辑非. 

**a & b:** 将数值按二进制展开按位与, 同为1时该位结果为1.

**a | b:** 将数值按二进制展开按位或, 同为0时该位结果为0.

**~a:** 按位取反

**a ^ b:** 按位异或, 相同为0, 不同为1.

优先级:   **~** > **&** > **^** > **|**

### 5. 类型转换

从低位到高位可以隐式转换, 高位到低位需要强制转换.

|            | byte | short | int  | long | float | double |
| :--------: | :--: | :---: | :--: | :--: | :---: | :----: |
|  **byte**  |  —   |  隐   |  隐  |  隐  |  隐   |   隐   |
| **short**  |  显  |   —   |  隐  |  隐  |  隐   |   隐   |
|  **int**   |  显  |  显   |  —   |  隐  |  隐   |   隐   |
|  **long**  |  显  |  显   |  显  |  —   |  隐   |   隐   |
| **float**  |  显  |  显   |  显  |  显  |   —   |   隐   |
| **double** |  显  |  显   |  显  |  显  |  显   |   —    |

计算时默认采用**int**型, 但若计算的数值之一为double/float/long时,结果也为对应的double/float/long型.

`byte a =1; `   `byte b = 2;`

`a = a + b;   // 编译出错,计算时会自动提升为int`

需改为:`byte c = (byte) a + b;`  

### 6. i++与++i

`a = i++; //先执行a = i赋值, 再对 i执行 +1操作`

`a = ++i; //先执行 i + 1操作,再对a赋值`

### 7. 异常处理

需要处理异常时要用try-catch,  直接抛出时不用try-catch.

如果 a 调用 b, b 调用 c, 若 b 抛出异常, 则将异常抛回给 a.

栈溢出是由于递归调用太深.

### 8. 四舍五入

`Math.round(double a)` 返回四舍五入后的long型;`Math.round(float a)` 返回四舍五入后的int型

`Math.floor(double a)`返回不大于a的最大整数(double型)

`Math.ceil(double a)`返回不小于a的最大整数(double型)

### 9. INFINITY 与NaN(Not a Number)

`public static final double POSITIVE_INFINITY = 1.0 / 0.0;`

`public static final double NEGATIVE_INFINITY = -1.0 / 0.0;`

`public static final double NaN = 0.0d / 0.0;`

`Double.NaN != Double.NaN`

### 10.  char和codePoint

char类型使用UTF-16编码（包含2个/4个字节）.

codePoint包含2个或4个字节,所以一个codePoint可以表示一个或两个char.

### 11. final关键字

final 变量为常量, 因此必须进行初始化.

final 方法不能被重写

final class不能被继承

若final修饰的是一个引用,只是指被final修饰的变量与当前对象的关系是绑定的,该变量不能再指向其他引用,但所指向对象的值/状态可以改变.

### 12. String

String是不可变的(immutable),一旦被创建就不可再被改变,因此对String的任何操作实际上都是创建了一个新的String.

但可以使用StringBuffer类来操作字符串,就不会产生多余的中间对象, StringBuffer是线程安全的.

StringBuilder与StringBuffer类似,但线程不安全.

如果操作少量数据,使用String即可;

单线程大量数据,使用StringBuilder

多线程大量数据,使用StringBuffer

实参(Argument) vs 形参(Parameter)

### 13.  子类与父类构造方法的执行顺序

* 子类的构造方法总是先调用父类的构造方法，如果子类的构造方法没有显式指定调用父类的哪个构造方法，就默认调用父类的无参构造方法.
* 如果父类没有无参构造方法，则子类需要在自己的构造方法中显示调用父类的有参构造方法.
*  使用super调用构造方法的语句必须是子类构造方法的第一条语句(有且只有一次).

### 14. 类初始化顺序

(静态代码块、初始化静态变量）>（构造代码块、变量）>构造函数

### 15. equals()与hashCode()

重写equals方法必须重写hashCode方法

equals方法:先判断是否为同一个对象;判断是否为空;判断是否为同一个class;判断属性.

### 16.反射

在程序**运行**时,对于任意类都可以得到其属性和方法,对于任意对象都可以调用其属性和方法.

* 获得Class对象:

  1) Class.forName();

  2) .class;

  3) .getClass();

* 判断是否为某个类的实例:

  .isInstance();

* 创建实例:

  .newInstance();

* 获取方法:

  1) .getDeclaredMethods() 返回类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法

  2) .getMethods()方法返回某个类的所有公用（public）方法，包括其继承类的公用方法

* 调用方法:

  .invoke();

### 17. lambda表达式

* 功能接口（Functional interface）指**只有一个**抽象方法的接口，但可以有多个default方法。lambda实际上是一个功能接口的匿名类.

- Lambda 允许把函数作为一个方法的参数(函数作为参数传递进方法中): (argument) -> (body).

- **结构:**

- - Lambda 表达式可以具有零个，一个或多个参数。
  - 可以显式声明参数的类型，也可以由编译器自动从上下文推断参数的类型。例如 (int a) 与 (a)相同。
  - 参数用小括号括起来，用逗号分隔。例如 (a, b) 或 (int a, int b) 或 (String a, int b, float c).
  - 空括号用于表示一组空的参数。例如 () -> 42。
  - 当有且仅有一个参数时，如果不显式指明类型，则不必使用小括号。例如 a -> return a*a。
  - Lambda 表达式的正文可以包含零条，一条或多条语句。
  - 如果 Lambda 表达式的正文只有一条语句，则大括号可不用写，且表达式的返回值类型要与匿名函数的返回类型相同。
  - 如果 Lambda 表达式的正文有一条以上的语句必须包含在大括号（代码块）中，且表达式的返回值类型要与匿名函数的返回类型相同。

- 双冒号（::）操作符是 Java 中的方法引用——**目标引用::方法**

  目标引用的参数类型是 Function<T,R>，T 表示传入类型，R 表示返回类型。

### 18. Interface

1. 多实现（类的单继承）
2. 接口不能自我实现
3. field 可以为static，final，public
4. 不能实例化
5. 接口只能extends 接口，不能实现接口
6. 子类必须实现接口中的所有方法
7. 实现类不能继承static方法
8. 方法默认为public
9. default方法被继承
10. 接口默认为abstract
11. 接口的modifier默认为package private
12. 若一个类实现了多个接口且这些接口中的default方法包含同样的方法签名,当该类要调用某一个接口中的方法时,需要使用`接口名.super.方法名()`调用,或者需要在子类中重写该方法.

### 19. 方法签名

指方法名和参数,不包括返回类型

重写(override):子类对父类允许访问的方法进行重写, 返回值和形参都不能改变.

重载(overload):在一个类里，方法名字相同，而参数不同。返回类型可以相同也可以不同.

### 20. 闭包

闭包指自带“运行环境”的函数(定义时环境).

被闭包捕获的变量能够扩大作用域.



















​         

