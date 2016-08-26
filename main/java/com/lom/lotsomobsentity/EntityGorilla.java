package com.lom.lotsomobsentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import com.lom.lotsomobsinit.LotsOMobsItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGorilla extends EntityAgeableMob
{
    /**
     * Ticker used to determine the time remaining for this zombie to convert into a villager when cured.
     */
    private int conversionTime = 0;
	private float moveSpeed;

    public EntityGorilla(World par1World)
    {
        super(par1World);
        this.moveSpeed = .2F;
        this.setSize(1.2F, 1.3F);
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed + 0.2F, false));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityDeer.class, this.moveSpeed, true));
       // this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityEmpirosaurus.class, this.moveSpeed, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, this.moveSpeed));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, moveSpeed));
        this.tasks.addTask(2, new EntityAIMate(this, moveSpeed));
        this.tasks.addTask(3, new EntityAITempt(this, moveSpeed, Items.wheat, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, moveSpeed));
        this.tasks.addTask(0, new EntityAIWander(this, moveSpeed));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, moveSpeed));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        //this.tasks.addTask(3, new EntityAITempt(this, 0.25F, Item.wheat.itemID, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDeer.class, 0, false));
      //  this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEmpirosaurus.class, 0, false));
        this.experienceValue = 100;
    }
    /**
     * This method returns a value to be applied directly to entity speed, this factor is less than 1 when a slowdown
     * potion effect is applied, more than 1 when a haste potion effect is applied and 2 for fleeing entities.
     */



@SideOnly(Side.CLIENT)

protected void applyEntityAttributes()
{
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    //this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
}


    /**
     * Returns true if the newer Entity AI code should be run
     */
    protected boolean isAIEnabled() 
    {
        return true;
    }
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild())
        {
            float var1 = this.getBrightness(100.0F);

            if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
            {
                boolean var2 = true;
                if (var2)
                {
                    this.setFire(-99);
                }
            }
        }

        super.onLivingUpdate();
    }


     /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        ItemStack var2 = this.getHeldItem();
        int var3 = 5;

 

        return var3;
    }
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "lom:gorilla";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "lom:gorilla";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "lom:gorilla";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("lom:trexfootstep", 0.15F, 1.0F);
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
        int var4;
            
        
        this.dropItem(Items.leather, var3);
        

        var3 = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

        for (var4 = 0; var4 < var3; ++var4)
        {
            if (this.isBurning())
            {
                this.dropItem(Items.cooked_beef, 1);
            }
            else
            {
                this.dropItem(Items.beef, 1);
            }
        }
    }
    /**
     * How large the spider should be scaled.
     */
    public float spiderScaleAmount()
    {
return 1F;
    }
    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntityGorilla spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
        return new EntityGorilla(this.worldObj);
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }
    
    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    
    @Override
    protected boolean isValidLightLevel()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32))
        {
            return true;
        }
        else
        {
//            int l = this.worldObj.getBlockLightValue(i, j, k);
//
//            if (this.worldObj.isThundering())
//            {
//                int i1 = this.worldObj.skylightSubtracted;
//                this.worldObj.skylightSubtracted = 10;
//                l = this.worldObj.getBlockLightValue(i, j, k);
//                this.worldObj.skylightSubtracted = i1;
//            }
//
//        	System.out.println("RETURN TRUE");
//            return l <= this.rand.nextInt(8);
        	return true;
        }
    }
    
    @Override
    public float getBlockPathWeight(int par1, int par2, int par3)
    {
//    	System.out.println(0.5F - this.worldObj.getLightBrightness(par1, par2, par3));
//        return 0.5F - this.worldObj.getLightBrightness(par1, par2, par3);
        return this.worldObj.getLightBrightness(par1, par2, par3);
    }

}
