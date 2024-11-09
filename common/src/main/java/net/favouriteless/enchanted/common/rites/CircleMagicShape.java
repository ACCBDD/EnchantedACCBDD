package net.favouriteless.enchanted.common.rites;

import com.mojang.serialization.Codec;
import net.favouriteless.enchanted.api.Vec2i;
import net.favouriteless.enchanted.common.init.EData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class CircleMagicShape {

    public static final Codec<Holder<CircleMagicShape>> HOLDER_CODEC = Codec.lazyInitialized(() -> RegistryFixedCodec.create(EData.CIRCLE_SHAPE_REGISTRY));
    public static final Codec<CircleMagicShape> CODEC = Codec.STRING.listOf(1, Integer.MAX_VALUE).xmap(CircleMagicShape::new, CircleMagicShape::getStrings);

    private final List<String> input;
    private final List<Vec2i> points = new ArrayList<>();

    public CircleMagicShape(List<String> rows) {
        this.input = rows;
        int width = rows.getFirst().length();
        if(rows.size() != width)
            throw new IllegalArgumentException("Circle magic shapes must be square");

        if(width % 2 == 0)
            throw new IllegalArgumentException("Circle magic shapes must have an odd width.");

        for(String row : rows) {
            if(row.length() != width)
                throw new IllegalArgumentException("All rows in a circle magic shape must be the same width.");
        }

        int radius = width / 2;
        boolean foundPoint = false;
        for(int y = 0; y < width; y++) {
            String rowString = rows.get(y);

            if(!foundPoint && !rowString.isBlank())
                foundPoint = true;

            for(int x = 0; x < width; x++) {
                if(rowString.charAt(x) == 'X')
                    points.add(new Vec2i(x-radius, y-radius));
            }
        }

        if(!foundPoint)
            throw new IllegalArgumentException("A circle magic shape cannot have zero points");
    }

    public boolean matches(Level level, BlockPos pos, Block block) {
        for(Vec2i point : points) {
            BlockState state = level.getBlockState(pos.offset(point.x(), 0, point.y()));

            if(!state.is(block))
                return false; // Return false if any of the points doesn't match.
        }
        return true;
    }

    public List<String> getStrings() {
        return input;
    }

}
