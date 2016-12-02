
public abstract class Pokemon {
    protected float DEFAULT_HEALTH;
    protected float DAMAGE;

    public String name;
    protected float health;
    protected int experience = 0;

    protected float lastDamage = 0f;

    public Pokemon(){

    }


    public boolean IsAlive(){
        return health > 0;
    }

    public float GetLastDamage(){
        return lastDamage;
    }

    public void Doctor(){
        FullHealth();
    }

    public void Hit(Pokemon pokemon){
        float damage = DAMAGE * (0.8f + Main.Random.nextFloat() * 0.4f);

        pokemon.GetDamage((int)damage);
    }

    public void GetDamage(int damage){
        if (health > damage){
            health -= damage;
        } else {
            health = 0;
        }

        lastDamage = damage;
    }

    protected void FullHealth(){
        health = DEFAULT_HEALTH;
    }

}
