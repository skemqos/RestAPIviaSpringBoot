package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentServiceController {
	private static Map<Integer, StudentData> studentDataRepo = new HashMap<>();
	static {
		try {
			FileInputStream fis = new FileInputStream(new File("D:\\TestData\\StudentData.xls"));
			// creating workbook instance that refers to .xls file
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			// creating a Sheet object to retrieve the object
			HSSFSheet sheet = wb.getSheetAt(0);
			// evaluating cell type
			FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
			for (Row row : sheet) // iteration over row using for each loop
			{
				if (row != null) {
					studentDataRepo.put(new Double(row.getCell(0).getNumericCellValue()).intValue(),
							new StudentData(new Double(row.getCell(0).getNumericCellValue()).intValue(),
									row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(),
									row.getCell(3).getStringCellValue()));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/Students", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudents() {
		return new ResponseEntity<>(studentDataRepo.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/Students", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createProduct(@RequestBody StudentData studentData) {
		studentDataRepo.put(studentData.getStudentId(), studentData);
		return new ResponseEntity<>("studentData is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/Students/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> updateProduct(@PathVariable("id") int id, @RequestBody StudentData studentData) {
		studentDataRepo.remove(id);
		studentData.setStudentId(id);
		studentDataRepo.put(id, studentData);
		JSONObject obj = new JSONObject();
		obj.put("msg", "studentData is created successfully");
		return new ResponseEntity<JSONObject>(obj, HttpStatus.OK);
	}

	@RequestMapping(value = "/Students/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		studentDataRepo.remove(id);
		return new ResponseEntity<>("studentData is deleted successsfully", HttpStatus.OK);
	}
}
