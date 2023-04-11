import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessadorDeBoletosTest {
    private Fatura fatura, fatura2;
    private Boleto boleto, boleto2, boleto3;
    private List<?> boletosValidos, boletosInsuficientes;

    @BeforeAll
    public void setUp() {
        boletosValidos, boletosInsuficientes = new ArrayList<?>();
        boleto = new Boleto(1, new Date(), 100.0);
        boleto2 = new Boleto(2, new Date(), 400.0);
        boleto3 = new Boleto(3, new Date(), 1500.0);
        boletosValidos.addAll(boleto,boleto2,boleto3);
        fatura = new Fatura(new Date(), 2000, "Holliver");
        boletosInsuficientes.addAll(boleto2,boleto);
        fatura2 = new Fatura(new Date(), 1000, "Holliver");
    }
    @Test
    public void testProcessaFaturaPaga() {
        ProcessadorBoletos.processa(boletosValidos, fatura2);

        assertTrue(fatura.isPago());
        assertEquals(3, fatura.getPagamentos().size());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(0).getTipo());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(1).getTipo());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(2).getTipo());
    }

    @Test
    public void testProcessaFaturaNaoPaga() {
        ProcessadorBoletos.processa(boletosInsuficientes, fatura);

        assertFalse(fatura.isPago());
        assertEquals(2, fatura.getPagamentos().size());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(0).getTipo());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(1).getTipo());
    }


}
