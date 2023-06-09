package uz.ruzibekov.domain.model

sealed class Resource<T>(
    val data: T? = null,
    val error: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)

    class Error<T>(data: Int) : Resource<T>(null, data)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${error.toString()}]"
        }
    }
}
