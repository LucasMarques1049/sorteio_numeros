package bean;

import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.JogoDao;
import entidades.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;
	private Integer maiorValor;
	private int maiorSorteado;
	private String verificacaoValorSorteado;

	public String salvar() {
		Random rand = new Random();
		int valorAleatorio = rand.nextInt(10) + 1;
		jogo.setNumeroSorteado(valorAleatorio);
		JogoDao.salvar(jogo);
		jogo = new Jogo();
		String msg = "Jogo salvo com sucesso ";
		FacesMessage mensage = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext aviso = FacesContext.getCurrentInstance();
		aviso.addMessage(null, mensage);
		return null;
	}

	public Integer maiorValorSorteado() {
		maiorSorteado = JogoDao.maiorNumeroSorteado();
		String msg = "Maior numero sorteado: " + maiorSorteado;
		FacesMessage mensage = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext aviso = FacesContext.getCurrentInstance();
		aviso.addMessage(null, mensage);
		System.out.println(maiorSorteado);
		return maiorSorteado;
	}

	public String atualizar(Jogo jogo) {
		JogoDao.atualizar(jogo);
		return null;
	}

	public String excluir(int id) {
		JogoDao.excluir(id);
		lista = JogoDao.listar();
		String msg = "Id do jogo excluido: " + id;
		FacesMessage mensage = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext aviso = FacesContext.getCurrentInstance();
		aviso.addMessage(null, mensage);
		System.out.println("excluir sucesso " + id);
		return null;
	}

	public Integer valorMaximoLinha(Jogo jogo) {
		maiorValor = JogoDao.maiorNumero(jogo);
		String msg = "maior valor: " + maiorValor;
		FacesMessage mensage = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext aviso = FacesContext.getCurrentInstance();
		aviso.addMessage(null, mensage);
		System.out.println(maiorValor);
		return maiorValor;
	}

	public void verificandoValorSorteado(Jogo jogo, int valorSorteado) {
		int numeroSorteado = jogo.getNumeroSorteado();
		if (valorSorteado == jogo.getV1() || valorSorteado == jogo.getV5() || valorSorteado == jogo.getV3()
				|| valorSorteado == jogo.getV4() || valorSorteado == jogo.getV5()) {
			verificacaoValorSorteado = "SIM";
			String msg = "Numero "+numeroSorteado+" esta entre os valores digitado ";
			FacesMessage mensageSim = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext aviso = FacesContext.getCurrentInstance();
			aviso.addMessage(null, mensageSim);
			System.out.println("sim");
		} else {
			verificacaoValorSorteado = "NAO";
			String msg = "Numero "+numeroSorteado+" não esta entre os valores digitado";
			FacesMessage mensageNao = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext aviso = FacesContext.getCurrentInstance();
			aviso.addMessage(null, mensageNao);
			System.out.println("nao");
		}
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getLista() {
		if (lista == null) {
			lista = JogoDao.listar();
		}
		return lista;
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}

	public Integer getMaiorValor() {
		return maiorValor;
	}

	public void setMaiorValor(Integer maiorValor) {
		this.maiorValor = maiorValor;
	}

	public int getMaiorSorteado() {
		return maiorSorteado;
	}

	public void setMaiorSorteado(int maiorSorteado) {
		this.maiorSorteado = maiorSorteado;
	}

	public String getVerificacaoValorSorteado() {
		return verificacaoValorSorteado;
	}

	public void setVerificacaoValorSorteado(String verificacaoValorSorteado) {
		this.verificacaoValorSorteado = verificacaoValorSorteado;
	}

}
