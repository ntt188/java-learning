// Task #9: Dùng Debugger để tìm lỗi
// Mỗi method dưới đây có ĐÚNG 1 lỗi. Luật chơi:
//   1. KHÔNG đọc code để đoán lỗi. Đặt breakpoint, chạy Debug, nhìn giá trị biến.
//   2. Ghi bằng chứng vào Notes.md: breakpoint ở dòng nào, thấy biến nào = bao nhiêu.
//   3. Sau đó mới sửa, chạy lại cho đến khi ra 11/11 PASS.

import java.util.Objects;

public class DebugPractice {

    // Bug 1: trung bình cộng của mảng
    static double average(int[] nums) {
        if (nums.length == 0) {
            // Chặn mảng rỗng: không chặn thì double ra NaN (âm thầm sai), int thì ArithmeticException
            throw new IllegalArgumentException("Mảng rỗng, không tính được trung bình");
        }
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum / nums.length;
    }

    // Bug 2: đếm nguyên âm (a, e, i, o, u), không phân biệt hoa thường
    static int countVowels(String s) {
        String lower = s.toLowerCase();
        int count = 0;
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    // Bug 3: số đối xứng? (121 -> true, 123 -> false), n >= 0
    static boolean isPalindromeNumber(int n) {
        int reversed = 0;
        int original = n;
        while (n > 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed == original;
    }

    // Bug 4: tổng bình phương 1^2 + 2^2 + ... + n^2
    // Gợi ý công cụ: vòng lặp 50000 lần, đừng bấm Step Over 50000 lần.
    // Hãy dùng CONDITIONAL BREAKPOINT (chuột phải vào chấm đỏ -> Condition).
    static long sumOfSquares(int n) {
        long total = 0;
        for (long i = 1; i <= n; i++) {
            total += i * i;
        }
        return total;
    }

    // ================== TEST (không cần sửa phần dưới) ==================
    static int pass = 0, fail = 0;

    static void check(String name, Object actual, Object expected) {
        if (Objects.equals(String.valueOf(actual), String.valueOf(expected))) pass++;
        else { fail++; System.out.println("FAIL " + name + "\n  expected: [" + expected + "]\n  actual:   [" + actual + "]"); }
    }

    public static void main(String[] args) {
        check("average({1,2})", average(new int[]{1, 2}), 1.5);
        check("average({2,4,6})", average(new int[]{2, 4, 6}), 4.0);
        check("average({1,2,2})", average(new int[]{1, 2, 2}), 5.0 / 3);

        check("countVowels(\"apple\")", countVowels("apple"), 2);
        check("countVowels(\"Education\")", countVowels("Education"), 5);
        check("countVowels(\"sky\")", countVowels("sky"), 0);

        check("isPalindromeNumber(121)", isPalindromeNumber(121), true);
        check("isPalindromeNumber(123)", isPalindromeNumber(123), false);
        check("isPalindromeNumber(7)", isPalindromeNumber(7), true);

        check("sumOfSquares(10)", sumOfSquares(10), 385L);
        check("sumOfSquares(50000)", sumOfSquares(50000), 41667916675000L);

        System.out.println("\n" + pass + "/" + (pass + fail) + " PASS");
    }
}
