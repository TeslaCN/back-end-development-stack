package ltd.scau.study.basic.practice;

/**
 * @author Weijie Wu
 *
 * 对JDK1.8 HashMap的红黑树影响不大😂😂
 */
public class HashCollision {

    public static void main(String[] args) {
        String[] ss = new String[] {
                "Aa",
                "BB",
                "AaBB",
                "AaAa",
                "AaAaAa",
                "AaAaBB",
        };
        for (String s : ss) {
            System.out.println(s.hashCode());
        }
    }
}
