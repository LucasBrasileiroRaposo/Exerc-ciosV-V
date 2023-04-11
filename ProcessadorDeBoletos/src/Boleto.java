import java.util.Date;

public class Boleto {

    private long codigo;
    private Date data;
    private double valorPago;

    public Boleto(long codigo, Date data, double valorPago) {
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
    }

    public long getCodigo() {
        return codigo;
    }

    public Date getData() {
        return data;
    }

    public double getValorPago() {
        return valorPago;
    }

}
