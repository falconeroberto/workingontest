package com.roberto.bear.idsc0.command;

import com.roberto.bear.idsc0.dto.AnagraficaClienteDTO;
import com.roberto.bear.idsc0.model.AnagraficaCliente;
import com.roberto.bear.idsc0.service.AnagraficaClienteService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AnagraficaClienteCommandTest {

    @InjectMocks
    private AnagraficaClienteCommand anagraficaClienteCommand;

    @Mock
    private AnagraficaClienteDTO anagraficaClienteDTO;

    @Mock
    private AnagraficaClienteService anagraficaClienteService;
    

    @Test
    public void test_unaAnagraficaCliente_assertDatiAnagrafici() throws Exception {
        anagraficaClienteDTO.setNominativoCliente("ROSSI");

        List<AnagraficaCliente.Cliente> clienti = new ArrayList<>();

        AnagraficaCliente.Cliente cliente1 = new AnagraficaCliente.Cliente();
        cliente1.setIdCliente("12345");
        cliente1.setNominativoCliente("ROSSI MARIO");
        clienti.add(cliente1);

        AnagraficaCliente.Cliente cliente2 = new AnagraficaCliente.Cliente();
        cliente2.setIdCliente("67890");
        cliente2.setNominativoCliente("ROSSI MARCO");
        clienti.add(cliente2);

        AnagraficaCliente response = new AnagraficaCliente();
        response.setClienti(clienti);

        when(anagraficaClienteService.ricercaAnagraficaCliente(anagraficaClienteDTO)).thenReturn(response);

        AnagraficaCliente responseFromCommand = anagraficaClienteCommand.doExecute();

        assertEquals( "ROSSI MARIO",responseFromCommand.getClienti().get(0).getNominativoCliente());
        assertEquals( "ROSSI MARCO",responseFromCommand.getClienti().get(1).getNominativoCliente());
    }
}
