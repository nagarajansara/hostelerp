package hostelerp.com.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/report")
@SuppressWarnings(
{ "unused", "unchecked" })
public class ReportManagerController extends BaseController
{

	@Autowired
	private DataSource dataSource;

	private static final Logger logger = Logger
			.getLogger(ReportManagerController.class.getName());

	@RequestMapping(value = "/ping", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String ping(HttpServletRequest request, HttpServletResponse res,
			ModelMap model) throws Exception
	{
		return "response";
	}

	@RequestMapping(value = "/generateReport", method =
	{ RequestMethod.POST, RequestMethod.GET })
	public String generateReport(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, SQLException
	{

		Connection conn = null;
		String rptFormat = request.getParameter("type");
		String reportFileName = request.getParameter("filename");

		try
		{
			conn = dataSource.getConnection();
			HashMap<String, Object> hmParams = new HashMap<String, Object>();
			JasperReport jasperReport =
					getCompiledFile(reportFileName, request);

			if (rptFormat.equalsIgnoreCase("html"))
			{

				// HTML format
				JasperPrint jasperPrint =
						JasperFillManager.fillReport(jasperReport, hmParams,
								conn);
				generateReportHtml(jasperPrint, request, response);

			} else if (rptFormat.equalsIgnoreCase("pdf"))
			{
				// PDF format
				generateReportPDF(response, hmParams, jasperReport, conn);
			} else
			{
				// Ignore headers repetation
				hmParams.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
				JasperPrint jasperPrint =
						JasperFillManager.fillReport(jasperReport, hmParams,
								conn);
				// CSV format
				generateReportCsv(jasperPrint, request, response);
			}

		} catch (Exception sqlExp)
		{
			logger.error("generateReport :" + sqlExp.getMessage());
		}

		return null;

	}

	private JasperReport getCompiledFile(String fileName,
			HttpServletRequest request) throws JRException
	{

		logger.info("Jasper file path: "
				+ request.getSession().getServletContext()
						.getRealPath("/jasper/" + fileName + ".jasper"));
		try
		{
			File reportFile =
					new File(request.getSession().getServletContext()
							.getRealPath("/jasper/" + fileName + ".jasper"));
			// If compiled file is not found, then compile XML template
			if (!reportFile.exists())
			{
				JasperCompileManager
						.compileReportToFile(
								request.getSession()
										.getServletContext()
										.getRealPath(
												"/jasper/" + fileName
														+ ".jrxml"),
								request.getSession()
										.getServletContext()
										.getRealPath(
												"/jasper/" + fileName
														+ ".jasper"));
			}
			JasperReport jasperReport =
					(JasperReport) JRLoader.loadObjectFromFile(reportFile
							.getPath());
			return jasperReport;
		} catch (Exception ex)
		{
			throw ex;
		}

	}

	private void generateReportHtml(JasperPrint jasperPrint,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException, JRException
	{
		try
		{
			HtmlExporter exporter = new HtmlExporter();
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			jasperPrintList.add(jasperPrint);
			exporter.setExporterInput(SimpleExporterInput
					.getInstance(jasperPrintList));
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(resp
					.getWriter()));
			SimpleHtmlReportConfiguration configuration =
					new SimpleHtmlReportConfiguration();
			exporter.setConfiguration(configuration);
			exporter.exportReport();
		} catch (Exception ex)
		{
			throw ex;
		}

	}

	private void generateReportPDF(HttpServletResponse resp, Map parameters,
			JasperReport jasperReport, Connection conn) throws JRException,
			NamingException, SQLException, IOException
	{

		try
		{
			byte[] bytes = null;
			bytes =
					JasperRunManager.runReportToPdf(jasperReport, parameters,
							conn);
			resp.reset();
			resp.resetBuffer();
			resp.setContentType("application/pdf");
			resp.setContentLength(bytes.length);
			ServletOutputStream ouputStream = resp.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception ex)
		{
			throw ex;
		}

	}

	private void generateReportCsv(JasperPrint jasperPrint,
			HttpServletRequest req, HttpServletResponse response)
			throws IOException, JRException
	{

		try
		{
			JRCsvExporter exporter = new JRCsvExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			ByteArrayOutputStream csvReport = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, csvReport);
			exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, ",");
			exporter.exportReport();
			byte[] bytes = csvReport.toByteArray();
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition",
					"attachment; filename=data.csv");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception ex)
		{
			throw ex;
		}

	}
}
