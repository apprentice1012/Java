**String源码阅读:**

**1.trim**

String源码中trim()方法只有无参类型

**关键源码:**

/*其中val[st] <= ' '和val[len - 1] <= ' '用于判断字符是否为空格或空字符。如果字符的Unicode值小于或等于空格字符的Unicode值，那么该字符就被认为是空格或空字符*/

public String trim() {
    int len = value.length;
    int st = 0;
    char[] val = value;
    
    /* avoid getfield opcode */
    
    while ((st < len) && (val[st] <= ' ')) {
        st++;
    }
    while ((st < len) && (val[len - 1] <= ' ')) {
        len--;
    }
    return ((st > 0) || (len < value.length)) ? substring(st, len) : this;
 }
 
**2.indexOf**

String源码中IndexOf()方法主要使用的有四种，详情可见StringMethod第二条

**关键源码:**

/*static 关键字修饰的方法表示它是一个静态方法。静态方法与类相关联，而不是与实例对象相关联。使用 static 关键字修饰的方法可以通过类名直接调用，而不需要创建类的实例对象。在indexOf的方法中，indexOf方法是一个静态方法，它用于在源字符数组中查找目标字符数组第一次出现的位置。使用static关键字修饰的原因是为了将indexOf方法定义为一个通用的工具方法，可以在不创建类实例的况下直接使用。static关键字见Static.md。这个解释不确定合理不合理，只是我个人觉得还算合理就放在了这里*/

static int indexOf(char[] source, int sourceOffset, int sourceCount, char[] target, int targetOffset, int targetCount, int fromIndex) { 
   
    
    if (fromIndex >= sourceCount) {
        return (targetCount == 0 ? sourceCount : -1);
    }
    if (fromIndex < 0) {
        fromIndex = 0;
    }
    if (targetCount == 0) {
        return fromIndex;
    }

    char first = target[targetOffset];
    int max = sourceOffset + (sourceCount - targetCount);

    for (int i = sourceOffset + fromIndex; i <= max; i++) {
        /* Look for first character. */
        /*查找第一个目标字符是否在原字符数组，这段代码的目的是通过跳过源字符数组中与目标字符数组第一个字符不相等的位置，快速定位到可能匹配的位置。如果没有找到第一个字符相等的位置，则可以提高查找效率*/
        if (source[i] != first) {
            while (++i <= max && source[i] != first);
        }

        /* Found first character, now look at the rest of v2 */
        if (i <= max) {
            int j = i + 1;
            int end = j + targetCount - 1;
            //这个循环找到和目标字符串完全相等的长度
            for (int k = targetOffset + 1; j < end && source[j]== target[k]; j++, k++);
                /*如果完全相等的长度和目标字符串长度相等，那么就认为找到了*/
                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
        }
    }
    return -1;
}

**参数说明:**

char[] source源字符串转化成char数组，int sourceOffset源字符串的偏移量(源码中写死为0)，int sourceCount源字符串的长度

char[] target目标字符串转化成char数组，int targetOffset目标字符串的偏移量(源码中写死为0)，int targetCount目标字符串的长度

int fromIndex原字符串的开始搜索的下标

**3.lastIndexOf**

String源码中有四种带参形式详情见StringMethod

**关键源码**

lastIndexOf关键源码大致有三个部分(根据参数来分的):分别是int ch是一个无效点，int ch有效，字符串

**/*第一段对应int ch是一个无效点*/**

/*MIN_SUPPLEMENTARY_CODE_POINT是Java character类中的一个常量它代表了 Unicode 编码中的最小补充代码点。Unicode 是一种字符编码标准，其中包含了各种语言和符号的字符。Unicode 编码将每个字符都分配了一个唯一的代码点。

基本多文种平面（BMP）是 Unicode 中的第一个平面，它包含了大部分常用字符。补充平面是 BMP 之外的字符所处的平面，其中包括一些较少使用的字符和辅助字符。MIN_SUPPLEMENTARY_CODE_POINT 的值是 0x010000，它标志着补充平面的起始代码点。

在这段代码中ch<Charcater.MIN_SUPPLEMENTARY_CODE_POINT是为了检查当前ch是否在BMP内或者说是一个无效点，如果是则使用当前逻辑进行判断，如果不是则使用第二段关键代码即lastIndexOfSupplementary方法(这段是百度的，后续个人有对Character新的理解会补充在Charater.md文档中)*/

    public int lastIndexOf(int ch, int fromIndex) {
        if (ch < Character.MIN_SUPPLEMENTARY_CODE_POINT) {
            // handle most cases here (ch is a BMP code point or a
            // negative value (invalid code point))
            final char[] value = this.value;
            int i = Math.min(fromIndex, value.length - 1);
            for (; i >= 0; i--) {
                if (value[i] == ch) {
                    return i;
                }
            }
            return -1;
        } else {
            return lastIndexOfSupplementary(ch, fromIndex);
        }
    }

**/*第二段对应int ch有效*/**

/*这段代码是lastIndexOf方法中调用的一个辅助方法。

方法接受两个参数，一个是要查找的补充平面字符的代码点，另一个是开始查找的索引位置。首先，它会检查给定的代码点是否是有效的代码点(Character.isValidCodePoint(ch)).然后,它会将代码点拆分为高代理项和低代理项,分别存储在变量hi和lo中。

接下来，它会获取字符串的字符数组value，并根据给定的起始索引和字符数组长度计算出循环的起始位置.然后，从循环起始位置开始向前遍历字符数组，判断每个字符是否与高代理项和低代理项相匹配。如果找到了匹配的字符序列，则返回它在字符串中的索引位置。

如果给定的代码点不是有效的代码点，或者未找到匹配的字符序列，则返回-1。这段代码实现了在字符串中反向查找补充平面字符的功能，以支持Unicode编码中的补充平面字符*/

private int lastIndexOfSupplementary(int ch, int fromIndex) {

        if (Character.isValidCodePoint(ch)) {
            final char[] value = this.value;
            char hi = Character.highSurrogate(ch);
            char lo = Character.lowSurrogate(ch);
            int i = Math.min(fromIndex, value.length - 2);
            for (; i >= 0; i--) {
                if (value[i] == hi && value[i + 1] == lo) {
                    return i;
                }
            }
        }
        return -1;
    }
    
**/*第三段对应入参String str*/**

/*大致流程是递归循环，先判断目标字符串在原字符串中的位置，之后通过变量定位源字串的之后的字符是否与目标字串之后的字符相同，如果不同则跳到外层标签重新开始循环，如果找不到则返回-1。

其中"startSearchForLastChar:"是一个外层标签，外部标签通常在嵌套循环中使用，提供一个跳转目标，当内层循环使用break或者continue的时候可以跳转到标签处进行相应的处理。但是不建议大量使用，避免后续代码难以维护

rightIndex右侧索引作用是避免fromIndex越界从而出现数组越界错误*/

    static int lastIndexOf(char[] source, int sourceOffset, int sourceCount,
            char[] target, int targetOffset, int targetCount,
            int fromIndex) {
        /*
         * Check arguments; return immediately where possible. For
         * consistency, don't check for null str.
         */
        int rightIndex = sourceCount - targetCount;
        if (fromIndex < 0) {
            return -1;
        }
        if (fromIndex > rightIndex) {
            fromIndex = rightIndex;
        }
        /* Empty string always matches. */
        if (targetCount == 0) {
            return fromIndex;
        }

        int strLastIndex = targetOffset + targetCount - 1;
        char strLastChar = target[strLastIndex];
        int min = sourceOffset + targetCount - 1;
        int i = min + fromIndex;

    startSearchForLastChar:
        while (true) {
            /*查找第一个符合目标字符的位置*/
            while (i >= min && source[i] != strLastChar) {
                i--;
            }
            if (i < min) {
                return -1;
            }
            int j = i - 1;
            int start = j - (targetCount - 1);
            int k = strLastIndex - 1;

            while (j > start) {
                if (source[j--] != target[k--]) {
                    i--;
                    continue startSearchForLastChar;
                }
            }
            return start - sourceOffset + 1;
        }
    }

**参数说明:**

char[] source源字符串转化成char数组，int sourceOffset源字符串的偏移量(源码中写死为0)，int sourceCount源字符串的长度

char[] target目标字符串转化成char数组，int targetOffset目标字符串的偏移量(源码中写死为0)，int targetCount目标字符串的长度

int fromIndex开始搜索源字符串的位置

**4.length**

String提供的计算源字符串长度的方法

**关键源码**

/*主要是先将原字符串转换成char类型的数组，然后通过调用基本类型数组的length方法来判断源字符串的长度*/

private final char value[];

    public int length() {
        return value.length;
    }

**5.isEmpty**

判断字符串是否为空的一个方法

**关键源码**

/*由于是boolean类型的所以返回true or false条件就是value.length==0*/

    public boolean isEmpty() {
        return value.length == 0;
    }

**6.charAt**

查询源字符串中指定位置所对应的字符

**关键源码**

/*首先判断所给的查询位置是否有越界行为，如果有数组越界行为的话则抛出异常，然后由于数组的随机访问直接返回当前位置的值即可*/

    public char charAt(int index) {
        if ((index < 0) || (index >= value.length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return value[index];
    }
**参数说明**

int index字符所在位置的索引

**7.codePointAt**

返回源字符串中所选位置字符的UniCode值，开发中用的较少，常用来查询某个字符的Unicode值

**关键源码**

/*首先判断所给的查询位置是否有越界行为，如果有数组越界行为的话则抛出异常，然后调用Character中的codePointAtImpl方法来实现*/

    public int codePointAt(int index) {
        if ((index < 0) || (index >= value.length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return Character.codePointAtImpl(value, index, value.length);
    }

    static int codePointAtImpl(char[] a, int index, int limit) {
        char c1 = a[index];
        if (isHighSurrogate(c1) && ++index < limit) {
            char c2 = a[index];
            if (isLowSurrogate(c2)) {
                return toCodePoint(c1, c2);
            }
        }
        return c1;
    }


