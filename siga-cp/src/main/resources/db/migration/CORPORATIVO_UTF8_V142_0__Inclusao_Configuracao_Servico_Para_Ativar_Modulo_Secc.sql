-- CADASTRO DO SERVICO DO MÓDULO SECC
INSERT INTO CORPORATIVO.CP_SERVICO (ID_SERVICO,SIGLA_SERVICO,DESC_SERVICO,ID_SERVICO_PAI,ID_TP_SERVICO)
SELECT CORPORATIVO.CP_SERVICO_SEQ.NEXTVAL, 'SIGA-SECC','Módulo serviço de compra e contratação', (SELECT ID_SERVICO FROM CORPORATIVO.CP_SERVICO WHERE SIGLA_SERVICO = 'SIGA'),'2'
FROM DUAL
WHERE NOT EXISTS (SELECT ID_SERVICO FROM CORPORATIVO.CP_SERVICO WHERE SIGLA_SERVICO = 'SIGA-SECC');

-- PERMISSAO DEFAULT NAO PODE AO SERVICO DO MÓDULO SECC
INSERT INTO CORPORATIVO.CP_CONFIGURACAO (ID_CONFIGURACAO,HIS_DT_INI,ID_SIT_CONFIGURACAO,ID_TP_CONFIGURACAO,ID_SERVICO,HIS_IDC_INI) 
    VALUES (CORPORATIVO.CP_CONFIGURACAO_SEQ.NEXTVAL, SYSDATE, '2', '200', (SELECT ID_SERVICO FROM CORPORATIVO.CP_SERVICO WHERE SIGLA_SERVICO = 'SIGA-SECC'), '1');