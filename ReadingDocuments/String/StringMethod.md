String的常用方法，用法示例见Demo/StringDemo

1.trim(）

作用:去除字符串头尾的空格以及其他预定义字符

2.indexOf()

作用:判断目标字符串/字符是否在源字符串中存在，存在则返回相应下标，不存在则返回-1

几种主要带参形式:

1)indexOf(int ch)

2)indexOf(int ch, int fromIndex)

3)indexOf(String str)

4)indexOf(String str, int fromIndex)

参数解释:int ch:某个字符的ASCII码值,int fromIndex:源字符串的起始搜索位置,String str:某个字符或字符串

Tips:在源码中1),3)通过调用2),4)来实现判断操作，其中参数fromIndex默认为0

3.lastIndexof()

作用:在原字符串中查找目标字符串最后一次出现的位置，存在返回位置，不存在则返回-1

几种主要带参形式:

1)lastIndexOf(int ch)

2)lastIndexOf(int ch, int fromIndex)

3)lastIndexOf(String str)

4)lastIndexOf(String str, int fromIndex)

对比:lastIndexOf与indexOf方法有异曲同工之妙，都是查询目标字符串/字符是否在所给的源字符串中，不同的是indexOf是返回目标在原字符串中第一次出现的位置，而latIndexOf返回的是目标在源字符串中最后一次出现的位置。因此在lastIndexOf中循环是倒叙循环。详细解析请见StringRecourceCode.md中lastIndexOf的源码分析
