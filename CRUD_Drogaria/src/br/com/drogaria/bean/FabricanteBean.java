package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	// bean é variavel de tela, domain é de banco de dados
	private Fabricante fabricante;
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	// postconstruct e quando a pagina for carregada, quando nao tem botao
	@PostConstruct
	public void prepararPesquisa() {
		FabricanteDAO fdao = new FabricanteDAO();

		try {
			ArrayList<Fabricante> lista = fdao.listar();
			itens = fdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			// System.out.println("erro lista bean");
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	public void novo() {
		try {
			FabricanteDAO fdao = new FabricanteDAO();
			fdao.salvar(fabricante);
			
			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fabricante salvo com sucesso!");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}


	public void excluir() {

		try {
			FabricanteDAO fdao = new FabricanteDAO();
			fdao.excluir(fabricante);

			itens = fdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Fabricante excluido com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
		
	public void editar(){
		try{
		FabricanteDAO fdao = new FabricanteDAO();
		fdao.editar(fabricante);
		
		itens = fdao.listar();
		
		JSFUtil.adicionarMensagemSucesso("Fabricante alterado com sucesso!");
		}catch (SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
}
