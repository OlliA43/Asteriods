package dk.sdu.cbse.EnemySystem;

import dk.sdu.cbse.common.data.Entity;

public class Enemy extends Entity {

    private double attackSpeed = 5.0;
    private float attackCooldown = 2.0f;
    private long lastShotTime = 0;

    public Enemy() {

    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public float getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(float attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public long getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }
}
