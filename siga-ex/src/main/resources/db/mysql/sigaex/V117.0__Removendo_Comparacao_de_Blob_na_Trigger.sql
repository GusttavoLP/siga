-- Trigger não pode comparar blobs, mas sim id_arq

DROP TRIGGER IF EXISTS `siga`.`EX_DOCUMENTO_BLOCK_UPD`;

DELIMITER $$
USE `siga`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `EX_DOCUMENTO_BLOCK_UPD` BEFORE UPDATE ON `ex_documento` FOR EACH ROW BEGIN

    DECLARE QTD_DOC_VAR  BIGINT;
	DECLARE PID_ORGAO_USU BIGINT;
	DECLARE PID_FORMA_DOC BIGINT;
	DECLARE PANO_EMISSAO INT;
	DECLARE v_Return DOUBLE;
	
	SET QTD_DOC_VAR = 0;
	if OLD.dt_finalizacao is not null and OLD.dt_finalizacao <> NEW.dt_finalizacao THEN
		   signal sqlstate value '20101' set message_text = 'Não é permitido alterar uma DATA de Finalização já existente';
	end if; 
	if OLD.dt_finalizacao is not null and OLD.fg_eletronico = 'N' then
		if  NEW.ID_ARQ <> OLD.ID_ARQ then
			signal sqlstate value '20101' set message_text = 'Não é permitido alterar: não eletrônico com data de fechamento e com conteúdo.';
		end if;
		if OLD.ID_DOC <> NEW.ID_DOC OR
		  OLD.NUM_EXPEDIENTE <> NEW.NUM_EXPEDIENTE OR
		  OLD.ANO_EMISSAO <> NEW.ANO_EMISSAO OR
		  OLD.ID_TP_DOC <> NEW.ID_TP_DOC OR
		  OLD.ID_CADASTRANTE <> NEW.ID_CADASTRANTE OR
		  OLD.ID_LOTA_CADASTRANTE <> NEW.ID_LOTA_CADASTRANTE OR
		  OLD.ID_SUBSCRITOR <> NEW.ID_SUBSCRITOR OR
		  OLD.ID_LOTA_SUBSCRITOR <> NEW.ID_LOTA_SUBSCRITOR OR
		  OLD.DESCR_DOCUMENTO <> NEW.DESCR_DOCUMENTO OR
		  OLD.DT_DOC <> NEW.DT_DOC OR
		  OLD.DT_REG_DOC <> NEW.DT_REG_DOC OR
		  OLD.NM_SUBSCRITOR_EXT <> NEW.NM_SUBSCRITOR_EXT OR
		  OLD.NUM_EXT_DOC <> NEW.NUM_EXT_DOC OR
		  OLD.NM_ARQ_DOC <> NEW.NM_ARQ_DOC OR
		  OLD.ID_DESTINATARIO <> NEW.ID_DESTINATARIO OR
		  OLD.ID_LOTA_DESTINATARIO <> NEW.ID_LOTA_DESTINATARIO OR
		  OLD.NM_DESTINATARIO <> NEW.NM_DESTINATARIO OR
		  OLD.dt_finalizacao <> NEW.dt_finalizacao OR
		  OLD.ID_MOD <> NEW.ID_MOD OR
		  OLD.ID_ORGAO_USU <> NEW.ID_ORGAO_USU OR
		  OLD.ID_CLASSIFICACAO <> NEW.ID_CLASSIFICACAO OR
		  OLD.ID_FORMA_DOC <> NEW.ID_FORMA_DOC OR
		  OLD.FG_PESSOAL <> NEW.FG_PESSOAL OR
		  OLD.ID_ORGAO_DESTINATARIO <> NEW.ID_ORGAO_DESTINATARIO OR
		  OLD.ID_ORGAO <> NEW.ID_ORGAO OR
		  OLD.OBS_ORGAO_DOC <> NEW.OBS_ORGAO_DOC OR
		  OLD.NM_ORGAO_DESTINATARIO <> NEW.NM_ORGAO_DESTINATARIO OR
		  OLD.FG_SIGILOSO <> NEW.FG_SIGILOSO OR
		  OLD.NM_FUNCAO_SUBSCRITOR <> NEW.NM_FUNCAO_SUBSCRITOR OR
		  OLD.FG_ELETRONICO <> NEW.FG_ELETRONICO OR
		  OLD.NUM_ANTIGO_DOC <> NEW.NUM_ANTIGO_DOC OR
		  OLD.ID_LOTA_TITULAR <> NEW.ID_LOTA_TITULAR OR
		  OLD.ID_TITULAR <> NEW.ID_TITULAR OR
		  OLD.NUM_AUX_DOC <> NEW.NUM_AUX_DOC OR
		  OLD.DSC_CLASS_DOC <> NEW.DSC_CLASS_DOC OR
		  OLD.ID_DOC_PAI <> NEW.ID_DOC_PAI OR
		  OLD.NUM_VIA_DOC_PAI <> NEW.NUM_VIA_DOC_PAI OR
		  OLD.ID_DOC_ANTERIOR <> NEW.ID_DOC_ANTERIOR OR
		  OLD.ID_MOB_PAI <> NEW.ID_MOB_PAI OR
		  OLD.NUM_SEQUENCIA <> NEW.NUM_SEQUENCIA OR
		  OLD.NUM_PAGINAS <> NEW.NUM_PAGINAS OR
		  OLD.DT_DOC_ORIGINAL <> NEW.DT_DOC_ORIGINAL OR
		  OLD.ID_MOB_AUTUADO <> NEW.ID_MOB_AUTUADO 
		then
			signal sqlstate value '20101' set message_text = 'Não é permitido alterar: não eletrônico com data de finalização e com conteúdo.';
		end if;
	elseif OLD.fg_eletronico = 'S' then
	    SET QTD_DOC_VAR = ( select count(*) from siga.ex_documento doc
			where  EXISTS (select * from siga.ex_movimentacao m, 
								  siga.ex_mobil mb
							where mb.id_doc = doc.id_doc and
								  mb.id_doc = OLD.id_doc and
								  (m.id_tp_mov = 11 ) and
								  m.id_mov_canceladora is null and
								  m.id_mobil = mb.id_mobil) );
		if  QTD_DOC_VAR <> 0 then
			if OLD.ID_DOC <> NEW.ID_DOC OR
			  OLD.NUM_EXPEDIENTE <> NEW.NUM_EXPEDIENTE OR
			  OLD.ANO_EMISSAO <> NEW.ANO_EMISSAO OR
			  OLD.ID_TP_DOC <> NEW.ID_TP_DOC OR
			  OLD.ID_CADASTRANTE <> NEW.ID_CADASTRANTE OR
			  OLD.ID_LOTA_CADASTRANTE <> NEW.ID_LOTA_CADASTRANTE OR
			  OLD.ID_SUBSCRITOR <> NEW.ID_SUBSCRITOR OR
			  OLD.ID_LOTA_SUBSCRITOR <> NEW.ID_LOTA_SUBSCRITOR OR
			  OLD.DESCR_DOCUMENTO <> NEW.DESCR_DOCUMENTO OR
			  OLD.DT_DOC <> NEW.DT_DOC OR
			  OLD.DT_REG_DOC <> NEW.DT_REG_DOC OR
			  OLD.NM_SUBSCRITOR_EXT <> NEW.NM_SUBSCRITOR_EXT OR
			  OLD.NUM_EXT_DOC <> NEW.NUM_EXT_DOC OR
			  OLD.ID_ARQ <> NEW.ID_ARQ OR
			  OLD.NM_ARQ_DOC <> NEW.NM_ARQ_DOC OR
			  OLD.ID_DESTINATARIO <> NEW.ID_DESTINATARIO OR
			  OLD.ID_LOTA_DESTINATARIO <> NEW.ID_LOTA_DESTINATARIO OR
			  OLD.NM_DESTINATARIO <> NEW.NM_DESTINATARIO OR
			  OLD.dt_finalizacao <> NEW.dt_finalizacao OR
			  OLD.ID_MOD <> NEW.ID_MOD OR
			  OLD.ID_ORGAO_USU <> NEW.ID_ORGAO_USU OR
			  OLD.ID_CLASSIFICACAO <> NEW.ID_CLASSIFICACAO OR
			  OLD.ID_FORMA_DOC <> NEW.ID_FORMA_DOC OR
			  OLD.FG_PESSOAL <> NEW.FG_PESSOAL OR
			  OLD.ID_ORGAO_DESTINATARIO <> NEW.ID_ORGAO_DESTINATARIO OR
			  OLD.ID_ORGAO <> NEW.ID_ORGAO OR
			  OLD.OBS_ORGAO_DOC <> NEW.OBS_ORGAO_DOC OR
			  OLD.NM_ORGAO_DESTINATARIO <> NEW.NM_ORGAO_DESTINATARIO OR
			  OLD.FG_SIGILOSO <> NEW.FG_SIGILOSO OR
			  OLD.NM_FUNCAO_SUBSCRITOR <> NEW.NM_FUNCAO_SUBSCRITOR OR
			  OLD.FG_ELETRONICO <> NEW.FG_ELETRONICO OR
			  OLD.NUM_ANTIGO_DOC <> NEW.NUM_ANTIGO_DOC OR
			  OLD.ID_LOTA_TITULAR <> NEW.ID_LOTA_TITULAR OR
			  OLD.ID_TITULAR <> NEW.ID_TITULAR OR
			  OLD.NUM_AUX_DOC <> NEW.NUM_AUX_DOC OR
			  OLD.DSC_CLASS_DOC <> NEW.DSC_CLASS_DOC OR
			  OLD.ID_DOC_PAI <> NEW.ID_DOC_PAI OR
			  OLD.NUM_VIA_DOC_PAI <> NEW.NUM_VIA_DOC_PAI OR
			  OLD.ID_DOC_ANTERIOR <> NEW.ID_DOC_ANTERIOR OR
			  OLD.ID_MOB_PAI <> NEW.ID_MOB_PAI OR
			  OLD.NUM_SEQUENCIA <> NEW.NUM_SEQUENCIA OR
			  OLD.NUM_PAGINAS <> NEW.NUM_PAGINAS OR
			  OLD.DT_DOC_ORIGINAL <> NEW.DT_DOC_ORIGINAL OR
			  OLD.ID_MOB_AUTUADO <> NEW.ID_MOB_AUTUADO 
			then       
			  signal sqlstate value '20101' set message_text = 'Não é permitido alterar: eletrônico, com conteúdo, tipo mov. 11 e sem mov. canceladora.';
			end if;
	   end if;
	end if;

end$$
DELIMITER ;
