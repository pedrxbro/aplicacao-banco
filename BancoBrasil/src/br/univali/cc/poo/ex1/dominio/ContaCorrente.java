package br.univali.cc.poo.ex1.dominio;

public class ContaCorrente {
    private boolean especial;
    private double limite;
    private int numero;
    private double saldo;
    private Movimentacao[] movimentacoes;

    public ContaCorrente(int numero, double saldoInicial, double limite) {
        this.especial = true;
        this.limite = limite;
        this.numero = numero;
        this.saldo = saldoInicial;
        this.criarMovimentacao("Depósito inicial", 'C', saldoInicial);
    }
    
    public ContaCorrente(int numero, double saldoInicial) {
        this.especial = false;
        this.limite = 0;
        this.numero = numero;
        this.saldo = saldoInicial;
        this.criarMovimentacao("Depósito inicial", 'C', saldoInicial);
    }    

    protected int getNumeroConta() {
        return this.numero;
    }
    
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            this.criarMovimentacao("Deposito", 'C', valor);
            return true;
        }
        return false;
    }
    
    public boolean sacar(double valor) throws SaldoInsuficienteException{
        double saldoDisponivel = this.especial ? this.saldo+this.limite: this.saldo;
        if (valor > 0 && saldoDisponivel >= valor){
            this.saldo -= valor;
            this.criarMovimentacao("Saque", 'D', valor);
            return true;
        }
        throw new SaldoInsuficienteException();
    }
    
    private void criarMovimentacao(String descricao, char tipo, double valor) {
        if (movimentacoes == null) {
            this.movimentacoes = new Movimentacao[999];
        }
        Movimentacao movimentacao = new Movimentacao(descricao, tipo, valor);
        for(int x=0; x <movimentacoes.length; x++) {
            if (movimentacoes[x] == null) {
                movimentacoes[x] = movimentacao;
                break;
            }
        }
    }
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public String emitirExtrato() {
        String extrato = "Conta nº "+this.numero;
        for (Movimentacao movimentacao:movimentacoes) {
            if (movimentacao != null) {
                extrato += "\n "+movimentacao.getMovimentacao();
            }
        }
        extrato += "\n "+"Saldo final: "+this.getSaldo();
        return extrato;
    }
    
}
