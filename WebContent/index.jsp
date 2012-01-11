<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bulapédia - Página Principal</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<f:loadBundle var="msgs" basename="mensagens.textos" />
	<f:view>
		<div id="header">
			<h1>
				<h:outputText value="#{msgs.titulo}" />
			</h1>
			<ul id="menu">
				<li class="active"><a href="#">Sobre</a></li>
				<li><a href="#">Registro</a></li>
				<li><a href="#">Instruções</a></li>
				<li><a href="#">Contato</a></li>
			</ul>
		</div>
		<div id="teaser">
			<div class="wrap">
				<div id="image"></div>
				<div class="box">
					<h2>
						Bem vindo ao <em
							title="Medicamentos, pesquisa, interações medicamentosas">Bulapédia</em>
					</h2>
					<p>
						<h:outputText value="#{msgs.introducao}" />
					</p>
				</div>
			</div>
		</div>
		<div id="bar">
			<div class="wrap">
				<span class="step"><a>1</a> <h:outputText
						value="#{msgs.passo1}" /></span> <span class="step"><a>2</a> <h:outputText
						value="#{msgs.passo2}" /></span> <span class="step"><a>3</a> <h:outputText
						value="#{msgs.passo3}" /> </span>
			</div>
		</div>
		<div class="wrap">
			<div class="col">
				<h:form>
					<h:outputText value="#{msgs.textoBarra}" />
					<h:inputText id="valorprocura" size="150"
						value="#{buscaBean.textobusca}"></h:inputText>
					<rich:suggestionbox id="suggestionBoxId" for="valorprocura"
						suggestionAction="#{buscaBean.autocompletar}" var="result"
						tokens="," height="150" width="650" cellpadding="2"
						cellspacing="2" shadowOpacity="4" shadowDepth="4" minChars="3"
						rules="none" nothingLabel="Não há resultados.">
						
						<f:facet name="header">
							<h:outputText value="Nome Comerial / Substância" />
						</f:facet>
						<h:column>
							<h:outputText value="#{result}" style="font-style:bold" />
						</h:column>
					</rich:suggestionbox>

					<h:commandButton type="submit" action="busca" value="Buscar" />
					<h:commandButton type="reset" value="Limpar" />
				</h:form>
				<br>
				<h:form>
					<h:commandButton type="submit" value="Rodar Indexação"
						action="indexa" />
				</h:form>
			</div>

		</div>
		<div id="footer">
			(C)2011 Bulapedia.com.br<br /> Desenvolvido por <a
				href="http://www.ivancosta.fot.br">Ivan Costa</a>
		</div>
	</f:view>
</body>
</html>