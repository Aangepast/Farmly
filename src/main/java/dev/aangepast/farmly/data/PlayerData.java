package dev.aangepast.farmly.data;

public class PlayerData {
    private double cash;
    private double xp;
    private int level;
    private int skillPoints;
    private double skillXp;
    private String group;
    private int groupId;
    private int farmId;
    private int levelFarming;
    private int levelForgaging;

    private int levelCrafting;
    private int levelLifestock;
    private int xpFarming;
    private int xpCrafting;
    private int xpForgaging;
    private int xpLifestock;
    private boolean hasAccessTradeMarket;

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void levelUp(int OldLevel) {
        this.level = OldLevel++;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public double getSkillXp() {
        return skillXp;
    }

    public void setSkillXp(double skillXp) {
        this.skillXp = skillXp;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public int getLevelFarming() {
        return levelFarming;
    }

    public void setLevelFarming(int levelFarming) {
        this.levelFarming = levelFarming;
    }

    public int getLevelForgaging() {
        return levelForgaging;
    }

    public void setLevelForgaging(int levelForgaging) {
        this.levelForgaging = levelForgaging;
    }

    public int getLevelCrafting() {
        return levelCrafting;
    }

    public void setLevelCrafting(int levelCrafting) {
        this.levelCrafting = levelCrafting;
    }

    public int getLevelLifestock() {
        return levelLifestock;
    }

    public void setLevelLifestock(int levelLifestock) {
        this.levelLifestock = levelLifestock;
    }

    public boolean isHasAccessTradeMarket() {
        return hasAccessTradeMarket;
    }

    public void setHasAccessTradeMarket(boolean hasAccessTradeMarket) {
        this.hasAccessTradeMarket = hasAccessTradeMarket;
    }

    public int getXpFarming() {
        return xpFarming;
    }

    public void setXpFarming(int xpFarming) {
        this.xpFarming = xpFarming;
    }

    public int getXpCrafting() {
        return xpCrafting;
    }

    public void setXpCrafting(int xpCrafting) {
        this.xpCrafting = xpCrafting;
    }

    public int getXpForgaging() {
        return xpForgaging;
    }

    public void setXpForgaging(int xpForgaging) {
        this.xpForgaging = xpForgaging;
    }

    public int getXpLifestock() {
        return xpLifestock;
    }

    public void setXpLifestock(int xpLifestock) {
        this.xpLifestock = xpLifestock;
    }
}
