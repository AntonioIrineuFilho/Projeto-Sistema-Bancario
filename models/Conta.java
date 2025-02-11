public class Conta {
    private int id;
    private String numero;
    private double saldo;

    public Conta(int id, String numero, double saldo) {
        this.setId(id);
        this.setNumero(numero);
        this.setSaldo(saldo);
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        } else {
            throw new IlegalArgumentException("INVALID ID");
        }
    }

    public void setNumero(String numero) {
        String vazio = "";
        if (!(numero.equals(vazio))) {
            this.numero = numero;
        } else {
            throw new IlegalArgumentException("INVALID ACCOUNT NUM");
        }
    }

    public void setSaldo(double saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        } else {
            throw new IlegalArgumentException("INVALID ACCOUNT BALANCE");
        }
    }

    public int getId() {
        return this.id;
    }

    public String getNumero() {
        return this.numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String toString() {
        return String.format("Número: %s - Saldo: R$ %.2f", this.getNumero(), this.getSaldo());
    }
}