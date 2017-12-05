package ru.ezhov.pdf

import groovy.xml.MarkupBuilder
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.util.regex.Matcher
import java.util.regex.Pattern

PDDocument document =
        PDDocument.load(new File("E:/programmer/wpc.pdf"))

if (!document.isEncrypted()) {
    PDFTextStripper tStripper = new PDFTextStripper()
    tStripper.setAddMoreFormatting(true)
    tStripper.setShouldSeparateByBeads(true)

    String pdfFileInText = tStripper.getText(document)

    String[] lines = pdfFileInText.split("\\r?\\n")
    Pattern pattern = Pattern.compile("^\\d+")

    StringBuilder stringBuilder = new StringBuilder()
    def stringSet = getSet()

    def listText = new ArrayList()
    def map = new HashMap<String, String>()

    for (String line : lines) {
        Matcher matcher = pattern.matcher(line);
        //Очищаем от мусора
        if (!line.startsWith("WebSphere Product") && !matcher.find()) {
            if ("" == line) {
                def text = stringBuilder.toString()
                if ("" != text) {
                    objectText = new ObjectText(id: listText.size(), text: text)
                    listText.add(objectText)
                    map.put(listText.size(), text)
                }
                stringBuilder = new StringBuilder()
            } else {
                stringBuilder.append(line)
                stringBuilder.append("\n")
            }
        }
    }

//    println(listText)
//    println(map)
    def xmlText = createXml(map)
    println(xmlText)

    def file = new File("xml.xml")
    println(file.absolutePath)
    def fileWriter = new FileWriter(file)
    fileWriter.write(xmlText.toString())
    fileWriter.close()

}

private String createXml(HashMap<String, String> hashMap) {
    def mB = new MarkupBuilder()

    // Compose the builder
    def text = mB.collection() {
        hashMap.each {
            sd ->
                mB.textObject() {
                    id(sd.key)
                    text(sd.value)
                }
        }
    }

    mB
}

private Set<String> getSet() {
    def set = [
            "basic",
            "bidi",
            "currency",
            "date",
            "db",
            "error",
            "excel",
            "libxml",
            "math",
            "number",
            "operations",
            "operations_admin",
            "operations_assert",
            "operations_attr_group",
            "operations_catalog",
            "operations_category",
            "operations_col_area",
            "operations_ctgaccprv",
            "operations_ctgview",
            "operations_distribution",
            "operations_docstore",
            "operations_entry",
            "operations_export",
            "operations_import",
            "operations_import",
            "operations_item",
            "operations_jms",
            "operations_lkp",
            "operations_locale",
            "operations_mq",
            "operations_mutablespec",
            "operations_perf",
            "operations_queuemgr",
            "operations_report",
            "operations_scheduler",
            "operations_search",
            "operations_soap",
            "operations_spec",
            "operations_specmap",
            "operations_userdefinedlog",
            "operations_webservices",
            "operations_wfl",
            "operations_wflstep",
            "operations_widget",
            "operations_worklist",
            "other",
            "page_layout",
            "re",
            "reader",
            "reflect",
            "scripting",
            "security",
            "set",
            "string",
            "system",
            "timezone",
            "writer",
            "zip",
            "zip_archive"
    ]
}