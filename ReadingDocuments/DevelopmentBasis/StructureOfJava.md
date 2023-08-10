# 1.从一个程序开始分析

```Java
  public class FirstDemo{
    public static void main(String[] args){
      System.out.println("Hello world");
    } 
  }
```
&emsp;&emsp;首先在Java中是区分大小写的，比如说String这个字符串对象，如果是string则是一个变量(后续会谈到变量)；</br>

&emsp;&emsp;其次public是一个访问修饰符，与之相同的还有protected和private；</br>

&emsp;&emsp;还有就是class,由于全部的代码都写在class里面，所以暂时可以把它理解为一个存放Java代码的容器；</br>

&emsp;&emsp;紧跟在class后面的是这个类的名字，在Java中类名方法名的命名规则很随意除了必须是以字母开头之外没有任何规则，但是我们在开发中默认规定类名首字母是大写而方法名则是小写，并且大多使用驼峰命名法例如：FirstDemo,firstMethod除了第一个单词外需要遵照前面的命名规则外其余每个单词的首字母大写即可，对了还不能使用保留字。
**(ps：有个大佬跟我说做开发就两个难点：1.不知道起什么名字 2.不知道放到哪里)**;

&emsp;&emsp;这段代码里面包含了两个{}每个大括号里面都是一个块，包含着属于这个块的代码，任何方法都必须以"{"开始，以"}"结束

&emsp;&emsp;每个Java应用必须包含一个main方法，声明方式如上，main方法里面包含的代码是可以不一样的。其中main方法由于是void所以不会返回一个退出码，如果程序正常执行则是给出一个退出码0，如果需要以别的返回退出程序需要使用System.exit();</br>

## 一些需要注意的点  

&emsp;&emsp;1. 根据Java语言规范main函数必须声明成public。但是之前并不是这样的，当时Java工作人员给出的解释Java虚拟机并没有强制要求main方法一定要是public的，并且修复这个问题可能会带来别的问题。但是在Java1.4版本之后还是修改了这个问题也就是main函数必须声明成public的</br>

&emsp;&emsp;2. 每个Java语句必须以";"结尾</br>

## 注释

&emsp;&emsp;作为一个开发一定要记得写注释，避免自己看不懂自己之前写的代码，也避免后续维护的时候不必要的麻烦。</br>

&emsp;&emsp; Java中提供了三种注释方式单行注释："//",多行注释："/* */",JavaDOC注释："/**"。其中最常用的就是//因为在学校学c语言的时候也先学的是//不做说明了，/* */这种注释可以包含多行，将内容写在中间空格处就好，JavaDOC注释可以标注信息比如类名，方法名，作者，时间等等信息，是可以自动生成的前提是设置好模版。大概就是这些了</br>

# 2.基本类型

&emsp;&emsp; Java的基本类型有8种。分别是int,short,long,byte,double,float,char,bollean，其中前四种是整形类型，五六是浮点型，char是字节型可以表示字符的Unicode值，最后一种是布尔类型。**谨记String不是基本类型而是引用类型**，(我在String源码分析中应该有解释)</br>

## 整形byte，short，int，long


   ![image](https://github.com/apprentice1012/Java/assets/126549223/b3ece07c-cacc-4f15-9555-ed7e411c138b)

   
&emsp;&emsp; 在开发中我们经常用到的整形都是int类型但是如果需要表示超过int范围的数字是就需要使用long类型。byte和short使用场景多用于节省内存而实用，因为Java不只支持pc端的软件，还支持智能设备这个时候就需要使用合适的整形来节省空间。</br>

&emsp;&emsp;在Java中，整形的范围与运行代码的机器无关。这样就避免了不同机器对范围定义不同从而导致同一代码在不同机器无法运行的问题。而C或者C++在针对不同处理器会自动选择最高效的整形。</br>

&emsp;&emsp; **一些标识问题:** 

&emsp;&emsp;&emsp;&emsp;1. 在Long整形中通常会给数值加上一个后缀L或l来标识这个整形是长整形。</br>

&emsp;&emsp;&emsp;&emsp;2. 16进制前缀是0X或0x；8进制前缀是0但是由于特别容易混淆所以尽量不要使用；2进制前缀是0B或者0b</br>

&emsp;&emsp;&emsp;&emsp;3. 可以每隔几位数字添加一个下划线方便读取例如100_000_000,Java编译器在编译的时候会自动去掉这些下划线</br>

## 浮点类型float，double

  ![image](https://github.com/apprentice1012/Java/assets/126549223/df5cf30d-8f6a-4a92-afbc-b61276d3b0d5)

&emsp;&emsp;double表示范围是float的两倍，也有人称double双精度浮点数。通常情况下直接使用double即可，因为float的精度不够，但是存在即合理float也是有使用场景的，比如说数据库中一定要单精度浮点数的时候。。。</br>

&emsp;&emsp;float类型的数值要加后缀F或者f们如果不加后缀默认为double类型。双精度也有可选后缀D或者d，记得住就加记不住拉到没有影响。</br>

&emsp;&emsp;有三种特殊的浮点数用来表明错误或者溢出DOUBLE.POSITIVE_INFINITY,DOUBLE.NEGATIVE_INFINITY,DOUBLE.NaN(以及对应的float形式)分别表示正无穷大，负无穷大以及不是一个有理数(除0运算就不是有理数)。</br>

&emsp;&emsp;另外double不能用来无法接受舍入误差的金融计算。例如2.0-1.1将打印出来0.8999999999，而不是期望的0.9。这种舍入误差主要是因为计算机是由二进制来计算的，但是二进制无法精确地表示分数1/10.如果需要精确的数值计算，不允许出现舍入误差，则应该使用BigDecimal类。</br>

## char类型

&emsp;&emsp;char类型原本用来表示单个自负。不过现在有的Unicode可以用一个char值描述，有些则需要两个。char的字面量需要用单引号引起来例如'A'就是表示编码值为65的字符常量。(人能一眼看出来信息的就叫字面量例如ABC像65，66，67则是Unicode）。</br>

&emsp;&emsp;在Unicode编码规则出现前就已经有好多编码规则了，最著名的就是美国的ASCII码。但是由于这些规则不够统一有时候使用一个小众的编码书写，在别的解释器无法进行解释或者某些字符在当前编码中采用的单子街边吗，在另一种编码方式中采取的是多字节编码，从而出现各种问题。所以才有了目前统一的编码规则。但是由于中文，日文，韩文的加入，现在16位的char类型已经无法描述所有的Unicode字符了。但是在Java中做了详细处理，有兴趣可以了解。但是在此强烈建议不要在代码中使用char类型。</br>

## boolean类型

&emsp;&emsp;这个类型只有两个只true，false，用来进行逻辑判断。在Java中整型值不能和布尔值进行转换。但在C++中可以。</br>

# 3.变量和常量

&emsp;&emsp;与所有程序设计语言一样，Java也使用变量来存储值。常量就是值不会变的变量。</br>

## 声明变量

&emsp;&emsp;在Java中每个变量都有一个类型，可能是引用类型或者是基本类型。声明一个变量的时候，首先要声明变量所属的类型。例如：String str；int num；等等诸如此类。每个声明都要以分号来结尾。作为变量名必须是由字母，数字，货币符号来组成，但是第一个字符不能是数字。变量名是区分大小写的，而且空格，不再规则内的符号是禁止出现在变量名中的以及不能使用Java关键字作为变量名。最简单的就是使用驼峰命名法，根据变量所表示的含义进行命名。虽然可以在一行中声明多个变量，但是尽量不要这么操作会降低程序可读性。</br>
  
## 初始化变量
  
&emsp;&emsp;声明一个变量之后，必须用赋值语句显示地初始化变量，千万不要使用未初始化的变量值，会影响程序正常运行。变量的声明要尽量在靠近这次第一次使用该变量的位置方便后续阅读。 </br>

&emsp;&emsp;在java中是不需要区分变量的定义和声明的。但在C和C++是有区分的。</br>

## 常量

&emsp;&emsp;在Java中可以用关键字final来指示常量。例如 final int NUM = 0；关键字final的作用就是表示这个变量智能呗赋值一次。一旦赋值就不能再进行更改。习惯上常量名全为大写。在Java中，可能一个常量在类中多处使用可能不止一个代码域，这个时候可以使用static final声明一个类常量。例如：public static final int NUM=0;类常量定义在main方法之外，当前类所有方法均可使用该常量。如果声明为public则其他类也可以使用</br>

## 枚举类型

&emsp;&emsp;枚举类型中包含一个或多个常量。避免因为多次定义而出现错误的情况。其中null表示该变量不设置任何值。</br>

# 4.运算符

## 算术运算符
  
&emsp;&emsp;不知道说些什么，+-*/%会用就行。除0会异常。</br>

## 数学函数与常量

&emsp;&emsp;Math类中包含可能会用到的各种数学函数。源码中见。</br>

## 数值类型之间的转换

&emsp;&emsp;不同的数值类型之间互相转换可能会产生精度损耗从而导致信息丢失。</br>

  ![image](https://github.com/apprentice1012/Java/assets/126549223/99ca3668-289c-424c-9194-90d3b66ca696)

&emsp;&emsp;图中实线箭头表示不会有信息丢失的转换。虚线箭头表示可能有精度损失的转换。应该很好理解，之前有每个类型能表示的范围，当大的范围向小的范围转换肯定会有精度丢失。如果两个值之间用二元运算符连接时，需要将两个值转换成同一类型然后进行计算。如果其中一个是double则另一个默认转换成double。</br>

## 强制类型转换

&emsp;&emsp;在必要的时候，需要将大范围的数值类型转换成小范围的数值类型，虽然会丢失一定的精度信息。在语言中称为强制类型转换。通过(cast)来完成</br>

## 赋值

&emsp;&emsp;赋值emmmmm不知道能说点啥，目前来说等号后面就是值，还有+=，*=等二元运算符。可以嵌套使用但是没必要会造成不必要的混淆。</br>

## 自增与自减运算符

&emsp;&emsp;++，--。++在前比如说++i表示先自增后使用i，i++表示先使用i在对i进行自增(--同理)。</br>

## 关系和boolean运算符

&emsp;&emsp;大于小于等于，boolean是配合其使用的。在Java中&&表示逻辑与，||表示逻辑或，!=表示逻辑非。</br>

## 条件运算符

&emsp;&emsp;条件运算符也称为三目运算符，格式：condition?(true):(false)。</br>

## switch表达式

&emsp;&emsp;需要在两个及以上做出选择的时候可以使用switch case表达式。

```Java
  String str = switch(code){
    case xxx->"xxx";
    case xxx->"xxx";
    case xxx->"xxx";
    case xxx->"xxx";
    case xxx->"xxx";
    default -> "xxx";
  };
```

&emsp;&emsp;示例中都是单标签case，但是也可以为case添加多标签。</br>

&emsp;&emsp;**注意**

&emsp;&emsp;&emsp;&emsp;在使用整数或String类型的时候不可以省略掉default，因为无论如何都要输出一个值。在其他时候可以根据业务情况进行省略</br>

## 位运算符

&emsp;&emsp;使用的频率不高。有&('and'),|('or'),^('xor'),~('not'),>>('左移运算符'),<<('右移运算符'),>>>('与>>用符号位填充高位不同，左移会使用0来填充高位')</br>

## 括号与运算符级别

&emsp;&emsp;如图所示</br>

![image](https://github.com/apprentice1012/Java/assets/126549223/4f8aee36-fec8-4270-8832-def4b9708c8e)
![image](https://github.com/apprentice1012/Java/assets/126549223/f8c0bcfb-cad1-44de-927b-4966a8a7d5d6)


# 5.字符串

## 字符串不可变

&emsp;&emsp;Java设计者认为共享的效率远远超过编辑的效率</br>

## 空串和NULL

&emsp;&emsp;空串是长度为0的字符串，而NULL只是一个特殊的值。</br>

## 构建字符串

&emsp;&emsp;有时候需要由短的字符串构建字符串。如果使用String来进行拼接会有效率问题产生，这时候我们可以使用StringBuilder调用append方法完成拼接。构建完成之后调用toString方法就可以获得一个String字符串。</br>

&emsp;&emsp**注释**

&emsp;&emsp;&emsp;&emsp;StringBuffer类的效率不如StringBuilder类，不过StringBuffer允许采用多线程的方式添加或删除字符。如果所有字符串编辑操作都在单线程中执行，则应当使用StringBuilder类。两个类的API一模一样</br>

&emsp;&emsp;&emsp;&emsp;源码中StringBuffer效率低但是能满足多线程的原因是因为在重写方法的时候实现了synchronized，而StringBuilder并没有实现。</br>

## 文本块

&emsp;&emsp;在Java15中添加了文本块特性。可以很容易的提供跨越多行的字符串字面量，文本块以"""开头和结尾。</br>
```Java
  String str = """
    Hello
    World
  """
```
&emsp;&emsp;文本块适合包含其他语言编写的代码。</br>

# 输入和输出

## 读取输入

&emsp;&emsp;要想读取控制台输入首先要构造一个与“标准输入流”System.in关联的Scanner对象</br>
```java
  Scanner in = new Scanner(System.in);
```
&emsp;&emsp;然后通过Scanner中的方法来进行读取。但是因为Scanner输入对所有人可见，所以Scanner类不适合输入密码。可以使用Console类来达到这个目的，但是并不方便。</br>

```
 Scanner常用API：
  String nextLine();//读取下一行输入
  String next()//读取输入的下一个单词
  int nextInt()//读取并转换下一个表示整数的字符序列
  double newtDouble()//读取并转换下一个表示浮点数的字符序列
  boolean hasNext()//检测输入中是否还有其他单词
```

## 格式化输出

&emsp;&emsp;printf()</br>

## 文件输入与输出

&emsp;&emsp;要想读取一个文件，需要构造一个Scanner对象，如下所示：
```Java
  Scanner in = new Scanner(Path.of("myfile.txt"),StandardCharsets.UTF-8);
```
如果文件包含反斜线符号，要在每个反斜线之前在添加一个额外的反斜线进行转义。</br>

&emsp;&emsp;要想写入文件需要使用PrintWriter</br>

# 控制流程

&emsp;&emsp;与其他语言一样，Java同样拥有条件语句和循环语句来确定控制流程。Java中的控制流程与C或者C++类似，除了没有goto语句以外，Java还可以在break上添加标签。</br>

## 块作用域

&emsp;&emsp;每对大括号包起来的内容属于一个块结构，块结构是支持嵌套的，但是在嵌套块内不能存在同名变量.</br>

## 条件语句

&emsp;&emsp;条件语句有两种if和if/else。语法如下：
```Java
  if(condition){
  }
  if(condition){
  }else{
  }
```
&emsp;&emsp;通过块结构，可以在原来只能放一条语句的位置多放几条语句，所以如果{}内只有一条语句，这个{}是可以省略的，但是非常不建议省略。else语句是可选的总是和离的最近if组成if/else条件语句。当然也可以使用if/else if.../else组成语句，但是尽量不要超过5次不然公司的代码规约可能过不去。</br>

**流程图**

**if**

![image](https://github.com/apprentice1012/Java/assets/126549223/c41a57b9-b1cc-4cae-bbe6-df4aab668540)

**if/else**

![image](https://github.com/apprentice1012/Java/assets/126549223/8827ba90-66e0-451b-b433-fba9320166d5)

**if/else if/else**

![image](https://github.com/apprentice1012/Java/assets/126549223/60aefea3-40ff-4e3c-81f5-129965119ba6)

# 循环

## while循环

&emsp;&emsp;while循环会在条件为true是执行一个语句也可以是代码块。
```Java
  while(condition) statement
```
&emsp;&emsp;如果初始条件为false则不会执行while循环，但有些场景需要代码执行一次所以这个时候就应该使用do/while。
```Java
  do statement
  while(condition);
```
**流程图**

**while**

![image](https://github.com/apprentice1012/Java/assets/126549223/5d8cb06b-888e-45e1-9101-e95fd40b84ff)

**do/while**

![image](https://github.com/apprentice1012/Java/assets/126549223/522ff9fc-eac6-4dd3-8286-b310bf0562ea)

## 确定性循环

&emsp;&emsp;foe循环也叫确定性循环(我也是刚知道)支持迭代的一种通用结构。</br>
```Java
for(int i = 1;i<=x;i++)
statement
```
&emsp;&emsp;for语句第一部分通常是对计数器进行初始化；第二部分给出每次新一轮循环执行前要检测的循环条件；第三部分指定如何更新计数器。虽然Java没有规定但部分一定要放什么语句，但是默认对应同一计数器变量进行初始化，检测和更新。还有一种for循环也称为for each循环在数组中进行介绍。</br>

## 多重选择：switch语句

&emsp;&emsp;switch/case说起来其实有四种形式但是核心都是一样的，无非是有直通行为和无直通行为的区别，说简单点就是带不带break语句，因为有时候可能匹配的case不只一个，或者不存在default语句但是又没有匹配到相应的case坑会出现问题，在表现形式上有直通行为case后面是":",无直通是行为"->"。在switch语句中有一个关键字yield作用类似于break；但是不同的是yield后面要跟一个值作为当前case的值。</br>

## 中断控制流程的语句

&emsp;&emsp;有break，带标签的break，continue以及带标签的continue四种语句</br>

&emsp;&emsp;在Java中虽然没有goto关键字，而且大量使用goto会导致代码混乱，但是偶尔使用goto跳出循环确是不错的选择，所以Java提供了带标签的break语句来实现goto。标签必须放在想跳出的最外层循环处并且紧跟一个冒号。</br>

&emsp;&emsp;continue与break不同的是会跳过剩余语句来到循环首部，而不会直接跳出循环。当然continue也是可以带标签的。</br>
