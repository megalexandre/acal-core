package acal.com.core.infrastructure.serializer

import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import net.sf.jasperreports.export.SimplePdfExporterConfiguration
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.InputStream

enum class ReportType {
    PDF, XLSX
}

enum class Report(val value: String){
    INVOICE("invoice")
}

@Service
class JasperReportService {

    fun generate(data: Collection<*>, parameters: Map<String, Any> = emptyMap(), report: Report): ByteArray {

        return generateReport(
            reportName = report.value,
            reportData = data,
            parameters =  HashMap<String, Any>(parameters),
        )
    }

private fun generateReport(
        reportName: String,
        reportData: Collection<*>,
        parameters: Map<String, Any> = emptyMap(),
    ): ByteArray {
        val mutableParams = HashMap(parameters)
        mutableParams.put("Logo", "")

        runCatching {
            val logoStream = ClassPathResource("assets/logo.jpg").inputStream
            val logo = javax.imageio.ImageIO.read(logoStream)
            mutableParams.put("logo", logo)
        }

        val reportStream = ClassPathResource("report/$reportName.jrxml").inputStream
        val jasperReport = compileReport(reportStream)
        val dataSource = JRBeanCollectionDataSource(reportData)

        val jasperPrint = JasperFillManager.fillReport(jasperReport, mutableParams, dataSource)
        return exportToPdf(jasperPrint)
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

}
