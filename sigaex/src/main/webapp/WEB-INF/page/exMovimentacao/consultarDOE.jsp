<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" buffer="64kb"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://localhost/customtag" prefix="tags"%>
<%@ taglib uri="http://localhost/jeetags" prefix="siga"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<siga:pagina titulo="Movimentação">
	
	<script type="text/javascript" language="Javascript1.1">
		function sbmt(offset) {
			
			if(!data(document.getElementById("dataEnvio").value)) {
				sigaModal.alerta('Campo "Data de Envio de" inválido.');	
				return;
			}
			if(!data(document.getElementById("dataAte").value)) {
				sigaModal.alerta('Campo "Data de Envio até" inválido.');
				return;
			}
			$("#buscar").prop("disabled", true);
			submitBuscaFrm(offset);
		}
		
		function submitBuscaFrm(offset) {
			sigaSpinner.mostrar();
			if (offset == null) {
				offset = 0;
			}
			frm.elements["paramoffset"].value = offset;
			frm.elements["p.offset"].value = offset;
			frm.submit();
		}
		
		function data(campo) {
			var reg = /(([0-2]{1}[0-9]{1}|3[0-1]{1})\/(0[0-9]{1}|1[0-2]{1})\/[0-9]{4})/g; //valida dd/mm/aaaa
			if(campo.search(reg) == -1) {
				return false;
			}
			return true;
		}
		
		function atualizarCheck(a) {
			var aChk = document.getElementsByName("pubSelecionados");
			
			for (var i=0;i<aChk.length;i++){
				 var item = aChk[i];
			     if (item.type == "checkbox" && item.checked && item.value != a.value) {
			    	 item.checked = false;
					 item.click();
			     }
			}
		}
		
		function clr() {
			document.getElementById("dataEnvio").value = "";
			document.getElementById("dataAte").value = "";
			$("#limpar").prop("disabled", true);
			submitBuscaFrm(0);
		}
		
		function validarCampos() {
			var aChk = document.getElementsByName("pubSelecionados");
			var boolArquivo = true;
			for (var i=0;i<aChk.length;i++){
				 var item = aChk[i];
			    if (item.type == "checkbox" && item.checked) {
			    	boolArquivo = false;
					break;
			    }
			}

			if (boolArquivo) {									
				sigaModal.alerta("Atenção! Selecione pelo menos um arquivo.");
				return;	
			}
			
			$('#confirmacaoModal').modal('show');
		}
		
		function atualizarTela(tempo){
			window.setTimeout( function() {
				window.location.reload();
				sigaSpinner.mostrar();
			}, tempo);
		}
		
	</script>
	
	<div class="container-fluid" id="content-doe">
		<div id="alertError"></div>
		<div class="card bg-light mb-3">
			<div class="card-header">
				<h5>
					Consultar Publicação DOE
				</h5>
			</div>
			<div class="card-body">
				<form name="frm" action="consultarDOE" method="post">
					<input type="hidden" name="paramoffset" value="0" />
					<input type="hidden" name="p.offset" value="0" />
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
								<label>Login</label>
								<input type="text" id="loginDOE" name="loginDOE" value="${usuarioDOE}" class="form-control" readonly/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
								<label for="dataEnvio">Data de Envio de:</label> <input
									name="dataEnvio" id="dataEnvio" value="${dataEnvio}" class="form-control campoData"
									autocomplete="off" />
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<label for="dataAte">Data de Envio até:</label> <input
									name="dataAte" id="dataAte" value="${dataAte}" class="form-control campoData"
									autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm">
							<input type="button" value="Buscar" class="btn btn-primary" id="buscar" onclick="javascript:sbmt(0);"/>
							<input type="button" value="Cancelar Arquivo" ${(not empty lista) ? '' : 'disabled="disabled"'} class="btn btn-primary" id="cancelar" onclick="javascript:validarCampos();"/>
							<input type="button" value="Limpar" class="btn btn-primary" id="limpar" onclick="javascript:clr();"/>
							<input type="button" value="Voltar" class="btn btn-primary" id="buscar" onclick="location.href='/siga/app/principal'"/>
						</div>				
					</div>
				</form>
			</div>
		</div>
		<c:if test="${(not empty lista)}">
			<table border="0" class="table table-sm table-striped">
				<thead class="${thead_color}">
					<tr>
						<th class="text-left" style="width: 5%;"></th>
						<th class="text-center" style="width: 25%;">Arquivo</th>
						<th class="text-center" style="width: 25%;">Data de Recebimento</th>
						<th class="text-center" style="width: 20%;">Status da Publicação</th>
						<th class="text-center" style="width: 25%;">Data Cancelamento</th>	
					</tr>
					
				</thead>
				<tbody class="table-bordered" >
					<siga:paginador maxItens="15" maxIndices="10" totalItens="${tamanho}" itens="${lista}" var="pub">
				    	<tr class="even">
							<td class="text-center align-middle"><input type="checkbox" name="pubSelecionados" id="${x}" class="chk" value="${pub.idMaterial}" ${x_checked} onclick="javascript:atualizarCheck(this);"/></td>
	  			        	<td class="text-center align-middle" style="font-weight: normal;">${pub.nomeArquivo}</td>
	  			        	<td class="text-center align-middle" style="font-weight: normal;"><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${pub.dataRecebimento}" /></td>
	  			        	<td class="text-center align-middle" style="font-weight: normal;">${pub.statusPublicacaoDto.statusPublicacaoDescr}</td> 
	  			        	<td class="text-center align-middle" style="font-weight: normal;"><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${pub.dataCancelamento}" /></td>
	  			        </tr>
					</siga:paginador>
				</tbody>
			</table>
		</c:if>
		<siga:siga-modal id="confirmacaoModal" exibirRodape="false" tituloADireita="Confirmação" linkBotaoDeAcao="">
			<div class="modal-body">
				<div class="form-group">
					<label>Motivo</label>
					<select class="form-control siga-select2" id="motivo" name="motivo" >
						<c:forEach items="${listaJustifCancel}" var="item">
							<option value="${item.justificativaId}" 'selected'}>${item.justificativaDescr}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="modal-footer">
	       		<a href="#" class="btn btn-primary" role="button" aria-pressed="true">Ok</a>
	       		<button type="button" class="btn btn-cancel btn-light" data-dismiss="modal">Voltar</button>		        
			</div>
		</siga:siga-modal>
		
	</div>
</siga:pagina>
<script>
	$('input.chk').on('change', function() {
	    $('input.chk').not(this).prop('checked', false);  
	});
</script>