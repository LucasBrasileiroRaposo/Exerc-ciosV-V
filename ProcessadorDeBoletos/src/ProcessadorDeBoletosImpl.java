import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessadorDeBoletosImpl implements ProcessadorDeBoletos {

    public void processa(List<Boleto> boletos, Fatura fatura) {
        double totalPago = 0;
        List<Pagamento> pagamentos = new ArrayList<Pagamento>();

        for (Boleto boleto : boletos) {
            totalPago += boleto.getValorPago();
            Date dataPagamento = boleto.getData();
            Pagamento pagamento = new Pagamento(boleto.getValorPago(), dataPagamento, TipoPagamento.BOLETO);
            pagamentos.add(pagamento);
        }

        if (totalPago >= fatura.getValorTotal()) {
            fatura.setPago(true);
        }

        fatura.setPagamentos(pagamentos);
    }

}
