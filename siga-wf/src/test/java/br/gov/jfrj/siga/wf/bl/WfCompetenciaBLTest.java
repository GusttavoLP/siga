package br.gov.jfrj.siga.wf.bl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.gov.jfrj.siga.cp.CpConfiguracaoCache;
import br.gov.jfrj.siga.cp.bl.CpCompetenciaBL;
import br.gov.jfrj.siga.cp.bl.CpConfiguracaoBL;
import br.gov.jfrj.siga.cp.model.enm.CpSituacaoDeConfiguracaoEnum;
import br.gov.jfrj.siga.cp.model.enm.ITipoDeConfiguracao;
import br.gov.jfrj.siga.dp.CpOrgaoUsuario;
import br.gov.jfrj.siga.dp.DpCargo;
import br.gov.jfrj.siga.dp.DpFuncaoConfianca;
import br.gov.jfrj.siga.dp.DpLotacao;
import br.gov.jfrj.siga.dp.DpPessoa;
import br.gov.jfrj.siga.wf.model.WfConfiguracaoCache;
import br.gov.jfrj.siga.wf.model.WfDefinicaoDeProcedimento;
import br.gov.jfrj.siga.wf.model.enm.WfAcessoDeEdicao;
import br.gov.jfrj.siga.wf.model.enm.WfTipoDeConfiguracao;
import br.gov.jfrj.siga.cp.CpConfiguracao;

public class WfCompetenciaBLTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void podeInstanciarProcedimentoPorConfiguracaoTRUE() {
		//Configurando PESSOA
		DpCargo cargo = new DpCargo();
		cargo.setNomeCargo("CARGO-TESTE");
		cargo.setIdCargo(1l);
		DpFuncaoConfianca funcaoConf = new DpFuncaoConfianca();
		
		DpPessoa titular = new DpPessoa();
		titular.setCargo(new DpCargo());
		titular.setFuncaoConfianca(funcaoConf);
		
		//Configurando LOTACAO TITULAR
		DpLotacao lotaTitular = new DpLotacao();
		CpOrgaoUsuario orgaoUsuario = new CpOrgaoUsuario();
		lotaTitular.setOrgaoUsuario(orgaoUsuario);
		
		WfDefinicaoDeProcedimento definicaoDeProcedimento = new WfDefinicaoDeProcedimento();
		definicaoDeProcedimento.setAcessoDeEdicao(WfAcessoDeEdicao.ACESSO_PUBLICO);
		WfCompetenciaBL wfCompetenciaBL = new WfCompetenciaBL();
		
		Boolean result = wfCompetenciaBL.podeInstanciarProcedimentoPorConfiguracao(titular, lotaTitular, definicaoDeProcedimento);
		
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void podeInstanciarProcedimentoPorConfiguracaoFALSE() {
		//Configurando PESSOA
		DpCargo cargo = new DpCargo();
		cargo.setNomeCargo("CARGO-TESTE");
		cargo.setIdCargo(1l);
		DpFuncaoConfianca funcaoConf = new DpFuncaoConfianca();
		
		DpPessoa titular = new DpPessoa();
		titular.setCargo(new DpCargo());
		titular.setFuncaoConfianca(funcaoConf);
		
		//Configurando LOTACAO TITULAR
		DpLotacao lotaTitular = new DpLotacao();
		CpOrgaoUsuario orgaoUsuario = new CpOrgaoUsuario();
		lotaTitular.setOrgaoUsuario(orgaoUsuario);
		
		WfDefinicaoDeProcedimento definicaoDeProcedimento = new WfDefinicaoDeProcedimento();
		definicaoDeProcedimento.setAcessoDeEdicao(WfAcessoDeEdicao.ACESSO_PESSOAL);
		WfCompetenciaBL wfCompetenciaBL = new WfCompetenciaBL();
		
		Boolean result = wfCompetenciaBL.podeInstanciarProcedimentoPorConfiguracao(titular, lotaTitular, definicaoDeProcedimento);
		
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void podeInstanciarProcedimentoPorConfiguracaoNULOS() {
		//Configurando PESSOA
		DpPessoa titular = null;
		
		//Configurando LOTACAO TITULAR
		DpLotacao lotaTitular = null;
		
		WfDefinicaoDeProcedimento definicaoDeProcedimento = new WfDefinicaoDeProcedimento();
		definicaoDeProcedimento.setAcessoDeEdicao(WfAcessoDeEdicao.ACESSO_PESSOAL);
		WfCompetenciaBL wfCompetenciaBL = new WfCompetenciaBL();
		
		Boolean result = wfCompetenciaBL.podeInstanciarProcedimentoPorConfiguracao(titular, lotaTitular, definicaoDeProcedimento);
		
		Assert.assertEquals(false, result);
	}
	
	
	@Test
	public void podeEditarProcedimentoPorConfiguracaoTRUE() {
		//Configurando PESSOA
		DpCargo cargo = new DpCargo();
		cargo.setNomeCargo("CARGO-TESTE");
		cargo.setIdCargo(1l);
		DpFuncaoConfianca funcaoConf = new DpFuncaoConfianca();
		
		DpPessoa titular = new DpPessoa();
		titular.setCargo(new DpCargo());
		titular.setFuncaoConfianca(funcaoConf);
		
		//Configurando LOTACAO TITULAR
		DpLotacao lotaTitular = new DpLotacao();
		CpOrgaoUsuario orgaoUsuario = new CpOrgaoUsuario();
		lotaTitular.setOrgaoUsuario(orgaoUsuario);
		
		WfDefinicaoDeProcedimento definicaoDeProcedimento = new WfDefinicaoDeProcedimento();
		definicaoDeProcedimento.setAcessoDeEdicao(WfAcessoDeEdicao.ACESSO_PUBLICO);
		WfCompetenciaBL wfCompetenciaBL = new WfCompetenciaBL();
		
		Boolean result = wfCompetenciaBL.podeEditarProcedimentoPorConfiguracao(titular, lotaTitular, definicaoDeProcedimento);
		
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void podeEditarProcedimentoPorConfiguracaoFALSE() {
		//Configurando PESSOA
		DpCargo cargo = new DpCargo();
		cargo.setNomeCargo("CARGO-TESTE");
		cargo.setIdCargo(1l);
		DpFuncaoConfianca funcaoConf = new DpFuncaoConfianca();
		
		DpPessoa titular = new DpPessoa();
		titular.setCargo(new DpCargo());
		titular.setFuncaoConfianca(funcaoConf);
		
		//Configurando LOTACAO TITULAR
		DpLotacao lotaTitular = new DpLotacao();
		CpOrgaoUsuario orgaoUsuario = new CpOrgaoUsuario();
		lotaTitular.setOrgaoUsuario(orgaoUsuario);
		
		WfDefinicaoDeProcedimento definicaoDeProcedimento = new WfDefinicaoDeProcedimento();
		definicaoDeProcedimento.setAcessoDeEdicao(WfAcessoDeEdicao.ACESSO_PESSOAL);
		WfCompetenciaBL wfCompetenciaBL = new WfCompetenciaBL();
		
		Boolean result = wfCompetenciaBL.podeEditarProcedimentoPorConfiguracao(titular, lotaTitular, definicaoDeProcedimento);
		
		Assert.assertEquals(true, result);
	}
	
	
//	@Test(expected = Exception.class)
//	public void testaCompetencia1() {
//		// Configurando PESSOA
//		DpCargo cargo = new DpCargo();
//		cargo.setNomeCargo("CARGO-TESTE");
//		cargo.setIdCargo(1l);
//		DpFuncaoConfianca funcaoConf = new DpFuncaoConfianca();
//
//		DpPessoa titular = new DpPessoa();
//		titular.setCargo(new DpCargo());
//		titular.setFuncaoConfianca(funcaoConf);
//
//		// Configurando LOTACAO TITULAR
//		DpLotacao lotaTitular = new DpLotacao();
//		CpOrgaoUsuario orgaoUsuario = new CpOrgaoUsuario();
//		lotaTitular.setOrgaoUsuario(orgaoUsuario);
//		
//		WfCompetenciaBL wfCompetenciaBL = new WfCompetenciaBL();
//		
//		thrown.expect(Exception.class);
//		try {
//			wfCompetenciaBL.testaCompetencia("SegundoFatorPin", titular, lotaTitular);
//		}catch(Exception e) {
//			
//		}
//	}
}