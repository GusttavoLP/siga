package br.gov.jfrj.siga.wf.bl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.gov.jfrj.siga.cp.CpConfiguracaoCache;
import br.gov.jfrj.siga.cp.bl.CpCompetenciaBL;
import br.gov.jfrj.siga.cp.bl.CpConfiguracaoBL;
import br.gov.jfrj.siga.cp.model.enm.CpSituacaoDeConfiguracaoEnum;
import br.gov.jfrj.siga.cp.model.enm.ITipoDeConfiguracao;
import br.gov.jfrj.siga.dp.DpLotacao;
import br.gov.jfrj.siga.dp.DpPessoa;
import br.gov.jfrj.siga.wf.model.WfConfiguracaoCache;
import br.gov.jfrj.siga.wf.model.WfDefinicaoDeProcedimento;
import br.gov.jfrj.siga.wf.model.enm.WfAcessoDeEdicao;
import br.gov.jfrj.siga.wf.model.enm.WfTipoDeConfiguracao;
import br.gov.jfrj.siga.cp.CpConfiguracao;

public class WfCompetenciaBLTest {

	@Test
	public void test_returns_true_if_configuration_exists_and_situation_is_pode() {
		DpPessoa titular = new DpPessoa();
		DpLotacao lotaTitular = new DpLotacao();
		WfDefinicaoDeProcedimento definicaoDeProcedimento = new WfDefinicaoDeProcedimento();
		definicaoDeProcedimento.setAcessoDeEdicao(WfAcessoDeEdicao.ACESSO_PUBLICO);

		WfCompetenciaBL wfCompetenciaBL = new WfCompetenciaBL();

		boolean result = wfCompetenciaBL.podeInstanciarProcedimentoPorConfiguracao(titular, lotaTitular, definicaoDeProcedimento);

		Assert.assertTrue(result);
	}

	@Test
	public void test_correctly_handles_null_values_for_titular_parameter() {
		DpPessoa titular = null;
		DpLotacao lotaTitular = new DpLotacao();
		WfDefinicaoDeProcedimento definicaoDeProcedimento = new WfDefinicaoDeProcedimento();
		definicaoDeProcedimento.setAcessoDeEdicao(WfAcessoDeEdicao.ACESSO_PUBLICO);

		WfCompetenciaBL wfCompetenciaBL = new WfCompetenciaBL();

		boolean result = wfCompetenciaBL.podeInstanciarProcedimentoPorConfiguracao(titular, lotaTitular, definicaoDeProcedimento);

		Assert.assertFalse(result);
	}
}