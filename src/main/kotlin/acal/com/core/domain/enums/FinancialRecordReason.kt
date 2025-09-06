package acal.com.core.domain.enums

enum class FinancialRecordReason(val description: String) {
    INVOICE_PAYMENT("PAGAMENTO"),
    INVOICE_REFUND("ESTORNO"),
    INVOICE_DELETE("CANCELAMENTO");
}
