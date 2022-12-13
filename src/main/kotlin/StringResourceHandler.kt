import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class StringResourceHandler : DefaultHandler() {
    val stringResources = mutableListOf<StringResource>()

    private var currentKey: String? = ""
    private var currentValue = ""

    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        currentValue = ""

        when {
            qName?.equals(STRING_TAG, ignoreCase = true) == true -> {
                currentKey = attributes?.getValue(NAME_ATTRIBUTE)
            }
            qName?.equals(RESOURCES_TAG, ignoreCase = true) == true -> {
                stringResources.clear()
            }
            else -> {}
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)

        if (qName?.equals(STRING_TAG, ignoreCase = true) == true) {
            currentKey?.takeIf { it.isNotBlank() }?.let {
                stringResources.add(StringResource(it, currentValue))
            }
        }
    }

    override fun characters(ch: CharArray, start: Int, length: Int) {
        super.characters(ch, start, length)
        currentValue += String(ch, start, length)
    }

    companion object {
        private const val RESOURCES_TAG = "resources"
        private const val STRING_TAG = "string"
        private const val NAME_ATTRIBUTE = "name"
    }
}
