package acal.com.core.infrastructure.serializer

import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import net.sf.jasperreports.export.SimplePdfExporterConfiguration
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.HashMap

enum class ReportType {
    PDF, XLSX
}

@Service
class JasperReportService {

    fun generateReport(
        reportName: String,
        reportData: Collection<*>,
        parameters: Map<String, Any> = emptyMap(),
        reportType: ReportType = ReportType.PDF
    ): ByteArray {

        val reportStream = ClassPathResource("report/$reportName.jrxml").inputStream
        val jasperReport = compileReport(reportStream)
        val dataSource = JRBeanCollectionDataSource(reportData)
        // Cria uma cópia mutável do mapa de parâmetros
        val mutableParams = HashMap<String, Any>(parameters)
        val jasperPrint = JasperFillManager.fillReport(jasperReport, mutableParams, dataSource)
        return when (reportType) {
            ReportType.PDF -> exportToPdf(jasperPrint)
            ReportType.XLSX -> exportToXlsx(jasperPrint)
        }
    }
    
    private fun compileReport(inputStream: InputStream): JasperReport {
        return JasperCompileManager.compileReport(inputStream)
    }
    
    private fun exportToPdf(jasperPrint: JasperPrint): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val exporter = JRPdfExporter()
        
        exporter.setExporterInput(SimpleExporterInput(jasperPrint))
        exporter.setExporterOutput(SimpleOutputStreamExporterOutput(outputStream))
        
        val configuration = SimplePdfExporterConfiguration()
        configuration.isCreatingBatchModeBookmarks = true
        configuration.isCompressed = true
        exporter.setConfiguration(configuration)

        exporter.exportReport()

        return outputStream.toByteArray()
    }

    private fun exportToXlsx(jasperPrint: JasperPrint): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val exporter = JRXlsxExporter()

        exporter.setExporterInput(SimpleExporterInput(jasperPrint))
        exporter.setExporterOutput(SimpleOutputStreamExporterOutput(outputStream))

        val configuration = SimpleXlsxReportConfiguration()
        configuration.isOnePagePerSheet = false
        configuration.isRemoveEmptySpaceBetweenColumns = true
        configuration.isRemoveEmptySpaceBetweenRows = true
        configuration.isDetectCellType = true
        configuration.isWhitePageBackground = false
        exporter.setConfiguration(configuration)
        
        exporter.exportReport()
        
        return outputStream.toByteArray()
    }
}
