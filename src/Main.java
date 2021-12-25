public class Main {

    private static void init(Table table) {
        String names[] = {"Socrates", "Platao", "Aristoteles", "Maquiavel", "Descartes"};
        for (int i = 0; i < 5; i++) {
            new Philosophers(table, i, names[i]).start();
        }
    }

    public static void main(String[] args) {

        Table table = new Table();

        init(table);

    }
}