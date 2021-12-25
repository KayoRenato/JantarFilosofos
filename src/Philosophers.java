public class Philosophers extends Thread {

    private int id;
    private String name;
    private Table table;

    public Philosophers(Table table, int id, String name) {
        this.table = table;
        this.id = id;
        this.name = name;
    }

    public void run() {
        while (true) {
            this.think();
            this.tryEat();
        }
    }

    private void think() {
        System.out.println("O Filosofo " + name + " esta pensando.\n");
        try {
            sleep((this.id + 1) * 1000);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private synchronized void tryEat() {
        System.out.println("O Filosofo " + name + " esta tentando comer.");
        if (table.getTwo(this.id)) {
            table.getRightFork(this.id, this.name);
            table.getLeftFork(this.id, this.name);
            eat();
            downFork();
        } else {
            System.out.println("O Filosofo " + name + " nao conseguiu comer.");
        }
    }

    private void eat() {
        System.out.println("O Filosofo " + name + " esta comendo.\n");
        try {
            sleep((this.id + 1) * 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downFork() {

        table.downFork(this.id, this.name);
    }


}