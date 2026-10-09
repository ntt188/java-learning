import java.util.*;

// Bài 2: Kiểm chứng câu trả lời bằng code chạy thật.
// Cách làm: với mỗi câu, GÕ code vào chỗ TODO → chạy → so với ANSWERS.md → ghi kết quả vào Notes.md
// Chạy: java lesson02-fundamentals/Verify.java
public class Verify {

    // ===== Q4: String pool và intern() =====
    static void q4() {
        System.out.println("=== Q4: String pool ===");
        String a = "hi";
        String b = "hi";
        String c = new String("hi");
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(c));
        System.out.println(a == c.intern());
    }

    // ===== Q5: Immutable =====
    static void q5() {
        System.out.println("=== Q5: immutable ===");
        String s = "java";
        s.toUpperCase();
        System.out.println(s);
        s = s.toUpperCase();
        System.out.println(s);
    }

    // ===== Q7: Pass-by-value =====
    static void change(int x) {
        x = 100;
    }

    static void append(StringBuilder sb) {
        sb.append(" world");
    }

    static void reassign(StringBuilder sb) {
        sb = new StringBuilder("new");
    }

    static void q7() {
        System.out.println("=== Q7: pass-by-value ===");int n = 1;
        StringBuilder sb = new StringBuilder("hello");
        change(n);
        System.out.println("After change(n): n = " + n + ", sb = " + sb);
        append(sb);
        System.out.println("After append(sb): n = " + n + ", sb = " + sb);
        reassign(sb);
        System.out.println("After reassign(sb): n = " + n + ", sb = " + sb);
    }

    // ===== Q8: Integer cache và unboxing =====
    static void q8() {
        System.out.println("=== Q8: Integer cache ===");

        Integer x = 127;
        Integer y = 127;
        Integer p = 128;
        Integer q = 128;
        System.out.println("x == y: " + (x == y));
        System.out.println("p == q: " + (p == q));
        System.out.println("p.equals(q): " + p.equals(q));

        try {
            Integer z = null;
            int k = z; // unboxing null → NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }
    }

    // ===== Q10: final và immutable =====
    static void q10() {
        System.out.println("=== Q10: final vs immutable ===");
        final List<String> list = new ArrayList<>();
        list.add("a");
        System.out.println("List after adding 'a': " + list);
        // list = new ArrayList<>(); // Uncommenting this line will cause a compile-time error: "cannot assign a value to final variable list"
    }

    public static void main(String[] args) {
        q4();
        q5();
        q7();
        q8();
        q10();
    }
}
