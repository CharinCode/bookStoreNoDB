package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    List<Book> arrayLists = new ArrayList<>(Arrays.asList(new Book(1, "Book One", 100), new Book(2, "Book Two", 200), new Book(3, "Book Three", 300)));

    @GetMapping
    public List<Book> getBook() {
        return arrayLists;
    }

    @PostMapping()
    public String addBook(@RequestBody Book book) {
        arrayLists.add(book);
        return "Add successfully";
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") int id) {
        return arrayLists.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable("id") int id) {
        Book book = arrayLists.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        if (book != null) {
            arrayLists.remove(book);
            return "Removed successfully";
        }
        return "Book not found";
    }

    static class Book {
        private int id;
        private String title;
        private int price;

        public Book(int id, String title, int price) {
            this.id = id;
            this.title = title;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
