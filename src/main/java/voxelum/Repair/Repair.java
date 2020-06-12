package voxelum.Repair;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderTippedArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = Repair.MODID, name = Repair.NAME, version = Repair.VERSION)
public class Repair {
    public static final String MODID = "repair";
    public static final String NAME = "Repair Mod";
    public static final String VERSION = "1.0";
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(proxy);
        logger = event.getModLog();
    }

    @SidedProxy
    public static CommonProxy proxy;

    public static final String REPAIR_NAME = "repair_stone";
    public static final Item REPAIR_STONE_ITEM = new ItemRepairStone().setRegistryName(REPAIR_NAME)
            .setTranslationKey(REPAIR_NAME);

    public static class CommonProxy {
        @SubscribeEvent
        public void registerEntity(RegistryEvent.Register<EntityEntry> entityEntryRegistryEvent) {
            // entityEntryRegistryEvent.getRegistry().register(EntityEntryBuilder.create()
            // .name(GRAVITATIONAL_ARROW_NAME)
            // .entity(EntityGravitationalPullArrow.class)
            // .id(GRAVITATIONAL_ARROW_NAME, 0)
            // .tracker(150, 5, true)
            // .build());

        }

        @SubscribeEvent
        public void registerItem(RegistryEvent.Register<Item> itemRegister) {
            logger.log(Level.INFO, "Loaded");
            itemRegister.getRegistry().register(REPAIR_STONE_ITEM);

        }
    }

    public static class ClientProxy extends CommonProxy {
        @SubscribeEvent
        public void regEntityModel(ModelRegistryEvent registryEvent) {
            // RenderingRegistry.registerEntityRenderingHandler(EntityGravitationalPullArrow.class,
            // RenderTippedArrow::new);

            // ModelLoader.setCustomModelResourceLocation(SHOOTING_STAR_ARROW_ITEM, 0, new
            // ModelResourceLocation(SHOOTING_STAR_ARROW_ITEM.getRegistryName(),
            // "inventory"));

        }
    }
}
