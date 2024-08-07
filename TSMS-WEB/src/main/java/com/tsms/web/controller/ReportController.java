package com.tsms.web.controller;

import static com.tsms.web.utils.WebUtils.setModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tsms.pojo.ReportPojo;
import com.tsms.pojo.TimesheetReportResponsePojo;
import com.tsms.web.dao.AdminDao;
import com.tsms.web.dao.BranchDao;
import com.tsms.web.entity.BranchMaster;
import com.tsms.web.entity.ProjectMaster;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.service.AdminService;
import com.tsms.web.service.TimesheetReportService;
import com.tsms.web.utils.BundleUtils;
import com.tsms.web.utils.DateUtils;


@Controller
public class ReportController {
	public static final float titleFont = Float.valueOf(BundleUtils.message("pdf.titleFont")).floatValue();// 12f
	public static final Font fontTitleReport = new Font(FontFactory.getFont("VERDANA", "BLACK", titleFont));
	public static final float subTitleNamefont = Float.valueOf(BundleUtils.message("pdf.subTitleNamefont")).floatValue();
	public static final Font fontsubTitleName = new Font(FontFactory.getFont("VERDANA", "BLACK", subTitleNamefont));
	public static final int BUFFER_SIZE = Integer.valueOf(BundleUtils.message("title.BufferSize"));
	public static final String COMMA_DELIMITER = ",";
	public static final String NEW_LINE_SEPARATOR = "\n";
	public static final float colunFont = Float.valueOf(BundleUtils.message("pdf.colunFont")).floatValue();
	public static final float columndataFont = Float.valueOf(BundleUtils.message("pdf.dataFont")).floatValue();
	public static final Font f1 = new Font(
			FontFactory.getFont("VERDANA", columndataFont, Font.NORMAL, BaseColor.BLACK));
	public static final Font hf = new Font(FontFactory.getFont("VERDANA", colunFont, Font.BOLD));
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private TimesheetReportService timesheetReportService;
	
	@Autowired 
	private BranchDao branchDao;
	
	@GetMapping("BranchWiseReport.html")
	public String BranchWiseReport(Model model,@ModelAttribute("reportPojo") ReportPojo reportPojo) {
		String view = "";
		List<BranchMaster> branchList = (List<BranchMaster>) branchDao.findAllByOrderByBranchNameAsc();
		model.addAttribute("branchList", branchList);
		view = setModel(model, "BranchWiseReport.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
				"meta.key.TimesheetReport.title");
		return view;
	}

	@PostMapping("BranchWiseReport.html")
	public String BranchWiseReportPost(Model model, HttpServletRequest request, HttpServletResponse response ,@ModelAttribute("reportPojo") ReportPojo reportPojo) {
		String view = null;
//		String branch = reportPojo.getBranch();
		List<TimesheetReportResponsePojo> listBranchWiseReport = timesheetReportService.getlistBranchWiseReport(reportPojo);
		List<BranchMaster> branchList = (List<BranchMaster>) branchDao.findAllByOrderByBranchNameAsc();
		if(reportPojo.getAction().equals("View")) {
			model.addAttribute("showData", true);
			model.addAttribute("listBranchWiseReport", listBranchWiseReport);
			view = setModel(model, "BranchWiseReport.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
					"meta.key.TimesheetReport.title");
		} else if(reportPojo.getAction().equals("Excel")) {
			generateProjectExcel(listBranchWiseReport,response,"Branch");
		} else if(reportPojo.getAction().equals("PDF")) {
			generateTimesheetPdf(listBranchWiseReport,"Branch",response);
		}
		model.addAttribute("branchid", reportPojo.getBranch());
		model.addAttribute("branchList", branchList);
		return view;
	}

	@GetMapping("ProjectWiseReport.html")
	public String ProjectWiseReport(Model model,@ModelAttribute("reportPojo") ReportPojo reportPojo) {
		List<ProjectMaster> projectList = adminService.findAllByOrderByProjectNameAsc();
		model.addAttribute("AllProjectList", projectList);
		String view = "";
		view = setModel(model, "ProjectWiseReport.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
				"meta.key.TimesheetReport.title");
		return view;
	}
	
	@PostMapping("ProjectWiseReport.html")
	public String ProjectWiseReportPost(Model model, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("reportPojo") ReportPojo reportPojo) {
		String view = null;
//		Long projectID = Long.parseLong(reportPojo.getProject());
		List<TimesheetReportResponsePojo> listProjectWiseReport = timesheetReportService.getProjectWiseReport(reportPojo);
		List<ProjectMaster> projectList = adminService.findAllByOrderByProjectNameAsc();
		if(reportPojo.getAction().equals("View")) {
			model.addAttribute("listProjectWiseReport", listProjectWiseReport);
			model.addAttribute("showData", true);
			view = setModel(model, "ProjectWiseReport.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
					"meta.key.TimesheetReport.title");
		} else if(reportPojo.getAction().equals("Excel")) {
			generateProjectExcel(listProjectWiseReport,response, "Project");
		} else if(reportPojo.getAction().equals("PDF")) {
			generateTimesheetPdf(listProjectWiseReport,"Project",response);
		}
		model.addAttribute("AllProjectList", projectList);
		model.addAttribute("projectid", reportPojo.getProject());
		return view;
	}
	


	@GetMapping("ManagerWiseReport.html")
	public String ManagerWiseReport(Model model,@ModelAttribute("reportPojo") ReportPojo reportPojo) {
		List<UserMaster> managerList = adminDao.findAllByOrderByFirstNameAsc();
		model.addAttribute("managerList", managerList);
		String view = "";
		view = setModel(model, "ManagerWiseReport.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
				"meta.key.TimesheetReport.title");
		return view;
	}
	
	@PostMapping("ManagerWiseReport.html")
	public String ManagerWiseReportPost(Model model, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("reportPojo") ReportPojo reportPojo) {
		String view = null;
//		Long managerID = Long.parseLong(reportPojo.getManagername());
		List<TimesheetReportResponsePojo> listManagerWiseReport = timesheetReportService.getManagerWiseReport(reportPojo);
		List<UserMaster> managerList = adminDao.findAllByOrderByFirstNameAsc();
		if(reportPojo.getAction().equals("View")) {			
			model.addAttribute("listManagerWiseReport", listManagerWiseReport);
			model.addAttribute("showData", true);
			view = setModel(model, "ManagerWiseReport.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
					"meta.key.TimesheetReport.title");
		} else if(reportPojo.getAction().equals("Excel")) {
			generateProjectExcel(listManagerWiseReport,response, "Manager");
		} else if(reportPojo.getAction().equals("PDF")) {
			generateTimesheetPdf(listManagerWiseReport,"Manager",response);
		}
		model.addAttribute("managerList", managerList);
		model.addAttribute("managerid", reportPojo.getManagername());
		return view;
	}

	public static void addCell(PdfPTable table, PdfPCell pcell, String data, Font f1, int elment, int colSpan) {
		pcell.setPhrase(new Phrase(data, f1));
		pcell.setHorizontalAlignment(elment);
		pcell.setColspan(colSpan);
		table.addCell(pcell);
	}
	
	@GetMapping("TimesheetNotFilled.html")
	public String TimesheetNotFilled(Model model,@ModelAttribute("userMaster") UserMaster userMaster) {
		String view = "";
		view = setModel(model, "TimesheetNotFilled.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
				"meta.key.TimesheetReport.title");
		return view;
	}
	
	@PostMapping("TimesheetNotFilledReport.html")
	public String TimesheetNotFilledpost(Model model,@ModelAttribute("userMaster") UserMaster userMaster,HttpServletResponse response,HttpServletRequest httpServletRequest) {
		String view = "";
		String action=httpServletRequest.getParameter("action");
		List<UserMaster> ListEmployee=timesheetReportService.getTimesheetNotFilledReport(httpServletRequest.getParameter("fromDate"),httpServletRequest.getParameter("toDate"));
		if(action.equals("View")) {			
	model.addAttribute("ListEmployee", ListEmployee);
	model.addAttribute("fromDate", httpServletRequest.getParameter("fromDate"));
	model.addAttribute("showData", true);

		view = setModel(model, "TimesheetNotFilled.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
				"meta.key.TimesheetReport.title");
		}
		else if(action.equals("Excel")) {	
			generateTimesheetNotFilledExcel(ListEmployee,response, "TimesheetNotFilled",httpServletRequest.getParameter("fromDate"));
		} else if(action.equals("PDF")) {
			generateTimesheetNotFilledPdf(ListEmployee,"TimesheetNotFilled",response,httpServletRequest.getParameter("fromDate"));
		
		}
		
		return view;
	}
	
	@GetMapping("EmployeeWiseReport.html")
	public String EmployeeWiseReport(Model model,@ModelAttribute("reportPojo") ReportPojo reportPojo) {
		List<UserMaster> empList = adminDao.findAllByOrderByFirstNameAsc();

		model.addAttribute("empList", empList);
		String view = "";
		view = setModel(model, "EmployeeWiseReport.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
				"meta.key.TimesheetReport.title");
		return view;
	}
	
	@PostMapping("EmployeeWiseReport.html")
	public String EmployeeWiseReportPost(Model model, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("reportPojo") ReportPojo reportPojo) {
		String view = null;
//		Long EmpID = Long.parseLong(reportPojo.getEmployeename());
		List<TimesheetReportResponsePojo> listEmployeeWiseReport = timesheetReportService.getEmployeeWiseReport(reportPojo);
		List<UserMaster> empList = adminDao.findAllByOrderByFirstNameAsc();
		if(reportPojo.getAction().equals("View")) {			
			model.addAttribute("listEmployeeWiseReport", listEmployeeWiseReport);
			model.addAttribute("showData", true);
			view = setModel(model, "EmployeeWiseReport.url", "TimesheetReport.page", "meta.key.desc.TimesheetReport.title",
					"meta.key.TimesheetReport.title");
		} else if(reportPojo.getAction().equals("Excel")) {
			generateProjectExcel(listEmployeeWiseReport, response, "Emp");
		} else if(reportPojo.getAction().equals("PDF")) {
			generateTimesheetPdf(listEmployeeWiseReport,"Emp",response);
		}
		model.addAttribute("empList", empList);
		model.addAttribute("employeeid", reportPojo.getEmployeename());
		return view;
	}
	
	
	private void generateTimesheetNotFilledPdf(List<UserMaster> listEmployee, String string,
			HttpServletResponse httpServletResponse,String fromDate) {
		Document document = new Document();
		List<String> column = new ArrayList<>();
		try {
			PdfWriter.getInstance(document, httpServletResponse.getOutputStream());
			document.open();
			String fileName = BundleUtils.message("title.pdf.name");
			PdfPTable table = null;
				table = new PdfPTable(4);
			
			table.setWidthPercentage(100);
			PdfPCell p = new PdfPCell();
			
			column.add("Employee Name");
			column.add("Employee Code");
			column.add("Designation");
			column.add("Date");

			pdfGenerationTitle(httpServletResponse, document, "Timesheet Not Filled",
					fileName, column, table, p, null);

			if (listEmployee.size() > 0) {
				for (UserMaster list1 : listEmployee) {
					addCell(table, p, list1.getFirstName()+list1.getLastName(), f1, Element.ALIGN_LEFT, 1);
					addCell(table, p, list1.getEmpCode(), f1, Element.ALIGN_LEFT, 1);
					if(list1.getFkdesignation()!=null)
					addCell(table, p, list1.getFkdesignation().getDesignationName(), f1, Element.ALIGN_LEFT, 1);
					else
					addCell(table, p, "--", f1, Element.ALIGN_LEFT, 1);
					addCell(table, p, fromDate, f1, Element.ALIGN_LEFT, 1);
				}
			} else {
				addCell(table, p, BundleUtils.message("pdf.no.record.found"), hf, Element.ALIGN_LEFT, 1);
			}
			document.add(table);
			document.close();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		
	}

	private void generateTimesheetNotFilledExcel(List<UserMaster> listEmployee, HttpServletResponse httpServletResponse,
			String string,String FromDate) {
		FileWriter fileWriter = null;
		FileInputStream inputStream = null;
		OutputStream outStream = null;
		try {
//			String fileName = BundleUtils.message("title.fileNameTimeSheet")+ DateUtils.formatDateForSystemGeneratedDate();
			String	fileName ="Report "+DateUtils.formatDateForSystemGeneratedDate();
			String filePath=null;
			filePath = BundleUtils.message("title.folderName.wcc");
			Path path = Paths.get(filePath);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}
			fileWriter = new FileWriter(BundleUtils.message("title.folderName.wcc")+ fileName +".csv");
			fileWriter.append("TimeSheet Details Report");
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("Report Generated On " + DateUtils.formatDateForSystemGeneratedDate());
			fileWriter.append(NEW_LINE_SEPARATOR);
			StringBuffer columnName = new StringBuffer("");
			
			columnName.append("Employee Name");
			columnName.append(COMMA_DELIMITER);
			columnName.append("Employee Code");
			columnName.append(COMMA_DELIMITER);
			columnName.append("Designation");
			columnName.append(COMMA_DELIMITER);
			columnName.append("Date");
			columnName.append(COMMA_DELIMITER);
			
			fileWriter.append(columnName.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			if (listEmployee.size() > 0) {
				for (UserMaster listTimesheetReport : listEmployee) {
					
					fileWriter.append(listTimesheetReport.getFirstName()+listTimesheetReport.getLastName());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(listTimesheetReport.getEmpCode());
					fileWriter.append(COMMA_DELIMITER);
					if(listTimesheetReport.getFkdesignation()!=null)
					fileWriter.append(listTimesheetReport.getFkdesignation().getDesignationName());
					else
						fileWriter.append("--");
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(FromDate);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
			} else {
				fileWriter.append(NEW_LINE_SEPARATOR);
				fileWriter.append("No Record Found");
				fileWriter.append(COMMA_DELIMITER);
			}
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.flush();
			fileWriter.close();
			httpServletResponse.setContentType("text/csv;charset=utf-8");
			httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".csv");
			outStream = httpServletResponse.getOutputStream();
			File downloadFile = new File(BundleUtils.message("title.folderName.wcc") +  fileName +".csv");
			inputStream = new FileInputStream(downloadFile);
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead=-1 ;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	

	private void generateTimesheetPdf(List<TimesheetReportResponsePojo> listTimesheetReportRespon,
			String reportOf, HttpServletResponse httpServletResponse) {
		Document document = new Document();
		List<String> column = new ArrayList<>();
		try {
			PdfWriter.getInstance(document, httpServletResponse.getOutputStream());
			document.open();
			String fileName = BundleUtils.message("title.pdf.name");
			PdfPTable table = null;
			if(reportOf.equals("Manager")) {
				table = new PdfPTable(5);
			}else {
				table = new PdfPTable(4);
			}
			table.setWidthPercentage(100);
			PdfPCell p = new PdfPCell();
			if(reportOf.equals("Branch")) {	
				column.add("Branch Name");
			}
			if(!reportOf.equals("Branch") || reportOf.equals("Emp")) {
				column.add("Project Name");
			}
			if(reportOf.equals("Manager")) {
				column.add("Manager Name");
			}
			column.add("Employee Name");
			column.add("Timesheet Work");
			column.add("Approval Status");

			pdfGenerationTitle(httpServletResponse, document, reportOf,
					fileName, column, table, p, null);

			if (listTimesheetReportRespon.size() > 0) {
				for (TimesheetReportResponsePojo list1 : listTimesheetReportRespon) {
					if(reportOf.equals("Branch")) {	
						addCell(table, p, list1.getBranchName(), f1, Element.ALIGN_LEFT, 1);
					}
					if(!reportOf.equals("Branch") || reportOf.equals("Emp")) {
						addCell(table, p, list1.getProjectName(), f1, Element.ALIGN_LEFT, 1);
					}
					if(reportOf.equals("Manager") || reportOf.equals("Emp")) {	
						addCell(table, p, list1.getManagerName(), f1, Element.ALIGN_LEFT, 1);
					}
					addCell(table, p, list1.getEmployeeName(), f1, Element.ALIGN_LEFT, 1);
					addCell(table, p, list1.getTimesheetWork(), f1, Element.ALIGN_LEFT, 1);
					addCell(table, p, list1.getApprovalStatus(), f1, Element.ALIGN_LEFT, 1);
				}
			} else {
				addCell(table, p, BundleUtils.message("pdf.no.record.found"), hf, Element.ALIGN_LEFT, 1);
			}
			document.add(table);
			document.close();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
	
	public static void pdfGenerationTitle(HttpServletResponse response, Document document, String Title,
			String fileName, List<String> columnName, PdfPTable table, PdfPCell pcell, String subTitle)
			throws DocumentException {

		Paragraph headGeneratedDate = new Paragraph(BundleUtils.message("pdf.report.generated")+ DateUtils.formatDateForSystemGeneratedDate());
		headGeneratedDate.setAlignment(Element.ALIGN_RIGHT);
		document.add(headGeneratedDate);

		Paragraph head = new Paragraph(BundleUtils.message("pdf.title.company"));
		head.setAlignment(Element.ALIGN_CENTER);
		document.add(head);

		Paragraph hed2 = new Paragraph(Title+ " Report", fontTitleReport);
		hed2.setAlignment(Element.ALIGN_CENTER);
		document.add(hed2);

		if (subTitle != null) {
			Paragraph headTitleReport = new Paragraph(subTitle, fontsubTitleName);
			headTitleReport.setAlignment(Element.ALIGN_CENTER);
			document.add(headTitleReport);
		}
		document.add(new Paragraph("\n"));

		if (Title.replaceAll(" ", "").contains("Regist")) {

		} else {
			for (String column : columnName) {
				addCell(table, pcell, column, hf, Element.ALIGN_CENTER, 1);
			}
		}
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName+"PDF" + ".pdf" + "");
	}
	
	private void generateProjectExcel(List<TimesheetReportResponsePojo> listProjectWiseReport,
			HttpServletResponse httpServletResponse, String reportOf) {
		FileWriter fileWriter = null;
		FileInputStream inputStream = null;
		OutputStream outStream = null;
		try {
//			String fileName = BundleUtils.message("title.fileNameTimeSheet")+ DateUtils.formatDateForSystemGeneratedDate();
			String	fileName ="Report "+DateUtils.formatDateForSystemGeneratedDate();
			String filePath=null;
			filePath = BundleUtils.message("title.folderName.wcc");
			Path path = Paths.get(filePath);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}
			fileWriter = new FileWriter(BundleUtils.message("title.folderName.wcc")+ fileName +".csv");
			fileWriter.append("TimeSheet Details Report");
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("Report Generated On " + DateUtils.formatDateForSystemGeneratedDate());
			fileWriter.append(NEW_LINE_SEPARATOR);
			StringBuffer columnName = new StringBuffer("");
			
			if(reportOf.equals("Branch")) {
				columnName.append("Branch Name");
				columnName.append(COMMA_DELIMITER);
			}
			if(reportOf.equals("Project")) {				
				columnName.append("Project Name");
				columnName.append(COMMA_DELIMITER);
			}
			if(reportOf.equals("Manager") || reportOf.equals("Emp")) {				
				columnName.append("Manager Name");
				columnName.append(COMMA_DELIMITER);
				columnName.append("Project Name");
				columnName.append(COMMA_DELIMITER);
			}
			columnName.append("Employee Name");
			columnName.append(COMMA_DELIMITER);
			columnName.append("TimeSheet Work");
			columnName.append(COMMA_DELIMITER);
			columnName.append("Approval Status");
			columnName.append(COMMA_DELIMITER);
			
			fileWriter.append(columnName.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			if (listProjectWiseReport.size() > 0) {
				for (TimesheetReportResponsePojo listTimesheetReport : listProjectWiseReport) {
					if(reportOf.equals("Branch")) {	
						fileWriter.append(listTimesheetReport.getBranchName());
						fileWriter.append(COMMA_DELIMITER);
					}
					if(reportOf.equals("Project")) {				
						fileWriter.append(listTimesheetReport.getProjectName());
						fileWriter.append(COMMA_DELIMITER);
					}
					if(reportOf.equals("Manager") || reportOf.equals("Emp")) {		
						fileWriter.append(listTimesheetReport.getManagerName());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(listTimesheetReport.getProjectName());
						fileWriter.append(COMMA_DELIMITER);
					}
					fileWriter.append(listTimesheetReport.getEmployeeName());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(listTimesheetReport.getTimesheetWork());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(listTimesheetReport.getApprovalStatus());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
			} else {
				fileWriter.append(NEW_LINE_SEPARATOR);
				fileWriter.append("No Record Found");
				fileWriter.append(COMMA_DELIMITER);
			}
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.flush();
			fileWriter.close();
			httpServletResponse.setContentType("text/csv;charset=utf-8");
			httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".csv");
			outStream = httpServletResponse.getOutputStream();
			File downloadFile = new File(BundleUtils.message("title.folderName.wcc") +  fileName +".csv");
			inputStream = new FileInputStream(downloadFile);
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead=-1 ;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
