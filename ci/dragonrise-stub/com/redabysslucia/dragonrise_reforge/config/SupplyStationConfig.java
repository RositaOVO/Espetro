package com.redabysslucia.dragonrise_reforge.config;

import java.util.Map;

/**
 * CI-only compile stub. Mirrors the public API of DragonRise Reforge
 * 1.5.0-beta-20260921 that Espetro calls; never shipped (compileOnly).
 */
public class SupplyStationConfig {
    public ResupplyRule defaultRule;
    public Map<String, ResupplyRule> vehicleOverrides;

    public ResupplyRule getRuleForVehicle(String vehicleId) {
        throw new UnsupportedOperationException("stub");
    }

    public String getEffectiveBonusItem(ResupplyRule rule) {
        throw new UnsupportedOperationException("stub");
    }

    public int getEffectiveBonusItemCount(ResupplyRule rule) {
        throw new UnsupportedOperationException("stub");
    }

    public AmmoTypeRule getAmmoRule(ResupplyRule rule, String ammoId) {
        throw new UnsupportedOperationException("stub");
    }

    public static class ResupplyRule {
        public String mode;
        public int fixedAmount;
        public float healPercent;
        public String bonusItem;
        public int bonusItemCount;
        public Map<String, AmmoTypeRule> ammoOverrides;
    }

    public static class AmmoTypeRule {
        public String mode;
        public int fixedAmount;
        public String customItem;
        public int customItemCount;
    }
}
