// Task #15: Book có constructor (thay cho hàm newBook ở Task #13–14)
public class Book {
    String title;
    String author;
    int pages;
    int currentPage;

    // ===== Constructors =====

    // Bài 1: constructor đầy đủ. pages âm thì lưu 0.
    Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = Math.max(0, pages);
        this.currentPage = 0;
    }

    // Bài 2: không có author -> author = "Không rõ".
    // Cách A: tự gán từng field. 
    // Book(String title, int pages) {
    //     this.title = title;
    //     this.author = "Không rõ";
    //     this.pages = Math.max(0, pages);
    //     this.currentPage = 0;
    // }

    // Bài 2: không có author -> author = "Không rõ".
    // Cách B: gọi constructor khác bằng this(...)
    Book(String title, int pages) {
        this(title, "Không rõ", pages);
    }


    // Bài 3: copy constructor - tạo object MỚI giống other (title, author, pages),
    // currentPage bắt đầu lại từ 0.
    Book(Book other) {
        this(other.title, other.author, other.pages);
        this.currentPage = 0;
    }

    // ===== Methods (giữ từ Task #13) =====
    void read(int n) {
        if (n > (pages - currentPage)) {
            currentPage = pages;
        } else if (n > 0) {
            currentPage += n;
        }
    }

    boolean isFinished() {
        return currentPage >= pages;
    }

    String info() {
        return title + " - " + author + " (" + currentPage + "/" + pages + ")";
    }
}
