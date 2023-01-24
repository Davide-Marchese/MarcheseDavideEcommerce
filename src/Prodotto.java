import java.util.concurrent.locks.*;

public class Prodotto {
    int quantità;
    String nome;
    Lock l = new ReentrantLock();

    public Prodotto(String nome, int quantità) {
        this.nome = nome;
        this.quantità = quantità;
    }

    public int getQuantità() {
        l.lock();
        try {
            return quantità;
        } finally {
            l.unlock();
        }
    }

    public void acquista(int acquistati) {
        l.lock();
        quantità -= acquistati;
        l.unlock();
    }

    public String getNome() {
        return nome;
    }
}
