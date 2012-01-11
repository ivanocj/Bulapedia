<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bulapédia - Resultados da Busca</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<f:loadBundle var="msgs" basename="mensagens.textos" />
	<f:view>
		<div class="wrap">
			<h1>
				<h:outputText value="#{msgs.resultadoBusca}" />
			</h1>
			<div style="width: 100%;border-top: 10px;">
				<p><br>
					<h:outputText escape="false" value="#{buscaBean.resultadoBusca}" />
				</p>
			</div>
			<div class="col">
				<a href="javascript: history.go(-1)"><h:outputText
						value="#{msgs.voltaPagina }" /></a>
			</div>
		</div>

		<div id="footer">
			(C)2011 Bulapedia.com.br<br /> Desenvolvido por <a
				href="http://www.ivancosta.fot.br">Ivan Costa</a>
		</div>
	</f:view>
</body>
</html>