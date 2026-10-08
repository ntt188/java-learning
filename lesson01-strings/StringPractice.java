public class StringPractice {

    // ===== Hàm kiểm tra =====
    static void check(String name, Object actual, Object expected) {
        boolean ok = java.util.Objects.equals(actual, expected);
        System.out.println((ok ? "PASS " : "FAIL ") + name
            + (ok ? "" : "  -> got: " + actual + ", expected: " + expected));
    }

    // ===== Bài 1.1 — Cách A: thủ công với char[] và hai con trỏ =====
    static String reverseA(String s) {
        if (s == null) {
            return null;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    // ===== Bài 1.1 — Cách B: dùng StringBuilder =====
    static String reverseB(String s) {
        if (s == null) {
            return null;
        }
        return new StringBuilder(s).reverse().toString();
    }

    // ===== Bài 1.1 — Mở rộng: đảo thứ tự các từ =====
    static String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        // trim(): bỏ khoảng trắng ở 2 đầu
        // "\\s+": regex = một hoặc nhiều khoảng trắng liên tiếp
        String[] words = s.trim().split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== 1.1 reverseA ===");
        check("hello", reverseA("hello"), "olleh");
        check("java",  reverseA("java"),  "avaj");
        check("empty", reverseA(""),      "");
        check("one",   reverseA("a"),     "a");
        check("space", reverseA("ab cd"), "dc ba");
        check("null", reverseA(null), null);

        System.out.println("=== 1.1 reverseB ===");
        check("hello", reverseB("hello"), "olleh");
        check("java",  reverseB("java"),  "avaj");
        check("empty", reverseB(""),      "");
        check("one",   reverseB("a"),     "a");
        check("space", reverseB("ab cd"), "dc ba");
        check("null", reverseB(null), null);

        System.out.println("=== 1.1 reverseWords ===");
        check("hello", reverseWords("hello"), "hello");
        check("java",  reverseWords("java"),  "java");
        check("empty", reverseWords(""),      "");
        check("one",   reverseWords("a"),     "a");
        check("space", reverseWords("ab cd"), "cd ab");
        check("null", reverseWords(null), null);
        check("sentence", reverseWords("I love Java"),      "Java love I");
        check("multi",    reverseWords("  I  love Java  "), "Java love I");
        check("blank",    reverseWords("   "),              "");

        System.out.println("=== emoji ===");
        System.out.println(reverseA("a😀b"));
        System.out.println(reverseB("a😀b"));
        System.out.println(reverseWords("a😀b"));
    }
}