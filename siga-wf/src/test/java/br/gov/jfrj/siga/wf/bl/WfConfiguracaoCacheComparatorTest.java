package br.gov.jfrj.siga.wf.bl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.gov.jfrj.siga.wf.model.WfConfiguracaoCache;

public class WfConfiguracaoCacheComparatorTest {
    WfConfiguracaoCacheComparator comparator;
    WfConfiguracaoCache c1;
    WfConfiguracaoCache c2;

    @Before
    public void setComparator() {
        comparator = new WfConfiguracaoCacheComparator();
        c1 = new WfConfiguracaoCache();
        c2 = new WfConfiguracaoCache();
    }

    @Test
    public void igualASiMesmo() {
        Assert.assertEquals(0, comparator.compare(c1, c1));
    }

    @Test
    public void objetosComMesmosValores() {
        Assert.assertEquals(0, comparator.compare(c1, c2));
    }

    @Test
    public void procedimentosDiferentes1() {
        c1.definicaoDeProcedimento = 0;
        c2.definicaoDeProcedimento = 1;
        Assert.assertEquals(1, comparator.compare(c1, c2));
    }

    @Test
    public void procedimentosDiferentes2() {
        c1.definicaoDeProcedimento = 1;
        c2.definicaoDeProcedimento = 0;
        Assert.assertEquals(-1, comparator.compare(c1, c2));
    }

    @Test
    public void idsConfigIguais() {
        c1.idConfiguracao = 1;
        c2.idConfiguracao = 1;
        Assert.assertEquals(0, comparator.compare(c1, c2));
    }

    @Test
    public void idsConfig1MaiorQue2() {
        c1.idConfiguracao = 2;
        c2.idConfiguracao = 1;
        Assert.assertEquals(1, comparator.compare(c1, c2));
    }

    @Test
    public void idsConfig2MaiorQue1() {
        c1.idConfiguracao = 1;
        c2.idConfiguracao = 2;
        Assert.assertEquals(-1, comparator.compare(c1, c2));
    }
}