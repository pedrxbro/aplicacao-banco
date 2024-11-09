package br.univali.cc.poo.ex1.dominio;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    private String nome;
    private int numero;
    private Map<Integer, ContaCorrente> contas;


    public Banco(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
        this.contas = new HashMap<>();
    }

    public void criarConta(double saldoInicial, int numero) throws MesmoNumContasException {
        if (contas.containsKey(numero)) {
            throw new MesmoNumContasException();
        }
        ContaCorrente conta = new ContaCorrente(numero, saldoInicial);
        this.contas.put(numero, conta);
    }

    public void criarConta(double saldoInicial, int numero, double limite) throws MesmoNumContasException {
        if (contas.containsKey(numero)) {
            throw new MesmoNumContasException();
        }
        ContaCorrente conta = new ContaCorrente(numero, saldoInicial, limite);
        this.contas.put(numero, conta);
    }

    public void depositar(int numeroConta, double valor) throws ContaInexistenteException, ValorNegativoException {
        ContaCorrente conta = this.localizarConta(numeroConta);
        if (valor < 0) {
            throw new ValorNegativoException();
        }
        conta.depositar(valor);
    }

    public void transferir(int numeroContaOrigem, int numeroContaDestino, double valor)
            throws ContaInexistenteException, SaldoInsuficienteException, ValorNegativoException {

        if (valor < 0) {
            throw new ValorNegativoException();
        }

        ContaCorrente contaOrigem = this.localizarConta(numeroContaOrigem);
        ContaCorrente contaDestino = this.localizarConta(numeroContaDestino);

        if (contaOrigem.getSaldo() < valor) {
            throw new SaldoInsuficienteException();
        }

        if (contaOrigem.sacar(valor)) {
            contaDestino.depositar(valor);
        }
    }


    public void sacar(int numeroConta, double valor) throws ContaInexistenteException, ValorNegativoException, SaldoInsuficienteException {
        ContaCorrente conta = this.localizarConta(numeroConta);
        if (valor < 0) {
            throw new ValorNegativoException();
        }

        else if ((conta.getSaldo() + conta.getLimite()) < valor) {
            throw new SaldoInsuficienteException();
        }
        conta.sacar(valor);
    }


    private ContaCorrente localizarConta(int numeroConta) throws ContaInexistenteException {
        ContaCorrente conta = contas.get(numeroConta);
        if (conta == null) {
            throw new ContaInexistenteException();
        }
        return conta;
    }

    public String emitirExtrato(int numeroConta) throws ContaInexistenteException{
        ContaCorrente conta = this.localizarConta(numeroConta);
        return conta.emitirExtrato();
    }
}
