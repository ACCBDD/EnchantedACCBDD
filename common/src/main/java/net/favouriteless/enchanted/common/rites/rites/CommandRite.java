package net.favouriteless.enchanted.common.rites.rites;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CommandRite extends Rite implements CommandSource {

    private final List<List<String>> commands;
    private final int delay;

    private int current = 1;

    public CommandRite(BaseRiteParams params, List<List<String>> commands, int delay) {
        super(params);
        this.commands = commands;
        this.delay = delay;
    }

    @Override
    protected boolean onStart(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                              @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        CommandSourceStack sourceStack = new CommandSourceStack(this, Vec3.atCenterOf(getPos()),
                new Vec2(0, 0), level, 2, "Command Rite",
                Component.literal("Command Rite"), level.getServer(), null);

        if(delay == 0)
            commands.forEach(list -> list.forEach(command -> level.getServer().getCommands().performPrefixedCommand(sourceStack,
                    replacedVars(command, caster, target))));
        else {
            commands.getFirst().forEach(c -> level.getServer().getCommands().performPrefixedCommand(sourceStack,
                    replacedVars(c, caster, target)));
        }
        return delay > 0 && commands.size() > 1;
    }

    @Override
    protected boolean onTick(ServerLevel level, BlockPos pos, @Nullable ServerPlayer caster,
                             @Nullable ServerPlayer target, List<ItemStack> consumedItems) {
        if(++ticks % delay == 0) {
            CommandSourceStack sourceStack = new CommandSourceStack(this, Vec3.atCenterOf(getPos()),
                    new Vec2(0, 0), level, 2, "Command Rite",
                    Component.literal("Command Rite"), level.getServer(), null);

            commands.get(current++).forEach(c -> level.getServer().getCommands().performPrefixedCommand(sourceStack,
                    replacedVars(c, caster, target)));
        }
        return current < commands.size();
    }

    protected String replacedVars(String command, @Nullable ServerPlayer caster, @Nullable ServerPlayer target) {
        return command
                .replaceAll("@caster", caster != null ? caster.getName().getString() : "@caster")
                .replaceAll("@target", target != null ? target.getName().getString() : "@target");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, ServerLevel level) {
        tag.putInt("current", current);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, ServerLevel level) {
        current = tag.getInt("current");
    }

    @Override
    public void sendSystemMessage(Component component) {

    }

    @Override
    public boolean acceptsSuccess() {
        return false;
    }

    @Override
    public boolean acceptsFailure() {
        return false;
    }

    @Override
    public boolean shouldInformAdmins() {
        return false;
    }

}
