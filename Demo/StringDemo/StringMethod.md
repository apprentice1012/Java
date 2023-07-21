String的常用方法，用法示例见Demo/StringDemo
1.trim(）
作用:去除字符串头尾的空格以及其他预定义字符

2.indexOf()
作用:判断某个字符是否在字符串中存在，存在则返回相应下标，不存在则返回-1
几种带参形式:
indexOf(int ch)
indexOf(int ch, int fromIndex)
indexOf(String str)
indexOf(String str, int fromIndex) 
关键源码:
//参数说明:
//char[] source源字符串转化成char数组，int sourceOffset源字符串的偏移量(源码中写死为0)，int sourceCount源字符串的长度，
//char[] target目标字符串转化成char数组，int targetOffset目标字符串的偏移量(源码中写死为0)，int targetCount目标字符串的长度
//int fromIndex原字符串的开始搜索的下标
    static int indexOf(char[] source, int sourceOffset, int sourceCount,
            char[] target, int targetOffset, int targetCount,
            int fromIndex) {
        //IndexDemo中有示例
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
            //查找第一个目标字符是否在原字符数组
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                //这个循环找到和目标字符串完全相等的长度
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++);
                //如果完全相等的长度和目标字符串长度相等，那么就认为找到了
                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }


