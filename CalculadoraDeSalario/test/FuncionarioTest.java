import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioTest {

    private final double  SALARIO_MEDIO_DEV = 3000;
    private final double  SALARIO_MEDIO_DBA = 2000;
    private final double  SALARIO_MEDIO_TESTADOR = 2000;
    private final double  SALARIO_MEDIO_GERENTE = 5000;

    private Funcionario funcionario;
    @BeforeAll
    public void setUp() {
        Funcionario funcionarioDev = new Funcionario("Lucas brasileiro", "lucas@gmail.com", SALARIO_MEDIO_DEV, DESENVOLVEDOR);
        Funcionario funcionarioDBA = new Funcionario("Holliver costa", "holliver@gmail.com", SALARIO_MEDIO_DBA, DBA);
        Funcionario funcionarioTestador = new Funcionario("Italo silva", "italo@gmail.com",SALARIO_MEDIO_TESTADOR , TESTADOR);
        Funcionario funcionarioGerente = new Funcionario("Felipe santos", "felipe@gmail.com",SALARIO_MEDIO_GERENTE , GERENTE);
    }


}
