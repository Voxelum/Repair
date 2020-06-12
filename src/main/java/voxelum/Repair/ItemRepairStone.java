package voxelum.Repair;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemRepairStone extends Item {
    public ItemRepairStone() {
        this.maxStackSize = 1;
        this.setMaxDamage(800);
        this.setCreativeTab(CreativeTabs.TOOLS);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!player.capabilities.isCreativeMode) {
            ItemStack itemStack = player.getHeldItem(hand);
            if (!world.isRemote) {
                world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ANVIL_PLACE,
                        SoundCategory.PLAYERS, 1.0f, 1.0f);
                Iterable<ItemStack> armorList = player.getArmorInventoryList();
                for (ItemStack i : armorList) {
                    int damage = i.getItemDamage();
                    int toBeFixed = Math.min(damage, itemStack.getMaxDamage() - itemStack.getItemDamage());

                    if (toBeFixed > 0) {
                        itemStack.setItemDamage(itemStack.getItemDamage() + toBeFixed);
                        i.setItemDamage(damage - toBeFixed);
                    }
                    if(itemStack.getItemDamage()>=itemStack.getMaxDamage()){
                    world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ANVIL_BREAK,
                                SoundCategory.PLAYERS, 1.0f, 1.0f);
                    }
                }
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
        }
        return super.onItemRightClick(world, player, hand);
    }

}
