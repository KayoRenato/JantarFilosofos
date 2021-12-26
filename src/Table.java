public class Table {

    private boolean[] fork;
    String names[] = {"Socrates", "Platao", "Aristoteles", "Maquiavel", "Descartes"};

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

    public synchronized boolean getTwo(int id) {
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

        if (!statusLeftFork(id)) {
            System.out.println("O garfo esquerdo esta com " + this.names[idLeftName] + ".");
            return false;
        } else if (!statusRightFork(id)) {
            System.out.println("O garfo direito esta com " + this.names[idRightName] + ".");
            return false;
        }

        return true;
    }

    public synchronized boolean getRightFork(int id, String name) {
        if (this.fork[id] == false) {
            System.err.println("O garfo direita esta em uso.");
            return false;
        } else {
            this.fork[id] = false;
            System.out.println("O Philosophers " + name + " pegou o garfo direita.");
            return true;
        }

    }

    public synchronized boolean getLeftFork(int id, String name) {
        int id_ = id;

        if (id == 4) {
            id_ = 0;
        } else {
            id_ += 1;
        }

        if (this.fork[id_] == false) {
            System.err.println("O garfo esquerdo esta em uso.");
            return false;
        } else {
            this.fork[id_] = false;
            System.out.println("O Philosophers " + name + " pegou o garfo esquerda.");
            return true;
        }
    }

    public synchronized void downFork(int id, String nome) {

        downLeftFork(id);
        downRightFork(id);

        System.out.println("O Philosophers " + nome + " devolveu os garfos.");

    }


}