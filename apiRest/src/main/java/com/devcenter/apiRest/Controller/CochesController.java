package com.devcenter.apiRest.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcenter.apiRest.constants.Constantes;
import com.devcenter.apiRest.entity.Api_call;
import com.devcenter.apiRest.entity.Coche;
import com.devcenter.apiRest.entity.Precio;
import com.devcenter.apiRest.model.CocheInfo;
import com.devcenter.apiRest.model.Response;
import com.devcenter.apiRest.service.Api_callService;
import com.devcenter.apiRest.service.CocheService;
import com.devcenter.apiRest.service.MarcaService;
import com.devcenter.apiRest.service.PrecioService;

@RestController
@RequestMapping("/coches")
public class CochesController {

	private Response response;
	private HttpStatus responseStatus;
	private Api_call llamadaApi;
	private List<Coche> coches;
	private Coche coche;
	private List<CocheInfo> cocheInfos;
	private CocheInfo cocheInfo;
	private List<Precio> precios;
	private int marcaId;

	@Autowired
	@Qualifier("CochesServiceImpl")
	private CocheService cochesService;

	@Autowired
	@Qualifier("PreciosServiceImpl")
	private PrecioService preciossService;

	@Autowired
	@Qualifier("MarcasServiceImpl")
	private MarcaService marcassService;

	@Autowired
	@Qualifier("Api_callServiceImpl")
	private Api_callService apiCallService;

	// http://localhost:8080/coches/2/fecha/2020-06-01
	@GetMapping("/{coche_id}/fecha/{fecha}")
	public ResponseEntity<Response> obtenerCocheEnFecha(HttpServletRequest request,
			@PathVariable(Constantes.COCHE_ID) int coche_id, @PathVariable("fecha") String fecha) {
		llamadaApi = new Api_call(request.getRemoteAddr(), new Timestamp(System.currentTimeMillis()),
				(String) request.getAttribute(Constantes.REQUEST_PATH));

		apiCallService.insert(llamadaApi);

		coche = cochesService.obtenerCoche(coche_id);
		marcaId = coche.getMarcaC().getMarca_id();
		precios = preciossService.findPreciosByCoche_idAndDate(fecha, coche_id);
		cocheInfos = new ArrayList<CocheInfo>();

		for (Precio p : precios) {
			cocheInfo = new CocheInfo();
			cocheInfo.setCoche_id(coche_id);
			cocheInfo.setMarca_id(marcaId);
			cocheInfo.setPrecio(p.getPrecio());
			cocheInfo.setFechaInicio(p.getFechaInicio());
			cocheInfo.setFechaFin(p.getFechaFin());

			cocheInfos.add(cocheInfo);
		}

		if (cocheInfos.isEmpty()) {
			response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Constantes.DATOS_VACIOS,
					HttpStatus.OK.toString(), Constantes.SUCCESS);
			responseStatus = HttpStatus.NOT_FOUND;
		}
		responseStatus = HttpStatus.OK;
		response = new Response(new Timestamp(System.currentTimeMillis()).toString(), cocheInfos.toString(),
				HttpStatus.OK.toString(), Constantes.SUCCESS);
		return new ResponseEntity(response, responseStatus);
	}

	// http://localhost:8080/h2-console/
	// GET /coches?filter=coche_id=1

	/// coches/filter?id=2
	// http://localhost:8080/coches/filter?id=2
	// http://localhost:8080/coches/filter?color=Verde
	// http://localhost:8080/coches/filter?nombre_modelo=Corolla
	// http://localhost:8080/coches/filter?color=Verde&id=4
	// No existen
	// http://localhost:8080/coches/filter?color=rojo&id=4&nombre_modelo=Toyota
	@RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Response> getName(HttpServletRequest request,
			@RequestParam(value = "id", defaultValue = Constantes.STRING_CERO) String id,
			@RequestParam(value = Constantes.COLOR, defaultValue = Constantes.STRING_CERO) String color,
			@RequestParam(value = Constantes.NOMBRE_MODELO, defaultValue = Constantes.STRING_CERO) String nombre_modelo) {

		llamadaApi = new Api_call(request.getRemoteAddr(), new Timestamp(System.currentTimeMillis()),
				(String) request.getAttribute(Constantes.REQUEST_PATH));

		apiCallService.insert(llamadaApi);

		coches = new ArrayList<Coche>();
		int idInt = Integer.parseInt(id);

		if (Constantes.STRING_CERO.equals(id) && Constantes.STRING_CERO.equals(color)
				&& Constantes.STRING_CERO.equals(nombre_modelo)) {
			// No tenemos ningún parámetro
		} else if (Constantes.STRING_CERO.equals(id) && Constantes.STRING_CERO.equals(color)) {
			// Tenemos el nombre_modelo
			coches = cochesService.obtenerCochesModelo(nombre_modelo);
		} else if (Constantes.STRING_CERO.equals(nombre_modelo) && Constantes.STRING_CERO.equals(color)) {
			// Tenemos el id
			coche = new Coche();
			coche = cochesService.obtenerCoche(idInt);
			coches.add(coche);
		} else if (Constantes.STRING_CERO.equals(id) && Constantes.STRING_CERO.equals(nombre_modelo)) {
			// Tenemos el color
			coches = cochesService.obtenerCochesColor(color);
		} else if (Constantes.STRING_CERO.equals(id)) {
			// tenemos el color y nombre_modelo
			coches = cochesService.obtenerCochesColorModelo(color, nombre_modelo);
		} else if (Constantes.STRING_CERO.equals(color)) {
			// tenemos el id y nombre_modelo
			coches = cochesService.obtenerCochesIdModelo(idInt, nombre_modelo);
		} else if (Constantes.STRING_CERO.equals(nombre_modelo)) {
			// tenemos el id y color
			coches = cochesService.obtenerCochesIdColor(idInt, color);
		} else {
			// tenemos los 3 parámetros
			coches = cochesService.obtenerCoches3Parametro(idInt, color, nombre_modelo);
		}

		if (coches.isEmpty()) {
			response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Constantes.DATOS_VACIOS,
					HttpStatus.OK.toString(), Constantes.SUCCESS);
			responseStatus = HttpStatus.NOT_FOUND;
		} else if (null == coches.get(0)) {
			response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Constantes.DATOS_VACIOS,
					HttpStatus.OK.toString(), Constantes.SUCCESS);
			responseStatus = HttpStatus.NOT_FOUND;
		} else {
			response = new Response(new Timestamp(System.currentTimeMillis()).toString(), coches.toString(),
					HttpStatus.OK.toString(), Constantes.SUCCESS);
			responseStatus = HttpStatus.OK;
		}

		return new ResponseEntity(response, responseStatus);
	}
}
