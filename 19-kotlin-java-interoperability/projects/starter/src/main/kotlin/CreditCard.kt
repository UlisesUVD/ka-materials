import java.text.SimpleDateFormat
import java.util.*

data class CreditCard @JvmOverloads constructor(
    @JvmField val cardNumber: String,
    @JvmField val expirationMonth: String,
    @JvmField val expirationYear: String,
    @JvmField val cvv: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if(cardNumber != (other as CreditCard).cardNumber) return false
        if(expirationMonth != (other).expirationMonth) return false
        if(expirationYear != (other).expirationYear) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    companion  object{

        @JvmStatic
        fun validateExpirationDate(creditCard: CreditCard): Boolean{

            val month = creditCard.expirationMonth
            val year = creditCard.expirationYear

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val expirationDate = sdf.parse("01/$month/20$year")
            val calendar = Calendar.getInstance()
            val currentDate = calendar.time

            if(currentDate.before(expirationDate)){
                return true
            }
            return false

        }
    }


}