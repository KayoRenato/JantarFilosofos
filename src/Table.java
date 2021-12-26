public class Table {

    String[] names = {"Socrates", "Platao", "Aristoteles", "Maquiavel", "Descartes"};
    private final boolean[] fork;

    public Table() {
        this.fork = new boolean[5];

        for (int i = 0; i < 5; i++) {
            fork[i] = true;
        }
    }

    private boolean statusRightFork(int id) {
        return this.fork[id];
    }

    private boolean statusLeftFork(int id) {
        if (id == 4) {
            return this.fork[0];
        }
        return this.fork[id + 1];
    }

    private void downRightFork(int id) {
        this.fork[id] = true;
    }

    private void downLeftFork(int id) {

        if (id == 4) {
            this.fork[0] = true;
        } else {
            this.fork[id + 1] = true;
        }

    }

    public boolean statusForks(int id) {

        int idRightName, idLeftName;

        if (id == 0) {
            idRightName = 4;
        } else {
            idRightName = id - 1;
        }

        if (id == 4) {
            idLeftName = 0;
        } else {
            idLeftName = id + 1;
        }

        if (!statusLeftFork(id) || !statusRightFork(id)) {
            if (!statusLeftFork(id)) {
                System.out.println("O garfo esquerdo esta com " + this.names[idLeftName] + ".");
            }

            if (!statusRightFork(id)) {
                System.out.println("O garfo direito esta com " + this.names[idRightName] + ".");
            }
            System.out.println("O Filosofo nao conseguiu comer.");
            return false;
        }

        return true;
    }

    public void getRightFork(int id, String name) {
        if (!this.fork[id]) {
            System.err.println("O garfo direita esta em uso.");

        } else {
            this.fork[id] = false;
            System.out.println("O Filosofo " + name + " pegou o garfo direita.");
        }

    }

    public void getLeftFork(int id, String name) {
        int id_ = id;

        if (id == 4) {
            id_ = 0;
        } else {
            id_ += 1;
        }

        if (!this.fork[id_]) {
            System.err.println("O garfo esquerdo esta em uso.");

        } else {
            this.fork[id_] = false;
            System.out.println("O Philosophers " + name + " pegou o garfo esquerda.");
        }
    }

    public void getForks(int id, String name)  {
        this.getRightFork(id, name);
        this.getLeftFork(id, name);
    }

    public synchronized void downFork(int id, String nome) {

        downLeftFork(id);
        downRightFork(id);
        System.out.println("O Filosofo " + nome + " devolveu os garfos.");
        notifyAll();
    }

    public synchronized void waitFork() throws InterruptedException {
        wait();
    }
}