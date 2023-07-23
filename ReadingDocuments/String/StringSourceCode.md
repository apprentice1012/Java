******String源码阅读:******
****1.trim****
String源码中trim()方法只有无参类型

**关键源码:**

public String trim() {
    int len = value.length;
    int st = 0;
    char[] val = value;    
    /* avoid getfield opcode */
    /*val[st] <= ' '和val[len - 1] <= ' '用于判断字符是否为空格或空字符。如果字符的Unicode值小于或等于空格字符的Unicode值，那么该字符就被认为是空格或空字符*/
    while ((st < len) && (val[st] <= ' ')) {
        st++;
    }
    while ((st < len) && (val[len - 1] <= ' ')) {
        len--;
    }
    return ((st > 0) || (len < value.length)) ? substring(st, len) : this;
 }
****2.indexOf****

String源码中IndexOf()方法主要使用的有四种，详情可见StringMethod第二条

**关键源码:**

/*static 关键字修饰的方法表示它是一个静态方法。静态方法与类相关联，而不是与实例对象相关联。使用 static 关键字修饰的方法可以通过类名直接调用，而不需要创建类的实例对象。
在indexOf的方法中，indexOf方法是一个静态方法，它用于在源字符数组中查找目标字符数组第一次出现的位置。使用static关键字修饰的原因是为了将indexOf方法定义为一个通用的工具方法，可以在不创建类实例的
况下直接使用。static关键字见Static.md*/
static int indexOf(char[] source, int sourceOffset, int sourceCount, char[] target, int targetOffset, int targetCount, int fromIndex) { 
    /*IndexDemo中有示例*/
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
char[] source源字符串转化成char数组，int sourceOffset源字符串的偏移量(源码中写死为0)，int sourceCount源字符串的长度，
char[] target目标字符串转化成char数组，int targetOffset目标字符串的偏移量(源码中写死为0)，int targetCount目标字符串的长度
int fromIndex原字符串的开始搜索的下标
