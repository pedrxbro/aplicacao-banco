import br.univali.cc.poo.ex1.dominio.Banco;
import br.univali.cc.poo.ex1.visao.CaixaEletronico;

public class Principal {

    public static void main(String[] args) {
        Banco bb = new Banco("Banco do Brasil", 1);
        CaixaEletronico caixa = new CaixaEletronico(bb);
        caixa.menu();
    }
    
}
