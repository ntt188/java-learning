// Task #8: Control flow & methods: 10 bài nhỏ
// Chạy: javac ControlFlowPractice.java && java ControlFlowPractice
// Mục tiêu: tất cả PASS. Nhớ XÓA "// TODO" khi làm xong mỗi bài!

import java.util.Objects;

public class ControlFlowPractice {

    // ===== Bài 1: FizzBuzz =====
    // Chia hết cho 3 và 5 -> "FizzBuzz", chỉ 3 -> "Fizz", chỉ 5 -> "Buzz", còn lại -> chính số đó dạng String
    static String fizzBuzz(int n) {
        if (n % 15 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(n);
    }

    // ===== Bài 2: Số nguyên tố =====
    // Số nguyên tố: > 1 và chỉ chia hết cho 1 và chính nó
    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // ===== Bài 3: Fibonacci (F0 = 0, F1 = 1, Fn = Fn-1 + Fn-2) =====
    // Cách 1: vòng lặp. Cách 2 (viết thêm hàm fibonacciRecursive): đệ quy.
    static long fibonacciLoop(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    static long fibonacciRecursive(int n) {
        if (n == 0) {   
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // ===== Bài 4: Bảng cửu chương =====
    // Trả về 10 dòng "n x i = kết quả", nối bằng "\n", KHÔNG có "\n" ở cuối
    static String multiplicationTable(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            sb.append(n).append(" x ").append(i).append(" = ").append(n * i);
            if (i < 10) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // ===== Bài 5: Đổi cơ số (n >= 0, base từ 2 đến 16) =====
    // Ví dụ: toBase(10, 2) -> "1010", toBase(255, 16) -> "FF"
    // Không dùng Integer.toString(n, base) hay Integer.toBinaryString
    static String toBase(int n, int base) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = n % base;
            if (remainder < 10) {
                sb.append(remainder);
            } else {
                sb.append((char) ('A' + (remainder - 10)));
            }
            n /= base;
        }
        return sb.reverse().toString();
    }

    // ===== Bài 6: Tổng các chữ số (số âm thì bỏ dấu) =====
    static int sumDigits(int n) {
        n = Math.abs(n);
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    // ===== Bài 7: Đảo ngược số (giữ dấu). 1200 -> 21, -45 -> -54 =====
    // Không chuyển sang String, chỉ dùng % và /
    static int reverseNumber(int n) {
        int reversed = 0;
        int sign = n < 0 ? -1 : 1;
        n = Math.abs(n);
        while (n > 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed * sign;
    }

    // ===== Bài 8: Ước chung lớn nhất (a, b >= 0, không cùng = 0) =====
    // Gợi ý: thuật toán Euclid. Cách 1: while. Cách 2: đệ quy.
    static int gcdLoop(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int gcdRecursive(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdRecursive(b, a % b);
    }

    // ===== Bài 9: Giai thừa n! (0 <= n <= 20) =====
    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // ===== Bài 10: Đếm số nguyên tố <= limit =====
    // Bắt buộc GỌI LẠI isPrime() của bài 2 (method gọi method)
    static int countPrimes(int limit) {
        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    // ===== Bài luyện thêm: tổng 1 + 2 + ... + n bằng đệ quy =====
    static int sumTo(int n) {
        if (n < 0) {
            // Chặn đầu vào sai: không có dòng này thì sumTo(-3) gọi -4, -5... mãi -> StackOverflowError
            throw new IllegalArgumentException("n phải >= 0, nhận được: " + n);
        }
        if (n == 0) {
            return 0;               // base case (điểm dừng)
        }
        return n + sumTo(n - 1);    // bước thu nhỏ: n -> n - 1, tiến dần về 0
    }

    // ================== TEST (không cần sửa phần dưới) ==================
    static int pass = 0, fail = 0;

    static void check(String name, String actual, String expected) {
        if (Objects.equals(actual, expected)) pass++;
        else { fail++; System.out.println("FAIL " + name + "\n  expected: [" + expected + "]\n  actual:   [" + actual + "]"); }
    }

    static void check(String name, long actual, long expected) {
        check(name, String.valueOf(actual), String.valueOf(expected));
    }

    static void check(String name, boolean actual, boolean expected) {
        check(name, String.valueOf(actual), String.valueOf(expected));
    }

    public static void main(String[] args) {
        // Bài 1
        check("fizzBuzz(1)", fizzBuzz(1), "1");
        check("fizzBuzz(3)", fizzBuzz(3), "Fizz");
        check("fizzBuzz(5)", fizzBuzz(5), "Buzz");
        check("fizzBuzz(15)", fizzBuzz(15), "FizzBuzz");
        check("fizzBuzz(30)", fizzBuzz(30), "FizzBuzz");
        check("fizzBuzz(7)", fizzBuzz(7), "7");
        // Bài 2
        check("isPrime(-7)", isPrime(-7), false);
        check("isPrime(0)", isPrime(0), false);
        check("isPrime(1)", isPrime(1), false);
        check("isPrime(2)", isPrime(2), true);
        check("isPrime(3)", isPrime(3), true);
        check("isPrime(4)", isPrime(4), false);
        check("isPrime(9)", isPrime(9), false);
        check("isPrime(25)", isPrime(25), false);
        check("isPrime(97)", isPrime(97), true);
        check("isPrime(7919)", isPrime(7919), true);
        // Bài 3
        check("fibonacci(0)", fibonacciLoop(0), 0);
        check("fibonacci(1)", fibonacciLoop(1), 1);
        check("fibonacci(2)", fibonacciLoop(2), 1);
        check("fibonacci(10)", fibonacciLoop(10), 55);
        check("fibonacci(50)", fibonacciLoop(50), 12586269025L);
        check("fibonacci(0)", fibonacciRecursive(0), 0);
        check("fibonacci(1)", fibonacciRecursive(1), 1);
        check("fibonacci(2)", fibonacciRecursive(2), 1);
        check("fibonacci(10)", fibonacciRecursive(10), 55);
        check("fibonacci(50)", fibonacciRecursive(50), 12586269025L);
        // Bài 4
        check("multiplicationTable(3)", multiplicationTable(3),
                "3 x 1 = 3\n3 x 2 = 6\n3 x 3 = 9\n3 x 4 = 12\n3 x 5 = 15\n"
              + "3 x 6 = 18\n3 x 7 = 21\n3 x 8 = 24\n3 x 9 = 27\n3 x 10 = 30");
        // Bài 5
        check("toBase(10,2)", toBase(10, 2), "1010");
        check("toBase(5,2)", toBase(5, 2), "101");
        check("toBase(0,2)", toBase(0, 2), "0");
        check("toBase(8,8)", toBase(8, 8), "10");
        check("toBase(31,16)", toBase(31, 16), "1F");
        check("toBase(255,16)", toBase(255, 16), "FF");
        // Bài 6
        check("sumDigits(0)", sumDigits(0), 0);
        check("sumDigits(123)", sumDigits(123), 6);
        check("sumDigits(-123)", sumDigits(-123), 6);
        check("sumDigits(9999)", sumDigits(9999), 36);
        // Bài 7
        check("reverseNumber(123)", reverseNumber(123), 321);
        check("reverseNumber(1200)", reverseNumber(1200), 21);
        check("reverseNumber(0)", reverseNumber(0), 0);
        check("reverseNumber(7)", reverseNumber(7), 7);
        check("reverseNumber(-45)", reverseNumber(-45), -54);
        // Bài 8
        check("gcdLoop(12,18)", gcdLoop(12, 18), 6);
        check("gcdLoop(17,5)", gcdLoop(17, 5), 1);
        check("gcdLoop(0,9)", gcdLoop(0, 9), 9);
        check("gcdLoop(9,0)", gcdLoop(9, 0), 9);
        check("gcdLoop(100,75)", gcdLoop(100, 75), 25);
        check("gcdRecursive(12,18)", gcdRecursive(12, 18), 6);
        check("gcdRecursive(17,5)", gcdRecursive(17, 5), 1);
        check("gcdRecursive(0,9)", gcdRecursive(0, 9), 9);
        check("gcdRecursive(9,0)", gcdRecursive(9, 0), 9);
        check("gcdRecursive(100,75)", gcdRecursive(100, 75), 25);
        // Bài 9
        check("factorial(0)", factorial(0), 1);
        check("factorial(1)", factorial(1), 1);
        check("factorial(5)", factorial(5), 120);
        check("factorial(20)", factorial(20), 2432902008176640000L);
        // Bài 10
        check("countPrimes(1)", countPrimes(1), 0);
        check("countPrimes(2)", countPrimes(2), 1);
        check("countPrimes(10)", countPrimes(10), 4);
        check("countPrimes(100)", countPrimes(100), 25);

        // Bài luyện thêm: sumTo
        check("sumTo(0)", sumTo(0), 0);
        check("sumTo(1)", sumTo(1), 1);
        check("sumTo(10)", sumTo(10), 55);
        check("sumTo(100)", sumTo(100), 5050);
        boolean thrown = false;
        try {
            sumTo(-3);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        check("sumTo(-3) ném IllegalArgumentException", thrown, true);

        System.out.println("\n" + pass + "/" + (pass + fail) + " PASS");
    }
}
