// Chạy: javac *.java && java BookTest
public class BookTest {
    static int pass = 0, fail = 0;

    static void check(String name, Object expected, Object actual) {
        if (String.valueOf(expected).equals(String.valueOf(actual))) {
            pass++;
        } else {
            fail++;
            System.out.println("FAIL " + name + ": mong đợi " + expected + ", thực tế " + actual);
        }
    }

    static Book newBook(String title, String author, int pages) {
        Book b = new Book();
        b.title = title;
        b.author = author;
        b.pages = pages;
        return b;
    }

    public static void main(String[] args) {
        // Bài 1: info()
        Book b = newBook("Clean Code", "Robert Martin", 300);
        check("info mới", "Clean Code - Robert Martin (0/300)", b.info());

        // Bài 2: read() + isFinished()
        b.read(120);
        check("read 120", 120, b.currentPage);
        b.read(0);
        check("read 0", 120, b.currentPage);
        b.read(-5);
        check("read âm", 120, b.currentPage);
        check("chưa xong", false, b.isFinished());
        b.read(500);
        check("không vượt pages", 300, b.currentPage);
        check("đã xong", true, b.isFinished());

        // Bài 3: progressPercent()
        Book c = newBook("Java", "Ai đó", 300);
        c.read(50);
        check("50/300", 16, c.progressPercent());
        c.read(100);
        check("150/300", 50, c.progressPercent());
        Book empty = newBook("Rỗng", "?", 0);
        check("pages 0", 0, empty.progressPercent());

        Book d = newBook("Ảo thuật", "copper field", 300);
        d.read(Integer.MAX_VALUE);
        check("300/300", 100, d.progressPercent());

        // Hai object độc lập
        check("b không ảnh hưởng c", 150, c.currentPage);

        System.out.println(pass + "/" + (pass + fail) + " PASS");
    }
}
