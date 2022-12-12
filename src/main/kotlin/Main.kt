import javax.xml.parsers.SAXParserFactory

fun main() {
    val parserFactory = SAXParserFactory.newInstance()
    try {
        val parser = parserFactory.newSAXParser()
        val stringResourceHandler = StringResourceHandler()
        parser.parse("src/main/resources/strings.xml", stringResourceHandler)
        print(stringResourceHandler.stringResources)
    } catch (e: Exception) {
        print(e)
    }
}
