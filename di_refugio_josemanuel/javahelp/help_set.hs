<?xml version="1.0" encoding='ISO-8859-1' ?>
<helpset version="1.0">
	<title>Ayuda Refugio de Animales</title>
	<maps>
		<homeID>Ayuda</homeID>
		<mapref location="mapa.jhm"/>
	</maps>
 
	<view>
		<name>Tabla Contenidos</name>
		<label>Tabla de contenidos</label>
		<type>javax.help.TOCView</type>
		<data>tabladecontenidos.xml</data>
	</view>
 
	<view>
		<name>Indice</name>
		<label>Indice</label>
		<type>javax.help.IndexView</type>
		<data>indice.xml</data>
	</view>
 
	<view>
		<name>Buscar</name>
		<label>Buscar</label>
		<type>javax.help.SearchView</type>
		<data engine="com.sun.java.help.search.DefaultSearchEngine">
			JavaHelpSearch
		</data>
	</view>
 
</helpset>