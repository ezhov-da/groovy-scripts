package ru.ezhov

(1..36).each {
    def num = it
    def urlText = "http://vse-raskraski.ru/assets/images/resources/549/raskraski-ben-10-${-> num}.jpg"
    def completeUrl = urlText

    println(completeUrl)

    def url = new URL(completeUrl)

    def nameFile = urlText.substring(urlText.lastIndexOf("/") + 1)
    println nameFile

    def os = null
    def is = null
    try {
        os = new File("C:\\Users\\ezhov\\Desktop\\раскраски", nameFile).newOutputStream()
        is = url.openStream().eachByte { b -> os.write(b) }
    } finally {
        if (os != null) {
            os.close()
        }
        if (is != null) {
            is.close()
        }
    }
}