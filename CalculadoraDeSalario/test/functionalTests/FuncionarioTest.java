import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioTest {

    private final double  SALARIO_MEDIO_DEV = 3000;
    private final double  SALARIO_MEDIO_DBA = 2000;
    private final double  SALARIO_MEDIO_TESTADOR = 2000;
    private final double  SALARIO_MEDIO_GERENTE = 5000;

    private Funcionario funcionario;
    private CalculadoraSalarioImpl calculadoraSalario = new CalculadoraSalarioImpl();

    @BeforeEach
    public void setUp() {
        funcionario = new Funcionario("Lucas brasileiro", "lucas@gmail.com", SALARIO_MEDIO_DEV, Cargo.DESENVOLVEDOR);
    }
    @Test
    public void calculaSalarioDev(){
        assertEquals(2400,calculadoraSalario.calculaSalario(funcionario));
        assertEquals(SALARIO_MEDIO_DEV*0.8,calculadoraSalario.calculaSalario(funcionario));
        funcionario.setSalarioBase(SALARIO_MEDIO_DEV-1);
        assertEquals(2699.1,calculadoraSalario.calculaSalario(funcionario));
        assertEquals((SALARIO_MEDIO_DEV-1)*0.9,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDBA(){
        funcionario.setCargo(Cargo.DBA);
        funcionario.setSalarioBase(SALARIO_MEDIO_DBA);
        assertEquals(1500,calculadoraSalario.calculaSalario(funcionario));
        assertEquals(SALARIO_MEDIO_DBA*0.75,calculadoraSalario.calculaSalario(funcionario));
        funcionario.setSalarioBase(SALARIO_MEDIO_DBA/2);
        assertEquals(850,calculadoraSalario.calculaSalario(funcionario));
        assertEquals((SALARIO_MEDIO_DBA/2)*0.85,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioTestador(){
        funcionario.setCargo(Cargo.TESTADOR);
        funcionario.setSalarioBase(SALARIO_MEDIO_TESTADOR);
        assertEquals(1500,calculadoraSalario.calculaSalario(funcionario));
        assertEquals(SALARIO_MEDIO_TESTADOR*0.75,calculadoraSalario.calculaSalario(funcionario));
        funcionario.setSalarioBase(SALARIO_MEDIO_TESTADOR/2);
        assertEquals(850,calculadoraSalario.calculaSalario(funcionario));
        assertEquals((SALARIO_MEDIO_TESTADOR/2)*0.85,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioGerente(){
        funcionario.setCargo(Cargo.GERENTE);
        funcionario.setSalarioBase(SALARIO_MEDIO_GERENTE);
        assertEquals(3500,calculadoraSalario.calculaSalario(funcionario));
        assertEquals(SALARIO_MEDIO_GERENTE*0.70,calculadoraSalario.calculaSalario(funcionario));
        funcionario.setSalarioBase(SALARIO_MEDIO_GERENTE-1);
        assertEquals((SALARIO_MEDIO_GERENTE-1)*0.80,calculadoraSalario.calculaSalario(funcionario));
    }

}
