package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.FieldDAO;
import dao.QuestionDAO;
import dao.TypeQuestionDAO;
import model.AnswerEntry;
import model.FieldEntry;
import model.QuestionEntry;
import model.TypeQuestionEntry;

@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadFile() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("file");
		InputStream inputStream = null;
		
		List<FieldEntry> fields = FieldDAO.getFields();
		List<TypeQuestionEntry> typeQuestions = TypeQuestionDAO.getTypeQuestions();
		List<QuestionEntry> questions = new ArrayList<QuestionEntry>();
		
		try {
			inputStream = filePart.getInputStream();
			OPCPackage pkg = OPCPackage.open(inputStream);
			XSSFWorkbook wb = new XSSFWorkbook(pkg);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			
			int rows = sheet.getPhysicalNumberOfRows();

			for (int i = 0; i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					String questionContent = row.getCell(0).getStringCellValue();
					
					List<AnswerEntry> answers = new ArrayList<AnswerEntry>();
					answers.add(new AnswerEntry(row.getCell(1).getRawValue()));
					answers.add(new AnswerEntry(row.getCell(2).getRawValue()));
					answers.add(new AnswerEntry(row.getCell(3).getRawValue()));
					answers.add(new AnswerEntry(row.getCell(4).getRawValue()));
					
					AnswerEntry rightAnswer = getRightAnswer(answers, (int)row.getCell(5).getNumericCellValue());
					FieldEntry field = getField(fields, row.getCell(6).getStringCellValue());
					TypeQuestionEntry typeQuestion = getTypeQuestion(typeQuestions, row.getCell(7).getStringCellValue());

					QuestionEntry question = new QuestionEntry(questionContent, rightAnswer, answers, field, typeQuestion);
					
					questions.add(question);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
		boolean insertQuestionsError = QuestionDAO.insertQuestions(questions);
		getServletContext().setAttribute("insertQuestionsError", insertQuestionsError);
		
		response.sendRedirect(request.getContextPath() + "/DanhSachCauHoi");
	}
	
	private AnswerEntry getRightAnswer(List<AnswerEntry> answers, int index) {
		return answers.get(index - 1);
	}
	
	private FieldEntry getField(List<FieldEntry> fields, String fieldName) {
		String name = fieldName.toLowerCase().trim();
		for (FieldEntry item : fields) {
			if (item.getName().toLowerCase().equals(name)) {
				return item;
			}
		}
		return null;
	}
	
	private TypeQuestionEntry getTypeQuestion(List<TypeQuestionEntry> typeQuestions, String typeQuestionName){
		String name = typeQuestionName.toLowerCase().trim();
		for (TypeQuestionEntry item : typeQuestions) {
			if (item.getName().toLowerCase().equals(name)) {
				return item;
			}
		}
		return null;
	}
}
