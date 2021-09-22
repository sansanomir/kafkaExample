package com.devcenter.apiRest.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcenter.apiRest.constants.Constantes;
import com.devcenter.apiRest.entity.Api_call;
import com.devcenter.apiRest.entity.Coche;
import com.devcenter.apiRest.entity.Precio;
import com.devcenter.apiRest.model.CocheInfo;
import com.devcenter.apiRest.model.Response;
import com.devcenter.apiRest.service.Api_callService;
import com.devcenter.apiRest.service.CocheService;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	private Response response;
	private HttpStatus responseStatus;
	private Api_call llamadaApi;
	private List<Coche> coches;

	@Autowired
	@Qualifier("Api_callServiceImpl")
	private Api_callService apiCallService;
	
	@Autowired
	@Qualifier("CochesServiceImpl")
	private CocheService cochesService;
	
	@GetMapping("")
	public ResponseEntity<Response> obtenerExcel(HttpServletRequest request) {

		llamadaApi = new Api_call(request.getRemoteAddr(), new Timestamp(System.currentTimeMillis()),
				(String) request.getAttribute(Constantes.REQUEST_PATH));

		apiCallService.insert(llamadaApi);

		coches = new ArrayList<Coche>();

		coches = cochesService.listaTodosLosCoches();

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet(Constantes.TITULO_HOJA);
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 6000);

		Row header = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setBold(true);
		headerStyle.setFont(font);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue(Constantes.COCHE_ID);
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue(Constantes.COLOR);
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue(Constantes.NOMBRE_MODELO);
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(3);
		headerCell.setCellValue(Constantes.COCHE_P);
		headerCell.setCellStyle(headerStyle);

		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		for (int i = 0; i < coches.size(); i++) {
			Row row = sheet.createRow(i + 2);
			Cell cell = row.createCell(0);
			Coche c = coches.get(i);

			cell.setCellValue(c.getId());
			cell.setCellStyle(style);

			cell = row.createCell(1);
			cell.setCellValue(c.getColor());
			cell.setCellStyle(style);

			cell = row.createCell(2);
			cell.setCellValue(c.getNombreModelo());
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue(c.getPrecios().toString());
			cell.setCellStyle(style);
		}

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + Constantes.NOMBRE_ARCHIVO;

		FileOutputStream outputStream;
		
		try {
			outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Constantes.DATOS_VACIOS,
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), Constantes.MENSAJE_INTERNAL_ERROR + e.getMessage());
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (IOException e) {
			response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Constantes.DATOS_VACIOS,
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), Constantes.MENSAJE_INTERNAL_ERROR + e.getMessage());
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		responseStatus = HttpStatus.OK;
		response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Constantes.MENSAJE_EXPORTADO_OK,
				HttpStatus.OK.toString(), Constantes.SUCCESS);

		return new ResponseEntity(response, responseStatus);
	}
}
