import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioValoresLimitesTest {

    // SALARIOS E TAXAS CARGO DESENVOLVEDOR
    private final double SALARIO_DEV_LIMITE = 2999;
    private final double SALARIO_DEV_MENOR_LIMITE = 1000;
    private final double SALARIO_DEV_MAIOR_LIMITE = 3000;
    private final double SALARIO_DEV_MAIOR_LIMITE_COM_SOBRA = 3001;
    private final double TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE = 0.8;
    private final double TAXA_SALARIO_DEV_MENOR_QUE_LIMITE = 0.9;

    // SALARIOS E TAXAS CARGO DBA E TESTADOR
    private final double SALARIO_DBA_TESTADOR_LIMITE = 1999;
    private final double SALARIO_DBA_TESTADOR_MENOR_LIMITE = 1200;
    private final double SALARIO_DBA_TESTADOR_MAIOR_LIMITE = 2000;
    private final double SALARIO_DBA_TESTADOR_MAIOR_LIMITE_COM_SOBRA = 2001;
    private final double TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE = 0.75;
    private final double TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE = 0.85;

    // SALARIOS E TAXAS CARGO GERENTE
    private final double SALARIO_GERENTE_LIMITE = 4999;
    private final double SALARIO_GERENTE_MENOR_LIMITE = 2500;
    private final double SALARIO_GERENTE_MAIOR_LIMITE = 5000;
    private final double SALARIO_GERENTE_MAIOR_LIMITE_COM_SOBRA = 5001;
    private final double TAXA_SALARIO_GERENTE_MAIOR_QUE_LIMITE = 0.7;
    private final double TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE = 0.8;

    private Funcionario funcionario;
    private CalculadoraSalarioImpl calculadoraSalario = new CalculadoraSalarioImpl();

    @BeforeEach
    public void setUp() {
        funcionario = new Funcionario("Lucas brasileiro", "lucas@gmail.com", SALARIO_DEV_LIMITE, Cargo.DESENVOLVEDOR);
    }

    // TESTES PARA CARGO DESENVOLVEDOR
    @Test
    public void calculaSalarioDevTaxaMinValorMax(){
        Assertions.assertEquals(2699.1,calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_DEV_LIMITE * TAXA_SALARIO_DEV_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDevTaxaMinValorRandom(){
        funcionario.setSalarioBase(SALARIO_DEV_MENOR_LIMITE);
        Assertions.assertEquals(900,calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_DEV_MENOR_LIMITE * TAXA_SALARIO_DEV_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDevTaxaMaiorValorMin(){
        funcionario.setSalarioBase(SALARIO_DEV_MAIOR_LIMITE);
        Assertions.assertEquals(2400,calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_DEV_MAIOR_LIMITE * TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDevTaxaMaiorValorMaiorQueLimite(){
        funcionario.setSalarioBase(SALARIO_DEV_MAIOR_LIMITE_COM_SOBRA);
        Assertions.assertEquals(2400.8,calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_DEV_MAIOR_LIMITE_COM_SOBRA * TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO DBA
    @Test
    public void calculaSalarioDBATaxaMinValorMax(){
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_LIMITE);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_LIMITE * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1699.15,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDBATaxaMinValorRandom(){
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MENOR_LIMITE);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MENOR_LIMITE * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1020,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDBATaxaMaiorValorMin(){
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MAIOR_LIMITE);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MAIOR_LIMITE * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1500,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDBATaxaMaiorValorMaiorQueLimite(){
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MAIOR_LIMITE_COM_SOBRA);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MAIOR_LIMITE_COM_SOBRA * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1500.75,calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO TESTADOR
    @Test
    public void calculaSalarioTestadorTaxaMinValorMax(){
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_LIMITE);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_LIMITE * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1699.15,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioTestadorTaxaMinValorRandom(){
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MENOR_LIMITE);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MENOR_LIMITE * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1020,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioTestadorTaxaMaiorValorMin(){
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MAIOR_LIMITE);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MAIOR_LIMITE * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1500,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioTestadorTaxaMaiorValorMaiorQueLimite(){
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MAIOR_LIMITE_COM_SOBRA);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MAIOR_LIMITE_COM_SOBRA * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1500.75,calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO GERENTE
    @Test
    public void calculaSalarioGerenteTaxaMinValorMax(){
        funcionario.setSalarioBase(SALARIO_GERENTE_LIMITE);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(SALARIO_GERENTE_LIMITE * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(3999.2,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioGerenteTaxaMinValorRandom(){
        funcionario.setSalarioBase(SALARIO_GERENTE_MENOR_LIMITE);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(SALARIO_GERENTE_MENOR_LIMITE * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(2000,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioGerenteTaxaMaiorValorMin(){
        funcionario.setSalarioBase(SALARIO_GERENTE_MAIOR_LIMITE);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(SALARIO_GERENTE_MAIOR_LIMITE * TAXA_SALARIO_GERENTE_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(3500,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioGerenteTaxaMaiorValorMaiorQueLimite(){
        funcionario.setSalarioBase(SALARIO_GERENTE_MAIOR_LIMITE_COM_SOBRA);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(SALARIO_GERENTE_MAIOR_LIMITE_COM_SOBRA * TAXA_SALARIO_GERENTE_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(3500.7,calculadoraSalario.calculaSalario(funcionario));
    }

}
