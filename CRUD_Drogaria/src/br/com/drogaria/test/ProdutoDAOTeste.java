package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	@Test
	//@Ignore
	public void salvar() throws SQLException {
		Produto p = new Produto();
		p.setDescricao("Produto 18");
		p.setPreco(2.45);
		p.setQuantidade(13L);

		Fabricante f = new Fabricante();
		f.setCodigo(42L);

		p.setFabricante(f);

		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);

	}

	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();

		ArrayList<Produto> lista = dao.listar();

		for (Produto p : lista) {
			System.out.println("Codigo: " + p.getCodigo());
			System.out.println("Descricao: " + p.getDescricao());
			System.out.println("Preco: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Fabricante Codigo: "
					+ p.getFabricante().getCodigo());
			System.out.println("Fabricante Descricao: "
					+ p.getFabricante().getDescricao());
			System.out.println("\n");
		}

	}

	@Test
	@Ignore
	public void excluir() throws SQLException {
		Produto p = new Produto();

		p.setCodigo(1L);

		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);

	}

	@Test
	@Ignore
	public void editar() throws SQLException {

		Produto p = new Produto();
		
		p.setCodigo(3L);
		p.setDescricao("to com fome ");
		p.setPreco(50.00D);
		p.setQuantidade(12L);

		Fabricante f = new Fabricante();

		f.setCodigo(31L);

		p.setFabricante(f);

		ProdutoDAO dao = new ProdutoDAO();

		dao.editar(p);

	}

}
