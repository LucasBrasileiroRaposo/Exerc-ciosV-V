import java.util.Date;

public class Pagamento {

    private double valor;
    private Date data;
    private TipoPagamento tipo;


    public Pagamento(double valor, Date data, TipoPagamento tipo) {
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

}
