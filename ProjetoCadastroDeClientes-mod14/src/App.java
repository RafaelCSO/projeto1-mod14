import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class App {
    private static IClienteDAO IClienteDAO;

    public static void main(String args[]) {
        IClienteDAO = new ClienteMapDAO();
        String opcao = JOptionPane.showInputDialog("Digite 1 para cadastrar, 2 consultar, 3 excluir, 4 alterar, 5 sair");

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog("Opção inválida! Digite 1 para cadastrar, 2 consultar, 3 excluir, 4 alterar, 5 sair");
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            }


            if (isOpcaoCadastrar(opcao)) {
                String dados = JOptionPane.showInputDialog("Digite os dados do cliente separados por vírgula sem caracteres especiais conforme o exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado ");
                cadastrar(dados);
            }
            if (isOpcaoConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog("Digite o cpf");
                consultar(dados);
            }
            opcao = JOptionPane.showInputDialog("Digite 1 para cadastrar, 2 consultar, 3 excluir, 4 alterar, 5 sair");
            if (isOpcaoExcluir(opcao)) {
                String dados = JOptionPane.showInputDialog("Digite o cpf do cadastro que deseja excluir");
                excluir(dados);
            }
            if (isOpcaoAlterar(opcao)) {
                String dados = JOptionPane.showInputDialog("Digite os dados do cliente separados por vírgula sem caracteres especiais conforme o exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado ");
                alterar(dados);
            }
        }
    }

    private static void alterar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        IClienteDAO.alterar(cliente);
    }

    private static boolean isOpcaoAlterar(String opcao) {
        if ("4".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void excluir(String dados) {
        IClienteDAO.excluir(Long.parseLong(dados));
        JOptionPane.showInputDialog("Cliente excluído com sucesso");
    }

    private static boolean isOpcaoExcluir(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void consultar(String dados) {
        Cliente cliente = IClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado" + cliente.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
        }
    }

    private static boolean isOpcaoConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Boolean isCadastrado = IClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showInputDialog("Cliente cadastrado com sucesso");
        } else {
            JOptionPane.showInputDialog("Cliente já se encontra cadastrado");
        }
    }

    private static boolean isOpcaoCadastrar(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void sair() {
        JOptionPane.showInputDialog("Até logo");
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

}
