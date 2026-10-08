public class Concepts {

    // Khái niệm 1: String là immutable
    static void concept1() {
        System.out.println("=== Concept 1: immutable ===");
        String s = "java";
        System.out.println(s);

        s = s.toUpperCase();
        System.out.println(s);
    }

    // Khái niệm 2: + so với StringBuilder trong vòng lặp
    static void concept2() {
        System.out.println("=== Concept 2: + vs StringBuilder ===");
        int n = 100000;

        long start = System.nanoTime();
        // TODO: vòng lặp nối bằng +
        String result = "";
        for (int i = 0; i < n; i++) {
            result += i;
        }
        long timePlus = System.nanoTime() - start;

        start = System.nanoTime();
        // TODO: vòng lặp dùng StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }
        long timeSb = System.nanoTime() - start;

        System.out.println("+             : " + timePlus / 1_000_000 + " ms");
        System.out.println("StringBuilder : " + timeSb / 1_000_000 + " ms");
    }

    // Khái niệm 3: == và equals()
    static void concept3() {
        System.out.println("=== Concept 3: == vs equals ===");
        // TODO: gõ đoạn code khái niệm 3 vào đây
        String s1 = "hi";
        String s2 = "hi";
        String s3 = new String("hi");

        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s1 == s3: " + (s1 == s3));
        System.out.println("s1.equals(s3): " + s1.equals(s3));

        char x = 'a', y = 'a';
        System.out.println("x == y: " + (x == y));
    }

    // Câu hỏi nhanh: reverse() sửa chính đối tượng hay tạo đối tượng mới?
    static void quickQuestion() {
        System.out.println("=== Quick question: reverse() ===");
        StringBuilder sb = new StringBuilder("abc");
        StringBuilder r = sb.reverse();
        System.out.println(sb);        // ❓ dự đoán
        System.out.println(sb == r);   // ❓ dự đoán
    }

    public static void main(String[] args) {
        concept1();
        concept2();
        concept3();
        quickQuestion();
    }
}