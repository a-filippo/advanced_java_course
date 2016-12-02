import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    public static Random Random;
    private static final String QUIT = "quit";
    private static Trainer t1;
    private static Trainer t2;

    public static void main(String[] args) {
        Random = new Random(System.currentTimeMillis());

        Print("You have 2 trainers: t1 and t2");
        Print("Each trainer you must add one of some pokemons: bulbasaur, ekans, weedle by command for example below");
        Print("setpokemon t1 bulbasaur");


        t1 = new Trainer("t1");
        t2 = new Trainer("t2");


        StartCommands();
    }

    private static void StartCommands(){
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                Print(Command(sequence.toLowerCase()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Print(String string){
        System.out.println(string);
    }


    public static String Command(String command){
        String[] line = command.split("\\s+");
        switch (line[0]){
            case "setpokemon":
                return (line.length == 3) ? CommandSetPokemon(line[1], line[2]) : "check your syntax";
            case "attack":
                return (line.length == 3) ? CommandAttack(line[1], line[2]) : "check your syntax";
            default:
                return "check your syntax";
        }
    }

    private static String CommandSetPokemon(String trainer, String pokemon){
        Trainer t;
        switch (trainer){
            case "t1":
                t = t1;
                break;
            case "t2":
                t = t2;
                break;
            default:
                return "You can use only t1 and t2 trainers";
        }
        switch (pokemon){
            case "bulbasaur":
                t.Train(new Bulbasaur());
                break;
            case "ekans":
                t.Train(new Ekans());
                break;
            case "weedle":
                t.Train(new Weedle());
                break;
            default:
                return "You can select pokemons only from this list: bulbasaur, ekans, weedle";
        }
        return "Success";
    }

    private static String CommandAttack(String trainer1, String trainer2){
        Trainer _t1;
        Trainer _t2;
        if (trainer1.equals("t1") && trainer2.equals("t2")){
            _t1 = t1;
            _t2 = t2;
        } else if (trainer1.equals("t2") && trainer2.equals("t1")){
            _t1 = t2;
            _t2 = t1;
        } else {
            return "You can use only t1 and t2 trainers";
        }

        if (!_t1.HasPokemon()){
            return _t1.name + " not has pokemon";
        }
        if (!_t2.HasPokemon()){
            return _t2.name + " not has pokemon";
        }

        int i = 0;
        String message;
        float damage;
        while (_t1.pokemon.IsAlive() && _t2.pokemon.IsAlive()){
            if (i % 2 == 0){
                _t1.Bump(_t2);
                damage = _t2.pokemon.GetLastDamage();
                message = _t2.pokemon.name + " (" + _t2.name + ") got damage on " + damage + " from " + _t1.pokemon.name + " (" + _t1.name + ") ";
            } else {
                _t2.Bump(_t1);
                damage = _t1.pokemon.GetLastDamage();
                message = _t1.pokemon.name + " (" + _t1.name + ") got damage on " + damage + " from " + _t2.pokemon.name + " (" + _t2.name + ") ";
            }
            Print(message);
            i++;
        }

        return _t1.pokemon.name + " (" + _t1.name + ") won!";
    }
}
