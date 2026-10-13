// Task #14: nhiều object từ 1 class + sơ đồ reference
// Book.java chép từ Task #13 (không sửa).
// Chạy: mở file này trong IntelliJ, bấm ▶
public class ReferenceDemo {

    // Bài 2: trả về REFERENCE tới cuốn sách có nhiều trang nhất.
    // shelf rỗng thì trả về null. Nếu bằng nhau thì lấy cuốn đứng TRƯỚC.
    static Book findLongest(Book[] shelf) {
        for (int i = 0; i < shelf.length; i++) {
            if (shelf[i] != null) {
                Book longest = shelf[i];
                for (int j = i + 1; j < shelf.length; j++) {
                    if (shelf[j] != null && shelf[j].pages > longest.pages) {
                        longest = shelf[j];
                    }
                }
                return longest;
            }
        }
        return null;
    }

    // Bài 3: đếm số object KHÁC NHAU trong mảng (bỏ qua null).
    // Gợi ý: 2 ô cùng trỏ 1 object thì chỉ tính 1. So sánh reference bằng ==.
    static int countDistinct(Book[] shelf) {
        int count = 0;
        for (int i = 0; i < shelf.length; i++) {
            if (shelf[i] != null) {
                boolean isDistinct = true;
                for (int j = 0; j < i; j++) {
                    if (shelf[j] == shelf[i]) {
                        isDistinct = false;
                        break;
                    }
                }
                if (isDistinct) {
                    count++;
                }
            }
        }
        return count;
    }

    // Có sẵn, KHÔNG sửa: dùng cho câu 🔮
    static void rename(Book b) {
        b.title = "Đã đổi";
    }

    static void replace(Book b) {
        b = new Book();
        b.title = "Sách mới";
    }

    // ================== Test ==================
    static int pass = 0, fail = 0;

    static void check(String name, Object expected, Object actual) {
        if (String.valueOf(expected).equals(String.valueOf(actual))) {
            pass++;
        } else {
            fail++;
            System.out.println("FAIL " + name + ": mong đợi " + expected + ", thực tế " + actual);
        }
    }

    static Book newBook(String title, int pages) {
        Book b = new Book();
        b.title = title;
        b.author = "?";
        b.pages = pages;
        return b;
    }

    // Tránh NullPointerException khi Bài 2 chưa làm
    static String titleOf(Book b) {
        return b == null ? "null" : b.title;
    }

    public static void main(String[] args) {
        Book a = newBook("A", 100);
        Book b = newBook("B", 300);
        Book c = newBook("C", 300);
        Book[] shelf = {a, b, c, b, null};

        // Bài 2
        check("dài nhất là B (đứng trước C)", "B", titleOf(findLongest(shelf)));
        check("trả về đúng object b (==)", true, findLongest(shelf) == b);
        check("mảng rỗng", null, findLongest(new Book[0]));
        check("chỉ có null", null, findLongest(new Book[]{null, null}));

        // Sửa qua reference trả về -> object trong mảng đổi theo
        Book longest = findLongest(shelf);
        if (longest != null) {
            longest.read(50);
        }
        check("shelf[1] đọc được 50", 50, shelf[1].currentPage);
        check("shelf[3] cũng 50 (cùng object)", 50, shelf[3].currentPage);
        check("c không bị ảnh hưởng", 0, c.currentPage);

        // Bài 3
        check("đếm khác nhau", 3, countDistinct(shelf));
        check("đếm mảng rỗng", 0, countDistinct(new Book[0]));
        check("1 object lặp 3 lần", 1, countDistinct(new Book[]{a, a, a}));

        System.out.println(pass + "/" + (pass + fail) + " PASS");
    }
}
