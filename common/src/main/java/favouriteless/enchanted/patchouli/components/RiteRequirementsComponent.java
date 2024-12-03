package favouriteless.enchanted.patchouli.components;

import com.google.gson.annotations.SerializedName;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import favouriteless.enchanted.common.Enchanted;
import favouriteless.enchanted.common.circle_magic.CircleMagicShape;
import favouriteless.enchanted.common.circle_magic.RiteType;
import favouriteless.enchanted.common.init.EData;
import favouriteless.enchanted.common.init.registry.EnchantedBlocks;
import favouriteless.enchanted.util.Vector2i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import vazkii.patchouli.api.IComponentRenderContext;
import vazkii.patchouli.api.ICustomComponent;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.client.book.gui.BookTextRenderer;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.client.book.gui.GuiBookEntry;

import java.util.*;
import java.util.function.UnaryOperator;

public class RiteRequirementsComponent implements ICustomComponent {

    public static final String PATH = "textures/patchouli/";
    public static final String FILE_END = ".png";
    public static final int IMAGE_SIZE = 110;
    public static final int TEXTURE_SIZE = 128;
    public static final float IMAGE_OFFSET = IMAGE_SIZE/2F;
    public static final HashMap<Block, String> BLOCK_IMAGES = new HashMap<>();

    static {
        BLOCK_IMAGES.put(EnchantedBlocks.RITUAL_CHALK.get(), PATH + "white");
        BLOCK_IMAGES.put(EnchantedBlocks.NETHER_CHALK.get(), PATH + "red");
        BLOCK_IMAGES.put(EnchantedBlocks.OTHERWHERE_CHALK.get(), PATH + "purple");
    }

    public int startRadius;
    public float scale;
    public int itemsFirstCircle;
    @SerializedName("rite") public IVariable riteName;

    private transient BookTextRenderer powerTextRenderer;
    private transient RiteType rite;
    private transient final List<ItemRing> itemRings = new ArrayList<>();
    private transient int x;
    private transient int y;

    @Override
    public void build(int componentX, int componentY, int pageNum) {
        this.x = componentX;
        this.y = componentY;

        if (rite != null) {
            this.itemRings.clear();
            List<ItemStack> items = rite.getItems();

            int index = 0;
            int currentItems = itemsFirstCircle;
            int currentRadius = startRadius;

            while(true) {
                List<ItemStack> subList = items.subList(index, Math.min(items.size(), index + currentItems));
                itemRings.add(new ItemRing(subList, x, y, currentRadius, scale));

                index += currentItems;
                if(index >= items.size()) break;
                currentItems *= 2;
                currentRadius += 17;
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, IComponentRenderContext context, float partialticks, int mouseX, int mouseY) {
        if(rite != null && context.getGui() instanceof GuiBook gui) {
            renderCircles(guiGraphics);

            for (ItemRing ring : itemRings) {
                ring.render(guiGraphics, gui, mouseX, mouseY);
            }

            powerTextRenderer.render(guiGraphics, mouseX, mouseY);
        }
    }

    @Override
    public void onDisplayed(IComponentRenderContext context) {
        Screen gui = context.getGui();
        if(gui instanceof GuiBookEntry entry) {
            this.powerTextRenderer = new BookTextRenderer(entry, Component.literal(rite.getPower() + " Power Required"), 2, 140, 116, 9, 0x000000);
        }
    }

    private void renderCircles(GuiGraphics guiGraphics) {
        PoseStack poseStack1 = guiGraphics.pose();
        poseStack1.pushPose();
        poseStack1.translate(x-IMAGE_OFFSET, y-IMAGE_OFFSET, 0);
        RenderSystem.enableBlend();
        guiGraphics.blit(Enchanted.id("textures/gui/jei/gold_glyph.png"), 0, 0, 0, 0, IMAGE_SIZE, IMAGE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
        poseStack1.popPose();
        for(Map.Entry<ResourceKey<CircleMagicShape>, Block> entry : rite.getShapes().entrySet()) {
            CircleMagicShape shapeKey = Minecraft.getInstance().level.registryAccess().registryOrThrow(EData.CIRCLE_SHAPE_REGISTRY).get(entry.getKey());
            if(shapeKey == null)
                return;

            ResourceLocation location = entry.getKey().location();

            ResourceLocation texture = ResourceLocation.tryBuild(
                    location.getNamespace(),
                    String.format("textures/gui/circle_magic_shapes/%s_%s.png", location.getPath(), BuiltInRegistries.BLOCK.getKey(entry.getValue()).getPath())
            );

            if(Minecraft.getInstance().getResourceManager().getResource(texture).isPresent()) {
                PoseStack poseStack = guiGraphics.pose();
                poseStack.pushPose();
                poseStack.translate(x-IMAGE_OFFSET, y-IMAGE_OFFSET, 0);
                RenderSystem.enableBlend();
                guiGraphics.blit(texture, 0, 0, 0, 0, IMAGE_SIZE, IMAGE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
                poseStack.popPose();
            }
        }
    }

    @Override
    public void onVariablesAvailable(UnaryOperator<IVariable> lookup) {
        Level level = Minecraft.getInstance().level;

        Optional<Registry<RiteType>> optional = level.registryAccess().registry(EData.RITE_TYPES_REGISTRY);
        this.rite = optional.isPresent() ? optional.get().get(new ResourceLocation(lookup.apply(riteName).asString())) : null;

        if(rite == null)
            throw new IllegalStateException();
    }

    private static class ItemRing {

        private final List<ItemStack> items;
        private final float scale;
        private final Vector2i[] scaledPositions;
        private final Vector2i[] absPositions;
        private final int itemSize;

        private ItemRing(List<ItemStack> items, int x, int y, float radius, float scale) {
            this.items = items;
            this.itemSize = Math.round(16*scale);
            this.scaledPositions = new Vector2i[items.size()];
            this.absPositions = new Vector2i[items.size()];
            this.scale = scale;
            recalculatePositions(x, y, radius);
        }

        private void recalculatePositions(int x, int y, float radius) {
            double xLocal = 0;
            double yLocal = -radius/scale;

            double angle = Math.toRadians(360D/items.size());
            double sin = Math.sin(angle);
            double cos = Math.cos(angle);

            for(int i = 0; i < scaledPositions.length; i++) {
                scaledPositions[i] = new Vector2i(
                        (int)Math.round((xLocal + x/scale) - 8),
                        (int)Math.round((yLocal + y/scale) - 8));
                absPositions[i] = new Vector2i(
                        (int)Math.round(xLocal*scale + x - (8*scale)),
                        (int)Math.round(yLocal*scale + y - (8*scale)));

                Vec3 newLocalPos = new Vec3(
                        cos * xLocal - sin * yLocal,
                        sin * xLocal + cos * yLocal, 0);
                xLocal = newLocalPos.x;
                yLocal = newLocalPos.y;
            }
        }

        private void render(GuiGraphics graphics, GuiBook gui, int mouseX, int mouseY) {
            PoseStack poseStack = graphics.pose();
            poseStack.pushPose();
            poseStack.scale(scale, scale, scale);
            for(int i = 0; i < items.size(); i++) {
                ItemStack item = items.get(i);
                Vector2i scaledPos = scaledPositions[i];
                Vector2i absPos = absPositions[i];

                Minecraft mc = Minecraft.getInstance();
                graphics.renderItem(item, scaledPos.x, scaledPos.y);
                graphics.renderItemDecorations(mc.font, item, scaledPos.x, scaledPos.y);

                if(gui.isMouseInRelativeRange(mouseX, mouseY, absPos.x, absPos.y, itemSize, itemSize)) {
                    gui.setTooltipStack(item);
                }
            }
            poseStack.popPose();
        }
    }

}
