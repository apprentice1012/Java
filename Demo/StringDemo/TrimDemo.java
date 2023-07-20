package Demo.StringDemo;

/**
 * The type Trim.
 */
//String.trim()
//trim()方法主要作用是去除字符串头尾的空格
public class TrimDemo {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String str1 = " A ";
        System.out.println(str1.trim());
        String str2 = " A ";
        str2 = str2.trim();
        System.out.println(str2);
    }
}
