package com.lom.lotsomobsrender;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.lom.lotsomobscore.LotsOMobs;
import com.lom.lotsomobsentity.EntityEasterBunny;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEasterBunny extends RenderLiving
{
    public RenderEasterBunny(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderEasterBunny(EntityEasterBunny par1EntityEasterBunny, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityEasterBunny, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderEasterBunny((EntityEasterBunny)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderEasterBunny((EntityEasterBunny)par1Entity, par2, par4, par6, par8, par9);
    }
	private static final ResourceLocation EasterBunnytexture = new ResourceLocation(LotsOMobs.modid, "LotsOMobs/Mobs/EasterBunny.png");
	 protected ResourceLocation func_110872_a(EntityEasterBunny par1Entity)
	    {
	        return EasterBunnytexture;
	    }
	 @Override
	    protected ResourceLocation getEntityTexture(Entity par1Entity)
	    {
	        return this.func_110872_a((EntityEasterBunny)par1Entity);
	    }

}
