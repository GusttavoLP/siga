package br.gov.jfrj.siga.wf.bl;

import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import br.gov.jfrj.siga.base.Par;
import br.gov.jfrj.siga.cp.model.enm.CpMarcadorEnum;
import br.gov.jfrj.siga.dp.CpMarcador;
import br.gov.jfrj.siga.dp.DpLotacao;
import br.gov.jfrj.siga.dp.DpPessoa;
import br.gov.jfrj.siga.wf.model.WfMarca;
import br.gov.jfrj.siga.wf.model.WfProcedimento;

public class WfMarcaBL {
	private WfMarcaBL() {

	} 

	public static void atualizarMarcas(WfProcedimento pi) {
		SortedSet<WfMarca> setA = new TreeSet<>();
		setA.addAll(pi.getMarcas());
		SortedSet<WfMarca> setB = calcularMarcadores(pi);
		Set<WfMarca> marcasAIncluir = new TreeSet<>();
		Set<WfMarca> marcasAExcluir = new TreeSet<>();
		Set<Par<WfMarca, WfMarca>> atualizar = new TreeSet<>();
		encaixar(setA, setB, marcasAIncluir, marcasAExcluir, atualizar);

		for (WfMarca i : marcasAIncluir) {
			i.save();
			pi.getMarcas().add(i);
		}
		for (WfMarca e : marcasAExcluir) {
			pi.getMarcas().remove(e);
			e.delete();
		}
		for (Entry<WfMarca, WfMarca> pair : atualizar) {
			WfMarca a = pair.getKey();
			WfMarca b = pair.getValue();
			a.setDpLotacaoIni(b.getDpLotacaoIni());
			a.setDpPessoaIni(b.getDpPessoaIni());
			a.setDtFimMarca(b.getDtFimMarca());
			a.setDtIniMarca(b.getDtIniMarca());
			a.setProcedimento(b.getProcedimento());
			a.save();
		}
	}

	private static SortedSet<WfMarca> calcularMarcadores(WfProcedimento pi) {
		SortedSet<WfMarca> set = new TreeSet<>();

		if (pi.isPausado())
			acrescentarMarca(pi, set, CpMarcadorEnum.EM_ANDAMENTO.getId(), pi.getEventoData(), null, pi.getEventoPessoa(),
					pi.getEventoLotacao());

		return set;
	}

	private static boolean precisaIncluir(WfMarca a, WfMarca b) {
		return (a == null) || (b != null && a.compareTo(b) > 0);
	}

	private static boolean precisaExcluir(WfMarca a, WfMarca b) {
		return b == null || b.compareTo(a) > 0;
	}

	private static void encaixar(SortedSet<WfMarca> setA, SortedSet<WfMarca> setB, Set<WfMarca> incluir,
			Set<WfMarca> excluir, Set<Par<WfMarca, WfMarca>> atualizar) {
		Iterator<WfMarca> ia = setA.iterator();
		Iterator<WfMarca> ib = setB.iterator();

		WfMarca a = null;
		WfMarca b = null;
		
		try {
			a = ia.next();
			b = ib.next();
			while (a != null || b != null) {
				if (precisaIncluir(a, b)) {
					// Existe em setB, mas nao existe em setA
					incluir.add(b);
					b = ib.next();

				} else if (precisaExcluir(a, b)) {
					// Existe em setA, mas nao existe em setB
					excluir.add(a);
					a = ia.next();
				} else {

					// O registro existe nos dois
					atualizar.add(new Par<>(a, b));
					b = ib.next();
					a = ia.next();
				}
			}
		} catch (NoSuchElementException e) {
			// fim da operação
		}
	}

	private static void acrescentarMarca(WfProcedimento pi, SortedSet<WfMarca> set, Long idMarcador, Date dtIni,
			Date dtFim, DpPessoa pess, DpLotacao lota) {
		WfMarca mar = new WfMarca();
		// Edson: nao eh necessario ser this.solicitacaoInicial em vez de this
		// porque este metodo soh eh chamado por atualizarMarcas(), que ja se
		// certifica de chamar este metodo apenas para a solicitacao inicial
		mar.setProcedimento(pi);
		mar.setCpMarcador(CpMarcador.AR.findById(idMarcador));
		if (pess != null)
			mar.setDpPessoaIni(pess.getPessoaInicial());
		if (lota != null)
			mar.setDpLotacaoIni(lota.getLotacaoInicial());
		mar.setDtIniMarca(dtIni);
		mar.setDtFimMarca(dtFim);
		set.add(mar);
	}
}
