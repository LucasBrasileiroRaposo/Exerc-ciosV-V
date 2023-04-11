import java.util.List;

public interface ProcessadorDeBoletos {
    void processa(List<Boleto> boletos, Fatura fatura);
}
