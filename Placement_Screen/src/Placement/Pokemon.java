package Placement;

public class Pokemon {
    private int pokemonNum;
    private int Lv;
    private int exp;
    private String name;
    private String type;
    private int grade;
    private int health;
    private int power;
    private String ability;

    public Pokemon(int pokemonNum, int Lv, int exp, String name, String type, int grade, int health, int power, String ability) {
        this.pokemonNum = pokemonNum;
        this.Lv = Lv;
        this.exp = exp;
        this.name = name;
        this.type = type;
        this.grade = grade;
        this.health = health;
        this.power = power;
        this.ability = ability;
    }

    public int getPokemonNum() {
        return pokemonNum;
    }

    public void setPokemonNum(int pokemonNum) {
        this.pokemonNum = pokemonNum;
    }

    public int getLv() {
        return Lv;
    }

    public void setLv(int lv) {
        Lv = lv;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }


}
