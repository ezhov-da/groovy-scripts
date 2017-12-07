package ru.ezhov.pdf

import groovy.xml.MarkupBuilder
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import java.util.regex.Matcher
import java.util.regex.Pattern

PDDocument document =
        PDDocument.load(new File("wpc.pdf"))

if (!document.isEncrypted()) {
    PDFTextStripper tStripper = new PDFTextStripper()
    tStripper.setAddMoreFormatting(true)
    tStripper.setShouldSeparateByBeads(true)

    String pdfFileInText = tStripper.getText(document)

    String[] lines = pdfFileInText.split("\\r?\\n")
    Pattern pattern = Pattern.compile("^\\d+")

    StringBuilder stringBuilder = new StringBuilder()
    def stringSet = getSet()

    def listText = new ArrayList<DataObject>()
    def map = new HashMap<String, String>()

    for (String line : lines) {
        Matcher matcher = pattern.matcher(line);
        //Очищаем от мусора
        if (!line.startsWith("WebSphere Product") && !matcher.find()) {
            if ("" == line) {
                def text = stringBuilder.toString()
                if ("" != text) {
                    objectText = new DataObject(id: listText.size(), text: text)
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
//    def xmlText = createXml(map)
//    println(xmlText)

//    def file = new File("output.xml")
//    println(file.absolutePath)
//    file.write(xmlText)

//    def listLittleRows = listText.findAll{it.text.split("\\n").length < 3}
//    println(listLittleRows)

//    ListDataObject listDataObject = new ListDataObject(dataObjectList: listText)
//    JAXB.marshal(listDataObject, new File("output.xml"))

}

private String createXml(HashMap<String, String> hashMap) {
    def writer = new StringWriter()
    def mB = new MarkupBuilder(writer)

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

    writer.toString()
}