package acal.com.core.application.invoice.data.out

import acal.com.core.comons.currency
import acal.com.core.comons.current
import acal.com.core.comons.currentDate
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.WaterAnalysis
import java.time.LocalDateTime

class InvoicePDFResponse (
    val id: String,
    val detail: DetailInvoicePDFResponse,
)

class DetailInvoicePDFResponse (
    val id: String,
    val reference: String,
    val number: String,
    val partnerName: String,
    val partnerNumber: String,
    val paidAt: String ? = null,
    val paidAtLabel: String? = null,
    val total: String,
    val category: String,
    val address: String,
    val addressNumber: String,
    val consumptionStart: String,
    val consumptionEnd: String,
    val freeTier: String,
    val waterValue: String,
    val currentDate: String,

    val categoryValue: String,
    val waterTotalValue: String,
    val paidUsageValue: String,
    val analysis: Collection<WaterAnalysisPDFResponse>
)

data class WaterAnalysisPDFResponse(
    val name: String,
    val required: String,
    val analyzed: String,
    val conformity: String,
)

fun Invoice.toPDF(): InvoicePDFResponse = InvoicePDFResponse(
    id = id,
    detail = DetailInvoicePDFResponse(
        id = id,
        partnerName = customer.name,
        partnerNumber = customer.partnerNumber ?: "0",
        reference = reference.toString(),
        number = number,
        paidAt = paidAt?.currentDate(),
        paidAtLabel = paidAt?.let { "Pago em: ${paidAt.currentDate()}" },
        total = totalValue.currency(),
        category = "Categoria: ${category.group.description} ${category.name}",
        address = "Endereço: ${place.address.name} ${place.number}/${place.letter}",
        addressNumber = "${place.number}/${place.letter}",
        consumptionStart = "Litros Anterior: ${ waterMeter?.start}" ,
        consumptionEnd = "Litros Atual: ${waterMeter?.end.toString()}",
        waterValue = "Total Litros: ${waterMeter?.paidUsageValue}",
        freeTier = "Gratuidade: ${waterMeter?.freeTier}",

        currentDate = LocalDateTime.now().current(),
        categoryValue = category.price.partnerValue.currency(),
        waterTotalValue = category.price.waterValue.currency(),
        paidUsageValue = waterMeter?.total?.currency() ?: "R$ 0,00",
        analysis = waterQuality?.analysis?.toWaterPDF() ?: emptyList()
    )
)

fun Collection<WaterAnalysis>.toWaterPDF(): List<WaterAnalysisPDFResponse> = this.map {
    WaterAnalysisPDFResponse(
        name = it.name,
        required = it.required,
        analyzed = it.analyzed,
        conformity = it.conformity
    )
}

fun Collection<Invoice>.toPDF(): List<InvoicePDFResponse> = this.map { it.toPDF() }