package ro.puk3p.tuiasi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Scanner

interface PaymentMethod {
    fun pay(fee: Double) : Boolean
}

class CashPayment(private var availableAmount: Double) : PaymentMethod {
    override fun pay(fee: Double) : Boolean {
        return if (availableAmount >= fee) {
            availableAmount -= fee
            true
        } else {
            false
        }
    }
}

class CardPayment(private val bankAccount: BankAccount) : PaymentMethod {
    override fun pay(fee: Double): Boolean {
        return bankAccount.updateAmount(-fee)
    }
}

class BankAccount(
    private var availableAmount: Double,
    private val cardNumber: String,
    private val expirationDate: Date,
    private val cvvCode: Int,
    private val userName: String
) {
    fun updateAmount(value: Double) : Boolean {
        availableAmount += value
        return true
    }

    fun getAvailableAmount() = availableAmount
}

class TicketPurchaseSystem(private val paymentMethod: PaymentMethod) {
    fun purchaseTicket(ticketPrice: Double): Boolean {
        return paymentMethod.pay(ticketPrice)
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    println("Introduceti suma disponibila in cont: ")
    val availableAmount = scanner.nextDouble()
    println("Introducetu numarul cardului:")
    val cardNumber = scanner.next()
    println("Introduceti data de expirare a cardului (MM/YYYY):")
    val expirationDateStr = scanner.next()
    val expirationDate = SimpleDateFormat("MM/yyyy").parse(expirationDateStr)
    println("Introduceti codul CVV:")
    val cvvCode = scanner.nextInt()
    println("Introduceti numele de utilizator:")
    val userName = scanner.next()
    val bankAccount = BankAccount(availableAmount, cardNumber, expirationDate, cvvCode, userName)

    //card doar
    val cardPayment = CardPayment(bankAccount)
    println("Introduceti pretul biletului:")
    val ticketPrice = scanner.nextDouble()

    val cardTicketPurchaseSystem = TicketPurchaseSystem(cardPayment)
    val cardPaymentResult = cardTicketPurchaseSystem.purchaseTicket(ticketPrice)

    println("Rezultatul platii cu cardul: $cardPaymentResult")
    // Acum, permiterea platilor cu numerar
    println("Doriti sa platiti cu numerar? (da/nu)")
    val cashPaymentChoice = scanner.next()

    if (cashPaymentChoice.equals("da", ignoreCase = true)) {
        println("Introduceti suma disponibila pentru plata in numerar:")
        val cashAvailableAmount = scanner.nextDouble()
        val cashPayment = CashPayment(cashAvailableAmount)

        val cashTicketPurchaseSystem = TicketPurchaseSystem(cashPayment)
        val cashPaymentResult = cashTicketPurchaseSystem.purchaseTicket(ticketPrice)

        println("Rezultatul platii cu cardul: $cashPaymentResult")
    }
}