import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun readInput(name: String): List<String> {
    return File("src/inputs", "$name.txt").readLines()
}

fun String.md5(): String {
    return BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
}
