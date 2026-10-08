import java.util.*;

public class StringPractice {

    // ===== Hàm kiểm tra =====
    static void check(String name, Object actual, Object expected) {
        boolean ok = Objects.equals(actual, expected);
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

    // ===== Bài 1.2 — Cách A: mảng đếm =====
    // Mảng 65536 ô = đủ cho MỌI giá trị char (0..65535), nên "café" không bị crash.
    // (Mảng 128 ô chỉ đủ cho ASCII: 'é' = 233 sẽ vượt chỉ số.)
    static Map<Character, Integer> countCharsA(String s) {
        if (s == null) {
            return null;
        }
        int[] count = new int[Character.MAX_VALUE + 1];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                result.put((char) i, count[i]);
            }
        }
        return result;
    }

    // ===== Bài 1.2 — Cách B: HashMap =====
    static Map<Character, Integer> countCharsB(String s) {
        if (s == null) {
            return null;
        }
        Map<Character, Integer> result = new HashMap<>();
        for (char c : s.toCharArray()) {
            result.put(c, result.getOrDefault(c, 0) + 1);
        }
        return result;
    }

    // ===== Bài 1.3 — Cách A: hai con trỏ, bỏ qua ký tự không hợp lệ =====
    static boolean isPalindromeA(String s) {
        if (s == null) {
            return false;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(rightChar)) {
                right--;
                continue;
            }
            if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // ===== Bài 1.3 — Cách B: lọc chuỗi bằng StringBuilder rồi so với bản đảo ngược =====
    static boolean isPalindromeB(String s) {
        if (s == null) {
            return false;
        }
        StringBuilder filtered = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                filtered.append(Character.toLowerCase(c));
            }
        }
        String filteredStr = filtered.toString();
        String reversedStr = filtered.reverse().toString();
        return filteredStr.equals(reversedStr);
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

        System.out.println("=== 1.2 countCharsA ===");
        check("hello", countCharsA("hello"), Map.of('h', 1, 'e', 1, 'l', 2, 'o', 1));
        check("aaa",   countCharsA("aaa"),   Map.of('a', 3));
        check("case",  countCharsA("Aa"),    Map.of('A', 1, 'a', 1));
        check("space", countCharsA("a b"),   Map.of('a', 1, ' ', 1, 'b', 1));
        check("empty", countCharsA(""),      Map.of());
        check("null",  countCharsA(null),    null);
        check("cafe",  countCharsA("café"),  Map.of('c', 1, 'a', 1, 'f', 1, 'é', 1));

        System.out.println("=== 1.2 countCharsB ===");
        check("hello", countCharsB("hello"), Map.of('h', 1, 'e', 1, 'l', 2, 'o', 1));
        check("aaa",   countCharsB("aaa"),   Map.of('a', 3));
        check("case",  countCharsB("Aa"),    Map.of('A', 1, 'a', 1));
        check("space", countCharsB("a b"),   Map.of('a', 1, ' ', 1, 'b', 1));
        check("empty", countCharsB(""),      Map.of());
        check("null",  countCharsB(null),    null);
        check("cafe",  countCharsB("café"),  Map.of('c', 1, 'a', 1, 'f', 1, 'é', 1));

        System.out.println("=== 1.2 demo: thứ tự HashMap vs LinkedHashMap ===");
        Map<Character, Integer> linked = new LinkedHashMap<>();
        for (char c : "hello".toCharArray()) {
            linked.put(c, linked.getOrDefault(c, 0) + 1);
        }
        System.out.println("HashMap       : " + countCharsB("hello"));
        System.out.println("LinkedHashMap : " + linked);
        System.out.println("equals?       : " + countCharsB("hello").equals(linked));

        System.out.println("=== 1.2 demo: emoji ===");
        System.out.println("\"😀\".length() = " + "😀".length());
        System.out.println("countCharsB(\"😀\").size() = " + countCharsB("😀").size());

        System.out.println("=== 1.3 isPalindromeA ===");
        check("racecar", isPalindromeA("racecar"), true);
        check("abba",    isPalindromeA("abba"),    true);
        check("hello",   isPalindromeA("hello"),   false);
        check("case",    isPalindromeA("Aa"),      true);
        check("panama",  isPalindromeA("A man, a plan, a canal: Panama"), true);
        check("raceacar",isPalindromeA("race a car"), false);
        check("empty",   isPalindromeA(""),        true);
        check("symbols", isPalindromeA(".,!"),     true);
        check("0P",      isPalindromeA("0P"),      false);
        check("null",    isPalindromeA(null),     false);

        System.out.println("=== 1.3 isPalindromeB ===");
        check("racecar", isPalindromeB("racecar"), true);
        check("abba",    isPalindromeB("abba"),    true);
        check("hello",   isPalindromeB("hello"),   false);
        check("case",    isPalindromeB("Aa"),      true);
        check("panama",  isPalindromeB("A man, a plan, a canal: Panama"), true);
        check("raceacar",isPalindromeB("race a car"), false);
        check("empty",   isPalindromeB(""),        true);
        check("symbols", isPalindromeB(".,!"),     true);
        check("0P",      isPalindromeB("0P"),      false);
        check("null",    isPalindromeB(null),     false);
    }
}