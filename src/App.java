import characters.*;
import characters.Characters.Speed;

public class App {
    public static void main(String[] args) throws Exception {
        Tour pika = new Tour("pikachu", 5, Characters.Type.ELECTIK, Characters.Type.DRAGON);

        System.out.println(pika);
        pika.setSpeed(Speed.FAST);
        System.out.println(pika);

    }
}
