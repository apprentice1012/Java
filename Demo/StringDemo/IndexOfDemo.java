package Demo.StringDemo;


/**
 * The type Index of demo.
 */
//String.indexOf()
//indexOf()方法主要作用是查询某个字符是否存在
public class IndexOfDemo {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(str.indexOf("a"));//判断字符a是否在str中
        System.out.println(str.indexOf(99));//判断字符c是否在str中，99为c的ASCII码
        System.out.println(str.indexOf("e"));//e不再str中所以返回-1
        System.out.println(str.indexOf("d",1));//判断d是否在str的起始下标为1之后的字符串(bcd)中
        System.out.println(str.indexOf("",4));//验证源码中if (fromIndex >= sourceCount) {return (targetCount == 0 ? sourceCount : -1);}
    }
}
