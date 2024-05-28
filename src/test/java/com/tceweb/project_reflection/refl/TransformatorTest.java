package com.tceweb.project_reflection.refl;

import com.tceweb.project_reflection.entities.Endereco;
import com.tceweb.project_reflection.entities.Pessoa;
import com.tceweb.project_reflection.entities.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "João", "1234");
    Endereco endereco = new Endereco("10", 20);

    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

       Transformator transformator = new Transformator();
       PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO);
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());
    }

    @Test
    public void shouldNotTransform(){

       Assertions.assertThrows(ClassNotFoundException.class, () -> {
           Transformator transformator = new Transformator();
           transformator.transform(endereco);
       });


    }

    @Test
    public void shouldTransformWhenSomeFieldIsNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pessoa pessoaSemCPF = new Pessoa("João");
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTOSemCPF = transformator.transform(pessoaSemCPF);

        Assertions.assertEquals(pessoa.getNome(), pessoaDTOSemCPF.getNome());
        Assertions.assertNull(pessoaDTOSemCPF.getCpf());
    }

}
