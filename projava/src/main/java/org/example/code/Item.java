package org.example.code;

public record Item(String name) {
    public Item() {
        this("未設定");
    }
}