package net.favouriteless.enchanted.api;

public record Vec2i(int x, int y) {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vec2i v && v.x == x && v.y == y;
    }
}
