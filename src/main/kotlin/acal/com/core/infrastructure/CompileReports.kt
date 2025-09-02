package acal.com.core.infrastructure

import net.sf.jasperreports.engine.JasperCompileManager
import java.io.File

object CompileReports {
    @JvmStatic
    fun main(args: Array<String>) {
        val reportsDir = File("src/main/resources/report")
        val outputDir = File("build/generated-resources/jasper")

        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }

        reportsDir.listFiles { _, name -> name.endsWith(".jrxml") }?.forEach { jrxml ->
            val jasperFileBuild = File(outputDir, jrxml.nameWithoutExtension + ".jasper")
            val jasperFileSameDir = File(jrxml.parentFile, jrxml.nameWithoutExtension + ".jasper")

            JasperCompileManager.compileReportToFile(
                jrxml.absolutePath,
                jasperFileBuild.absolutePath
            )

            jasperFileBuild.copyTo(jasperFileSameDir, overwrite = true)
        }
    }
}
