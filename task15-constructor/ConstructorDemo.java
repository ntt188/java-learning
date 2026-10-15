// Chạy: mở file này trong IntelliJ, bấm ▶
public class ConstructorDemo {
    static int pass = 0, fail = 0;

    static void check(String name, Object expected, Object actual) {
        if (String.valueOf(expected).equals(String.valueOf(actual))) {
            pass++;
        } else {
            fail++;
            System.out.println("FAIL " + name + ": mong đợi " + expected + ", thực tế " + actual);
        }
    }

    public static void main(String[] args) {
        // Bài 1
        Book a = new Book("Clean Code", "Robert Martin", 300);
        check("info đầy đủ", "Clean Code - Robert Martin (0/300)", a.info());
        Book neg = new Book("Lỗi", "?", -5);
        check("pages âm -> 0", 0, neg.pages);

        // Bài 2
        Book b = new Book("Java", 200);
        check("không có author", "Java - Không rõ (0/200)", b.info());
        Book bNeg = new Book("Âm", -1);
        check("Bài 2 cũng chặn pages âm", 0, bNeg.pages);

        // Bài 3
        a.read(100);
        Book copy = new Book(a);
        check("copy cùng title", "Clean Code", copy.title);
        check("copy currentPage = 0", 0, copy.currentPage);
        check("copy là object KHÁC", false, copy == a);
        copy.read(50);
        check("đọc copy không ảnh hưởng a", 100, a.currentPage);
        copy.title = "Bản sao";
        check("đổi title copy, a giữ nguyên", "Clean Code", a.title);

        System.out.println(pass + "/" + (pass + fail) + " PASS");
    }
}
