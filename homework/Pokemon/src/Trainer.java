public class Trainer {
    public Pokemon pokemon;
    public String name;

    public Trainer(String name){
        this.name = name;
    }

    public void Train(Pokemon pokemon){
        this.pokemon = pokemon;
    }

    public boolean HasPokemon(){
        return (pokemon != null);
    }

    public void Bump(Trainer trainer){
        trainer.GetHit(pokemon);
    }

    public void GetHit(Pokemon pokemon){
        pokemon.Hit(this.pokemon);
    }
}
