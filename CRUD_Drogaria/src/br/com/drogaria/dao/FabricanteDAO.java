package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante objF) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, objF.getDescricao());

		comando.executeUpdate();

	}

	public void excluir(Fabricante objF) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from fabricante ");
		sql.append("WHERE codigo=?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, objF.getCodigo());

		comando.executeUpdate();

	}

	public void editar(Fabricante objF) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, objF.getDescricao());
		comando.setLong(2, objF.getCodigo());

		comando.executeUpdate();
	}

	public Fabricante buscarPorCodigo(Fabricante objF) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, objF.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;

		if (resultado.next()) {
			retorno = new Fabricante();

			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));

		}

		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();

			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}

		return lista;
	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante item = new Fabricante();

			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}

		return lista;
	}

	public static void main(String[] args) {
		/*
		 * Fabricante f1 = new Fabricante(); Fabricante f2 = new Fabricante();
		 * 
		 * f1.setDescricao("descricao1"); f2.setDescricao("descricao1");
		 * 
		 * FabricanteDAO fDao = new FabricanteDAO();
		 * 
		 * try { fDao.salvar(f2); fDao.salvar(f1);
		 * System.out.println("Sucesso"); } catch (SQLException e) { // TODO
		 * Auto-generated catch block System.out.println("Erro");
		 * e.printStackTrace();
		 * 
		 * }
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(2L);
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setCodigo(3L);
		 * 
		 * FabricanteDAO fDao = new FabricanteDAO();
		 * 
		 * try { fDao.excluir(f2); fDao.excluir(f1);
		 * System.out.println("Deletados"); } catch (SQLException e) { // TODO
		 * Auto-generated catch block System.out.println("Erro ao deletar");
		 * e.printStackTrace();
		 * 
		 * }
		 * 
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(4L);
		 * f1.setDescricao("novaDescricao");
		 * 
		 * FabricanteDAO fDao = new FabricanteDAO();
		 * 
		 * try { fDao.editar(f1); System.out.println("Alterado"); } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * System.out.println("Erro ao alterar"); e.printStackTrace();
		 * 
		 * }
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(4L);
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setCodigo(1L);
		 * 
		 * FabricanteDAO fDao = new FabricanteDAO();
		 * 
		 * try { Fabricante f3 = fDao.buscarPorCodigo(f1); Fabricante f4 =
		 * fDao.buscarPorCodigo(f2); System.out.println("Resultado:" + f3);
		 * System.out.println("Resultado:" + f4); } catch (SQLException e) {
		 * System.out.println("Erro na busca!"); e.printStackTrace(); }
		 * 
		 * 
		 * FabricanteDAO fDao = new FabricanteDAO();
		 * 
		 * try { ArrayList<Fabricante> arrFab = fDao.listar();
		 * 
		 * for (Fabricante f : arrFab) { System.out.println("Listado: " + f); }
		 * 
		 * } catch (SQLException e) { System.out.println("Erro ao listar");
		 * e.printStackTrace(); }
		 */

		Fabricante f1 = new Fabricante();
		f1.setDescricao("nova");

		FabricanteDAO fdao = new FabricanteDAO();

		try {
			ArrayList<Fabricante> lista = fdao.buscarPorDescricao(f1);
			for (Fabricante f : lista) {
				System.out.println("Bus_Descri: " + f);
			}
		} catch (SQLException ex) {
			System.out.println("erro ao listar busca");
			ex.printStackTrace();
		}

	}

}
