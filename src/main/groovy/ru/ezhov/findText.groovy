import groovy.io.FileType

def dir = new File("E:/programmer/ROOT")
dir.eachFileRecurse(FileType.FILES) { file ->
    def text = file.text
    if (text.contains("7777")) {
        println file.getAbsolutePath()

        println text
    }
}