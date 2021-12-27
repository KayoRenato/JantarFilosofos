public class Philosophers extends Thread {

    private final int id;
    private final String name;
    private final Table table;

    public Philosophers(Table table, int id, String name) {
        this.table = table;
        this.id = id;
        this.name = name;
    }

    public void run() {
        think();
        while (true) {
            try {
                this.tryEat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void think() {
        System.out.println("O Filosofo " + name + " esta pensando.\n");

    }

    private synchronized void tryEat() throws InterruptedException {
        System.out.println("O Filosofo " + name + " esta tentando comer.");

        if (table.statusForks(this.id)) {
            table.getForks(this.id, this.name);
            eat();
            downFork();
        } else {
            this.think();
            table.waitFork();
        }

    }

    private void eat() {
        System.out.println("O Filosofo " + name + " esta comendo.\n");
        try {
            sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downFork() {
        table.downFork(this.id, this.name);
    }

}